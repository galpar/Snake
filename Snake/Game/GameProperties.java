//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class GameProperties {
    private final int mainMenuWidth, mainMenuHeight;
    private int snakePanelWidth, snakePanelHeight, snakeSpeed, unitSize;
    private String snakeColor, mapColor;
    private Properties defaultProperties;

    public GameProperties() {
        try {
            FileReader fileProperties = new FileReader("../Project/src/Snake/config.properties");
            defaultProperties = new Properties();
            defaultProperties.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainMenuWidth = Integer.parseInt(Objects.requireNonNull(defaultProperties).getProperty("mainMenuWidth"));
        mainMenuHeight = Integer.parseInt(defaultProperties.getProperty("mainMenuHeight"));
        snakePanelWidth = Integer.parseInt(defaultProperties.getProperty("gameWidth"));
        snakePanelHeight = Integer.parseInt(defaultProperties.getProperty("gameHeight"));
        snakeSpeed = Integer.parseInt(defaultProperties.getProperty("snakeSpeed"));
        unitSize = Integer.parseInt(defaultProperties.getProperty("unitSize"));
        snakeColor = defaultProperties.getProperty("snakeColor");
        mapColor = defaultProperties.getProperty("mapColor");
    }

    public void updateProperties() {
        defaultProperties.setProperty("mainMenuWidth", String.valueOf(getMainMenuWidth()));
        defaultProperties.setProperty("mainMenuHeight", String.valueOf(getMainMenuHeight()));
        defaultProperties.setProperty("gameWidth", String.valueOf(getSnakePanelWidth()));
        defaultProperties.setProperty("gameHeight", String.valueOf(getSnakePanelHeight()));
        defaultProperties.setProperty("snakeSpeed", String.valueOf(getSnakeSpeed()));
        defaultProperties.setProperty("unitSize", String.valueOf(getUnitSize()));
        defaultProperties.setProperty("snakeColor", getSnakeColor());
        defaultProperties.setProperty("mapColor", getMapColor());

        try {
            defaultProperties.store(new FileWriter("C:\\Users\\galpar\\OneDrive\\Documents\\University\\Second Year\\I. term\\Java\\Project\\out\\artifacts\\Snake\\config.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMainMenuWidth() {
        return mainMenuWidth;
    }

    public int getMainMenuHeight() {
        return mainMenuHeight;
    }

    public int getSnakePanelWidth() {
        return snakePanelWidth;
    }

    public int getSnakePanelHeight() {
        return snakePanelHeight;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setSnakePanelWidth(int snakePanelWidth) {
        this.snakePanelWidth = snakePanelWidth;
    }

    public void setSnakePanelHeight(int snakePanelHeight) {
        this.snakePanelHeight = snakePanelHeight;
    }

    public void setSnakeSpeed(int snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public String getSnakeColor() {
        return snakeColor;
    }

    public void setSnakeColor(String snakeColor) {
        this.snakeColor = snakeColor;
    }

    public String getMapColor() {
        return mapColor;
    }

    public void setMapColor(String mapColor) {
        this.mapColor = mapColor;
    }
}
