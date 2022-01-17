//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.SnakeFrame;
import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenu extends JFrame implements ActionListener, MouseListener {
    private static MainMenu mainMenu;
    private Font messageFont, buttonFont;
    private final JButton setButton, startButton, settingsButton, leaderBoard, exitButton;
    private final JTextField playerNameField;
    private String playerName;
    private LeaderBoardFrame leaderBoardFrame;

    private MainMenu() {
        GameProperties gameProperties = new GameProperties();
        leaderBoardFrame = new LeaderBoardFrame();
        int mainMenuWidth = gameProperties.getMainMenuWidth();
        int mainMenuHeight = gameProperties.getMainMenuHeight();

        setTitle("Snake");
        setSize(new Dimension(mainMenuWidth, mainMenuHeight));
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

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.setBackground(new Color(130,102,68));

        try {
            messageFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(42.0f);
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(28.0f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 1));
        topPanel.setBackground(new Color(130,102,68));
        topPanel.setBorder(null);
        JLabel emptyLabel = new JLabel("");
        JLabel emptyLabel2 = new JLabel("");
        JLabel emptyLabel3 = new JLabel("");
        JLabel emptyLabel4 = new JLabel("");
        JLabel welcomeLabel = new JLabel("Welcome to the desert!");
        welcomeLabel.setFont(messageFont);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(emptyLabel);
        topPanel.add(welcomeLabel);
        topPanel.add(emptyLabel2);
        topPanel.add(emptyLabel3);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setPreferredSize(new Dimension(gameProperties.getMainMenuWidth(), 20));
        playerNamePanel.setBackground(new Color(130,102,68));
        playerNamePanel.setLayout(new FlowLayout());

        JLabel playerNameLabel = new JLabel("Enter your name: ");
        playerNameLabel.setBackground(new Color(130,102,68));
        playerNameLabel.setFont(buttonFont);

        playerNameField = new JTextField("Player");
        playerNameField.setSelectionColor(new Color(130, 102, 68));
        playerNameField.setPreferredSize(new Dimension(gameProperties.getMainMenuWidth() / 4, 50));
        playerNameField.setFont(buttonFont);
        playerNameField.setHorizontalAlignment(JTextField.CENTER);
        playerNameField.setBorder(null);
        playerNameField.setBackground(new Color(130,102,68));

        setButton = new JButton("Set");
        setButton.setPreferredSize(new Dimension(gameProperties.getMainMenuWidth() / 4, 50));
        setButton.setFont(buttonFont);
        setButton.setOpaque(true);
        setButton.setBackground(new Color(130,102,68));
        setButton.setBorder(null);
        setButton.addActionListener(this);
        setButton.addMouseListener(this);

        playerNamePanel.add(playerNameLabel);
        playerNamePanel.add(playerNameField);
        playerNamePanel.add(setButton, FlowLayout.RIGHT);
        topPanel.add(playerNamePanel);
        topPanel.add(emptyLabel4);
        mainMenu.add(topPanel, BorderLayout.NORTH);


        startButton = new JButton("Start");
        startButton.setFont(buttonFont);
        startButton.setOpaque(true);
        startButton.setBackground(new Color(130,102,68));
        startButton.setBorder(null);
        startButton.addActionListener(this);
        startButton.addMouseListener(this);

        leaderBoard = new JButton("Leaderboard");
        leaderBoard.setFont(buttonFont);
        leaderBoard.setOpaque(true);
        leaderBoard.setBackground(new Color(130,102,68));
        leaderBoard.setBorder(null);
        leaderBoard.addActionListener(this);
        leaderBoard.addMouseListener(this);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(buttonFont);
        settingsButton.setOpaque(true);
        settingsButton.setBackground(new Color(130,102,68));
        settingsButton.setBorder(null);
        settingsButton.addActionListener(this);
        settingsButton.addMouseListener(this);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setOpaque(true);
        exitButton.setBackground(new Color(130,102,68));
        exitButton.setBorder(null);
        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);

        buttonPanel.add(startButton);
        buttonPanel.add(leaderBoard);
        buttonPanel.add(settingsButton);
        buttonPanel.add(exitButton);

        mainMenu.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainMenu);
    }

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    public String getPlayerName() {
        return playerName;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setButton) {
            playerName = playerNameField.getText();
        }

        if (e.getSource() == startButton) {
            if (playerName == null) {
                playerName = playerNameField.getText();
            }
            dispose();
            System.setProperty("sun.java2d.opengl", "true");
            new SnakeFrame().setVisible(true);
        }

        if (e.getSource() == leaderBoard) {
            dispose();
            leaderBoardFrame = new LeaderBoardFrame();
            leaderBoardFrame.setVisible(true);
//            LeaderBoardFrame.getInstance().dispose();
//            LeaderBoardFrame.getInstance().setVisible(true);
        }

        if (e.getSource() == settingsButton) {
            dispose();
            SettingsFrame.getInstance().setVisible(true);
//            new SettingsFrame(buttonFont).setVisible(true);
        }

        if (e.getSource() == exitButton) {
            dispose();
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == startButton) {
            startButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == leaderBoard) {
            leaderBoard.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == settingsButton) {
            settingsButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == exitButton) {
            exitButton.setBackground(new Color(181,151,114));
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == setButton) {
            setButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == startButton) {
            startButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == leaderBoard) {
            leaderBoard.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == settingsButton) {
            settingsButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == exitButton) {
            exitButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == setButton) {
            setButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == startButton) {
            startButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == leaderBoard) {
            leaderBoard.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == settingsButton) {
            settingsButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == exitButton) {
            exitButton.setBackground(new Color(130,102,68));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == setButton) {
            setButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == startButton) {
            startButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == leaderBoard) {
            leaderBoard.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == settingsButton) {
            settingsButton.setBackground(new Color(181,151,114));
        }

        if (e.getSource() == exitButton) {
            exitButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == setButton) {
            setButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == startButton) {
            startButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == leaderBoard) {
            leaderBoard.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == settingsButton) {
            settingsButton.setBackground(new Color(130,102,68));
        }

        if (e.getSource() == exitButton) {
            exitButton.setBackground(new Color(130,102,68));
        }
    }

    public LeaderBoardFrame getLeaderBoardFrame() {
        return leaderBoardFrame;
    }
}
