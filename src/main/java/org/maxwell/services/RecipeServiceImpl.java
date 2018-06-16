package org.maxwell.services;

import org.maxwell.commands.RecipeCommand;
import org.maxwell.converters.RecipeCommandToRecipe;
import org.maxwell.converters.RecipeToRecipeCommand;
import org.maxwell.domain.Recipe;
import org.maxwell.reactive.repositories.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeReactiveRepository recipeReactiveRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository,
			RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeReactiveRepository = recipeReactiveRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Flux<Recipe> getRecipes() {
		log.debug("I'm in the service");
		return recipeReactiveRepository.findAll();
	}

	@Override
	public Mono<Recipe> findById(String id) {
		return recipeReactiveRepository.findById(id);
	}

	@Override
	@Transactional
	public Mono<RecipeCommand> findCommandById(String id) {
		return recipeReactiveRepository.findById(id).map(recipe -> {
			RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
			recipeCommand.getIngredients().forEach(rc -> {
				rc.setRecipeId(recipeCommand.getId());
			});
			return recipeCommand;
		});
	}

	@Override
	@Transactional
	public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
		return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
				.map(recipeToRecipeCommand::convert);
	}

	@Override
	public Mono<Void> deleteById(String idToDelete) {
		recipeReactiveRepository.deleteById(idToDelete).block();
		return Mono.empty();
	}
}
