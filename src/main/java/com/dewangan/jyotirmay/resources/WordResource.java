package com.dewangan.jyotirmay.resources;

import com.dewangan.jyotirmay.core.*;
import com.dewangan.jyotirmay.db.*;
import com.dewangan.jyotirmay.response.*;
import com.dewangan.jyotirmay.util.Language;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by jyotirmay.d on 01/11/17.
 */
@Path("/")
public class WordResource {

    private final SenseDAO senseDAO;
    private final SemanticLinkDAO semanticLinkDAO;
    private final LexicalLinkDAO lexicalLinkDAO;
    private final SampleDAO sampleDAO;
    private final HindiLanguageDAO hindiLanguageDAO;
    private final UrduLanguageDAO urduLanguageDAO;

    public WordResource(SenseDAO senseDAO, SemanticLinkDAO semanticLinkDAO, LexicalLinkDAO lexicalLinkDAO, SampleDAO sampleDAO,
                        HindiLanguageDAO hindiLanguageDAO, UrduLanguageDAO urduLanguageDAO) {
        this.senseDAO = senseDAO;
        this.semanticLinkDAO = semanticLinkDAO;
        this.lexicalLinkDAO = lexicalLinkDAO;
        this.sampleDAO = sampleDAO;
        this.hindiLanguageDAO = hindiLanguageDAO;
        this.urduLanguageDAO = urduLanguageDAO;
    }


    private void populateSynonyms(MeaningResponse response, List<Sense> senses, String lang){
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
                    wordNode.settWord(baseLanguageDAO.findTopTargetWordByWordId(lexicalSense.getWordId()).getTargetWord());

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
    @Path("/word/{eword}/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public MeaningResponse getSensesByWord(@PathParam(value = "eword") String eword, @PathParam(value = "lang") String lang) {

        MeaningResponse response = new MeaningResponse();

        response.setEnglishWord(eword);

        List<Sense> senses = senseDAO.findByWord(eword);

        populateTargetLanguage(response, senses, lang);
        populateSynonyms(response, senses, lang);
        populateSemantic(response, senses, lang);
        populateLexical(response, senses, lang);

        return response;
    }
}
