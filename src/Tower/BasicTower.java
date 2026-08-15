package Tower;

public class BasicTower implements Tower {

    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public String getName() {
        return "Basic Tower.Tower";
    }
}