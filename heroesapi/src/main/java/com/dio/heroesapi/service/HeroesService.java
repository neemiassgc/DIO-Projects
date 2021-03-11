package com.dio.heroesapi.service;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

	private final HeroesRepository heroesRepository;

	HeroesService(HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
	}

	public Flux<Heroes> findAll() {
		return Flux.fromIterable(this.heroesRepository.findAll());
	}

	public Mono<Heroes> findHeroById(String id) {
		return Mono.justOrEmpty(this.heroesRepository.findById(id));
	}

	public Mono<Heroes> saveHero(Heroes heroes) {
		return Mono.justOrEmpty(this.heroesRepository.save(heroes));
	}

	public Mono<Boolean> deleteHero(String id) {
		this.heroesRepository.deleteById(id);
		return Mono.just(true);
	}
}
