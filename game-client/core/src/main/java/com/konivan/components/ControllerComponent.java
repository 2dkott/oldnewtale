package com.konivan.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.konivan.input.CommandTypes;

import java.util.ArrayList;
import java.util.List;

public class ControllerComponent implements Component {

	public static final ComponentMapper<ControllerComponent> MAPPER = ComponentMapper.getFor(ControllerComponent.class);

	private final List<CommandTypes> pressedCommands;
	private final List<CommandTypes> releasedCommands;

	public ControllerComponent() {
		this.pressedCommands = new ArrayList<>();
		this.releasedCommands = new ArrayList<>();
	}

	public List<CommandTypes> getPressedCommands() {
		return pressedCommands;
	}

	public List<CommandTypes> getReleasedCommands() {
		return releasedCommands;
	}
}
