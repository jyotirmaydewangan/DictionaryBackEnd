package com.dewangan.jyotirmay.response;

import java.util.List;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
public class WordListResponse {
    private List<String> wordList;
    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }
}
