package com.epochs.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    public Vector2 position;
    public Vector2 velocity;
    private static final float BASE_SPEED = 50;
    public static int baseHealth = 2; // Początkowe HP
    private int health;
    private static final int ENEMY_SIZE = 32;

    public Enemy(Vector2 position) {
        this.position = new Vector2(position);
        this.health = baseHealth;
        this.velocity = new Vector2(BASE_SPEED,0);
    }

    public void update(float elapsedTime) {
        position.x += BASE_SPEED * elapsedTime;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, ENEMY_SIZE, ENEMY_SIZE);
    }

    public void hit(int damage) {
        health -= damage;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public static void increaseGlobalHP(int amount) {
        baseHealth += amount;
    }

    public static void reset() {
        baseHealth = 2;
    }
}
