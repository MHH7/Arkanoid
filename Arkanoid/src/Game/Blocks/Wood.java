package Game.Blocks;

import Game.Blocks.Block;
import Game.Environment;

import java.awt.*;

public class Wood extends Block {

    public Wood(int x, int y, Environment environment,int id){
        super(x,y,environment,id);
        type = "Wood";
        lifes = 2;
        color = new Color(210,105,30);
    }
}
