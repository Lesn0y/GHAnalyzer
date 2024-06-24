package com.lesnoy.async.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lesnoy.async.model.GHRepository;

import java.util.List;

public class GHRepDTO {
    @JsonProperty("total_count")
    private int count;
    @JsonProperty("items")
    private List<GHRepository> repositories;

    public GHRepDTO() {
    }

    public GHRepDTO(int count, List<GHRepository> repositories) {
        this.count = count;
        this.repositories = repositories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GHRepository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GHRepository> repositories) {
        this.repositories = repositories;
    }
}
