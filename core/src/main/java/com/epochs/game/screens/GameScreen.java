package com.epochs.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.epochs.game.Main;
import com.epochs.game.entities.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen {
    private static final int UI_HEIGHT = 40;
    SpriteBatch batch;
    Texture mapTexture;
    public static int gold = 100;
    boolean gameOver = false;
    boolean victory = false;
    long startTime;
    private boolean isPaused = false;
    private final Main game;
    BitmapFont font;
    private Stage stage;
    private int defeatedEnemies = 0;


    Array<Enemy> enemies;
    private Animation<TextureRegion> enemyAnimation;
    long lastSpawnTime;
    float spawnInterval = 2500;
    private float stateTime;

    Array<Tower> towers;
    private Animation<TextureRegion> towerAnimation;
    private float towerStateTime;

    Array<Bullet> bullets;


    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/czcionka.fnt"));
        font.getData().setScale(0.5f);
        mapTexture = Assets.mapTexture;
        enemies = new Array<>();
        towers = new Array<>();
        bullets = new Array<>();
        spawnEnemy();
        startTime = TimeUtils.millis();

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.getDrawable("default-round");
        buttonStyle.down = skin.getDrawable("default-round-down");

        Texture enemySheet = new Texture(Gdx.files.internal("enemy_animation.png"));
        TextureRegion[][] tmpFrames = TextureRegion.split(enemySheet, 64, 64);
        TextureRegion[] enemyFrames = new TextureRegion[tmpFrames[0].length];
        System.arraycopy(tmpFrames[0], 0, enemyFrames, 0, tmpFrames[0].length);

        enemyAnimation = new Animation<>(0.1f, enemyFrames);
        stateTime = 0f;

        Texture towerSheet = new Texture(Gdx.files.internal("tower_animation.png"));
        TextureRegion[][] towerFrames = TextureRegion.split(towerSheet, 70, towerSheet.getHeight());
        TextureRegion[] towerAnimationFrames = new TextureRegion[6];
        System.arraycopy(towerFrames[0], 0, towerAnimationFrames, 0, 6);

        towerAnimation = new Animation<>(0.1f, towerAnimationFrames);
        towerStateTime = 0f;


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                stateTime += Gdx.graphics.getDeltaTime();
            }
        }, 0, 0.01f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                towerStateTime += Gdx.graphics.getDeltaTime();
            }
        }, 0, 0.04f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Enemy.increaseGlobalHP(1);
            }
        }, 30, 30);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextButton pauseButton = new TextButton("Pauza", buttonStyle);
        pauseButton.setPosition(Gdx.graphics.getWidth() - pauseButton.getWidth() - 10,
            Gdx.graphics.getHeight() - pauseButton.getHeight() - 10);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                togglePause();
            }
        });

        TextButton defeatButton = new TextButton("Zakoncz gre", buttonStyle);
        defeatButton.setPosition(Gdx.graphics.getWidth() - pauseButton.getWidth() - 120,
            Gdx.graphics.getHeight() - pauseButton.getHeight() - 10);
        defeatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                endGame();
            }
        });

        TextButton upgradeRangeButton = new TextButton("Zwieksz zasieg (150 zlota)", buttonStyle);
        upgradeRangeButton.setPosition(Gdx.graphics.getWidth() - defeatButton.getWidth() - 285,
            Gdx.graphics.getHeight() - upgradeRangeButton.getHeight() - 10);
        upgradeRangeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gold >= 150) {
                    gold -= 150;
                    Tower.range += 20;
                }

            }
        });

        TextButton upgradeDamageButton = new TextButton("Zwiększ obrażenia (150 złota)", buttonStyle);
        upgradeDamageButton.setPosition(Gdx.graphics.getWidth() - 600,
            Gdx.graphics.getHeight() - upgradeDamageButton.getHeight() - 10);
        upgradeDamageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gold >= 150) {
                    gold -= 150;
                    Tower.increaseDamage(1);
                }
            }
        });

        TextButton superPowerButton = new TextButton("Super Moc (250 złota)", buttonStyle);
        superPowerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gold >= 250) {
                    gold -= 250;
                    activateSuperPower();
                }

            }
        });

        TextButton speedButton = new TextButton("Szybsze strzały (150 złota)", buttonStyle);
        speedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gold >= 150) {
                    gold -= 150;
                    Tower.increaseSpeed(0.1f);
                }
            }
        });


        Table buttonTable = new Table();
        buttonTable.top().right();
        buttonTable.setWidth(Gdx.graphics.getWidth());
        buttonTable.setHeight(Gdx.graphics.getHeight());

        buttonTable.add(superPowerButton).height(40).padRight(10).padTop(10);
        buttonTable.add(speedButton).height(40).padRight(10).padTop(10);
        buttonTable.add(upgradeRangeButton).height(40).padRight(10).padTop(10);
        buttonTable.add(upgradeDamageButton).height(40).padRight(10).padTop(10);
        buttonTable.add(pauseButton).height(40).padRight(10).padTop(10);
        buttonTable.add(defeatButton).height(40).padRight(10).padTop(10);


        stage.addActor(buttonTable);
    }

    @Override
    public void render(float delta) {
        if (isPaused) {
            batch.begin();
            font.getData().setScale(0.8f);
            font.setColor(Color.RED);
            font.draw(batch, "Gra wstrzymana", (float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2);
            font.setColor(Color.WHITE);
            font.getData().setScale(0.5f);

            batch.end();
            return;
        }

        batch.begin();
        batch.draw(mapTexture, 0, 0);

        for (Tower tower : towers) {
            TextureRegion currentTowerFrame = towerAnimation.getKeyFrame(towerStateTime, true);
            batch.draw(currentTowerFrame, tower.position.x, tower.position.y);
            tower.shoot(enemies, bullets);
        }

        for (Enemy enemy : enemies) {
            TextureRegion currentFrame = enemyAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, enemy.position.x, enemy.position.y);
            enemy.update(delta);
            if (enemy.position.x > Gdx.graphics.getWidth()) {
                gameOver = true;
            }
        }

        for (Bullet bullet : bullets) {
            bullet.update();
            bullet.draw(batch);
            checkBulletCollision(bullet);
        }

        font.getData().setScale(0.8f);
        font.draw(batch, "Zloto: " + gold, 10, Gdx.graphics.getHeight() - 10);
        String timePlay = String.valueOf(TimeUtils.timeSinceMillis(startTime) / 1000);
        font.draw(batch, "Czas: " + timePlay + " s", 10, Gdx.graphics.getHeight() - 35);

        font.getData().setScale(0.5f);
        batch.end();

        if (TimeUtils.millis() - lastSpawnTime > spawnInterval) {
            spawnEnemy();
            spawnInterval *= 0.98f;
        }

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            float adjustedX = touchPos.x - (float) 70 / 2;
            float adjustedY = touchPos.y;

            Vector2 adjustedPosition = new Vector2(adjustedX, adjustedY);

            if (gold >= 100 && isTowerPositionValid(adjustedPosition)) {
                towers.add(new Tower(adjustedPosition));
                gold -= 100;
            }
        }


        if (!victory && TimeUtils.timeSinceMillis(startTime) >= 360_000) {
            victory = true;
            int defen = defeatedEnemies;
            timePlay = String.valueOf(TimeUtils.timeSinceMillis(startTime) / 1000);
            restart();
            game.setScreen(new MenuScreen(game,2, defen, timePlay)); // Przejście na ekran zwycięstwa
        }

        if (gameOver) {
            int defen = defeatedEnemies;
            timePlay = String.valueOf(TimeUtils.timeSinceMillis(startTime) / 1000);
            restart();
            game.setScreen(new MenuScreen(game,1, defen, timePlay));
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void restart() {
        Tower.reset();
        Enemy.reset();
        gold = 100;
        defeatedEnemies = 0;
        startTime = 0;



    }

    private void spawnEnemy() {
        enemies.add(new Enemy(new Vector2(0, 250)));
        lastSpawnTime = TimeUtils.millis();
    }

    private void checkBulletCollision(Bullet bullet) {
        for (Enemy enemy : enemies) {
            if (bullet.getBounds().overlaps(enemy.getBounds())) {
                enemy.hit(bullet.getDamage()); // Przekazujemy obrażenia z pocisku
                bullets.removeValue(bullet, false);
                if (enemy.isDestroyed()) {
                    enemies.removeValue(enemy, false);
                    gold += 20;
                    defeatedEnemies++;
                }
                break;
            }
        }
    }

    private boolean isTowerPositionValid(Vector2 newPosition) {
        float towerWidth = 60;
        float towerHeight = 111;

        if (newPosition.y + towerHeight > Gdx.graphics.getHeight() - UI_HEIGHT) {
            return false; // Blokada stawiania w obszarze UI
        }

        for (Tower tower : towers) {
            float existingX = tower.position.x;
            float existingY = tower.position.y;

            boolean overlapX = Math.abs(existingX - newPosition.x) < towerWidth;
            boolean overlapY = Math.abs(existingY - newPosition.y) < towerHeight;

            if (overlapX && overlapY) {
                return false;
            }
        }
        return true;
    }

    private void togglePause() {
        isPaused = !isPaused;
    }

    private void endGame() {
        int defen = defeatedEnemies;
        String timePlay = String.valueOf(TimeUtils.timeSinceMillis(startTime) / 1000);
        restart();

        game.setScreen(new MenuScreen(game,3, defen, timePlay));
    }

    private void activateSuperPower() {
        int bulletCount = 20; // Liczba pocisków

        for (int i = 0; i < bulletCount; i++) {
            float randomX = MathUtils.random(0, Gdx.graphics.getWidth());
            Vector2 bulletPosition = new Vector2(randomX, Gdx.graphics.getHeight());
            Vector2 direction = new Vector2(0, -1); // ↓↓↓

            bullets.add(new Bullet(bulletPosition, direction, direction,5, true));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
