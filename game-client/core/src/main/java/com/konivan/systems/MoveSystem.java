package com.konivan.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.konivan.components.MoveComponent;
import com.konivan.components.PhysicComponent;

public class MoveSystem extends IteratingSystem {

	private static final Vector2 tempDirection = new Vector2();

	public MoveSystem() {
		super(Family.all(PhysicComponent.class, MoveComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MoveComponent move = MoveComponent.MAPPER.get(entity);
		PhysicComponent physic = PhysicComponent.MAPPER.get(entity);
		Body body = physic.getBody();

//		if (move.isRooted() || move.getDirection().isZero()) {
//			// no direction given or rooted -> stop movement
//			body.setLinearVelocity(0f, 0f);
//			return;
//		}

		float maxSpeed = move.getMaxSpeed();
		tempDirection.set(move.getDirection()).nor();
		body.setLinearVelocity(maxSpeed * tempDirection.x, maxSpeed * tempDirection.y);
	}
}
