package com.konivan.domain.levels;

import com.konivan.domain.assets.TiledMapAsset;

public interface GameLevel {

	Level getLevel();

	TiledMapAsset getTiledMapAsset();

}
