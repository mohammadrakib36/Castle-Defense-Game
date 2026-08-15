package Enemy;

import java.awt.*;

public abstract class Enemy implements EnemyPrototype {

    protected int x;
    protected int y;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected int speed;
    protected String name;

    public Enemy(String name, int hp, int damage, int speed) {

        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.speed = speed;

        x = 80;
        y = 250;
    }

    public void move() {
        x += speed;
    }

    public void takeDamage(int damage) {
        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public abstract void draw(Graphics2D g);
}