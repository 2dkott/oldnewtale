package com.konivan.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputMultiplexer;
import lombok.Getter;

@Getter
public class InputManager {

	private final InputMultiplexer inputMultiplexer = new InputMultiplexer();

	public InputManager(Engine engine) {

		KeyboardInputController keyboardController = new KeyboardInputController(PlayTimeControllerState.class, engine,
				null);
		inputMultiplexer.clear();
		inputMultiplexer.addProcessor(keyboardController);
		keyboardController.setActiveState(PlayTimeControllerState.class);
	}
}
