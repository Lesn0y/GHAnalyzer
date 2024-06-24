package com.lesnoy.async.controller;

import com.lesnoy.dto.GHRepDTO;
import com.lesnoy.async.service.AsyncGitRepositoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/rep", produces = MediaType.APPLICATION_JSON_VALUE)
public class AsyncRepController {

    private final AsyncGitRepositoryService ghService;

    public AsyncRepController(AsyncGitRepositoryService ghService) {
        this.ghService = ghService;
    }

    @GetMapping("/{lang}")
    public Flux<ResponseEntity<GHRepDTO>> getRepositoriesByLanguage(@PathVariable("lang") String language) {
        return ghService.getRepositoriesByLanguage(language)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
