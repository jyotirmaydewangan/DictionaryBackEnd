package com.dewangan.jyotirmay.resources;

import com.dewangan.jyotirmay.core.*;
import com.dewangan.jyotirmay.db.*;
import com.dewangan.jyotirmay.db.language.*;
import com.dewangan.jyotirmay.language.BaseLanguage;
import com.dewangan.jyotirmay.response.*;
import com.dewangan.jyotirmay.util.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.*;

/**
 * Created by jyotirmay.d on 01/11/17.
 */
@Path("/")
public class WordResource {

    private final WordDAO wordDAO;
    private final ScriptDAO scriptDAO;
    private final HindiLanguageDAO hindiLanguageDAO;
    private final UrduLanguageDAO urduLanguageDAO;
    private final TeluguLanguageDAO teluguLanguageDAO;
    private final BengaliLanguageDAO bengaliLanguageDAO;
    private final MarathiLanguageDAO marathiLanguageDAO;

    public WordResource(WordDAO wordDAO, ScriptDAO scriptDAO,

                        HindiLanguageDAO hindiLanguageDAO, UrduLanguageDAO urduLanguageDAO,
                        TeluguLanguageDAO teluguLanguageDAO, BengaliLanguageDAO bengaliLanguageDAO,
                        MarathiLanguageDAO marathiLanguageDAO) {
        this.wordDAO = wordDAO;
        this.scriptDAO = scriptDAO;
        this.hindiLanguageDAO = hindiLanguageDAO;
        this.urduLanguageDAO = urduLanguageDAO;
        this.teluguLanguageDAO = teluguLanguageDAO;
        this.bengaliLanguageDAO = bengaliLanguageDAO;
        this.marathiLanguageDAO = marathiLanguageDAO;
    }

    private BaseLanguageDAO selectProperLanguage(String lang) {
        if(lang.equalsIgnoreCase(Language.HINDI.name())){
            return this.hindiLanguageDAO;
        } else if(lang.equalsIgnoreCase(Language.URDU.name())){
            return this.urduLanguageDAO;
        } else if(lang.equalsIgnoreCase(Language.TELUGU.name())){
            return this.teluguLanguageDAO;
        } else if(lang.equalsIgnoreCase(Language.BENGALI.name())){
            return this.bengaliLanguageDAO;
        } else if(lang.equalsIgnoreCase(Language.MARATHI.name())){
            return this.marathiLanguageDAO;
        }
        return this.hindiLanguageDAO;
    }

    @GET
    @Path("/reverseWord/{tword}/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public DictionaryResponse getReverseSensesByWord(@PathParam(value = "tword") String tword, @PathParam(value = "lang") String lang) {

        DictionaryResponse response = new DictionaryResponse();
        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
        List<BaseLanguage> baseLanguages = baseLanguageDAO.findEnglishWordByTargetWord(tword);

        response.setWord(tword);

        for(BaseLanguage baseLanguage : baseLanguages) {
            response.addMeaning(baseLanguage.getPartOfSpeech(), baseLanguage.getEngWord().getEnglishWord());
        }

        return response;
    }


    @GET
    @Path("/wordList/{lang}/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Map<String, String>> getAjaxWordList(@PathParam(value = "lang") String lang, @PathParam(value = "word") String word){
        List<Map<String, String>> wordList = new ArrayList<Map<String, String>>();

        if(lang.equalsIgnoreCase("english")) {
            List<Word> words = wordDAO.findWordListAutoList(word, 0, 10);

            for (Word localWord : words) {
                Map<String, String> wordMap = new HashMap<String, String>();
                wordMap.put("word",localWord.getEnglishWord());
                wordList.add(wordMap);
            }
        } else {
            BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
            List<String> words = baseLanguageDAO.findWordListAutoList(word, 0, 10);
            for (String localWord : words) {
                Map<String, String> wordMap = new HashMap<String, String>();
                wordMap.put("word",localWord);
                wordList.add(wordMap);
            }
        }

        return wordList;
    }



    @GET
    @Path("/wordList/{lang}/{char}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public WordListResponse getWordList(@PathParam(value = "lang") String lang, @PathParam(value = "char") String ch, @PathParam(value = "page") Integer page) throws IOException {

        WordListResponse wordListResponse = new WordListResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> wordList = new ArrayList<String>();
        Integer count=1;

        if(lang.equalsIgnoreCase("english")) {
            List<Word> words = wordDAO.findWordList(ch, (page - 1) * 80, 80);
            count = wordDAO.findWordCount(ch);
            for (Word word : words) {
                wordList.add(word.getEnglishWord());
            }
        } else {
            BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
            List<String> words = baseLanguageDAO.findWordList(ch, (page - 1) * 80, 80);
            count = baseLanguageDAO.findWordCount(ch);
            for (String word : words) {
                wordList.add(word);
            }
        }

        String letters = scriptDAO.findScript(lang).getLetters();
        List<String> scripts = objectMapper.readValue(letters, ArrayList.class);

        wordListResponse.setWordList(wordList);
        wordListResponse.setPageCount(((count-1)/80)+1);
        wordListResponse.setLetters(scripts);

        return wordListResponse;
    }



    @GET
    @Path("/word/{eword}/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public DictionaryResponse getEnglishToTargetResponseByWord(@PathParam(value = "eword") String eword, @PathParam(value = "lang") String lang) {

        DictionaryResponse response = new DictionaryResponse();
        Word word = wordDAO.findByWord(eword);

        response.setWord(eword);
        populateTargetLanguage(response, word, lang);
        populateSynonyms(response, word);
        populateAntonyms(response, word);
        populateDefinationAndUse(response, word);
        populateSeeAlso(response, word);

        return response;
    }

    private void populateSynonyms(DictionaryResponse response, Word word) {

        if(word == null)
            return;

        for(Synonym synonym : word.getSynonyms()){
            response.addSynonym(synonym.getPartOfSpeech(), synonym.getSynonymWord().getEnglishWord());
        }
    }

    private void populateAntonyms(DictionaryResponse response, Word word) {

        if(word == null)
            return;

        for(Antonym antonym : word.getAntonyms()){
            response.addAntonym(antonym.getAntonymWord().getEnglishWord());
        }
    }

    private void populateDefinationAndUse(DictionaryResponse response, Word word) {

        if(word == null)
            return;

        for(Definition definition : word.getDefinitions()){
            response.addDefinition(definition.getPartOfSpeech(), definition.getDefinition());

            if(!StringUtils.isEmpty(definition.getExample())) {
                response.addUse(definition.getPartOfSpeech(), definition.getExample());
            }
        }
    }

    private void populateSeeAlso(DictionaryResponse response, Word word) {

        if(word == null)
            return;

        for(SeeAlso seeAlso : word.getSeeAlso()){
            response.addSeeAlso(seeAlso.getSimilarWord().getEnglishWord());
        }
    }

    private void populateTargetLanguage(DictionaryResponse response, Word word, String lang) {

        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);

        List<BaseLanguage> targetWords =  baseLanguageDAO.findTargetWordByWord(word.getEnglishWord());

        for(BaseLanguage targetWord : targetWords){
            response.addMeaning(targetWord.getPartOfSpeech(), targetWord.getTargetWord());
        }

    }
}
