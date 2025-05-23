package com.epochs.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.epochs.game.screens.MenuScreen;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CreditsDialog extends Dialog {

    public CreditsDialog(Skin skin, MenuScreen menuScreen) {
        super("", skin);

        setSize(800, 600);

        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2f
        );

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
        font.getData().setScale(0.7f);

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;

        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.newDrawable("default-round");
        buttonStyle.down = skin.newDrawable("default-round-down");

        Table contentTable = getContentTable();
        contentTable.align(Align.top);

        Table gameTable = new Table();
        Image gameLogo = new Image(new Texture("img/gamelogo.png"));
        Label gameDescription = new Label(
            "Epochs of Battle Copyright © 2024 - 2025\n Michał Łukaszczyk \nGra towerDefence gdzie twoim celem jest ochrona królestwa.",
            labelStyle
        );
        gameDescription.setWrap(true);
        gameDescription.setAlignment(Align.center);

        gameTable.add(gameLogo).padRight(10).align(Align.center);
        gameTable.add(gameDescription).width(600).align(Align.left);
        contentTable.add(gameTable).padBottom(30).center();
        contentTable.row();

        Table authorTable = new Table();
        Image myLogo = new Image(new Texture("img/mylogo.png"));
        Image githubLogo = new Image(new Texture("img/github.png"));

        Label authorInfo = new Label(
            "Autor:\n Michał Matian Łukaszczyk\nProgramista, Tester, Grafik",
            labelStyle
        );
        authorInfo.setAlignment(Align.center);

        authorTable.top().left();
        authorTable.add(myLogo)
            .width(128).height(128)
            .padRight(20)
            .align(Align.center);
        authorTable.add(authorInfo)
            .width(500)
            .padRight(20)
            .align(Align.center)
            .expandX();
        authorTable.add(githubLogo)
            .width(128).height(128)
            .padLeft(20)
            .align(Align.center);

        contentTable.add(authorTable).padBottom(30).center().expandX();  // Ustawienie, aby tabela zajmowała całą szerokość
        contentTable.row();



        Table engineTable = new Table();
        Label engineInfo = new Label("Silnik gry: libGDX", labelStyle);
        Image libgdxLogo = new Image(new Texture("img/libgdx.png"));

        engineTable.add(engineInfo).padRight(20).align(Align.center);
        engineTable.add(libgdxLogo).width(100).height(50).align(Align.center);
        contentTable.add(engineTable).padBottom(30).center();
        contentTable.row();

        Label assetsInfo = new Label(
            """
                Użyte assety:\s
                - Animated pixel art skeleton - AstroBob
                - Archer Towers Pixel Art - Free Game Assets (GUI, Sprite, Tilesets)""",
            labelStyle
        );
        assetsInfo.setWrap(true);
        assetsInfo.setAlignment(Align.center);
        contentTable.add(assetsInfo).width(700).padBottom(30).center();
        contentTable.row();

        TextButton closeButton = new TextButton("Zamknij", buttonStyle);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });
        button(closeButton);

        addListener(new DialogListener(menuScreen));

        githubLogo.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                return true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });
        githubLogo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("CreditsDialog", "Clicked GitHub logo");
                Gdx.net.openURI("https://github.com/matiqn18");
            }
        });
    }

    private static class DialogListener extends ClickListener {

        public DialogListener(MenuScreen menuScreen) {
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
        }
    }
}
