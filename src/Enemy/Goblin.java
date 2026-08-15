package Enemy;

import java.awt.*;

public class Goblin extends Enemy {

    public Goblin() {
        super("Enemy.Goblin", 40, 5, 2);
    }

    private Goblin(int hp, int damage, int speed) {
        super("Enemy.Goblin", hp, damage, speed);
    }

    @Override
    public Enemy cloneEnemy() {
        return new Goblin(hp, damage, speed);
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(new Color(50, 180, 70));
        g.fillOval(x, y, 45, 45);

        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 12, 6, 6);
        g.fillOval(x + 29, y + 12, 6, 6);

        g.drawString(name, x - 5, y - 8);

        g.setColor(Color.RED);
        g.fillRect(x, y - 5, 45, 4);

        g.setColor(Color.GREEN);
        g.fillRect(x, y - 5,
                (45 * hp) / maxHp, 4);
    }
}