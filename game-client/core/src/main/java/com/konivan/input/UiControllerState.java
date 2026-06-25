package com.konivan.input;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class UiControllerState implements InputControllerState {

    private final Stage stage;

    public UiControllerState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void keyDown(CommandTypes commandType) {
        //this.stage.getRoot().fire(new UiEvent(command));
    }
}
