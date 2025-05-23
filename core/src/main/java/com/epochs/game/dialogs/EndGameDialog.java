package com.epochs.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epochs.game.screens.MenuScreen;

import java.io.FileWriter;
import java.io.IOException;

public class EndGameDialog extends Dialog {

    public EndGameDialog(Skin skin, MenuScreen game, int defeatedEnemies, String timePlayed, int type) {
        super("", skin);
        setSize(800, 600);

        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2f
        );

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
        font.getData().setScale(0.7f);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.newDrawable("default-round");
        buttonStyle.down = skin.newDrawable("default-round-down");

        String message = switch (type) {
            case 1 -> "Przegrałeś!\n";
            case 2 -> "Wygrałeś!!\n";
            case 3 -> "Walczyłeś!\n";
            default -> "Nieznany wynik.\n";
        };


        message = message + "Zabici wrogowie: " + defeatedEnemies + "\n" +
            "Czas gry: " + timePlayed;

        Label defeatLabel = new Label(message, labelStyle);

        getContentTable().add(defeatLabel).pad(20);

        TextButton closeButton = new TextButton("Zamknij", buttonStyle);

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });
        button(closeButton);

        getButtonTable().row();
        getButtonTable().add(closeButton).padTop(10).fillX().uniformX();

        getButtonTable().defaults().padTop(20);

        saveGameResultToCSV(type, defeatedEnemies, timePlayed);
    }

    private void saveGameResultToCSV(int type, int defeatedEnemies, String timePlayed) {
        String filePath = Gdx.files.local("game_history.csv").file().getAbsolutePath();
        String resultType = switch (type) {
            case 1 -> "Przegrana";
            case 2 -> "Wygrana";
            case 3 -> "Walka";
            default -> "Nieznany";
        };

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(resultType)
                .append(", ")
                .append(String.valueOf(defeatedEnemies))
                .append(", ")
                .append(timePlayed)
                .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
