//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SettingsFrame extends JFrame implements ActionListener, MouseListener {
    private final JButton changeMapColor, changeSnakeColor, changeUnitSize, changeResolution, saveSettings, loadSettings, backButton;
    private Font font;
    private static SettingsFrame settingsFrame;

    private SettingsFrame() {
        GameProperties defaultProperties = new GameProperties();
        setTitle("Settings");
        int screenWidth = defaultProperties.getMainMenuWidth();
        int screenHeight = defaultProperties.getMainMenuHeight();
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

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(28.0f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        changeMapColor = new JButton("Change map's color");
        changeMapColor.setFont(font);
        changeMapColor.setOpaque(true);
        changeMapColor.setBackground(new Color(130,102,68));
        changeMapColor.setBorder(null);
        changeMapColor.addActionListener(this);
        changeMapColor.addMouseListener(this);

        changeSnakeColor = new JButton("Change Snake's color");
        changeSnakeColor.setFont(font);
        changeSnakeColor.setOpaque(true);
        changeSnakeColor.setBackground(new Color(130,102,68));
        changeSnakeColor.setBorder(null);
        changeSnakeColor.addActionListener(this);
        changeSnakeColor.addMouseListener(this);

        changeUnitSize = new JButton("Change game's unit size");
        changeUnitSize.setFont(font);
        changeUnitSize.setOpaque(true);
        changeUnitSize.setBackground(new Color(130,102,68));
        changeUnitSize.setBorder(null);
        changeUnitSize.addActionListener(this);
        changeUnitSize.addMouseListener(this);

        changeResolution = new JButton("Change resolution");
        changeResolution.setFont(font);
        changeResolution.setOpaque(true);
        changeResolution.setBackground(new Color(130,102,68));
        changeResolution.setBorder(null);
        changeResolution.addActionListener(this);
        changeResolution.addMouseListener(this);

        saveSettings = new JButton("Save settings");
        saveSettings.setFont(font);
        saveSettings.setOpaque(true);
        saveSettings.setBackground(new Color(130,102,68));
        saveSettings.setBorder(null);
        saveSettings.addActionListener(this);
        saveSettings.addMouseListener(this);

        loadSettings = new JButton("Load settings");
        loadSettings.setFont(font);
        loadSettings.setOpaque(true);
        loadSettings.setBackground(new Color(130,102,68));
        loadSettings.setBorder(null);
        loadSettings.addActionListener(this);
        loadSettings.addMouseListener(this);

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        buttonPanel.add(changeMapColor);
        buttonPanel.add(changeSnakeColor);
        buttonPanel.add(changeUnitSize);
        buttonPanel.add(changeResolution);
//        buttonPanel.add(saveSettings);
//        buttonPanel.add(loadSettings);
        buttonPanel.add(backButton);

        settingsPanel.add(buttonPanel);
        setContentPane(settingsPanel);
    }

    public static SettingsFrame getInstance() {
        if (settingsFrame == null) {
            settingsFrame = new SettingsFrame();
        }
        return settingsFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeMapColor) {
            dispose();
//            new MapColorSettingsFrame(buttonFont).setVisible(true);
            MapColorSettingsFrame.getInstance().setVisible(true);
        }

        if (e.getSource() == changeSnakeColor) {
            dispose();
            SnakeColorSettingsFrame.getInstance().setVisible(true);
        }

        if (e.getSource() == changeUnitSize) {
            dispose();
            UnitSizeSettingsFrame.getInstance().setVisible(true);
        }

        if (e.getSource() == changeResolution) {
            dispose();
//            new ResolutionSettingsFrame(buttonFont).setVisible(true);
            ResolutionSettingsFrame.getInstance().setVisible(true);
        }

        if (e.getSource() == backButton) {
            dispose();
//            new MainMenu().setVisible(true);
            MainMenu.getInstance().setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == changeMapColor) {
            changeMapColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeSnakeColor) {
            changeSnakeColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeUnitSize) {
            changeUnitSize.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeResolution) {
            changeResolution.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == saveSettings) {
            saveSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == loadSettings) {
            loadSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == changeMapColor) {
            changeMapColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeSnakeColor) {
            changeSnakeColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeUnitSize) {
            changeUnitSize.setBackground(new Color(181, 151, 114));
        }

        if (e.getSource() == changeResolution) {
            changeResolution.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == saveSettings) {
            saveSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeUnitSize) {
            loadSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == changeMapColor) {
            changeMapColor.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeSnakeColor) {
            changeSnakeColor.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeUnitSize) {
            changeUnitSize.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeResolution) {
            changeResolution.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == saveSettings) {
            saveSettings.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == loadSettings) {
            loadSettings.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(130,102,68));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == changeMapColor) {
            changeMapColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeSnakeColor) {
            changeSnakeColor.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeUnitSize) {
            changeUnitSize.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == changeResolution) {
            changeResolution.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == saveSettings) {
            saveSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == loadSettings) {
            loadSettings.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == changeMapColor) {
            changeMapColor.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeSnakeColor) {
            changeSnakeColor.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeUnitSize) {
            changeUnitSize.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == changeResolution) {
            changeResolution.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == saveSettings) {
            saveSettings.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == loadSettings) {
            loadSettings.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(130,102,68));
        }
    }
}
