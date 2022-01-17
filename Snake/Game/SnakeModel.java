//  Golicza Alpar, HSZPM - Project
package Snake.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class SnakeModel {
    private BufferedImage headUp, headDown, headRight, headLeft, snakeBody;
    private BufferedImage wall, gravel;//, flower1, flower2, flower3;
    public ArrayList<BufferedImage> foodArray;//, flowerArray;
    private final int[] x;
    private final int[] y;

    public SnakeModel(){
        GameProperties snakeProperties = new GameProperties();

        int unitSize = snakeProperties.getUnitSize();
        String snakeColor = snakeProperties.getSnakeColor();
        String mapColor = snakeProperties.getMapColor();

        x = new int[150];
        y = new int[150];

        try {
            foodArray = new ArrayList<>();
//            flowerArray = new ArrayList<>();

            if (snakeColor.equalsIgnoreCase("brown")) {
                headUp = ImageIO.read(this.getClass().getResourceAsStream("../Images/Brown Snake/snakeHeadUp.png"));
                headDown = ImageIO.read(this.getClass().getResourceAsStream("../Images/Brown Snake/snakeHeadDown.png"));
                headLeft = ImageIO.read(this.getClass().getResourceAsStream("../Images/Brown Snake/snakeHeadLeft.png"));
                headRight = ImageIO.read(this.getClass().getResourceAsStream("../Images/Brown Snake/snakeHeadRight.png"));
                snakeBody = ImageIO.read(this.getClass().getResourceAsStream("../Images/Brown Snake/snakeBody.png"));
            } else {
                headUp = ImageIO.read(this.getClass().getResourceAsStream("../Images/Green Snake/snakeHeadUp.png"));
                headDown = ImageIO.read(this.getClass().getResourceAsStream("../Images/Green Snake/snakeHeadDown.png"));
                headLeft = ImageIO.read(this.getClass().getResourceAsStream("../Images/Green Snake/snakeHeadLeft.png"));
                headRight = ImageIO.read(this.getClass().getResourceAsStream("../Images/Green Snake/snakeHeadRight.png"));
                snakeBody = ImageIO.read(this.getClass().getResourceAsStream("../Images/Green Snake/snakeBody.png"));
            }

            if (mapColor.equalsIgnoreCase("pasture")) {
                wall = ImageIO.read(this.getClass().getResourceAsStream("../Images/Pasture/wall.png"));
            } else if (mapColor.equalsIgnoreCase("soil")) {
                wall = ImageIO.read(this.getClass().getResourceAsStream("../Images/Soil/wall.png"));
            } else if (mapColor.equalsIgnoreCase("cornfield")) {
                wall = ImageIO.read(this.getClass().getResourceAsStream("../Images/Cornfield/wall.png"));
            }

            gravel = ImageIO.read(this.getClass().getResourceAsStream("../Images/gravel.png"));

//            flower1 = ImageIO.read(new File("../Project/src/Snake/Images/Flowers/flower1.png"));
//            flower2 = ImageIO.read(new File("../Project/src/Snake/Images/Flowers/flower2.png"));
//            flower3 = ImageIO.read(new File("../Project/src/Snake/Images/Flowers/sunflower.png"));

            BufferedImage apple = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/apple.png"));
            BufferedImage banana = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/banana.png"));
            BufferedImage cherry = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/cherries.png"));
            BufferedImage mouse = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/mouse.png"));
            BufferedImage grapes = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/grape.png"));
            BufferedImage lemon = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/lemon.png"));
            BufferedImage strawberry = ImageIO.read(this.getClass().getResourceAsStream("../Images/Foods/strawberry.png"));

            foodArray.add(apple);
            foodArray.add(banana);
            foodArray.add(cherry);
            foodArray.add(mouse);
            foodArray.add(grapes);
            foodArray.add(lemon);
            foodArray.add(strawberry);

//            flowerArray.add(flower1);
//            flowerArray.add(flower2);
//            flowerArray.add(flower3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getHeadUp() {
        return headUp;
    }

    public BufferedImage getHeadDown() {
        return headDown;
    }

    public BufferedImage getHeadRight() {
        return headRight;
    }

    public BufferedImage getHeadLeft() {
        return headLeft;
    }

    public BufferedImage getSnakeBody() {
        return snakeBody;
    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getGravel() {
        return gravel;
    }

    public ArrayList<BufferedImage> getFoodArray() {
        return foodArray;
    }

//    public ArrayList<BufferedImage> getFlowerArray() {
//        return flowerArray;
//    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }
}
