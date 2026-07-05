package com.konivan.domain.characters;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.konivan.components.*;
import lombok.Getter;

@Getter
public class CharacterImpl implements Character {

	private final TextureComponent textureComponent;
	private final TransformComponent transformComponent;
	private final CamerTargetComponent camerTargetComponent;
	private final MoveComponent moveComponent;
	private PhysicComponent physicComponent;

	private Entity entity;

	private final BodyDef bodyDef;
	private Body body;
	private final FixtureDef fixtureDef;
	private Fixture fixture;

	public CharacterImpl() {

		this.bodyDef = new BodyDef();
		this.bodyDef.position.set(new Vector2(0, 0));
		this.bodyDef.type = BodyDef.BodyType.DynamicBody;

		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		this.fixtureDef = new FixtureDef();
		this.fixtureDef.shape = circle;
		this.fixtureDef.density = 0.5f;
		this.fixtureDef.friction = 0.4f;
		this.fixtureDef.restitution = 0.6f;

		this.textureComponent = new TextureComponent();
		this.transformComponent = new TransformComponent();
		this.camerTargetComponent = new CamerTargetComponent();
		this.moveComponent = new MoveComponent(4);
	}

	public void init(Entity entity, World world) {

		this.entity = entity;

		entity.add(textureComponent);
		entity.add(transformComponent);
		entity.add(camerTargetComponent);
		entity.add(moveComponent);

		this.body = world.createBody(bodyDef);
		this.fixture = body.createFixture(fixtureDef);

		this.physicComponent = new PhysicComponent(this.body);
		entity.add(this.physicComponent);

	}
}
