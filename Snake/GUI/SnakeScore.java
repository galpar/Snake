//  Golicza Alpar, HSZPM - Project
package Snake.GUI;

import java.io.*;
import java.util.Objects;

public record SnakeScore(int score, String playerName) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SnakeScore score1 = (SnakeScore) o;

        return score == score1.score && Objects.equals(playerName, score1.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, playerName);
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return getPlayerName() + " - " + getScore();
    }

    public String getPlayerName() {
        return playerName;
    }
}
