//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ResolutionSettingsFrame extends JFrame implements ActionListener, MouseListener {
    private static ResolutionSettingsFrame resolutionSettingsFrame;
    private Font buttonFont;
    private final JButton backButton;
    private final JCheckBox resolution1, resolution2, resolution3, resolution4;
    private final GameProperties defaultProperties;

    private ResolutionSettingsFrame() {
        defaultProperties = new GameProperties();
        int screenWidth = defaultProperties.getMainMenuWidth();
        int screenHeight = defaultProperties.getMainMenuHeight();
        try {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(28.0f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        checkBoxPanel.setLayout(new GridLayout(5, 1));

        resolution1 = new JCheckBox("600 x 600");
        resolution1.setFont(buttonFont);
        resolution1.setHorizontalAlignment(JCheckBox.CENTER);
        resolution1.setOpaque(true);
        resolution1.setBackground(new Color(130,102,68));
        resolution1.addActionListener(this);

        resolution2 = new JCheckBox("800 x 800");
        resolution2.setFont(buttonFont);
        resolution2.setHorizontalAlignment(JCheckBox.CENTER);
        resolution2.setOpaque(true);
        resolution2.setBackground(new Color(130,102,68));
        resolution2.addActionListener(this);

        resolution3 = new JCheckBox("1000 x 1000");
        resolution3.setFont(buttonFont);
        resolution3.setHorizontalAlignment(JCheckBox.CENTER);
        resolution3.setOpaque(true);
        resolution3.setBackground(new Color(130,102,68));
        resolution3.addActionListener(this);

        resolution4 = new JCheckBox("1600 x 900");
        resolution4.setFont(buttonFont);
        resolution4.setHorizontalAlignment(JCheckBox.CENTER);
        resolution4.setOpaque(true);
        resolution4.setBackground(new Color(130,102,68));
        resolution4.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        int resFromConfig = defaultProperties.getSnakePanelWidth();

        if (resFromConfig == 600) {
            resolution1.setSelected(true);
        } else if (resFromConfig == 800) {
            resolution2.setSelected(true);
        } else if (resFromConfig == 1000) {
            resolution3.setSelected(true);
        } else if (resFromConfig == 1600) {
            resolution4.setSelected(true);
        }

        checkBoxPanel.add(resolution1);
        checkBoxPanel.add(resolution2);
        checkBoxPanel.add(resolution3);
        checkBoxPanel.add(resolution4);
        checkBoxPanel.add(backButton);

        resolutionPanel.add(checkBoxPanel);
        setContentPane(resolutionPanel);
    }

    public static ResolutionSettingsFrame getInstance() {
        if (resolutionSettingsFrame == null) {
            resolutionSettingsFrame = new ResolutionSettingsFrame();
        }
        return resolutionSettingsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resolution1) {
            resolution2.setSelected(false);
            resolution3.setSelected(false);
            resolution4.setSelected(false);
            defaultProperties.setSnakePanelWidth(600);
            defaultProperties.setSnakePanelHeight(600);
            defaultProperties.updateProperties();
        }

        if (e.getSource() == resolution2) {
            resolution1.setSelected(false);
            resolution3.setSelected(false);
            resolution4.setSelected(false);
            defaultProperties.setSnakePanelWidth(800);
            defaultProperties.setSnakePanelHeight(800);
            defaultProperties.updateProperties();
        }

        if (e.getSource() == resolution3) {
            resolution1.setSelected(false);
            resolution2.setSelected(false);
            resolution4.setSelected(false);
            defaultProperties.setSnakePanelWidth(1000);
            defaultProperties.setSnakePanelHeight(1000);
            defaultProperties.updateProperties();
        }

        if (e.getSource() == resolution4) {
            resolution1.setSelected(false);
            resolution2.setSelected(false);
            resolution3.setSelected(false);
            defaultProperties.setSnakePanelWidth(1600);
            defaultProperties.setSnakePanelHeight(900);
            defaultProperties.updateProperties();
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
