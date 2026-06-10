package com.konivan;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.konivan.systems.RenderSystem;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private Engine engine;
    private TiledMap map;

    @Override
    public void create() {

        var sb = new SpriteBatch();

        map = new TmxMapLoader().load("maps/towns/first_town/first-town.tmx");

        RenderSystem renderingSystem = new RenderSystem(sb);

        var cam = renderingSystem.getCamera();

        renderingSystem.setCurrentMap(map);

        sb.setProjectionMatrix(cam.combined);

        engine = new PooledEngine();

        engine.addSystem(renderingSystem);

        setScreen(new FirstScreen(engine));
    }
}
