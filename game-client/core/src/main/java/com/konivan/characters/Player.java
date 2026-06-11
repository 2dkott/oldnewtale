package com.konivan.characters;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.konivan.components.TextureComponent;
import lombok.Getter;

@Getter
public class Player extends Character {


    public Player(Entity entity) {
        super(entity);
        var spriteSheet = new Texture(Gdx.files.internal("characters/main/test.png"));
        var region = new TextureRegion(spriteSheet, 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight());
        getTextureComponent().region = region;
    }
}
