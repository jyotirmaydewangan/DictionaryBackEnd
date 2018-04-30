package com.dewangan.jyotirmay.resources;

import com.dewangan.jyotirmay.core.*;
import com.dewangan.jyotirmay.db.*;
import com.dewangan.jyotirmay.db.language.*;
import com.dewangan.jyotirmay.language.BaseLanguage;
import com.dewangan.jyotirmay.response.*;
import com.dewangan.jyotirmay.util.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.hibernate.UnitOfWork;

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

    private final SenseDAO senseDAO;
    private final WordDAO wordDAO;
    private final ScriptDAO scriptDAO;
    private final SemanticLinkDAO semanticLinkDAO;
    private final LexicalLinkDAO lexicalLinkDAO;
    private final SampleDAO sampleDAO;
    private final HindiLanguageDAO hindiLanguageDAO;
    private final UrduLanguageDAO urduLanguageDAO;
    private final TeluguLanguageDAO teluguLanguageDAO;
    private final BengaliLanguageDAO bengaliLanguageDAO;
    private final MarathiLanguageDAO marathiLanguageDAO;

    public WordResource(WordDAO wordDAO, ScriptDAO scriptDAO, SenseDAO senseDAO, SemanticLinkDAO semanticLinkDAO,
                        LexicalLinkDAO lexicalLinkDAO, SampleDAO sampleDAO,
                        HindiLanguageDAO hindiLanguageDAO, UrduLanguageDAO urduLanguageDAO, TeluguLanguageDAO teluguLanguageDAO,
                        BengaliLanguageDAO bengaliLanguageDAO, MarathiLanguageDAO marathiLanguageDAO) {
        this.wordDAO = wordDAO;
        this.scriptDAO = scriptDAO;
        this.senseDAO = senseDAO;
        this.semanticLinkDAO = semanticLinkDAO;
        this.lexicalLinkDAO = lexicalLinkDAO;
        this.sampleDAO = sampleDAO;
        this.hindiLanguageDAO = hindiLanguageDAO;
        this.urduLanguageDAO = urduLanguageDAO;
        this.teluguLanguageDAO = teluguLanguageDAO;
        this.bengaliLanguageDAO = bengaliLanguageDAO;
        this.marathiLanguageDAO = marathiLanguageDAO;
    }

    private void populateSynonyms(MeaningResponse response, Collection<Sense> senses, String lang){
        Map<Integer, Set<BaseWordNode>> synonyms = new HashMap<Integer, Set<BaseWordNode>>();
        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);

        for (Sense sense: senses) {
            List<Sense> similerSenses = senseDAO.findSenseBySynsetId(sense.getSynsetId());

            for(Sense similerSense: similerSenses){
                if(similerSense.getWordId() != sense.getWordId()) {

                    CompleteWordNode wordNode = new CompleteWordNode();
                    wordNode.setWordId(similerSense.getWordId());
                    wordNode.setWord(similerSense.getWord());
                    wordNode.setPartOfSpeech(similerSense.getPartOfSpeech());
                    wordNode.setDefinition(similerSense.getDefinition());


                    BaseLanguage baseLanguage = baseLanguageDAO.findTopTargetWordByWordId(similerSense.getWordId());

                    if(baseLanguage!=null)
                        wordNode.settWord(baseLanguage.getTargetWord());

                    List<Sample> samples = sampleDAO.findSampleBySynsetId(similerSense.getSynsetId());
                    Set<String> examples = new HashSet<String>();
                    for (Sample sample : samples) {
                        if (sample.getSample().contains(similerSense.getWord()))
                            examples.add(sample.getSample());
                    }
                    wordNode.setExmples(examples);

                    Boolean isOldSynonym = synonyms.containsKey(similerSense.getSynsetId());

                    if(isOldSynonym){
                        synonyms.get(similerSense.getSynsetId()).add(wordNode);
                    } else {
                        Set<BaseWordNode> synonymSet = new HashSet<BaseWordNode>();
                        synonymSet.add(wordNode);
                        synonyms.put(similerSense.getSynsetId(), synonymSet);
                    }
                }
            }
        }
        response.setSynonym(synonyms);
    }

    private void populateSemantic(MeaningResponse response, List<Sense> senses, String lang){
        Map<String, Set<BaseWordNode>> semantic = new HashMap<String, Set<BaseWordNode>>();
        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
        for (Sense sense: senses) {
            List<SemanticLink> semanticLinks =  semanticLinkDAO.findSemanticLinkBySynsetId(sense.getSynsetId());

            for(SemanticLink semanticLink : semanticLinks){
                List<Sense> semantics = senseDAO.findSenseBySynsetId(semanticLink.getSynset2Id());

                for(Sense semanticSense: semantics){

                    WordNode wordNode = new WordNode();
                    wordNode.setWordId(semanticSense.getWordId());
                    wordNode.setWord(semanticSense.getWord());
                    wordNode.setPartOfSpeech(semanticSense.getPartOfSpeech());

                    BaseLanguage baseLanguage = baseLanguageDAO.findTopTargetWordByWordId(semanticSense.getWordId());

                    if(baseLanguage!=null)
                        wordNode.settWord(baseLanguage.getTargetWord());

                    Boolean isOldSemantic = semantic.containsKey(semanticLink.getRelatedBy());

                    if(isOldSemantic){
                        semantic.get(semanticLink.getRelatedBy()).add(wordNode);
                    } else {
                        Set<BaseWordNode> semanticSet = new HashSet<BaseWordNode>();
                        semanticSet.add(wordNode);
                        semantic.put(semanticLink.getRelatedBy(), semanticSet);
                    }
                }
            }
        }

        for (String key : semantic.keySet()) {
            response.getRelatedBy().put(key, semantic.get(key));
        }

    }

    private void populateLexical(MeaningResponse response, List<Sense> senses, String lang){
        Map<String, Set<BaseWordNode>> lexical = new HashMap<String, Set<BaseWordNode>>();
        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
        for (Sense sense: senses) {
            List<LexicalLink> lexicalLinks = lexicalLinkDAO.findLexicalLinkBySynsetId(sense.getSynsetId());
            for(LexicalLink lexicalLink : lexicalLinks){
                List<Sense> lexicals = senseDAO.findSenseByWordId(lexicalLink.getWord2Id());
                for(Sense lexicalSense: lexicals){
                    WordNode wordNode = new WordNode();
                    wordNode.setWordId(lexicalSense.getWordId());
                    wordNode.setWord(lexicalSense.getWord());
                    wordNode.setPartOfSpeech(lexicalSense.getPartOfSpeech());

                    BaseLanguage baseLanguage = baseLanguageDAO.findTopTargetWordByWordId(lexicalSense.getWordId());
                    if(baseLanguage != null) {
                        wordNode.settWord(baseLanguage.getTargetWord());
                    }

                    Boolean isOldlexical = lexical.containsKey(lexicalLink.getRelatedBy());
                    if(isOldlexical){
                        lexical.get(lexicalLink.getRelatedBy()).add(wordNode);
                    } else {
                        Set<BaseWordNode> lexicalLinkSet = new HashSet();
                        lexicalLinkSet.add(wordNode);
                        lexical.put(lexicalLink.getRelatedBy(), lexicalLinkSet);
                    }
                }
            }
        }

        for(String key: lexical.keySet()){
            response.getRelatedBy().put(key, lexical.get(key));
        }
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

    private void populateTargetLanguage(MeaningResponse response, List<Sense> senses, String lang) {

        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);

        Set<BaseWordNode> targetSenseLanguage = new HashSet<BaseWordNode>();

        Set<Integer> wordIds = new HashSet<Integer>();

        for(Sense sense: senses){
            wordIds.add(sense.getWordId());
        }

        for(Integer wordId: wordIds){
            List<BaseLanguage> targetWords = baseLanguageDAO.findTargetWordByWordId(wordId);

            for(BaseLanguage targetWord: targetWords){
                WordNode wordNode = new WordNode();
                wordNode.setWordId(targetWord.getWordId());
                wordNode.setWord(targetWord.getTargetWord());
                wordNode.setPartOfSpeech(targetWord.getPartOfSpeech());
                targetSenseLanguage.add(wordNode);
            }
        }

        response.setTargetWord(targetSenseLanguage);
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
            List<Word> words = wordDAO.findWordList(ch, (page - 1) * 80);
            count = wordDAO.findWordCount(ch);
            for (Word word : words) {
                wordList.add(word.getLemma());
            }
        } else {
            BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
            List<String> words = baseLanguageDAO.findWordList(ch, (page - 1) * 80);
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
    public MeaningResponse getSensesByWord(@PathParam(value = "eword") String eword, @PathParam(value = "lang") String lang) {

        MeaningResponse response = new MeaningResponse();

        response.setToWord(eword);

        List<Sense> senses = senseDAO.findByWord(eword);

        populateTargetLanguage(response, senses, lang);
        populateSynonyms(response, senses, lang);
        populateSemantic(response, senses, lang);
        populateLexical(response, senses, lang);

        return response;
    }


    @GET
    @Path("/reverseWord/{tword}/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public MeaningResponse getReverseSensesByWord(@PathParam(value = "tword") String tword, @PathParam(value = "lang") String lang) {

        MeaningResponse response = new MeaningResponse();

        response.setToWord(tword);
        BaseLanguageDAO baseLanguageDAO = selectProperLanguage(lang);
        List<BaseLanguage> baseLanguages = baseLanguageDAO.findTargetWordByWord(tword);

        Set<Integer> wordIds = new HashSet<Integer>();

        for(BaseLanguage baseLanguage : baseLanguages) {
            wordIds.add(baseLanguage.getWordId());
        }

        populateReverseTargetLanguage(response, wordIds);

        return response;
    }


    private void populateReverseTargetLanguage(MeaningResponse response, Set<Integer> wordIds) {

        Set<BaseWordNode> targetSenseLanguage = new HashSet<BaseWordNode>();

        for(Integer wordId: wordIds){

            List<Sense> senses = senseDAO.findSenseByWordId(wordId);

            for(Sense sense: senses){
                WordNode wordNode = new WordNode();
                wordNode.setWordId(sense.getWordId());
                wordNode.setWord(sense.getWord());
                wordNode.setPartOfSpeech(sense.getPartOfSpeech());
                targetSenseLanguage.add(wordNode);
            }
        }

        response.setTargetWord(targetSenseLanguage);
    }
}
