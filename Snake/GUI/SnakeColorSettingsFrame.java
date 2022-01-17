//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SnakeColorSettingsFrame extends JFrame implements ActionListener, MouseListener {
    private static SnakeColorSettingsFrame snakeColorSettingsFrame;
    private Font buttonFont;
    private final JButton backButton;
    private final JCheckBox brownSnakeColorBox, greenSnakeColorBox;
    private final GameProperties snakeProperties;

    private SnakeColorSettingsFrame() {
        snakeProperties = new GameProperties();
        int screenWidth = snakeProperties.getMainMenuWidth();
        int screenHeight = snakeProperties.getMainMenuHeight();
        try {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(28.0f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setTitle("Resolution");
        setSize(new Dimension(screenWidth, screenHeight));
        setLocationRelativeTo(null);
        setResizable(false);
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

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.setLayout(new BorderLayout());

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(3, 1));

        brownSnakeColorBox = new JCheckBox("Brown");
        brownSnakeColorBox.setFont(buttonFont);
        brownSnakeColorBox.setHorizontalAlignment(JCheckBox.CENTER);
        brownSnakeColorBox.setOpaque(true);
        brownSnakeColorBox.setBackground(new Color(130,102,68));
        brownSnakeColorBox.addActionListener(this);

        greenSnakeColorBox = new JCheckBox("Green");
        greenSnakeColorBox.setFont(buttonFont);
        greenSnakeColorBox.setHorizontalAlignment(JCheckBox.CENTER);
        greenSnakeColorBox.setOpaque(true);
        greenSnakeColorBox.setBackground(new Color(130,102,68));
        greenSnakeColorBox.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        String snakeColor = snakeProperties.getSnakeColor();

        if (snakeColor.equalsIgnoreCase("brown")) {
            brownSnakeColorBox.setSelected(true);
            greenSnakeColorBox.setSelected(false);
        } else if (snakeColor.equalsIgnoreCase("green")) {
            greenSnakeColorBox.setSelected(true);
            brownSnakeColorBox.setSelected(false);
        }

        checkBoxPanel.add(brownSnakeColorBox);
        checkBoxPanel.add(greenSnakeColorBox);
        checkBoxPanel.add(backButton);

        resolutionPanel.add(checkBoxPanel);
        setContentPane(resolutionPanel);
    }

    public static SnakeColorSettingsFrame getInstance() {
        if (snakeColorSettingsFrame == null) {
            snakeColorSettingsFrame = new SnakeColorSettingsFrame();
        }
        return snakeColorSettingsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == brownSnakeColorBox) {
            greenSnakeColorBox.setSelected(false);
            snakeProperties.setSnakeColor("brown");
            snakeProperties.updateProperties();
        }

        if (e.getSource() == greenSnakeColorBox) {
            brownSnakeColorBox.setSelected(false);
            snakeProperties.setSnakeColor("green");
            snakeProperties.updateProperties();
        }

        if (e.getSource() == backButton) {
            dispose();
            SettingsFrame.getInstance().setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
