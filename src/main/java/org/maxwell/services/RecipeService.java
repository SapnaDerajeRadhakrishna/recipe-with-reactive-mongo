package org.maxwell.services;

import java.util.Set;

import org.maxwell.commands.RecipeCommand;
import org.maxwell.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(String id);

	RecipeCommand findCommandById(String id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	void deleteById(String idToDelete);
}
