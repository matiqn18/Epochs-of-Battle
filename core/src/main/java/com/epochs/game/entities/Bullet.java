package com.epochs.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    public Vector2 position;
    public Vector2 velocity;
    private static final float SPEED = 300;
    private final Sprite bulletSprite;
    private final int damage;

    public Bullet(Vector2 position, Vector2 target, Vector2 enemyVelocity, int damage, boolean b) {
        if (b) {
            this.velocity = new Vector2(0, -SPEED);
        }
        else {
            Vector2 predictedTarget = new Vector2(target).add(enemyVelocity.scl(0.5f));
            this.velocity = new Vector2(predictedTarget).sub(position).nor().scl(SPEED);
        }
        this.position = new Vector2(position);
        bulletSprite = new Sprite(Assets.bulletTexture);
        float angle = velocity.angleDeg();
        bulletSprite.setRotation(angle);
        this.damage = damage;
    }

    public void update() {
        position.add(velocity.x * Gdx.graphics.getDeltaTime(), velocity.y * Gdx.graphics.getDeltaTime());
        bulletSprite.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, bulletSprite.getWidth(), bulletSprite.getHeight());
    }

    public void draw(SpriteBatch batch) {
        bulletSprite.draw(batch);
    }

    public int getDamage() {
        return damage;
    }
}
