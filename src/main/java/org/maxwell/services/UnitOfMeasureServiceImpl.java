package org.maxwell.services;

import org.maxwell.commands.UnitOfMeasureCommand;
import org.maxwell.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.maxwell.reactive.repositories.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureReactiveRepository uomReactiveRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository uomReactiveRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.uomReactiveRepository = uomReactiveRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Flux<UnitOfMeasureCommand> listAllUoms() {
		return uomReactiveRepository
				.findAll()
				.map(unitOfMeasureToUnitOfMeasureCommand::convert);
	}
}
