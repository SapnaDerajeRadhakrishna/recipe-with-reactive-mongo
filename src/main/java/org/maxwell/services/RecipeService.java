package org.maxwell.services;

import org.maxwell.commands.RecipeCommand;
import org.maxwell.domain.Recipe;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

	Flux<Recipe> getRecipes();

	Mono<Recipe> findById(String id);

	Mono<RecipeCommand> findCommandById(String id);

	Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

	Mono<Void> deleteById(String idToDelete);
}
