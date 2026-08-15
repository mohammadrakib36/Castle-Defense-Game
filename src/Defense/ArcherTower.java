package Defense;

import Enemy.Enemy;

public class ArcherTower extends DefenseHandler {

    @Override
    public void defend(Enemy enemy) {

        enemy.takeDamage(15);

        if (enemy.isDead()) {
            return;
        }

        if (next != null) {
            next.defend(enemy);
        }
    }
}