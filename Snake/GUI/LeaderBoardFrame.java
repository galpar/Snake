//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import Snake.Game.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderBoardFrame extends JFrame implements ActionListener, MouseListener {
    private final JButton saveLeaderBoard, clearLeaderBoard, backButton;
    private Font font;
    private final GameProperties defaultProperties;
    private List<SnakeScore> leaderBoard;

    public LeaderBoardFrame() {
        leaderBoard = new ArrayList<>();
        defaultProperties = new GameProperties();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../Fonts/Pixeboy-z8XGD.ttf")).deriveFont(28.0f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String fileName = "../Project/src/Snake/savedLeaderBoard.txt";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            leaderBoard = (ArrayList<SnakeScore>) ois.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Filet nem talaltam.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Hiba.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Hiba.");
        }

        setTitle("Leaderboard");
        setSize(new Dimension(defaultProperties.getMainMenuWidth(), defaultProperties.getMainMenuHeight()));
        setLocationRelativeTo(null);
        setResizable(false);
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

        JPanel leaderBoardPanel = new JPanel();
        leaderBoardPanel.setBorder(null);
        leaderBoardPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1));
        topPanel.setBackground(new Color(130,102,68));
        topPanel.setBorder(null);
        JLabel emptyLabel = new JLabel("");
        JLabel emptyLabel2 = new JLabel("");
        emptyLabel2.setBorder(null);
        JLabel leaderBoardLabel = new JLabel("Leaderboard");
        leaderBoardLabel.setFont(font);
        leaderBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(emptyLabel);
        topPanel.add(leaderBoardLabel);
        topPanel.add(emptyLabel2);
        leaderBoardPanel.add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
//        System.out.println(leaderBoard.size());
        contentPanel.setBorder(null);
        contentPanel.setLayout(new GridLayout(leaderBoard.size() + 3, 1));

        for (SnakeScore sc : leaderBoard) {
            contentPanel.add(new ScoreButton(font, sc));
        }

        saveLeaderBoard = new JButton("Export leaderboard");
        saveLeaderBoard.setFont(font);
        saveLeaderBoard.setOpaque(true);
        saveLeaderBoard.setBackground(new Color(130,102,68));
        saveLeaderBoard.setBorder(null);
        saveLeaderBoard.addActionListener(this);
        saveLeaderBoard.addMouseListener(this);

        clearLeaderBoard = new JButton("Clear leaderboard");
        clearLeaderBoard.setFont(font);
        clearLeaderBoard.setOpaque(true);
        clearLeaderBoard.setBackground(new Color(130,102,68));
        clearLeaderBoard.setBorder(null);
        clearLeaderBoard.addActionListener(this);
        clearLeaderBoard.addMouseListener(this);

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(130,102,68));
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        contentPanel.add(saveLeaderBoard);
        contentPanel.add(clearLeaderBoard);
        contentPanel.add(backButton);
        leaderBoardPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(leaderBoardPanel);
    }

    public void addNewScore(SnakeScore score) {
        leaderBoard.add(score);
        leaderBoard = leaderBoard.stream()
                .sorted(Comparator.comparing(SnakeScore::getScore).reversed())
                .collect(Collectors.toList());
    }

    public void printLeaderBoard() {
        leaderBoard.stream()
                .forEach(System.out::println);
    }

    public void saveLeaderBoard() {
        try {
            String fileName = "../Project/src/Snake/savedLeaderBoard.txt";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(leaderBoard);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            try {
                String fileName = "../Project/src/Snake/savedLeaderBoard.txt";
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(leaderBoard);
                oos.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            try {
                PrintWriter out = new PrintWriter(fileChooser.getSelectedFile());
                out.write(leaderBoard.stream().toList().toString());
                out.close();
                System.out.println("Export to file was successful.");
            } catch (FileNotFoundException ex) {
                System.out.println("Error, file could not be saved.");
            }
        }

        if (e.getSource() == clearLeaderBoard) {
            leaderBoard.clear();
            saveLeaderBoard();
            dispose();
            new LeaderBoardFrame().setVisible(true);
// Beolvasas filebol
//            try {
//                String fileName = "../Project/src/Snake/savedLeaderBoard.txt";
//                FileInputStream fis = new FileInputStream(fileName);
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                leaderBoard = (ArrayList<SnakeScore>) ois.readObject();
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//                System.out.println("Filet nem talaltam.");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                System.out.println("Hiba.");
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//                System.out.println("Hiba.");
//            }
        }

        if (e.getSource() == backButton) {
            dispose();
            MainMenu.getInstance().setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            saveLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == clearLeaderBoard) {
            clearLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            saveLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == clearLeaderBoard) {
            clearLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            saveLeaderBoard.setBackground(new Color(130,102,68));
        }
        if (e.getSource() == clearLeaderBoard) {
            clearLeaderBoard.setBackground(new Color(130,102,68));
        }
        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(130,102,68));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            saveLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == clearLeaderBoard) {
            clearLeaderBoard.setBackground(new Color(181,151,114));
        }
        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(181,151,114));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == saveLeaderBoard) {
            saveLeaderBoard.setBackground(new Color(130,102,68));
        }
        if (e.getSource() == clearLeaderBoard) {
            clearLeaderBoard.setBackground(new Color(130,102,68));
        }
        if (e.getSource() == backButton) {
            backButton.setBackground(new Color(130,102,68));
        }
    }
}
