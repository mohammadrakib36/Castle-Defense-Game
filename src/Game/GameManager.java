package Game;

public class GameManager {

    private static GameManager instance;

    private int score;
    private int castleHealth;
    private int wave;

    private GameManager() {
        score = 0;
        castleHealth = 100;
        wave = 1;
    }

    public static GameManager getInstance() {

        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public int getScore() {
        return score;
    }

    public int getCastleHealth() {
        return castleHealth;
    }

    public int getWave() {
        return wave;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public void damageCastle(int damage) {
        castleHealth -= damage;

        if (castleHealth < 0) {
            castleHealth = 0;
        }
    }

    public void nextWave() {
        wave++;
    }

    public boolean isGameOver() {
        return castleHealth <= 0;
    }
}