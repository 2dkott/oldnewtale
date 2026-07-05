package com.konivan.domain.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import lombok.Getter;

@Getter
public class GameCameraImpl implements GameCamera {

	private final OrthographicCamera camera;
	private final ExtendViewport viewport;

	public GameCameraImpl(float minWorldWidth, float minWorldHeight) {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(minWorldWidth, minWorldHeight, camera);
		viewport.apply();
	}

	public void setPosition(Vector3 position) {
		camera.position.set(position);
	}

}
