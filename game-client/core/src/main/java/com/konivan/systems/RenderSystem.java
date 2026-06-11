package com.konivan.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.konivan.components.TextureComponent;
import com.konivan.components.TransformComponent;
import com.konivan.render.ZComparator;
import lombok.Getter;

import java.util.Comparator;

@Getter
public class RenderSystem extends SortedIteratingSystem {

    static final float PPM = 64.0f; // sets the amount of pixels each metre of box2d objects contains

    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

    public static float PixelsToMeters(float pixelValue){
        return pixelValue * PIXELS_TO_METRES;
    }

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to draw images on top of each other
    private Comparator<Entity> comparator; // a comparator to sort images based on the z position of the transfromComponent
    private OrthographicCamera camera; // a reference to our camera

    // component mappers to get components from entities
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;
    private OrthogonalTiledMapRenderer renderer;

    private TiledMap currentMap;
    private Viewport viewport;

    public void setCurrentMap(TiledMap currentMap) {
        this.currentMap = currentMap;
        renderer = new OrthogonalTiledMapRenderer(currentMap, 1 / 64f);
    }

    @SuppressWarnings("unchecked")
    public RenderSystem(SpriteBatch batch, Viewport viewport, OrthographicCamera camera) {
        // gets all entities with a TransofmComponent and TextureComponent
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        //creates out componentMappers
        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);

        this.batch = batch;
        this.viewport = viewport;
        this.camera = camera;
        this.renderer = new OrthogonalTiledMapRenderer(null,1 / 64f, batch);
    }

    @Override
    public void update(float deltaTime) {
       //AnimatedTiledMapTile.updateAnimationBaseTime();
        ScreenUtils.clear(Color.WHITE);

        camera.update();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.setColor(Color.WHITE);
        this.renderer.setView(camera);
        this.renderer.render();
        //bgdLayers.forEach(tiledRenderer::renderMapLayer);

        forceSort();

        super.update(deltaTime);

        batch.setColor(Color.WHITE);
        //fgdLayers.forEach(tiledRenderer::renderMapLayer);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

            batch.setProjectionMatrix(camera.combined);

            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);

            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();

            float originX = width/2f;
            float originY = height/2f;

            batch.draw(tex.region,
                t.position.x - originX, t.position.y - originY,
                originX, originY,
                width, height,
                PixelsToMeters(t.scale.x), PixelsToMeters(t.scale.y),
                t.rotation);
    }

}
