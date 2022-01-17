//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class UnitSizeSettingsFrame extends JFrame implements ActionListener, MouseListener {
    private static UnitSizeSettingsFrame unitSizeSettingsFrame;
    private Font buttonFont;
    private final JButton backButton;
    private final JCheckBox mediumUnitSize, largeUnitSize;
    private final GameProperties snakeProperties;

    private UnitSizeSettingsFrame() {
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

        mediumUnitSize = new JCheckBox("Medium");
        mediumUnitSize.setFont(buttonFont);
        mediumUnitSize.setHorizontalAlignment(JCheckBox.CENTER);
        mediumUnitSize.setOpaque(true);
        mediumUnitSize.setBackground(new Color(130,102,68));
        mediumUnitSize.addActionListener(this);

        largeUnitSize = new JCheckBox("Large");
        largeUnitSize.setFont(buttonFont);
        largeUnitSize.setHorizontalAlignment(JCheckBox.CENTER);
        largeUnitSize.setOpaque(true);
        largeUnitSize.setBackground(new Color(130,102,68));
        largeUnitSize.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        int unitSizeFromConfig = snakeProperties.getUnitSize();

        if (unitSizeFromConfig == 25) {
            mediumUnitSize.setSelected(true);
            largeUnitSize.setSelected(false);
        } else if (unitSizeFromConfig == 50) {
            largeUnitSize.setSelected(true);
            mediumUnitSize.setSelected(false);
        }

        checkBoxPanel.add(mediumUnitSize);
        checkBoxPanel.add(largeUnitSize);
        checkBoxPanel.add(backButton);

        resolutionPanel.add(checkBoxPanel);
        setContentPane(resolutionPanel);
    }

    public static UnitSizeSettingsFrame getInstance() {
        if (unitSizeSettingsFrame == null) {
            unitSizeSettingsFrame = new UnitSizeSettingsFrame();
        }
        return unitSizeSettingsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mediumUnitSize) {
            largeUnitSize.setSelected(false);
            snakeProperties.setUnitSize(25);
            snakeProperties.updateProperties();
        }

        if (e.getSource() == largeUnitSize) {
            mediumUnitSize.setSelected(false);
            snakeProperties.setUnitSize(50);
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
