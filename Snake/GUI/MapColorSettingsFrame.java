//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MapColorSettingsFrame extends JFrame implements ActionListener, MouseListener {
    private static MapColorSettingsFrame mapColorSettingsFrame;
    private Font buttonFont;
    private final JButton backButton;
    private final JCheckBox brownMapColorBox, greenMapColorBox, yellowMapColorBox;
    private final GameProperties gameProperties;

    private MapColorSettingsFrame() {
        gameProperties = new GameProperties();
        int screenWidth = gameProperties.getMainMenuWidth();
        int screenHeight = gameProperties.getMainMenuHeight();
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
        checkBoxPanel.setLayout(new GridLayout(4, 1));

        brownMapColorBox = new JCheckBox("Soil");
        brownMapColorBox.setFont(buttonFont);
        brownMapColorBox.setHorizontalAlignment(JCheckBox.CENTER);
        brownMapColorBox.setOpaque(true);
        brownMapColorBox.setBackground(new Color(130,102,68));
        brownMapColorBox.addActionListener(this);

        greenMapColorBox = new JCheckBox("Pasture");
        greenMapColorBox.setFont(buttonFont);
        greenMapColorBox.setHorizontalAlignment(JCheckBox.CENTER);
        greenMapColorBox.setOpaque(true);
        greenMapColorBox.setBackground(new Color(130,102,68));
        greenMapColorBox.addActionListener(this);

        yellowMapColorBox = new JCheckBox("Cornfield");
        yellowMapColorBox.setFont(buttonFont);
        yellowMapColorBox.setHorizontalAlignment(JCheckBox.CENTER);
        yellowMapColorBox.setOpaque(true);
        yellowMapColorBox.setBackground(new Color(130,102,68));
        yellowMapColorBox.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        String mapColor = gameProperties.getMapColor();

        if (mapColor.equalsIgnoreCase("soil")) {
            brownMapColorBox.setSelected(true);
            greenMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(false);
        } else if (mapColor.equalsIgnoreCase("pasture")) {
            greenMapColorBox.setSelected(true);
            brownMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(false);
        } else if (mapColor.equalsIgnoreCase("cornfield")) {
            greenMapColorBox.setSelected(false);
            brownMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(true);
        }

        checkBoxPanel.add(brownMapColorBox);
        checkBoxPanel.add(greenMapColorBox);
        checkBoxPanel.add(yellowMapColorBox);
        checkBoxPanel.add(backButton);

        resolutionPanel.add(checkBoxPanel);
        setContentPane(resolutionPanel);
    }


    public static MapColorSettingsFrame getInstance() {
        if (mapColorSettingsFrame == null) {
            mapColorSettingsFrame = new MapColorSettingsFrame();
        }
        return mapColorSettingsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == brownMapColorBox) {
            brownMapColorBox.setSelected(true);
            greenMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(false);
            gameProperties.setMapColor("soil");
            gameProperties.updateProperties();
        }

        if (e.getSource() == greenMapColorBox) {
            greenMapColorBox.setSelected(true);
            brownMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(false);
            gameProperties.setMapColor("pasture");
            gameProperties.updateProperties();
        }

        if (e.getSource() == yellowMapColorBox) {
            greenMapColorBox.setSelected(false);
            brownMapColorBox.setSelected(false);
            yellowMapColorBox.setSelected(true);
            gameProperties.setMapColor("cornfield");
            gameProperties.updateProperties();
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
