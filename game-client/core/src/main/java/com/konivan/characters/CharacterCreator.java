package com.konivan.characters;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CharacterCreator {

	private final World world;
	private final Engine engine;

	public Player createPlayer() {
		return new Player(engine.createEntity(), createBody());
	}

	Body createBody() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(0, 0));
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		Body body = world.createBody(bodyDef);

		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;

		body.createFixture(fixtureDef);

		return body;
	}

}
