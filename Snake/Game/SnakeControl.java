//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import Snake.GUI.LeaderBoardFrame;
import Snake.GUI.MainMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeControl implements KeyListener, Runnable {
    SnakePanel snakePanel;
    SnakeModel snakeModel;


    public SnakeControl(SnakePanel gamePanel, SnakeModel snakeModel)
    {
        this.snakePanel = gamePanel;
        this.snakeModel = snakeModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if(snakePanel.getDirection() != SnakePanel.Direction.RIGHT) {
                    snakePanel.setDirection(SnakePanel.Direction.LEFT);
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if(snakePanel.getDirection() != SnakePanel.Direction.LEFT) {
                    snakePanel.setDirection(SnakePanel.Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if(snakePanel.getDirection() != SnakePanel.Direction.DOWN) {
                    snakePanel.setDirection(SnakePanel.Direction.UP);
                }

                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if(snakePanel.getDirection() != SnakePanel.Direction.UP) {
                    snakePanel.setDirection(SnakePanel.Direction.DOWN);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                snakePanel.setRunning(false);
                snakePanel.dispose();
//                new MainMenu().setVisible(true);
                MainMenu.getInstance().setVisible(true);
                break;
            case KeyEvent.VK_ENTER:
                snakePanel.dispose();
                System.setProperty("sun.java2d.opengl", "true");
                new SnakeFrame().setVisible(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        snakePanel.startGame();
    }
}