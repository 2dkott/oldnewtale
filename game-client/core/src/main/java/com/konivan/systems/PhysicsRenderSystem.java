package com.konivan.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.konivan.camera.GameCamera;

public class PhysicsRenderSystem extends EntitySystem implements Disposable {
	private final World physicWorld;
	private final Box2DDebugRenderer box2DDebugRenderer;
	private final Camera camera;

	public PhysicsRenderSystem(World physicWorld, GameCamera gameCamera) {
		this.box2DDebugRenderer = new Box2DDebugRenderer();
		this.physicWorld = physicWorld;
		this.camera = gameCamera.getCamera();
		setProcessing(false);
	}

	@Override
	public void update(float deltaTime) {
		this.box2DDebugRenderer.render(physicWorld, camera.combined);
	}

	@Override
	public void dispose() {
		this.box2DDebugRenderer.dispose();
		// this.shapeRenderer.dispose();
	}
}
