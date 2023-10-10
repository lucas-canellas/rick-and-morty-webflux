package com.lucasdc.webclientrickandmortyapi.controller;

import com.lucasdc.webclientrickandmortyapi.client.RickAndMortyClient;
import com.lucasdc.webclientrickandmortyapi.response.CharacterResponse;
import com.lucasdc.webclientrickandmortyapi.response.EpisodeResponse;
import com.lucasdc.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.lucasdc.webclientrickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findAndCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findAndLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findAndEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        return rickAndMortyClient.getAllEpisodes();
    }

}
