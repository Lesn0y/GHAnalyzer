package com.lesnoy.service;

import com.lesnoy.dto.GHRepositoryCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubApiImplService {

    private final WebClient webClient;

    public GitHubApiImplService(WebClient webClient) {
        this.webClient = webClient;
    }

    public GHRepositoryCollection getRepositoriesByLanguage(String language) {
        GHRepositoryCollection block = webClient.get()
                .uri("/search/repositories?q=language:" + language)
                .retrieve()
                .bodyToMono(GHRepositoryCollection.class)
                .block();
        return block;
    }
}
