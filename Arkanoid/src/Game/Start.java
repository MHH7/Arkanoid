package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JPanel {
    private MainFrame father;

    public Start(MainFrame father){
        this.father = father;
        setLayout(null);
        setBackground(new Color(46,133,110));
        JButton newGame = new JButton("New Game");
        newGame.setBounds(40,100,700,100);
        newGame.setBackground(new Color(237,177,32));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                father.getUser();
            }
        });
        add(newGame);
        JButton loadGame = new JButton("Load Game");
        loadGame.setBounds(40,500,700,100);
        loadGame.setBackground(new Color(237,177,32));
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = father.getGame();
                if(s != null)father.loadGame(s);
            }
        });
        add(loadGame);
    }

}
