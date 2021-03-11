package com.dio.heroesapi.controller;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.dio.heroesapi.constant.HeroesConstant.HEROES_ENDPOINT;

@RestController
@Slf4j
public class HeroesController {

	@Autowired
	private HeroesService heroesService;

	@GetMapping(path = HEROES_ENDPOINT)
	public Flux<Heroes> getAllHeroes() {
		log.info("Requesting the list of all heroes");
		return heroesService.findAll();
	}

	@GetMapping(path = HEROES_ENDPOINT+"/{id}")
	public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable(value = "id") String id) {
		log.info("Requesting hero with id {}", id);

		return this.heroesService.findHeroById(id)
			.map((body) ->  new ResponseEntity<Heroes>(body, HttpStatus.OK));
	}

	@PostMapping(path = HEROES_ENDPOINT+"/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Heroes> saveHero(@RequestBody Heroes heroes) {
		log.info("Creating a new hero");
		return heroesService.saveHero(heroes);
	}

	@DeleteMapping(path = HEROES_ENDPOINT+"/{id}")
	@ResponseStatus(HttpStatus.CONTINUE)
	public Mono<HttpStatus> deleteHero(@PathVariable(value = "id") String id) {
		log.info("Deleting user with id {}", id);
		heroesService.deleteHero(id);
		return Mono.just(HttpStatus.CONTINUE);
	}
}
