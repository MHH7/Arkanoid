package Game.Prizes;

import Game.Blocks.Block;

import javax.swing.*;
import java.awt.*;

public class ConfusedPad extends Prize {

    public ConfusedPad(int x, int y, Block father){
        super(x,y,father);
        type = "ConfusedPad";
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage("./src/Resources/Images/theConfusedPaddle.png");
    }

    @Override
    public void move() {
        if(isUsed())return;
        super.move();
        for(int i = 0;i <= image.getHeight(father.getEnvironment());i++){
            for(int j = 0;j <= image.getWidth(father.getEnvironment());j++){
                int tempx = father.getEnvironment().getPad().getX();
                int tempy = father.getEnvironment().getPad().getY();
                int tempWidth = father.getEnvironment().getPad().getWidth();
                int tempHeight = father.getEnvironment().getPad().getHeight();
                if(x + j >= tempx && x + j <= tempx + tempWidth && y + i >= tempy && y + i <= tempy + tempHeight){
                    setUsed(true);
                    father.getEnvironment().getPad().setConfused(!father.getEnvironment().getPad().isConfused());
                }
            }
        }
    }
}
