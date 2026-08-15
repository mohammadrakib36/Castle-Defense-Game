package Enemy;

import java.awt.*;

public class Dragon extends Enemy {

    public Dragon() {
        super("Enemy.Dragon", 120, 15, 1);
    }

    private Dragon(int hp, int damage, int speed) {
        super("Enemy.Dragon", hp, damage, speed);
    }

    @Override
    public Enemy cloneEnemy() {
        return new Dragon(hp, damage, speed);
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(new Color(180, 50, 50));
        g.fillOval(x, y, 70, 55);

        g.setColor(Color.BLACK);
        g.fillOval(x + 15, y + 15, 8, 8);
        g.fillOval(x + 47, y + 15, 8, 8);

        g.drawString(name, x + 10, y - 8);

        g.setColor(Color.RED);
        g.fillRect(x, y - 5, 70, 5);

        g.setColor(Color.GREEN);
        g.fillRect(x, y - 5,
                (70 * hp) / maxHp, 5);
    }
}