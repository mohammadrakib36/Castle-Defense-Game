package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {

    private final GameManager gameManager;

    private final ArrayList<Enemy> enemies;

    private final Timer timer;

    private final JButton startButton;
    private final JButton upgradeButton;
    private final JButton nextWaveButton;

    private DefenseHandler defenseChain;

    private Tower tower;

    private boolean waveRunning;
    private boolean gameFinished;

    public GamePanel() {

        gameManager = GameManager.getInstance();

        enemies = new ArrayList<>();

        setLayout(null);
        setBackground(new Color(180, 220, 250));

        startButton = new JButton("Start Wave");
        upgradeButton = new JButton("Upgrade Tower.Tower");
        nextWaveButton = new JButton("Next Wave");

        startButton.setBounds(30, 560, 130, 35);
        upgradeButton.setBounds(180, 560, 150, 35);
        nextWaveButton.setBounds(350, 560, 130, 35);

        add(startButton);
        add(upgradeButton);
        add(nextWaveButton);

        startButton.addActionListener(this);
        upgradeButton.addActionListener(this);
        nextWaveButton.addActionListener(this);

        createDefenseChain();

        tower = new BasicTower();

        waveRunning = false;
        gameFinished = false;

        timer = new Timer(30, this);
        timer.start();
    }

    private void createDefenseChain() {

        DefenseHandler wall = new Wall();
        DefenseHandler archer = new ArcherTower();
        DefenseHandler cannon = new Cannon();
        DefenseHandler guard = new Guard();

        wall.setNext(archer);
        archer.setNext(cannon);
        cannon.setNext(guard);

        defenseChain = wall;
    }

    private void startWave() {

        if (gameManager.isGameOver()) {
            return;
        }

        enemies.clear();

        int wave = gameManager.getWave();

        if (wave == 1) {

            Enemy original = EnemyFactory.createEnemy("Enemy.Goblin");

            enemies.add(original.cloneEnemy());
            enemies.add(original.cloneEnemy());

        } else if (wave == 2) {

            Enemy goblin = EnemyFactory.createEnemy("Enemy.Goblin");
            Enemy orc = EnemyFactory.createEnemy("Enemy.Orc");

            enemies.add(goblin.cloneEnemy());
            enemies.add(goblin.cloneEnemy());
            enemies.add(orc.cloneEnemy());

        } else {

            Enemy orc = EnemyFactory.createEnemy("Enemy.Orc");
            Enemy dragon = EnemyFactory.createEnemy("Enemy.Dragon");

            enemies.add(orc.cloneEnemy());
            enemies.add(dragon.cloneEnemy());
        }

        int startX = 80;

        for (Enemy enemy : enemies) {
            enemy.x = startX;
            enemy.y = 250;
            startX -= 100;
        }

        waveRunning = true;
        gameFinished = false;
    }

    private void upgradeTower() {

        if (tower.getName().contains("Damage Upgrade")) {
            return;
        }

        tower = new DamageUpgrade(tower);

        JOptionPane.showMessageDialog(
                this,
                "Tower.Tower upgraded!\nDamage: " + tower.getDamage()
        );
    }

    private void updateGame() {

        if (!waveRunning || gameFinished) {
            repaint();
            return;
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {

            Enemy enemy = enemies.get(i);

            enemy.move();

            if (enemy.getX() >= 700) {

                gameManager.damageCastle(enemy.getDamage());

                enemies.remove(i);

                if (gameManager.isGameOver()) {

                    waveRunning = false;
                    gameFinished = true;

                    JOptionPane.showMessageDialog(
                            this,
                            "GAME OVER!\nFinal Score: "
                                    + gameManager.getScore()
                    );
                }

                continue;
            }

            if (enemy.getX() >= 600) {

                defenseChain.defend(enemy);

                if (enemy.isDead()) {

                    gameManager.addScore(
                            getScore(enemy)
                    );

                    enemies.remove(i);
                }
            }
        }

        if (enemies.isEmpty() && waveRunning) {

            waveRunning = false;

            if (gameManager.getWave() >= 3) {

                gameFinished = true;

                JOptionPane.showMessageDialog(
                        this,
                        "YOU WIN!\nFinal Score: "
                                + gameManager.getScore()
                );

            }
        }

        repaint();
    }

    private int getScore(Enemy enemy) {

        if (enemy instanceof Dragon) {
            return 100;
        }

        if (enemy instanceof Orc) {
            return 50;
        }

        return 20;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        drawGame(g2);
    }

    private void drawGame(Graphics2D g) {

        // Ground

        g.setColor(new Color(100, 180, 90));
        g.fillRect(0, 150, 1000, 390);

        // Road

        g.setColor(new Color(210, 190, 150));
        g.fillRect(0, 225, 750, 100);

        // Castle

        g.setColor(new Color(130, 130, 130));
        g.fillRect(750, 170, 170, 250);

        g.setColor(new Color(90, 90, 90));
        g.fillRect(780, 130, 45, 100);
        g.fillRect(845, 130, 45, 100);

        g.setColor(new Color(160, 50, 50));
        g.fillRect(815, 230, 45, 190);

        g.setColor(Color.BLACK);
        g.drawString("CASTLE", 805, 155);

        // Defense.Wall

        g.setColor(new Color(150, 150, 150));
        g.fillRect(650, 260, 40, 120);

        g.setColor(Color.BLACK);
        g.drawString("WALL", 650, 400);

        // Archer Tower.Tower

        g.setColor(new Color(100, 70, 40));
        g.fillRect(560, 270, 50, 110);

        g.setColor(Color.BLACK);
        g.drawString("ARCHER", 555, 400);

        // Defense.Cannon

        g.setColor(new Color(70, 70, 70));
        g.fillRect(470, 300, 70, 40);

        g.setColor(Color.BLACK);
        g.drawString("CANNON", 470, 360);

        // Enemies

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // Top information

        g.setColor(Color.BLACK);

        g.setFont(new Font(
                "Arial",
                Font.BOLD,
                18
        ));

        g.drawString(
                "Castle HP: " +
                        gameManager.getCastleHealth(),
                30,
                35
        );

        g.drawString(
                "Score: " +
                        gameManager.getScore(),
                250,
                35
        );

        g.drawString(
                "Wave: " +
                        gameManager.getWave(),
                450,
                35
        );

        g.drawString(
                "Tower.Tower Damage: " +
                        tower.getDamage(),
                650,
                35
        );

        // Title

        g.setFont(new Font(
                "Arial",
                Font.BOLD,
                28
        ));

        g.drawString(
                "CASTLE DEFENSE",
                350,
                85
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            startWave();
        }

        if (e.getSource() == upgradeButton) {

            upgradeTower();
        }

        if (e.getSource() == nextWaveButton) {

            if (!waveRunning &&
                    gameManager.getWave() < 3 &&
                    !gameManager.isGameOver()) {

                gameManager.nextWave();

                startWave();
            }
        }

        updateGame();
    }
}