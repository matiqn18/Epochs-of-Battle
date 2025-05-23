//Deprecated


//package com.epochs.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.epochs.game.Main;
//
//public class CreditsScreen implements Screen {
//
//    private final Main game;
//    private final Stage stage;
//    private final SpriteBatch batch;
//    private final Skin skin;
//    private final BitmapFont font; // Niestandardowa czcionka
//
//    public CreditsScreen(Main game) {
//        this.game = game;
//        batch = new SpriteBatch();
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//
//        // Ładowanie skórki i niestandardowej czcionki
//        skin = new Skin(Gdx.files.internal("uiskin.json"));
//        font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
//
//        // Przezroczysty panel z 70% rozmiarem ekranu
//        Table overlayTable = new Table();
//        overlayTable.setFillParent(true);
//        overlayTable.setColor(1, 1, 1, 0.6f); // Ustawienie przezroczystości na 80%
//
//        // Ustawienie rozmiaru panelu na 70% ekranu
//        float panelWidth = Gdx.graphics.getWidth() * 0.7f;
//        float panelHeight = Gdx.graphics.getHeight() * 0.7f;
//        overlayTable.setSize(panelWidth, panelHeight);
//        overlayTable.setPosition(
//            (Gdx.graphics.getWidth() - panelWidth) / 2,
//            (Gdx.graphics.getHeight() - panelHeight) / 2
//        );
//
//        // Etykieta z tekstem "Twórcy gry"
//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        labelStyle.font = font;
//        Label creditsLabel = new Label("Twórcy gry:\n- Michał Łukaszczyk\n- Katarzyna Mieczkowska", labelStyle);
//
//        // Dodanie etykiety do tabeli
//        overlayTable.add(creditsLabel).center().expand();
//
//        // Przycisk zamknięcia "X" w prawym górnym rogu
//        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
//        buttonStyle.font = font;
//        buttonStyle.up = skin.getDrawable("default-round");
//        buttonStyle.down = skin.getDrawable("default-round-down");
//
//        TextButton closeButton = new TextButton("X", buttonStyle);
//        closeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                // Powrót do MenuScreen po kliknięciu
//                game.setScreen(new MenuScreen(game, 0,0, ""));
//            }
//        });
//
//        // Górna tabela z przyciskiem "X"
//        Table topTable = new Table();
//        topTable.setFillParent(true);
//        topTable.add(closeButton).top().right().pad(10);
//
//        // Dodanie tabel do sceny
//        stage.addActor(overlayTable);
//        stage.addActor(topTable);
//    }
//
//    @Override
//    public void show() {
//    }
//
//    @Override
//    public void render(float delta) {
//        // Czyszczenie ekranu z półprzezroczystym tłem
//        Gdx.gl.glClearColor(0, 0, 0, 0.5f); // Lekko przezroczyste tło
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Rysowanie aktorów
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
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void hide() {
//    }
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//        skin.dispose();
//        batch.dispose();
//    }
//}
