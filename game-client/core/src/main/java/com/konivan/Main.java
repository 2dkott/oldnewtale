package com.konivan;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.konivan.systems.RenderSystem;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static final float WORLD_HEIGHT = 9f ;
    public static final float WORLD_WIDTH = 16f;
    public static final float UNIT_SCALE = 1f / 64f;

    private OrthographicCamera camera;
    private Engine engine;
    private TiledMap map;

    private ExtendViewport viewport;

    FPSLogger fpsLogger;

    GLProfiler glProfiler;

    @Override
    public void create() {

        var sb = new SpriteBatch();

        map = new TmxMapLoader().load("maps/towns/first_town/first-town.tmx");

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        viewport.apply();

        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

        RenderSystem renderingSystem = new RenderSystem(sb, viewport, camera);


        renderingSystem.setCurrentMap(map);

        engine = new PooledEngine();

        engine.addSystem(renderingSystem);

        setScreen(new FirstScreen(engine));

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
