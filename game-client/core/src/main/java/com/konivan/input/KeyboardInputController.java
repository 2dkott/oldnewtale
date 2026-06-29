package com.konivan.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputController extends InputAdapter {

	private static final Map<Integer, CommandTypes> INPUT_MAP = Map.ofEntries(
			Map.entry(Input.Keys.W, CommandTypes.MOVE_UP), Map.entry(Input.Keys.S, CommandTypes.MOVE_DOWN),
			Map.entry(Input.Keys.A, CommandTypes.MOVE_LEFT), Map.entry(Input.Keys.D, CommandTypes.MOVE_RIGHT));

    private final boolean[] commandState;
    private final Map<Class<? extends InputControllerState>, InputControllerState> stateCache;
    private InputControllerState activeState;

    public KeyboardInputController(Class<? extends InputControllerState> initialState,
                              Engine engine,
                              Stage stage) {

        this.commandState = new boolean[CommandTypes.values().length];
        this.stateCache = new HashMap<>();
        this.activeState = null;

        this.stateCache.put(IdleControllerState.class, new IdleControllerState());

        if (engine != null) {
            this.stateCache.put(PlayTimeControllerState.class, new PlayTimeControllerState(engine));
        }
        if (stage != null) {
            this.stateCache.put(UiControllerState.class, new UiControllerState(stage));
        }
        setActiveState(initialState);
    }

    public void setActiveState(Class<? extends InputControllerState> stateClass) {

        InputControllerState state = stateCache.get(stateClass);

        if (state == null) {

            throw new GdxRuntimeException("State " + stateClass.getSimpleName() + " not found in cache");
        }

        for (CommandTypes command : CommandTypes.values()) {

            if (this.activeState != null && this.commandState[command.ordinal()]) {
                this.activeState.keyUp(command);
            }
            this.commandState[command.ordinal()] = false;
        }
        this.activeState = state;
    }

    @Override
    public boolean keyDown(int keycode) {

        CommandTypes command = INPUT_MAP.get(keycode);
        if (command == null) return false;

        this.commandState[command.ordinal()] = true;
        this.activeState.keyDown(command);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        CommandTypes command = INPUT_MAP.get(keycode);

        if (command == null) return false;

        // if a button was not pressed before, ignore it
        if (!this.commandState[command.ordinal()]) return false;

        this.commandState[command.ordinal()] = false;
        this.activeState.keyUp(command);
        return true;
    }
}
