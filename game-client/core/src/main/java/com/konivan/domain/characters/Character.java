package com.konivan.domain.characters;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

public interface Character {

	Entity getEntity();

	FixtureDef getFixtureDef();

	Fixture getFixture();

	BodyDef getBodyDef();

	Body getBody();

	void init(Entity entity, World world);

}
