package com.epochs.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.epochs.game.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsDialog extends Dialog {

    private final Preferences preferences;
    private static final String RESOLUTION_KEY = "selectedResolution";

    public SettingsDialog(Skin skin, Main game) {
        super("Ustawienia", skin);

        // Ładowanie czcionki i zmniejszenie jej skali
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
        font.getData().setScale(0.8f);

        preferences = Gdx.app.getPreferences("settings");

        getTitleTable().padBottom(20);
        getContentTable().pad(10);

        Label resolutionLabel = new Label("Rozdzielczość:", skin);
        resolutionLabel.setStyle(new Label.LabelStyle(font, resolutionLabel.getStyle().fontColor)); // Czcionka
        SelectBox<String> resolutionBox = new SelectBox<>(skin);

        List<String> availableResolutions = getAvailableResolutions();

        resolutionBox.setItems(availableResolutions.toArray(new String[0]));

        String savedResolution = preferences.getString(RESOLUTION_KEY, "1280x720");
        resolutionBox.setSelected(savedResolution);

        resolutionBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedResolution = resolutionBox.getSelected();
                String[] dims = selectedResolution.split("x");
                int width = Integer.parseInt(dims[0]);
                int height = Integer.parseInt(dims[1]);

                preferences.putString(RESOLUTION_KEY, selectedResolution);
                preferences.flush();

                Gdx.graphics.setWindowedMode(width, height);
            }
        });

        Label fullscreenLabel = new Label("Tryb pełnoekranowy:", skin);
        fullscreenLabel.setStyle(new Label.LabelStyle(font, fullscreenLabel.getStyle().fontColor)); // Czcionka

        CheckBox fullscreenCheckBox = new CheckBox("", skin);
        fullscreenCheckBox.getLabel().setStyle(new Label.LabelStyle(font, fullscreenCheckBox.getLabel().getStyle().fontColor)); // Czcionka

        fullscreenCheckBox.setChecked(Gdx.graphics.isFullscreen());

        fullscreenCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (fullscreenCheckBox.isChecked()) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

                    resolutionLabel.setVisible(false);
                    resolutionBox.setVisible(false);
                } else {
                    String selectedResolution = resolutionBox.getSelected();
                    String[] dims = selectedResolution.split("x");
                    int width = Integer.parseInt(dims[0]);
                    int height = Integer.parseInt(dims[1]);

                    Gdx.graphics.setWindowedMode(width, height);

                    resolutionLabel.setVisible(true);
                    resolutionBox.setVisible(true);
                }
            }
        });

        // Głośność
        Label volumeLabel = new Label("Głośność:", skin);
        volumeLabel.setStyle(new Label.LabelStyle(font, volumeLabel.getStyle().fontColor)); // Czcionka
        Slider volumeSlider = new Slider(0, 1, 0.1f, false, skin);
        volumeSlider.setValue(game.backgroundMusic.getVolume());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.backgroundMusic.setVolume(volumeSlider.getValue());
            }
        });

        getContentTable().add(resolutionLabel).left().pad(10);
        getContentTable().add(resolutionBox).pad(10);
        getContentTable().row();
        getContentTable().add(fullscreenLabel).left().pad(10);
        getContentTable().add(fullscreenCheckBox).pad(10);
        getContentTable().row();
        getContentTable().add(volumeLabel).left().pad(10);
        getContentTable().add(volumeSlider).pad(10);

        TextButton closeButton = new TextButton("Zamknij", skin);
        closeButton.getLabel().setStyle(new Label.LabelStyle(font, closeButton.getLabel().getStyle().fontColor)); // Czcionka
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hide();
            }
        });

        getButtonTable().add(closeButton).fillX().padTop(10);
        setSize(300, 300);

    }

    public void display(Stage stage) {
        super.show(stage);
    }

    @Override
    protected void result(Object object) {
        super.result(object);
    }

    private List<String> getAvailableResolutions() {
        List<String> resolutions = new ArrayList<>();
        DisplayMode[] modes = Gdx.graphics.getDisplayModes();

        int minWidth = 1280;
        int minHeight = 720;

        List<String> excludedResolutions = Arrays.asList("1360x768", "1280x768", "1280x800", "1440x900", "1600x900"); // Dodaj tutaj inne rozdzielczości, które chcesz wykluczyć

        for (DisplayMode mode : modes) {
            int width = mode.width;
            int height = mode.height;

            String resolution = width + "x" + height;

            if (width >= minWidth && height >= minHeight && !excludedResolutions.contains(resolution)) {
                if (!resolutions.contains(resolution)) {
                    resolutions.add(resolution);
                }
            }
        }

        resolutions.sort((res1, res2) -> {
            int width1 = Integer.parseInt(res1.split("x")[0]);
            int width2 = Integer.parseInt(res2.split("x")[0]);
            return Integer.compare(width1, width2);
        });

        return resolutions;
    }



}
