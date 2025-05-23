//Deprecated

//package com.epochs.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Slider;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.epochs.game.Main;
//
//public class SettingsScreen implements Screen {
//
//    private final Main game;
//    private final Stage stage;
//    private final Skin skin;
//    private final BitmapFont font;
//
//    public SettingsScreen(Main game) {
//        this.game = game;
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//
//        skin = new Skin(Gdx.files.internal("uiskin.json"));
//        font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
//
//        Table table = new Table();
//        table.setFillParent(true);
//        stage.addActor(table);
//
//        // Rozdzielczości do wyboru
//        Label resolutionLabel = new Label("Rozdzielczość:", skin);
//        SelectBox<String> resolutionBox = new SelectBox<>(skin);
//        resolutionBox.setItems("800x600", "1024x768", "1280x720", "1920x1080");
//
//        resolutionBox.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                String selectedResolution = resolutionBox.getSelected();
//                String[] dims = selectedResolution.split("x");
//                int width = Integer.parseInt(dims[0]);
//                int height = Integer.parseInt(dims[1]);
//                Gdx.graphics.setWindowedMode(width, height);
//            }
//        });
//
//        // Tryb pełnoekranowy
//        Label fullscreenLabel = new Label("Tryb pełnoekranowy:", skin);
//        TextButton fullscreenButton = new TextButton("Przełącz pełny ekran", skin);
//        fullscreenButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
//            }
//        });
//
//        // HUD size
//        Label hudLabel = new Label("Skalowanie HUD:", skin);
//        Slider hudSlider = new Slider(0.5f, 2f, 0.1f, false, skin);
//        hudSlider.setValue(1f); // domyślnie ustawiony na 100%
//        hudSlider.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                float hudScale = hudSlider.getValue();
//                // Skalowanie HUD - dopasuj implementację do potrzeb twojej gry
//            }
//        });
//
//        // Głośność aplikacji
//        Label volumeLabel = new Label("Głośność:", skin);
//        Slider volumeSlider = new Slider(0, 1, 0.1f, false, skin);
//        volumeSlider.setValue(game.backgroundMusic.getVolume());
//        volumeSlider.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                game.backgroundMusic.setVolume(volumeSlider.getValue());
//            }
//        });
//
//        // Powrót do menu głównego
//        TextButton backButton = new TextButton("Powrót", skin);
//        backButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                game.setScreen(new MenuScreen(game,0,0,""));
//            }
//        });
//
//        // Dodaj elementy do tabeli
//        table.add(resolutionLabel).left().pad(10);
//        table.add(resolutionBox).pad(10);
//        table.row();
//        table.add(fullscreenLabel).left().pad(10);
//        table.add(fullscreenButton).pad(10);
//        table.row();
//        table.add(hudLabel).left().pad(10);
//        table.add(hudSlider).pad(10);
//        table.row();
//        table.add(volumeLabel).left().pad(10);
//        table.add(volumeSlider).pad(10);
//        table.row();
//        table.add(backButton).colspan(2).center().padTop(20);
//    }
//
//    @Override
//    public void show() {}
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//        skin.dispose();
//    }
//}
