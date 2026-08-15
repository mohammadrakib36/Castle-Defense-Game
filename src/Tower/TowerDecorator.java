package Tower;

public abstract class TowerDecorator implements Tower {

    protected Tower tower;

    public TowerDecorator(Tower tower) {
        this.tower = tower;
    }

    @Override
    public int getDamage() {
        return tower.getDamage();
    }

    @Override
    public String getName() {
        return tower.getName();
    }
}