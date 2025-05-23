package com.epochs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epochs.game.screens.MenuScreen;


public class Main extends Game {
    public SpriteBatch batch;
    public Music backgroundMusic;

    @Override
    public void create() {
        batch = new SpriteBatch();


        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/backsong.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.play();

        this.setScreen(new MenuScreen(this, 0,0, ""));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (backgroundMusic != null) backgroundMusic.dispose();
    }
}
