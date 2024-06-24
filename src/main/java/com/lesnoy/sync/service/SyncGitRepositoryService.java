package com.lesnoy.sync.service;

import com.lesnoy.dto.GHRepDTO;
import com.lesnoy.model.GHRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyncGitRepositoryService {

    @Value("${github.base_url}")
    private String BASE_URL;
    private final RestTemplate restTemplate;

    public SyncGitRepositoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GHRepository> getRepositoriesByLanguage(String language) {
        List<GHRepository> repositories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GHRepDTO repDTO = restTemplate
                    .getForObject(BASE_URL + "/search/repositories?q=language:" + language, GHRepDTO.class);
            if (repDTO != null && !repDTO.getRepositories().isEmpty()) {
                repositories.addAll(repDTO.getRepositories());
            }
        }
        return repositories;
    }
}
