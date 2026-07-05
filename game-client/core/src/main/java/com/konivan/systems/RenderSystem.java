package com.konivan.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.konivan.domain.camera.GameCamera;
import com.konivan.components.TextureComponent;
import com.konivan.components.TransformComponent;
import com.konivan.render.ZComparator;
import lombok.Getter;

@Getter
public class RenderSystem extends SortedIteratingSystem {

	private final GameCamera gameCamera;

	static final float PPM = 64.0f; // sets the amount of pixels each metre of box2d objects contains

	public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

	public static float PixelsToMeters(float pixelValue) {
		return pixelValue * PIXELS_TO_METRES;
	}

	private final SpriteBatch batch;
	private final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
	private final ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);;
	private OrthogonalTiledMapRenderer renderer;

	private TiledMap currentMap;

	public void setCurrentMap(TiledMap currentMap) {
		this.currentMap = currentMap;
		renderer = new OrthogonalTiledMapRenderer(currentMap, 1 / 64f);
	}

	@SuppressWarnings("unchecked")
	public RenderSystem(SpriteBatch batch, GameCamera gameCamera) {
		super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());
		this.gameCamera = gameCamera;
		this.batch = batch;
		this.renderer = new OrthogonalTiledMapRenderer(null, 1 / 64f, batch);
	}

	@Override
	public void update(float deltaTime) {

		ScreenUtils.clear(Color.WHITE);

		gameCamera.getCamera().update();

		batch.setProjectionMatrix(gameCamera.getViewport().getCamera().combined);

		batch.begin();
		batch.setColor(Color.WHITE);
		this.renderer.setView(gameCamera.getCamera());
		this.renderer.render();

		forceSort();

		super.update(deltaTime);

		batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		batch.setProjectionMatrix(gameCamera.getCamera().combined);

		TextureComponent textureComponent = textureMapper.get(entity);
		TransformComponent transformComponent = transformM.get(entity);

		float width = textureComponent.getRegion().getRegionWidth();
		float height = textureComponent.getRegion().getRegionHeight();

		float originX = width / 2f;
		float originY = height / 2f;

		batch.draw(textureComponent.getRegion(), transformComponent.position.x - originX,
				transformComponent.position.y - originY, originX, originY, width, height,
				PixelsToMeters(transformComponent.scale.x), PixelsToMeters(transformComponent.scale.y),
				transformComponent.rotation);
	}

}
