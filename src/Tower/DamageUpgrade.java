package Tower;

public class DamageUpgrade extends TowerDecorator {

    public DamageUpgrade(Tower tower) {
        super(tower);
    }

    @Override
    public int getDamage() {
        return tower.getDamage() + 15;
    }

    @Override
    public String getName() {
        return tower.getName() + " + Damage Upgrade";
    }
}