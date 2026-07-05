package com.konivan.services;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.physics.box2d.*;
import com.konivan.domain.characters.Character;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CharacterService {

	private final World world;
	private final Engine engine;

	public Character createPlayer(Character character) {

		character.init(engine.createEntity(), world);
        engine.addEntity(character.getEntity());
		return character;
	}
}
