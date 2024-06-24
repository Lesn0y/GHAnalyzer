package com.lesnoy.sync.controller;

import com.lesnoy.model.GHRepository;
import com.lesnoy.sync.service.SyncGitRepositoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sync/rep", produces = MediaType.APPLICATION_JSON_VALUE)
public class SyncRepController {

    private final SyncGitRepositoryService service;

    public SyncRepController(SyncGitRepositoryService service) {
        this.service = service;
    }

    @GetMapping("/{lang}")
    public ResponseEntity<List<GHRepository>> getRepositoriesByLanguage(@PathVariable("lang") String language) {
        return ResponseEntity.ok(service.getRepositoriesByLanguage(language));
    }
}
