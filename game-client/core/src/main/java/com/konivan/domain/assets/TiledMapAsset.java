package com.konivan.domain.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapAsset implements Asset<TiledMap> {

	private final AssetDescriptor<TiledMap> descriptor;

	public TiledMapAsset(String tiledMapPath) {
		this.descriptor = new AssetDescriptor<>("maps/" + tiledMapPath, TiledMap.class);
	}

	@Override
	public AssetDescriptor<TiledMap> getDescriptor() {
		return descriptor;
	}
}
