package com.konivan;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.konivan.domain.camera.GameCameraImpl;
import com.konivan.input.InputManager;
import com.konivan.services.CharacterService;
import com.konivan.systems.*;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends Game {

	public static final float WORLD_HEIGHT = 9f;
	public static final float WORLD_WIDTH = 16f;

	private TiledMap map;

	private FPSLogger fpsLogger;

	private GLProfiler glProfiler;

	@Override
	public void create() {

		var engine = new PooledEngine();

		var inputManager = new InputManager(engine);
		Gdx.input.setInputProcessor(inputManager.getInputMultiplexer());

        var gameCamera = new GameCameraImpl(WORLD_WIDTH, WORLD_HEIGHT);

		gameCamera.setPosition(
				new Vector3(gameCamera.getCamera().viewportWidth / 2, gameCamera.getCamera().viewportHeight / 2, 0));

		map = new TmxMapLoader().load("maps/towns/first_town/first-town.tmx");

		var physicWorld = new World(Vector2.Zero, true);
		physicWorld.setAutoClearForces(false);

		RenderSystem renderingSystem = new RenderSystem(new SpriteBatch(), gameCamera);

		renderingSystem.setCurrentMap(map);

		engine.addSystem(new InputSystem(this));
		engine.addSystem(new MoveSystem());
		engine.addSystem(new PhysicSystem(physicWorld, 1f / 60f));
		engine.addSystem(new CameraSystem(gameCamera));
		engine.addSystem(renderingSystem);

		engine.addSystem(new PhysicsRenderSystem(physicWorld, gameCamera));

		setScreen(new FirstScreen(engine, new CharacterService(physicWorld, engine)));

		glProfiler = new GLProfiler(Gdx.graphics);
		glProfiler.enable();
		fpsLogger = new FPSLogger();
	}

	@Override
	public void render() {

		glProfiler.reset();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();

		Gdx.graphics.setTitle("Mystic Tutorial - Draw Calls: " + glProfiler.getDrawCalls());
		fpsLogger.log();
	}
}
