package Game.Prizes;

import Game.Blocks.Block;
import Game.Environment;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Prize {
    protected int x;
    protected int y;
    protected Image image;
    protected Block father;
    protected Boolean used;
    protected long time;
    protected String type;
    protected int fatherID;

    public Prize(int x, int y,Block father){
        fatherID = father.getId();
        this.x = x;
        this.y = y;
        this.father = father;
        used = false;
    }

    public int getFatherID() {
        return fatherID;
    }

    public String getType() {
        return type;
    }

    public boolean isUsed(){
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public void move(){
        y += 5;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
