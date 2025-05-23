package com.epochs.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Tower {
    public Vector2 position;
    private static float speed = 1.0f;
    private float lastShotTime = 0;
    public static float range = 150;
    private static int damage = 1;

    public Tower(Vector2 position) {
        this.position = position;
    }

    public void shoot(Array<Enemy> enemies, Array<Bullet> bullets) {
        float currentTime = com.badlogic.gdx.utils.TimeUtils.nanoTime() / 1000000000.0f;

        if (currentTime - lastShotTime >= speed) {
            for (Enemy enemy : enemies) {
                if (position.dst(enemy.position) <= range) {
                    Vector2 targetPosition = new Vector2(enemy.position.x, enemy.position.y + 20);
                    bullets.add(new Bullet(new Vector2(position.x + 30, position.y + 50), targetPosition, enemy.velocity, damage, false));
                    lastShotTime = currentTime;
                    break;
                }
            }
        }
    }

    public static void increaseDamage(int amount) {
        damage += amount;
    }

    public static void increaseSpeed(float amount) {
        speed = Math.max(0.1f, speed - amount);
    }

    public static void reset() {
        speed = 1.0f;
        damage = 1;
        range = 150;
    }
}
