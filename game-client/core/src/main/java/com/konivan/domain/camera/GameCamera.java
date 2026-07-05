package com.konivan.domain.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public interface GameCamera {

    OrthographicCamera getCamera();
    ExtendViewport getViewport();
    void setPosition(Vector3 position);
}
