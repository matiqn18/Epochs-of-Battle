package com.epochs.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.epochs.game.Main;
import com.epochs.game.screens.GameScreen;

public class StartGameDialog extends Dialog {

    public StartGameDialog(Skin skin, Main game) {
        super("Zasady", skin);

            BitmapFont customFont = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
            customFont.getData().setScale(0.6f);

            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = customFont;

            TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
            buttonStyle.font = customFont;
            buttonStyle.up = skin.getDrawable("default-round");
            buttonStyle.down = skin.getDrawable("default-round-down");

        Label rulesLabel = getLabel(labelStyle);

        TextButton startButton = new TextButton("Rozpocznij", buttonStyle);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        TextButton closeButton = new TextButton("Zamknij", buttonStyle);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hide();
            }
        });

        getContentTable().add(rulesLabel).width(600).pad(20).row();

        button(startButton);
        button(closeButton);
    }

    private static Label getLabel(Label.LabelStyle labelStyle) {
        Label rulesLabel = new Label("""
            1. Twoim celem jest obrona przed falami wrogów.
            2. Zbieraj złoto z poległych wrogów.
            3. Stawiaj wieże za złoto (100 złota każda).
            4. Ulepszaj obrażenia wież (150 za każde ulepszenie).
            5. Ulepszaj zasięg wież (150 za każde ulepszenie).
            6. Ulepszaj prędkość strzałów wież (150 za każde ulepszenie).
            7. Używaj mocy czarodzieja która przyzywa deszcz strzał (250 za przyzwanie).
            8. Przetrwaj jak najdłużej!""", labelStyle);
        rulesLabel.setWrap(true);
        rulesLabel.setAlignment(Align.center);
        return rulesLabel;
    }
}
