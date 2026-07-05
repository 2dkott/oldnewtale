package com.konivan.domain.characters;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.konivan.components.*;
import lombok.Getter;

@Getter
public class Player extends CharacterImpl {

	ControllerComponent controllerComponent;

	public Player() {
		super();
		var spriteSheet = new Texture(Gdx.files.internal("characters/main/test.png"));
		var region = new TextureRegion(spriteSheet, 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight());
		getTextureComponent().setRegion(region);
		this.controllerComponent = new ControllerComponent();

	}

	@Override
	public void init(Entity entity, World world) {
		super.init(entity, world);
		getEntity().add(controllerComponent);
	}
}
