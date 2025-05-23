package com.epochs.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.epochs.game.screens.MenuScreen;

public class ScoresDialog extends Dialog {

    public ScoresDialog(Skin skin, MenuScreen menuScreen) {
        super("Wyniki", skin);

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
        font.getData().setScale(0.7f);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.newDrawable("default-round");
        buttonStyle.down = skin.newDrawable("default-round-down");

        Table table = new Table();
        table.pad(10).defaults().padBottom(5);

        String scores = loadScores();
        Label scoresLabel = new Label(scores, labelStyle);
        scoresLabel.setWrap(true);

        ScrollPane scrollPane = new ScrollPane(scoresLabel, skin);
        scrollPane.setFadeScrollBars(false);

        table.add(scrollPane).width(500).height(300);
        getContentTable().add(table);

        TextButton closeButton = new TextButton("Zamknij", buttonStyle);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Można tutaj dodać logikę po naciśnięciu przycisku
                hide();
            }
        });

        button(closeButton);
    }

    private String loadScores() {
        FileHandle file = Gdx.files.local("game_history.csv");
        if (!file.exists()) {
            return "Brak zapisanych wyników.";
        }
        return file.readString();
    }
}
