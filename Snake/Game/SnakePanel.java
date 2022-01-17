//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import Snake.GUI.MainMenu;
import Snake.GUI.SnakeScore;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {
    public SnakePanel() {
        GameProperties defaultProperties = new GameProperties();
        String mapColor = defaultProperties.getMapColor();

        snakePanelWidth = defaultProperties.getSnakePanelWidth();
        snakePanelHeight = defaultProperties.getSnakePanelHeight();
        setPreferredSize(new Dimension(snakePanelWidth, snakePanelHeight));

        if (mapColor.equalsIgnoreCase("soil")) {
            setBackground(new Color(92,49,13));
        } else if (mapColor.equalsIgnoreCase("pasture")) {
            setBackground(new Color(166, 201, 100));
        } else if (mapColor.equalsIgnoreCase("cornfield")) {
            setBackground(new Color(165,135,44));
        }
//        setBackground(new Color(193,154,107));
        unitSize = defaultProperties.getUnitSize();
        speed = defaultProperties.getSnakeSpeed();
        direction = Direction.RIGHT;
        setDirection(direction);
        bodyParts = 2;
        score = 0;
        setRows(snakePanelWidth / unitSize);
        setColumns(snakePanelHeight / unitSize);

        random = new Random();
        snakeModel = new SnakeModel();

        try {
            if ((snakePanelWidth == 600 && unitSize == 50) || (snakePanelWidth == 800 && unitSize == 50) || (snakePanelWidth == 1000 && unitSize == 50) || (snakePanelWidth == 1600 && unitSize == 50)){
                scoreFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize());
                messageFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize() * 2);
                questionFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize());
            } else {
                scoreFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize());
                messageFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize() * 4);
                questionFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont((float) getUnitSize() * 2);
            }

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(scoreFont);
            ge.registerFont(messageFont);
            ge.registerFont(questionFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setRunning(true);
    }

    protected void startGame() {
        timer = new Timer(getSpeed(), this);       // ez fogja diktalni milyen gyorsan fut a game
        timer.setInitialDelay(500);
        timer.start();
        newFoodPosition();                                  // letrehozunk egy uj almat
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void dispose() {
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();
    }

    private void draw(Graphics g) {
        if (isRunning()) {
            Graphics2D g2 = (Graphics2D) g;
            drawMap(g2, g);
            drawSnakeBody(g2);
            drawScore(g);
        } else {
            // Ha falat ertunk vagy utkozott a sajt testevel, akkor game over.
            gameOver(g);
        }
    }

    private void drawMap(Graphics2D graphics2D, Graphics g) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (i == 0) {
                    graphics2D.drawImage(snakeModel.getWall(), 0, j * getUnitSize(), getUnitSize(), getUnitSize(), this);
                } else if (j == 0) {
                    graphics2D.drawImage(snakeModel.getWall(), i * getUnitSize(), 0, getUnitSize(), getUnitSize(), this);
                } else if (i == getRows() - 1) {
                    graphics2D.drawImage(snakeModel.getWall(), i * getUnitSize(), j * getUnitSize(), getUnitSize(), getUnitSize(), this);
                } else if (j == getColumns() - 1) {
                    graphics2D.drawImage(snakeModel.getWall(), i * getUnitSize(), j * getUnitSize(), getUnitSize(), getUnitSize(), this);
                } else {
                    g.drawImage(snakeModel.getGravel(), i * getUnitSize(), j * getUnitSize(), getUnitSize(), getUnitSize(), this);
                }
            }
        }
        // Etel kirajzolasa
        graphics2D.drawImage(snakeModel.getFoodArray().get(foodIndex), getFoodX(), getFoodY(), getUnitSize(), getUnitSize(), this);
    }

    private void drawSnakeBody(Graphics2D graphics2D) {
        for (int i = 0; i < getBodyParts(); i++) {
            if (i == 0) { // kigyo feje
                switch (getDirection()) {
                    // L - balra, R - jobbra, U - felfele, D - lefele
                    case UP -> graphics2D.drawImage(snakeModel.getHeadUp(), snakeModel.getX()[i] - getUnitSize() / 3 / 2 + getUnitSize(), snakeModel.getY()[i] - getUnitSize() / 3 + getUnitSize(), getUnitSize() + getUnitSize() / 3, getUnitSize() + getUnitSize() / 3, this);
                    case DOWN -> graphics2D.drawImage(snakeModel.getHeadDown(), snakeModel.getX()[i] - getUnitSize() / 3 / 2 + getUnitSize(), snakeModel.getY()[i] + getUnitSize(), getUnitSize() + getUnitSize() / 3, getUnitSize() + getUnitSize() / 3, this);
                    case LEFT -> graphics2D.drawImage(snakeModel.getHeadLeft(), snakeModel.getX()[i] - getUnitSize() / 3 + getUnitSize(), snakeModel.getY()[i] - getUnitSize() / 3 / 2 + getUnitSize(), getUnitSize() + getUnitSize() / 3, getUnitSize() + getUnitSize() / 3, this);
                    case RIGHT -> graphics2D.drawImage(snakeModel.getHeadRight(), snakeModel.getX()[i] + getUnitSize(), snakeModel.getY()[i] - getUnitSize() / 3 / 2 + getUnitSize(), getUnitSize() + getUnitSize() / 3, getUnitSize() + getUnitSize() / 3, this);
                }
            } else {    // kigyo teste
                graphics2D.setPaint(new Color(42, 73, 24));
                graphics2D.setStroke(new BasicStroke(5.0f));
                graphics2D.drawImage(snakeModel.getSnakeBody(), snakeModel.getX()[i] + getUnitSize(), snakeModel.getY()[i] + getUnitSize(), getUnitSize(), getUnitSize(), this);
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(new Color(130,102,68));
        g.setFont(scoreFont);
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + getScore(), (getSnakePanelWidth() - metrics.stringWidth("Score: " + getScore())) / 2, g.getFont().getSize() + getUnitSize());
    }

    protected void newFoodPosition() {
        int rows = getSnakePanelWidth() / getUnitSize();
        int columns = getSnakePanelHeight() / getUnitSize();

        int newX;
        newX = random.nextInt(rows) * getUnitSize();
        while (newX <= getUnitSize() || newX >= (rows * getUnitSize() - getUnitSize())) {
            newX = random.nextInt(rows) * getUnitSize();
        }
        setFoodX(newX);

        int newY;
        newY = random.nextInt(columns) * getUnitSize();
        while (newY <= getUnitSize() || newY >= (columns * getUnitSize() - getUnitSize())) {
            newY = random.nextInt(rows) * getUnitSize();
        }

        setFoodY(newY);
        checkNewFoodSnakeCollision();
        foodIndex = random.nextInt(snakeModel.getFoodArray().size());
    }

    private void checkNewFoodSnakeCollision() {
        int i = 0;
        while (i < getBodyParts()) {
            if((snakeModel.getX()[i] + getUnitSize() == getFoodX()) && (snakeModel.getY()[i] + getUnitSize() == getFoodY())){
                newFoodPosition();
            } else {
                i++;
            }
        }
    }

    private void move() {
        for (int i = getBodyParts(); i > 0; i--) {
            // a kigyo testreszeit eggyel fennebb mozgatja
            snakeModel.getX()[i] = snakeModel.getX()[i - 1];
            snakeModel.getY()[i] = snakeModel.getY()[i - 1];
        }

        switch (getDirection()) {
            case UP -> snakeModel.getY()[0] = snakeModel.getY()[0] - getUnitSize();
            case DOWN -> snakeModel.getY()[0] = snakeModel.getY()[0] + getUnitSize();
            case LEFT -> snakeModel.getX()[0] = snakeModel.getX()[0] - getUnitSize();
            case RIGHT -> snakeModel.getX()[0] = snakeModel.getX()[0] + getUnitSize();
        }
    }

    public synchronized void playScore() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("../Audio/score2.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void checkFoodCollision() {
        if ((snakeModel.getX()[0] + getUnitSize() == getFoodX()) && (snakeModel.getY()[0] + getUnitSize() == getFoodY())) {
            playScore();
            setBodyParts(getBodyParts() + 1);
            setScore(getScore() + 1);
            newFoodPosition();
        }
    }

    private void checkSideCollision() {
        // Ellenorizzuk, hogy a falat erinti-e a fej.
        switch (getDirection()) {
            case UP:
                // Teteje
                if (snakeModel.getY()[0] + getUnitSize() <= 0) {
                    setRunning(false);
                }
                break;
            case DOWN:
                // alja
                if (snakeModel.getY()[0] + getUnitSize() > getSnakePanelHeight() - 2 * getUnitSize()) {
                    setRunning(false);
                }
                break;
            case LEFT:
                // Baloldala
                if (snakeModel.getX()[0] + getUnitSize() <= 0) {
                    setRunning(false);
                }
                break;
            case RIGHT:
                // JobbOldala
                if (snakeModel.getX()[0] + getUnitSize() > getSnakePanelWidth() - 2 * getUnitSize()) {
                    setRunning(false);
                }
                break;
        }

        // Ellenorizzuk, hogy a fej a testet erinti-e.
        for (int i = getBodyParts(); i >= 1; i--) {
            if ((snakeModel.getX()[0] + getUnitSize() == snakeModel.getX()[i] + getUnitSize()) && (snakeModel.getY()[0] + getUnitSize() == snakeModel.getY()[i] + getUnitSize())) {
                setRunning(false);
            }
        }

        //  Ha falat vagy testtel utkoztunk, akkor leallitjuk a jatek futasat.
        if (!isRunning()) {
            //  Game over audio.
            new AudioPlayer().playGameOver();
            //  Stopping the execution of the game timer.
            timer.stop();
            // Save score to Leaderboard.
            SnakeScore actualScore = new SnakeScore(getScore(), MainMenu.getInstance().getPlayerName());
            MainMenu.getInstance().getLeaderBoardFrame().addNewScore(actualScore);
            MainMenu.getInstance().getLeaderBoardFrame().saveLeaderBoard();
        }
    }

    private void gameOver(Graphics g) {
        //  Game over
        setBackground(new Color(130,102,68));
        //  g.setColor(new Color(138,51,36));
        g.setFont(questionFont);
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Your score: " + getScore(), (getSnakePanelWidth() - metrics.stringWidth("Your score: " + getScore())) / 2, getSnakePanelHeight() / 2 - 6 * g.getFont().getSize());
        g.setFont(messageFont);
        metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (getSnakePanelWidth() - metrics.stringWidth("Game Over")) / 2, getSnakePanelHeight() / 2);
        g.setFont(questionFont);
        metrics = getFontMetrics(g.getFont());
        g.drawString("Press enter to", (getSnakePanelWidth() - metrics.stringWidth("Press enter to")) / 2, getSnakePanelHeight() / 2 + g.getFont().getSize());
        g.drawString("return to retry", (getSnakePanelWidth() - metrics.stringWidth("return to retry")) / 2, getSnakePanelHeight() / 2 + 2 * g.getFont().getSize());
    }

    private void setSnakeSpeed() {
        if (getSpeed() > 25) {
//            System.out.println("Speed = " + getSpeed());
            if (getScore() == 5) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 10) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 15) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            }else if (getScore() == 20) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 25) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 30) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 35) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 40) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 45) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 50) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 55) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            } else if (getScore() == 60) {
                timer.setDelay(getSpeed() - getSpeed() / 10);
                setSpeed(getSpeed() - getSpeed() / 10);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning()) {
            move();
            checkFoodCollision();
            checkSideCollision();
        }
        repaint();
    }

    int getSnakePanelWidth() {
        return snakePanelWidth;
    }

    int getSnakePanelHeight() {
        return snakePanelHeight;
    }

    protected int getUnitSize() {
        return unitSize;
    }

    private int getFoodX() {
        return foodX;
    }

    private void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    private int getFoodY() {
        return foodY;
    }

    private void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    private int getBodyParts() {
        return bodyParts;
    }

    private void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }

    protected Timer getTimer() {
        return timer;
    }

    protected boolean isRunning() {
        return running;
    }

    protected void setRunning(boolean running) {
        this.running = running;
    }

    protected int getSpeed() {
        return speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    private int getScore() {
        return score;
    }

    private void setScore(int foodsNumber) {
        this.score = foodsNumber;
        setSnakeSpeed();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    private final int snakePanelWidth;
    private final int snakePanelHeight;
    private final int unitSize;
    private int speed;

    private int rows;
    private int columns;

    private final SnakeModel snakeModel;
    private int bodyParts;
    private int score;
    private int foodX;
    private int foodY;
    private Direction direction;
    private boolean running;

    private Font scoreFont, messageFont, questionFont;
    private Timer timer;
    private final Random random;
    private int foodIndex, flowerIndex;
}
