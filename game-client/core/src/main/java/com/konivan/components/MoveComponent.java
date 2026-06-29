package com.konivan.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MoveComponent implements Component {

	public static final ComponentMapper<MoveComponent> MAPPER = ComponentMapper.getFor(MoveComponent.class);

	private final float maxSpeed;
	private final Vector2 direction;

	@Setter
	private boolean isRooted;

	public MoveComponent(float maxSpeed) {
		this.maxSpeed = maxSpeed;
		this.direction = new Vector2();
	}

}
