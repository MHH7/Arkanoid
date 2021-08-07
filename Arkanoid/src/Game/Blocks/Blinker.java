package Game.Blocks;

import Game.Environment;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Blinker extends Block {

    public Blinker(int x, int y, Environment environment,int id){
        super(x,y,environment,id);
        type = "Blinker";
        lifes = 1;
        color = Color.GREEN;
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                if(isBroken())setBroken(false);
                else setBroken(true);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(t,1000,1000);
    }
}
