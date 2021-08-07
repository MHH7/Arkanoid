package Game.Blocks;

import Game.*;
import Game.Prizes.*;

import java.awt.*;
import java.util.Random;

public class Prizer extends Block {
    private Prize prize;

    public Prizer(int x, int y, Environment environment,int id){
        super(x,y,environment,id);
        type = "Prizer";
        lifes = 1;
        color = Color.ORANGE;
    }

    public Prize getPrize() {
        if(prize == null){
            Random rnd = new Random();
            int n = rnd.nextInt(8);
            if(n == 0) prize = new ConfusedPad(x,y,this);
            else if(n == 1)prize = new FastBall(x,y,this);
            else if(n == 2)prize = new FireBall(x,y,this);
            else if(n == 3)prize = new LargePad(x,y,this);
            else if(n == 4)prize = new MultiBalls(x,y,this);
            else if(n == 5)prize = new RandomPrize(x,y,this);
            else if(n == 6)prize = new SlowBall(x,y,this);
            else prize = new SmallPad(x,y,this);
        }
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
