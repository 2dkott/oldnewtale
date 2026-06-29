package com.konivan;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.konivan.characters.CharacterCreator;
import com.konivan.systems.RenderSystem;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {

	private final Engine engine;
	private final RenderSystem renderSystem;
	private final CharacterCreator creator;

	public FirstScreen(Engine engine, CharacterCreator characterCreator) {

		this.engine = engine;
		this.creator = characterCreator;
		this.renderSystem = engine.getSystem(RenderSystem.class);
	}

	@Override
	public void show() {

		var player = creator.createPlayer();
		engine.addEntity(player.getEntity());
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {

		if (width <= 0 || height <= 0)
			return;

		renderSystem.getGameCamera().getViewport().update(width, height, true);
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
