package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Pad{
    private int x = 300;
    private int y = 700;
    private int height = 10;
    private int width = 200;
    private final Color color;
    private boolean confused;

    public Pad(){
        this.color = Color.red;
        confused = false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addWidth(int x){
        width += x;
    }

    public void setConfused(boolean confused) {
        this.confused = confused;
    }

    public boolean isConfused() {
        return confused;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public int getX(){
        return x;
    }

    public void drawPad(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    public void keyPressed(KeyEvent e){
        if(!confused) {
            if (e.getKeyCode() == 39 && x + getWidth() + 25 <= 780) x += 25;
            if (e.getKeyCode() == 37 && x - 25 >= 0) x -= 25;
        }
        else{
            if (e.getKeyCode() == 37 && x + getWidth() + 25 <= 780) x += 25;
            if (e.getKeyCode() == 39 && x - 25 >= 0) x -= 25;
        }
    }
}
