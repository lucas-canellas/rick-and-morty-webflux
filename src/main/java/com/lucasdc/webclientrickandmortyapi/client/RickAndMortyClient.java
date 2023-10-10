package com.lucasdc.webclientrickandmortyapi.client;

import com.lucasdc.webclientrickandmortyapi.response.CharacterResponse;
import com.lucasdc.webclientrickandmortyapi.response.EpisodeResponse;
import com.lucasdc.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.lucasdc.webclientrickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickAndMortyClient {
    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id) {
        log.info("buscando o personagem com o id[{}]", id);

        return webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros passados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findAndLocationById(String id) {
        log.info("buscando uma localizacao com o id[{}]", id);

        return webClient
                .get()
                .uri("/location/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros passados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findAndEpisodeById(String id) {
        log.info("buscando um episodio com o id[{}]", id);

        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros passados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        log.info("listando todos os episodios");

        return webClient
                .get()
                .uri("/episode/" )
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parametros passados")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}

