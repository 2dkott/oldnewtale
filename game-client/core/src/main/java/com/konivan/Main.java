package com.konivan;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.konivan.input.KeyboardInputController;
import com.konivan.input.PlayTimeControllerState;
import com.konivan.systems.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static final float WORLD_HEIGHT = 9f ;
    public static final float WORLD_WIDTH = 16f;
    public static final float UNIT_SCALE = 1f / 64f;

    private OrthographicCamera camera;
    private Engine engine;
    private TiledMap map;

    private ExtendViewport viewport;

    InputMultiplexer inputMultiplexer;

    FPSLogger fpsLogger;

    GLProfiler glProfiler;
    private KeyboardInputController keyboardController;

    private World physicWorld;

    @Override
    public void create() {

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        var sb = new SpriteBatch();

        map = new TmxMapLoader().load("maps/towns/first_town/first-town.tmx");

        this.physicWorld = new World(Vector2.Zero, true);
        this.physicWorld.setAutoClearForces(false);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        viewport.apply();

        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

        RenderSystem renderingSystem = new RenderSystem(sb, viewport, camera);


        renderingSystem.setCurrentMap(map);

        engine = new PooledEngine();


        engine.addSystem(new InputSystem(this));
        engine.addSystem(new MoveSystem());
        engine.addSystem(new PhysicSystem(physicWorld, 1f / 60f));
        engine.addSystem(new CameraSystem(camera));
        engine.addSystem(renderingSystem);

        engine.addSystem(new PhysicsRenderSystem(physicWorld, camera));

        keyboardController = new KeyboardInputController(PlayTimeControllerState.class, engine, null);

        inputMultiplexer.clear();

        inputMultiplexer.addProcessor(keyboardController);

        keyboardController.setActiveState(PlayTimeControllerState.class);

        setScreen(new FirstScreen(engine, physicWorld));

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
