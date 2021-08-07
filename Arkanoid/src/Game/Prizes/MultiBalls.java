package Game.Prizes;

import Game.Ball;
import Game.Blocks.Block;

import java.awt.*;

public class MultiBalls extends Prize{
    public MultiBalls(int x, int y, Block father){
        super(x,y,father);
        type = "MultiBalls";
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage("./src/Resources/Images/theMultipleBalls.png");
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
                    father.getEnvironment().getBalls().add(new Ball(father.getEnvironment()));
                    father.getEnvironment().getBalls().add(new Ball(father.getEnvironment()));
                    Ball b = father.getEnvironment().getBalls().get(father.getEnvironment().getBalls().size() - 1);
                    b.setX(b.getX() - 30);
                    b.setY(b.getY() - 30);
                    p = 1;
                }
                if(p == 1)break;
            }
            if(p == 1)break;
        }
    }
}
