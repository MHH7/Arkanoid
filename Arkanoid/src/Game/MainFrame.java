package Game;

import Game.Blocks.*;
import Game.Prizes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;

public class MainFrame extends JFrame implements KeyListener {
    private Pause pause;
    private Start start;
    private Environment environment;
    private Timer timer;

    public MainFrame(){
        start = new Start(this);
        addKeyListener(this);
        setFocusable(true);
        setSize(780,800);
        setLayout(null);
        setContentPane(start);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        setTimer();
    }

    public void getUser(){
        try {
            String Name = JOptionPane.showInputDialog(this, "Enter User Name: ");
            File f = new File("./src/Resources/Users/" + Name + ".txt");
            if (!f.exists()){
                f.createNewFile();
                PrintStream writer = new PrintStream(f);
                writer.println("0");
                writer.print("0");
                writer.close();
            }
            environment = new Environment(new User(Name),this);
            timer.cancel();
            setTimer();
            setContentPane(environment);
        }catch (IOException I){

        }
    }

    public String getGame(){
        String Name = "";
        Name = JOptionPane.showInputDialog(this, "Enter Game Name: ");
        File f = new File("./src/Resources/Games/" + Name + ".txt");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(environment != null)environment.setGameName(Name);
        return Name;
    }

    public void savePlayer(){
        File file = new File("./src/Resources/Users/" + environment.getUser().getName() + ".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Scanner sc = new Scanner(file);
            int maxscore = environment.getUser().getScore();
            sc.nextInt();
            int t = sc.nextInt();
            maxscore = Math.max(maxscore,t);
            sc.close();
            PrintStream print = new PrintStream(file);
            print.println(environment.getUser().getScore());
            print.print(maxscore);
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String game){
        File file = new File("./src/Resources/Users/" + environment.getUser().getName() + ".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file = new File("./src/Resources/Games/" + game + ".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintStream print = new PrintStream(file);

            print.println(environment.getUser().getName());
            print.println(environment.getBlocks().size());

            for(Block b : environment.getBlocks()){
                print.print(b.getX() + " ");
                print.print(b.getY() + " ");
                print.print(b.getType() + " ");
                print.print(b.getLifes() + " ");
                print.print(b.isVisible() + " ");
                print.println(b.getId());
            }

            print.println(environment.getBalls().size());
            for(Ball b : environment.getBalls()){
                print.print(b.getX() + " ");
                print.print(b.getY() + " ");
                print.print(b.getSpeedX() + " ");
                print.print(b.getSpeedY() + " ");
                print.print(b.isFireBall() + " ");
                print.println(b.getAngle());
            }

            print.print(environment.getPad().getX() + " ");
            print.print(environment.getPad().getY() + " ");
            print.print(environment.getPad().getWidth() + " ");
            print.println(environment.getPad().isConfused());

            print.print(environment.getRound() + " ");
            print.print(environment.getLifes() + " ");
            print.print(environment.getGameName() + " ");
            print.println(environment.getLastID());

            print.println(environment.getPrizes().size());
            for(Prize p : environment.getPrizes()){
                print.print(p.getX() + " ");
                print.print(p.getY() + " ");
                print.print(p.isUsed() + " ");
                print.print(p.getType() + " ");
                print.print(p.getFatherID() + " ");
                print.println(environment.getBlock(p.getFatherID()).isBroken());
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String game){
        File file = new File("./src/Resources/Games/" + game + ".txt");
        try {
            Scanner sc = new Scanner(file);
            String s = sc.next();
            User user = new User(s);
            environment = new Environment(user,this);
            environment.setGameName(game);
            int n = sc.nextInt();
            for(int i = 0;i < n;i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                s = sc.next();
                int lifes = sc.nextInt();
                boolean visible = sc.nextBoolean();
                int id = sc.nextInt();
                Block b;
                if(s.equals("Blinker")){
                    b = new Blinker(x,y,environment,id);
                }
                else if(s.equals("Glass")){
                    b = new Glass(x,y,environment,id);
                }
                else if(s.equals("Invisible")){
                    b = new Invisible(x,y,environment,id);
                }
                else if(s.equals("Prizer")){
                    b = new Prizer(x,y,environment,id);
                }
                else{
                    b = new Wood(x,y,environment,id);
                }
                b.addLifes(lifes - b.getLifes());
                b.setVisible(visible);
                environment.getBlocks().add(b);
            }
            n = sc.nextInt();
            for(int i = 0;i < n;i++){
                Ball b = new Ball(environment);
                int x = sc.nextInt();
                b.setX(x);
                x = sc.nextInt();
                b.setY(x);
                x = sc.nextInt();
                b.setSpeedX(x);
                x = sc.nextInt();
                b.setSpeedY(x);
                boolean fireball = sc.nextBoolean();
                b.setFireBall(fireball);
                x = sc.nextInt();
                b.setAngle(x);
                environment.getBalls().add(b);
            }
            int x = sc.nextInt();
            environment.getPad().setX(x);
            x = sc.nextInt();
            environment.getPad().setY(x);
            x = sc.nextInt();
            environment.getPad().addWidth(x - environment.getPad().getWidth());
            boolean confused = sc.nextBoolean();
            environment.getPad().setConfused(confused);

            environment.setStartTime(System.currentTimeMillis());
            x = sc.nextInt();
            environment.setRound(x);
            x = sc.nextInt();
            environment.setLifes(x);
            s = sc.next();
            environment.setGameName(s);
            x = sc.nextInt();
            environment.setLastID(x);

            n = sc.nextInt();
            for(int i = 0;i < n;i++){
                x = sc.nextInt();
                int y = sc.nextInt();
                boolean used = sc.nextBoolean();
                String type = sc.next();
                int fatherID = sc.nextInt();
                boolean broken = sc.nextBoolean();
                Prize p;
                if(type.equals("ConfusedPad")){
                    p = new ConfusedPad(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("FastBall")){
                    p = new FastBall(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("FireBall")){
                    p = new FireBall(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("LargePad")){
                    p = new LargePad(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("MultiBalls")){
                    p = new MultiBalls(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("RandomPrize")){
                    p = new RandomPrize(x,y,environment.getBlock(fatherID));
                }
                else if(type.equals("SlowBall")){
                    p = new SlowBall(x,y,environment.getBlock(fatherID));
                }
                else{
                    p = new SmallPad(x,y,environment.getBlock(fatherID));
                }
                ((Prizer)environment.getBlock(fatherID)).setPrize(p);
                environment.getBlock(fatherID).setBroken(broken);
            }
            sc.close();
            timer.cancel();
            setTimer();
            setContentPane(environment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Start getStart() {
        return start;
    }

    public void setTimer(){
        MainFrame m = this;
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                m.run();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(t,20,20);
    }

    public Timer getTimer() {
        return timer;
    }

    public void run(){
        repaint();
        revalidate();
    }

    @Override
    public void keyPressed(KeyEvent e){
        environment.getPad().keyPressed(e);
        if(e.getKeyCode() == 32){
            if(pause == null)pause = new Pause(environment,this);
            pause.Action();
            setContentPane(pause);
            repaint();
            revalidate();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
       // contentPane.requestFocus();
        this.repaint();
        this.revalidate();
    }
}
