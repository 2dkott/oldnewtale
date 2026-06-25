package com.konivan.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.konivan.Main;
import com.konivan.components.ControllerComponent;
import com.konivan.components.MoveComponent;
import com.konivan.input.CommandTypes;

public class InputSystem extends IteratingSystem {

	private final Main game;

	public InputSystem(Main game) {
		super(Family.all(ControllerComponent.class).get());
		this.game = game;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

        ControllerComponent controller = ControllerComponent.MAPPER.get(entity);

        if (controller.getPressedCommands().isEmpty() && controller.getReleasedCommands().isEmpty()) {
			return;
		}

		for (CommandTypes command : controller.getPressedCommands()) {
			switch (command) {
			case MOVE_UP -> moveEntity(entity, 0f, 1f);
			case MOVE_DOWN -> moveEntity(entity, 0f, -1f);
			case MOVE_LEFT -> moveEntity(entity, -1f, 0f);
			case MOVE_RIGHT -> moveEntity(entity, 1f, 0f);
			}
		}
		controller.getPressedCommands().clear();

		for (CommandTypes command : controller.getReleasedCommands()) {
			switch (command) {
			case MOVE_UP -> moveEntity(entity, 0f, -1f);
			case MOVE_DOWN -> moveEntity(entity, 0f, 1f);
			case MOVE_LEFT -> moveEntity(entity, 1f, 0f);
			case MOVE_RIGHT -> moveEntity(entity, -1f, 0f);
			}
		}
		controller.getReleasedCommands().clear();
	}

	private void moveEntity(Entity entity, float dx, float dy) {
		MoveComponent move = MoveComponent.MAPPER.get(entity);
		if (move != null) {
			move.getDirection().x += dx;
			move.getDirection().y += dy;
		}
	}
}
