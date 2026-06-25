package com.konivan;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.konivan.characters.CharacterCreator;
import com.konivan.characters.Player;
import com.konivan.systems.RenderSystem;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    Engine engine;
    RenderSystem renderSystem;



    private final Viewport uiViewport;

    private CharacterCreator creator;

    public FirstScreen(Engine engine, World world) {
        this.engine = engine;


        creator = new CharacterCreator(world, engine);

        this.uiViewport = new FitViewport(320f, 180f);
    }

    @Override
    public void show() {

        renderSystem = engine.getSystem(RenderSystem.class);
        var player = creator.createPlayer();
        engine.addEntity(player.getEntity());
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        renderSystem.getViewport().update(width, height, true);

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
