package org.maxwell.services;

import org.maxwell.commands.UnitOfMeasureCommand;

import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
	Flux<UnitOfMeasureCommand> listAllUoms();
}
