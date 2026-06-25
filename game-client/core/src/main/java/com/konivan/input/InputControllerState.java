package com.konivan.input;

public interface InputControllerState {

    void keyDown(CommandTypes commandType);

	default void keyUp(CommandTypes commandType) {
	}
}
