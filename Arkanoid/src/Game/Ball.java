package Game;

import Game.Blocks.Block;
import Game.Blocks.Wood;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

public class Ball{
    private int angle;
    private int speedX = 4;
    private int speedY = 4;
    private int r;
    private int x;
    private int y;
    private boolean fireBall;
    private Color color;
    private final Environment environment;
    private long lastHit = 0;

    public Ball(Environment environment){
        this.environment = environment;
        x = 380;
        y = 380;
        r = 16;
        angle = 45;
        this.color = Color.CYAN;
        fireBall = false;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getCenterX(){
        return x + (r / 2);
    }

    public int getCenterY(){
        return y + (r / 2);
    }

    public void setFireBall(boolean fireBall) {
        this.fireBall = fireBall;
    }

    public void moveBall(){
        x += speedX;
        y += speedY;
        if(y + r >= 800){
            speedY *= -1;
            for(int i = 0;i < environment.getBalls().size();i++){
                if(environment.getBalls().get(i).equals(this)){
                    environment.getBalls().remove(i);
                    break;
                }
            }
            if(environment.getBalls().size() == 0)environment.setLifes(environment.getLifes() - 1);
        }
        if(y <= 0) speedY *= -1;
        if(x  + r >= 780)speedX *= -1;
        if(x <= 0)speedX *= -1;
        Pad pd = environment.getPad();
        for(int i = 0;i <= pd.getWidth();i++){
            double d = Math.sqrt(((pd.getX() + i - getCenterX())*(pd.getX() + i - getCenterX())) + ((pd.getY() - getCenterY())*(pd.getY() - getCenterY())));
            if(d <= r && System.currentTimeMillis() - lastHit > 500){
                int l = Math.abs(i - (pd.getWidth() / 2));
                double rate = 0.0005*l;
                angle = (int)(((double)angle*(1. - rate)));
                if(angle == 0)angle = 10;
                speedY *= -1;
                if(speedX > 0 && i < (pd.getWidth() / 2))speedX *= -1;
                if(speedX < 0 && i > (pd.getWidth() / 2))speedX *= -1;
                lastHit = System.currentTimeMillis();
                break;
            }
        }
        for(Block b : environment.getBlocks()){
            if(b.isBroken())continue;
            int p = 0;
            for(int i = 0;i <= b.getWidth();i++){
                for(int j = 0;j <= b.getHeight();j++){
                    int tempx = b.getX() + i;
                    int tempy = b.getY() + j;
                    if(Math.sqrt(((tempx - getCenterX()) * (tempx - getCenterX())) + ((tempy - getCenterY()) * (tempy - getCenterY()))) <= r){
                        if(tempy == b.getY() || tempy == b.getY() + b.getHeight()){
                            speedY *= -1;
                            if(fireBall)speedY *= -1;
                        }
                        else if(tempx == b.getX() || tempx == b.getX() + b.getWidth()){
                            speedX *= -1;
                            if(fireBall)speedX *= -1;
                        }
                        else{
                            speedY *= -1;
                            speedX *= -1;
                            if(fireBall){
                                speedX *= -1;
                                speedY *= -1;
                            }
                        }
                        environment.getUser().setScore(environment.getUser().getScore() + 1);
                        b.addLifes(-1);
                        if(fireBall && b instanceof Wood)b.addLifes(-1);
                        p = 1;
                        break;
                    }
                }
                if(p == 1)break;
            }
        }
    }

    public boolean isFireBall() {
        return fireBall;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawBall(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,r,r);
    }
}
