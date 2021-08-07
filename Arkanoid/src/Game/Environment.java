package Game;

import Game.Blocks.*;
import Game.Prizes.Prize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Environment extends JPanel {
    private ArrayList<Ball> balls;
    private final ArrayList<Block> blocks;
    private ArrayList<Prize> prizes;
    private final Pad pad;
    private final MainFrame father;
    private String gameName;
    private final User user;
    private long startTime;
    private int round;
    private int lifes;
    private int lastID = 0;

    public Environment(User user,MainFrame father){
        this.father = father;
        round = 0;
        startTime = System.currentTimeMillis();
        this.user = user;
        lifes = 3;
        setBackground(new Color(0,0,0));
        balls = new ArrayList<>();
        blocks = new ArrayList<>();
        prizes = new ArrayList<>();
        pad = new Pad();
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Block getBlock(int x){
        for(Block b : blocks){
            if(b.getId() == x)return b;
        }
        return null;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Prize> getPrizes() {
        if(prizes.size() == 0 || prizes == null){
            prizes = new ArrayList<>();
            for(Block b : blocks){
                if(b instanceof Prizer){
                    prizes.add(((Prizer) b).getPrize());
                }
            }
        }
        return prizes;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getRound() {
        return round;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getLifes() {
        return lifes;
    }

    public User getUser() {
        return user;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public Pad getPad() {
        return pad;
    }

    public ArrayList<Block> getBlocks(){
        return blocks;
    }

    public void gameOver(){
        JOptionPane.showMessageDialog(father,"Game Over!");
        father.setContentPane(father.getStart());
        repaint();
        revalidate();
        father.getTimer().cancel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(getLifes() == 0){
            gameOver();
            return;
        }
        super.paintComponent(g);
        father.savePlayer();
        if(balls == null || balls.size() == 0){
            balls = new ArrayList<>();
            balls.add(new Ball(this));
        }
        if(System.currentTimeMillis() - startTime >= (long) 10000*round){
            round++;
            for(Block b: blocks){
                b.setY(b.getY() + 40);
                if(b.getY() + 25 >= pad.getY() && !b.isBroken())gameOver();
            }
            for (int i = 0; i < 10; i++) {
                Random rnd = new Random();
                int x = rnd.nextInt(5);
                if(x == 0)blocks.add(new Blinker(15 + i * 75, 15,this,lastID + 1));
                if(x == 1)blocks.add(new Glass(15 + i * 75, 15,this,lastID + 1));
                if(x == 2)blocks.add(new Invisible(15 + i * 75, 15,this,lastID + 1));
                if(x == 3){
                    Prizer p = new Prizer(15 + i * 75, 15,this,lastID + 1);
                    blocks.add(p);
                }
                if(x == 4)blocks.add(new Wood(15 + i * 75, 15,this,lastID + 1));
                lastID++;
            }
        }
        if(balls != null) {
            for (Ball b : balls) {
                b.drawBall(g);
                b.moveBall();
            }
        }
        for(Block b : blocks){
            if(!b.isBroken()) b.drawBlock(g);
            if(b instanceof Prizer){
                if(b.isBroken() && !((Prizer)b).getPrize().isUsed())g.drawImage(((Prizer)b).getPrize().getImage(), ((Prizer) b).getPrize().getX(),((Prizer) b).getPrize().getY(),this);
            }
        }
        pad.drawPad(g);
    }
}
