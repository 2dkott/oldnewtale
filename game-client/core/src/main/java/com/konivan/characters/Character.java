package com.konivan.characters;

import com.badlogic.ashley.core.Entity;
import com.konivan.components.TextureComponent;
import com.konivan.components.TransformComponent;
import lombok.Getter;

@Getter
public class Character {

    private final TextureComponent textureComponent;
    private final TransformComponent transformComponent;
    private final Entity entity;

    public Character(Entity entity) {
        this.entity = entity;
        textureComponent = new TextureComponent();
        transformComponent = new TransformComponent();
        entity.add(textureComponent);
        entity.add(transformComponent);
    }
}
