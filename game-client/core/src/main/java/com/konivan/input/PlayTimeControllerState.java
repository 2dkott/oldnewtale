package com.konivan.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.konivan.components.ControllerComponent;

public class PlayTimeControllerState implements InputControllerState {

	private final ImmutableArray<Entity> controllerEntities;

	public PlayTimeControllerState(Engine engine) {
		this.controllerEntities = engine.getEntitiesFor(Family.all(ControllerComponent.class).get());
	}

	@Override
	public void keyDown(CommandTypes commandType) {
		for (Entity entity : controllerEntities) {
			ControllerComponent.MAPPER.get(entity).getPressedCommands().add(commandType);
		}
	}

	@Override
	public void keyUp(CommandTypes commandType) {
		for (Entity entity : controllerEntities) {
			ControllerComponent.MAPPER.get(entity).getReleasedCommands().add(commandType);
		}
	}
}
