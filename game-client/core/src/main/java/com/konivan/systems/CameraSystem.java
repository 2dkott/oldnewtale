package com.konivan.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.konivan.domain.camera.GameCamera;
import com.konivan.components.CamerTargetComponent;
import com.konivan.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private final Camera camera;

    private final ComponentMapper<TransformComponent> transformMapper =  ComponentMapper.getFor(TransformComponent.class);

    public CameraSystem(GameCamera camera) {
        super(Family.all(CamerTargetComponent.class, TransformComponent.class).get());
        this.camera = camera.getCamera();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = transformMapper.get(entity);
        camera.position.set(transform.getPosition().x, transform.getPosition().y, camera.position.z);
    }
}
