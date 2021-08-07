package Game.Prizes;

import Game.Ball;
import Game.Blocks.Block;

import java.awt.*;
import java.util.Random;

public class RandomPrize extends Prize{
    public RandomPrize(int x, int y, Block father){
        super(x,y,father);
        type = "RandomPrize";
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage("./src/Resources/Images/RandomGift.png");
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
                    Random rnd = new Random();
                    int xx = rnd.nextInt(7);
                    if(xx == 0){
                        father.getEnvironment().getPad().setConfused(!father.getEnvironment().getPad().isConfused());
                    }
                    else if(xx == 1){
                        for(Ball b: father.getEnvironment().getBalls()){
                            if(b.getSpeedX() > 0)b.setSpeedX(b.getSpeedX() + 1);
                            else b.setSpeedX(b.getSpeedX() - 1);
                            if(b.getSpeedY() > 0)b.setSpeedY(b.getSpeedY() + 1);
                            else b.setSpeedY(b.getSpeedY() - 1);
                        }
                    }
                    else if(xx == 2){
                        for(Ball b : father.getEnvironment().getBalls())b.setFireBall(true);
                    }
                    else if(xx == 3){
                        father.getEnvironment().getPad().addWidth(20);
                    }
                    else if(xx == 4){
                        father.getEnvironment().getBalls().add(new Ball(father.getEnvironment()));
                        father.getEnvironment().getBalls().add(new Ball(father.getEnvironment()));
                        Ball b = father.getEnvironment().getBalls().get(father.getEnvironment().getBalls().size() - 1);
                        b.setX(b.getX() - 10);
                        b.setY(b.getY() - 10);
                    }
                    else if(xx == 5){
                        for(Ball b: father.getEnvironment().getBalls()){
                            if(b.getSpeedX() > 0)b.setSpeedX(b.getSpeedX() - 1);
                            else b.setSpeedX(b.getSpeedX() + 1);
                            if(b.getSpeedY() > 0)b.setSpeedY(b.getSpeedY() - 1);
                            else b.setSpeedY(b.getSpeedY() + 1);
                        }
                    }
                    else{
                        father.getEnvironment().getPad().addWidth(-20);
                    }
                    p = 1;
                }
                if(p == 1)break;
            }
            if(p == 1)break;
        }
    }
}
