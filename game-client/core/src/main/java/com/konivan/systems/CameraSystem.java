package com.konivan.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.konivan.components.CamerTargetComponent;
import com.konivan.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private Camera camera;
    private final float smoothingFactor = 0.05f;

    private Vector2 targetPosition = new Vector2();

    private final ComponentMapper<TransformComponent> transformMapper =  ComponentMapper.getFor(TransformComponent.class);

    public CameraSystem(Camera camera) {
        super(Family.all(CamerTargetComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = transformMapper.get(entity);
        camera.position.set(transform.getPosition().x, transform.getPosition().y, camera.position.z);
    }
}
