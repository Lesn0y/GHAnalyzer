package com.lesnoy.controller;

import com.lesnoy.dto.GHRepositoryCollection;
import com.lesnoy.service.GitHubApiImplService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GitController {

    private final GitHubApiImplService ghService;

    public GitController(GitHubApiImplService ghService) {
        this.ghService = ghService;
    }

    @GetMapping
    public ResponseEntity<GHRepositoryCollection> test(@RequestParam("lang") String language) {
        GHRepositoryCollection repositoriesByLanguage = ghService.getRepositoriesByLanguage(language);
        return ResponseEntity.ok(repositoriesByLanguage);
    }

}
