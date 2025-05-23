package com.epochs.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture enemyTexture = new Texture(Gdx.files.internal("enemy.png"));
    public static Texture towerTexture = new Texture(Gdx.files.internal("tower.png"));
    public static Texture bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
    public static Texture mapTexture = new Texture(Gdx.files.internal("map.png"));

    public static void dispose() {
        enemyTexture.dispose();
        towerTexture.dispose();
        bulletTexture.dispose();
        mapTexture.dispose();
    }
}
