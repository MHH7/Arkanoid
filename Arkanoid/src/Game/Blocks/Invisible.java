package Game.Blocks;

import Game.Blocks.Block;
import Game.Environment;

import java.awt.*;

public class Invisible extends Block {

    public Invisible(int x, int y, Environment environment,int id){
        super(x,y,environment,id);
        visible = false;
        type = "Invisible";
        color = Color.BLUE;
        lifes = 1;
    }

}
