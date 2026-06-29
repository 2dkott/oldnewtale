package com.konivan.characters;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.konivan.components.ControllerComponent;
import com.konivan.components.MoveComponent;
import com.konivan.components.PhysicComponent;
import lombok.Getter;

@Getter
public class Player extends Character {


    public Player(Entity entity, Body body) {
        super(entity);
        var spriteSheet = new Texture(Gdx.files.internal("characters/main/test.png"));
        var region = new TextureRegion(spriteSheet, 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight());
        getTextureComponent().setRegion(region);
        var moveComponent = new MoveComponent(4);
        var physicComponent = new PhysicComponent(body);
        var controllerComponent = new ControllerComponent();
        entity.add(moveComponent);
        entity.add(physicComponent);
        entity.add(controllerComponent);
    }
}
