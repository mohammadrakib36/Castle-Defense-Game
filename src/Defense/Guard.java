package Defense;

import Enemy.Enemy;

public class Guard extends DefenseHandler {

    @Override
    public void defend(Enemy enemy) {

        enemy.takeDamage(20);

        if (enemy.isDead()) {
            return;
        }

        if (next != null) {
            next.defend(enemy);
        }
    }
}