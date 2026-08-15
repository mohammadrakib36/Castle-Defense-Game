package Enemy;

import java.awt.*;

public class Orc extends Enemy {

    public Orc() {
        super("Enemy.Orc", 70, 8, 1);
    }

    private Orc(int hp, int damage, int speed) {
        super("Enemy.Orc", hp, damage, speed);
    }

    @Override
    public Enemy cloneEnemy() {
        return new Orc(hp, damage, speed);
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(new Color(100, 130, 50));
        g.fillRect(x, y, 50, 50);

        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 12, 7, 7);
        g.fillOval(x + 33, y + 12, 7, 7);

        g.drawString(name, x, y - 8);

        g.setColor(Color.RED);
        g.fillRect(x, y - 5, 50, 4);

        g.setColor(Color.GREEN);
        g.fillRect(x, y - 5,
                (50 * hp) / maxHp, 4);
    }
}