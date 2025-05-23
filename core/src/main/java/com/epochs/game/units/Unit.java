//Deprecated

//package com.epochs.game.units;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Rectangle;
//
//public class Unit {
//    // Statystyki jednostki
//    private final String name;
//    private final int cost;
//    private final float productionTime;
//    private final int damage;
//    private final int maxHp;
//    private final int expGainKill;
//    private final int goldGainKill;
//    private final int expLossDeath;
//
//    // Aktualny stan jednostki
//    private int currentHp;
//    private boolean isDead;
//    private float deathTimer; // Licznik czasu po śmierci
//
//    // Wizualne reprezentacje jednostki
//    private final Rectangle bounds; // Prostokąt dla pozycji i kolizji
//    private final Color color;      // Kolor jednostki
//    private final Texture texture;  // Opcjonalna tekstura jednostki
//
//    private final float attackRange; // Zasięg ataku (dla jednostek dystansowych)
//    private float attackCooldown;    // Czas między atakami
//    private float attackTimer = 0;   // Licznik czasu między atakami
//
//    // Konstruktor: dodaj zasięg ataku i cooldown
//    public Unit(String name, int cost, float productionTime, int damage, int maxHp,
//                int expGainKill, int goldGainKill, int expLossDeath,
//                float x, float y, float width, float height, Color color, float attackRange, float attackCooldown) {
//        this.name = name;
//        this.cost = cost;
//        this.productionTime = productionTime;
//        this.damage = damage;
//        this.maxHp = maxHp;
//        this.expGainKill = expGainKill;
//        this.goldGainKill = goldGainKill;
//        this.expLossDeath = expLossDeath;
//
//        this.currentHp = maxHp;
//        this.isDead = false;
//        this.deathTimer = 0;
//
//        this.bounds = new Rectangle(x, y, width, height);
//        this.color = color;
//        this.texture = null;  // Może być przypisana tekstura, jeśli chcesz
//
//        this.attackRange = attackRange;
//        this.attackCooldown = attackCooldown;
//    }
//
//    // Metoda aktualizacji
//    public void update(float delta) {
//        if (isDead) {
//            deathTimer += delta;
//        } else {
//            attackTimer += delta;
//        }
//    }
//
//    // Sprawdzenie, czy jednostka może zaatakować
//    public boolean canAttack() {
//        return attackTimer >= attackCooldown;
//    }
//
//    // Reset licznika ataku po wykonaniu ataku
//    public void resetAttackCooldown() {
//        attackTimer = 0;
//    }
//
//    // Getter dla zasięgu
//    public float getAttackRange() {
//        return attackRange;
//    }
//
//    // Rysowanie jednostki
//    public void render(SpriteBatch batch) {
//        if (isDead && deathTimer > 1) return; // Nie rysujemy, jeśli zniknęła
//
//        batch.setColor(isDead ? Color.RED : color); // Zmiana koloru na czerwony, jeśli martwa
//        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);  // Możesz tu użyć własnej tekstury, jeśli chcesz
//    }
//
//    // Obsługa obrażeń
//    public void takeDamage(int amount) {
//        if (isDead) return;
//
//        currentHp -= amount;
//        if (currentHp <= 0) {
//            die();
//        }
//    }
//
//    // Logika śmierci
//    private void die() {
//        isDead = true;
//        deathTimer = 0; // Reset timera śmierci
//    }
//
//    // Gettery i pomocnicze metody
//    public boolean isDead() {
//        return isDead;
//    }
//
//    public Rectangle getBounds() {
//        return bounds;
//    }
//
//    public int getDamage() {
//        return damage;
//    }
//
//    public int getExpGainKill() {
//        return expGainKill;
//    }
//
//    public int getGoldGainKill() {
//        return goldGainKill;
//    }
//}
