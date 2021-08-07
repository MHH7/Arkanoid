package Game.Prizes;

import Game.Ball;
import Game.Blocks.Block;

import java.awt.*;

public class FireBall extends Prize{
    public FireBall(int x, int y, Block father){
        super(x,y,father);
        type = "FireBall";
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage("./src/Resources/Images/fireBallGift.png");
    }

    @Override
    public void move() {
        if(isUsed())return;
        super.move();
        for(int i = 0;i <= image.getHeight(father.getEnvironment());i++){
            int p = 0;
            for(int j = 0;j <= image.getWidth(father.getEnvironment());j++){
                int tempx = father.getEnvironment().getPad().getX();
                int tempy = father.getEnvironment().getPad().getY();
                int tempWidth = father.getEnvironment().getPad().getWidth();
                int tempHeight = father.getEnvironment().getPad().getHeight();
                if(x + j >= tempx && x + j <= tempx + tempWidth && y + i >= tempy && y + i <= tempy + tempHeight){
                    setUsed(true);
                    for(Ball b : father.getEnvironment().getBalls())b.setFireBall(true);
                    p = 1;
                }
                if(p == 1)break;
            }
            if(p == 1)break;
        }
    }
}
