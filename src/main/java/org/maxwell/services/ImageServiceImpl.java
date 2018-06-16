package org.maxwell.services;

import java.io.IOException;

import org.maxwell.domain.Recipe;
import org.maxwell.reactive.repositories.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeReactiveRepository recipeReactiveRepository;

	public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
		this.recipeReactiveRepository = recipeReactiveRepository;
	}

	@Override
	@Transactional
	public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
		log.debug("saving the image for the recipeId: {}", recipeId);
		Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId).map(recipe -> {
			Byte[] byteObjects = new Byte[0];
			try {
				byteObjects = new Byte[file.getBytes().length];
				int i = 0;
				for (byte b : file.getBytes()) {
					byteObjects[i++] = b;
				}
				recipe.setImage(byteObjects);
				return recipe;
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		recipeReactiveRepository.save(recipeMono.block()).block();
		return Mono.empty();
	}
}
