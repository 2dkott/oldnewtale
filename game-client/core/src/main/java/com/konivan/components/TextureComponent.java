package com.konivan.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Data;
import lombok.Getter;

@Data
public class TextureComponent implements Component {

    public static final ComponentMapper<TextureComponent> MAPPER = ComponentMapper.getFor(TextureComponent.class);

    private TextureRegion region = null;
}
