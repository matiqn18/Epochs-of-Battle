package com.epochs.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.epochs.game.Main;
import com.epochs.game.dialogs.*;

public class MenuScreen implements Screen {

    private final Stage stage;
    private final SpriteBatch batch;
    private final Skin skin;
    private final Texture titleImage;
    private final Texture backgroundImage;


    public MenuScreen(Main game, int defeat, int defeatedEnemies, String timePlay) {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));

        titleImage = new Texture(Gdx.files.internal("Tittle.png"));
        backgroundImage = new Texture(Gdx.files.internal("background.png"));

        if (!game.backgroundMusic.isPlaying()) {
            game.backgroundMusic.play();
        }

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.getDrawable("default-round");
        buttonStyle.down = skin.getDrawable("default-round-down");

        TextButton startButton = new TextButton("Start gry", buttonStyle);
        TextButton scoresButton = new TextButton("Wyniki", buttonStyle);
        TextButton settingsButton = new TextButton("Ustawienia", buttonStyle);
        TextButton creditsButton = new TextButton("Twórcy", buttonStyle);
        TextButton exitButton = new TextButton("Zamknij grę", buttonStyle);

        Table buttonTable = new Table();
        buttonTable.add(startButton).fillX().uniformX();
        buttonTable.row().pad(10, 0, 0, 0);
        buttonTable.add(scoresButton).fillX().uniformX();
        buttonTable.row().pad(10, 0, 0, 0);
        buttonTable.add(settingsButton).fillX().uniformX();
        buttonTable.row().pad(10, 0, 0, 0);
        buttonTable.add(creditsButton).fillX().uniformX();
        buttonTable.row().pad(10, 0, 0, 0);
        buttonTable.add(exitButton).fillX().uniformX();

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.add().expand();
        mainTable.add(buttonTable).right().padRight(50);

        stage.addActor(mainTable);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new SettingsDialog(skin, game).show(stage);
            }
        });

        switch (defeat) {
            case 1:
                new EndGameDialog(skin, MenuScreen.this, defeatedEnemies, timePlay, 1).show(stage);
                break;
            case 2:
                new EndGameDialog(skin, MenuScreen.this, defeatedEnemies, timePlay, 2).show(stage);
                break;
            case 3:
                new EndGameDialog(skin, MenuScreen.this, defeatedEnemies, timePlay, 3).show(stage);
                break;
            default:
                break;
        }

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new CreditsDialog(skin, MenuScreen.this).show(stage);
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                new StartGameDialog(skin, game).show(stage);
            }
        });

        scoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new ScoresDialog(skin, MenuScreen.this).show(stage);
            }
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float scaleFactor = 1.0f;
        if (Gdx.graphics.getWidth() < titleImage.getWidth() || Gdx.graphics.getHeight() < titleImage.getHeight()) {
            scaleFactor = Math.min(
                (float) Gdx.graphics.getWidth() / titleImage.getWidth(),
                (float) Gdx.graphics.getHeight() / titleImage.getHeight()
            );
        }
        float xPosition = 20;
        float yPosition = Gdx.graphics.getHeight() - titleImage.getHeight() * scaleFactor - 20;
        batch.draw(titleImage, xPosition, yPosition, titleImage.getWidth() * scaleFactor, titleImage.getHeight() * scaleFactor);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        titleImage.dispose();
    }
}
