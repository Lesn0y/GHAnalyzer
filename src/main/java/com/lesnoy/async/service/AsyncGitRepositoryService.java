package com.lesnoy.async.service;

import com.lesnoy.dto.GHRepDTO;
import com.lesnoy.model.GHRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class AsyncGitRepositoryService {

    private static final Log log = LogFactory.getLog(AsyncGitRepositoryService.class);
    private final WebClient webClient;

    public AsyncGitRepositoryService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<GHRepDTO> getRepositoriesByLanguage(String language) {
        return Flux.range(0, 10)
                .flatMap(i -> fetchRepositoriesByLanguage(language))
                .doOnNext(i -> log.info("Fetching repository: " + i.getRepositories().size()))
                .subscribeOn(Schedulers.parallel());
    }

    public Mono<GHRepository> getRepositoryByFullName(String fullName) {
        return webClient.get()
                .uri("/repos" + fullName)
                .retrieve()
                .bodyToMono(GHRepository.class)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(throwable -> log.error("Error fetching repository", throwable));
    }

    public Mono<String> fetchContentFromRepository(String repoFullName, String fileName) {
        return webClient.get()
                .uri("/repos" + repoFullName + "/contents/" + fileName)
                .header("Accept", "application/vnd.github.v3.raw")
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    log.error("Error fetching content", throwable);
                    return Mono.empty();
                });
    }

    private Mono<GHRepDTO> fetchRepositoriesByLanguage(String language) {
        return webClient.get()
                .uri("/search/repositories?q=language:" + language)
                .retrieve()
                .bodyToMono(GHRepDTO.class)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(throwable -> log.error("Error fetching repositories", throwable));
    }
}
