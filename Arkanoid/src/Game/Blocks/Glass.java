package Game.Blocks;

import Game.Blocks.Block;
import Game.Environment;

import java.awt.*;

public class Glass extends Block {

    public Glass(int x, int y, Environment environment,int id){
        super(x,y,environment,id);
        type = "Glass";
        color = Color.WHITE;
        lifes = 1;
    }

}
