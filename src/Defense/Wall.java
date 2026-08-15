package Defense;

import Enemy.Enemy;

public class Wall extends DefenseHandler {

    @Override
    public void defend(Enemy enemy) {

        enemy.takeDamage(10);

        if (enemy.isDead()) {
            return;
        }

        if (next != null) {
            next.defend(enemy);
        }
    }
}