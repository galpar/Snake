//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import javax.swing.*;
import java.awt.*;

public class ScoreButton extends JButton {
    public ScoreButton(Font font, SnakeScore score) {
        setText(score.getPlayerName() + "'s score is:    " + score.score() + ".");
        setFont(font);
        setOpaque(true);
        setBackground(new Color(130,102,68));
        setBorder(null);
    }
}
