//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import javax.swing.*;

public class SnakeSpeedControl implements Runnable {
    int speed;
    SnakePanel gamePanel;
    Timer timer;

    public SnakeSpeedControl(SnakePanel gamePanel) {
        this.gamePanel = gamePanel;
        speed = gamePanel.getSpeed();
        timer = gamePanel.getTimer();
    }

    @Override
    public void run() {
        while (gamePanel.getSpeed() > 25) {
//            gamePanel.getTimer().setDelay(speed -  speed / 3);
//            speed -= speed / 3;
            System.out.println("sebesseget kene allitani");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
