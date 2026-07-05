package com.konivan.domain.levels;

import com.konivan.domain.assets.TiledMapAsset;
import lombok.Getter;

@Getter
public class StartLevel implements GameLevel {

	Level level = Level.START_LEVEL;

	TiledMapAsset tiledMapAsset = new TiledMapAsset("towns/first_town/first-town.tmx");

}
