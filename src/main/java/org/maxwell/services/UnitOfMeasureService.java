package org.maxwell.services;

import java.util.Set;

import org.maxwell.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
