package Defense;

import Enemy.Enemy;

public abstract class DefenseHandler {

    protected DefenseHandler next;

    public void setNext(DefenseHandler next) {
        this.next = next;
    }

    public abstract void defend(Enemy enemy);
}