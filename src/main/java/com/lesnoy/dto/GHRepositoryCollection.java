package com.lesnoy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GHRepositoryCollection {
    @JsonProperty("total_count")
    private int count;
    @JsonProperty("items")
    private List<GHRepositoryDTO> repositories;

    public GHRepositoryCollection() {
    }

    public GHRepositoryCollection(int count, List<GHRepositoryDTO> repositories) {
        this.count = count;
        this.repositories = repositories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GHRepositoryDTO> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GHRepositoryDTO> repositories) {
        this.repositories = repositories;
    }
}
