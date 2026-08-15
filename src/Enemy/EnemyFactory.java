package Enemy;

public class EnemyFactory {

    public static Enemy createEnemy(String type) {

        if (type.equalsIgnoreCase("Enemy.Goblin")) {
            return new Goblin();
        }

        if (type.equalsIgnoreCase("Enemy.Orc")) {
            return new Orc();
        }

        if (type.equalsIgnoreCase("Enemy.Dragon")) {
            return new Dragon();
        }

        return null;
    }
}