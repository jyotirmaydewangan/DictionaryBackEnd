package com.dewangan.jyotirmay.response;

import java.util.Objects;

/**
 * Created by jyotirmay.d on 16/11/17.
 */
public class SynsetNode {
    private Integer synsetId;
    private String definition;

    public Integer getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(Integer synsetId) {
        this.synsetId = synsetId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof SynsetNode)) {
            return false;
        }
        SynsetNode synsetNode = (SynsetNode)o;
        return  Objects.equals(synsetId, synsetNode.getSynsetId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(synsetId);
    }
}
