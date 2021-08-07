package Game.Blocks;

import Game.Environment;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Block {
    protected int x;
    protected int y;
    protected int height = 25;
    protected int width = 60;
    protected Color color;
    protected boolean broken;
    protected int lifes;
    protected boolean visible;
    protected final Environment environment;
    protected String type;
    private Timer timer;
    private final int id;

    public Block(int x,int y,Environment environment,int id){
        this.id = id;
        this.environment = environment;
        this.x = x;
        this.y = y;
        Random rnd = new Random();
        this.color = new Color(rnd.nextInt(255) + 1,rnd.nextInt(255) + 1,rnd.nextInt(255) + 1);
        broken = false;
        visible = true;
    }

    public void setBroken(boolean broken) {
        if(broken == false && lifes == 0)return;
        this.broken = broken;
        if(this instanceof Prizer && broken){
            setTimer();
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public int getLifes() {
        return lifes;
    }

    public String getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Boolean isBroken(){
        return broken;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void addLifes(int x){
        lifes += x;
        if(lifes == 0)setBroken(true);
        if(lifes == 1 && this instanceof Wood){
            color = new Color(165,62,42);
        }
    }

    public void drawBlock(Graphics g){
        g.setColor(color);
        if(visible)g.fillRect(x,y,width,height);
    }

    public Timer getTimer(){
        return timer;
    }

    public void setTimer() {
        Block bb = this;
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                ((Prizer)bb).getPrize().move();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(t,100,100);
    }
}
