package com.konivan.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import lombok.Data;

@Data
public class PhysicComponent implements Component {
	public static final ComponentMapper<PhysicComponent> MAPPER = ComponentMapper.getFor(PhysicComponent.class);

	private final Body body;
	private final Vector2 currentPosition =Vector2.Zero;

	public PhysicComponent(Body body) {
		this.body = body;
	}
}
