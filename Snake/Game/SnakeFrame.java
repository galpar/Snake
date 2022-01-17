//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import Snake.GUI.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakeFrame extends JFrame implements KeyListener {
    public SnakeFrame() {//int gameWidth, int gameHeight, int snakeSpeed, int unitSize, String snakeColor) {
        setTitle("Snake");
        SnakePanel snakePanel = new SnakePanel();//snakePanelWidth, snakePanelHeight, snakeSpeed, 2, unitSize, snakeColor);        //Screen resolutions working properly are 1 : 1 ratio. Unit size must divide the resolution without remainder.
        SnakeModel snakeModel = new SnakeModel();
        setContentPane(snakePanel);

        SnakeControl snakeControl = new SnakeControl(snakePanel, snakeModel);
        Thread snakeGame = new Thread(snakeControl);
        snakeGame.start();
        setResizable(false);
        pack();                                     // The pack() method is defined in Window class in Java and it sizes the frame so that all its contents are at or above their preferred sizes. An alternative to the pack() method is to establish a frame size explicitly by calling the setSize() or setBounds() methods. In general, using the pack() method is preferable to call than setSize() method, since pack leaves the frame layout manager in charge of the frame size and layout managers are good at adjusting to platform dependencies and other factors that affect the component size.
        setLocationRelativeTo(null);

        addKeyListener(new SnakeControl(snakePanel, null));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want exit the application?", "Exit application", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
            MainMenu.getInstance().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
