package Defense;

import Enemy.Enemy;

public class Cannon extends DefenseHandler {

    @Override
    public void defend(Enemy enemy) {

        enemy.takeDamage(25);

        if (enemy.isDead()) {
            return;
        }

        if (next != null) {
            next.defend(enemy);
        }
    }
}