package Game;

import Game.Blocks.Block;
import Game.Blocks.Prizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Pause extends JPanel {
    private  Environment environment;
    private final MainFrame father;

    public Pause(Environment environment,MainFrame father){
        this.father = father;
        this.environment = environment;
        setBackground(Color.CYAN);
        setFocusable(true);
        setVisible(true);
        setLayout(new GridLayout(5,1));
        Pause p = this;
        JButton Resume = new JButton("Resume");
        Resume.setBackground(Color.GREEN);
        Resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.Resume();
            }
        });
        add(Resume);
        JButton Restart = new JButton("Restart");
        Restart.setBackground(Color.white);
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.Restart();
            }
        });
        add(Restart);
        JButton Save = new JButton("Save");
        Save.setBackground(Color.cyan);
        if(environment.getGameName() == null)Save.setEnabled(false);
        if(environment.getGameName() != null)
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.Save();
            }
        });
        add(Save);
        JButton SaveAs = new JButton("Save As");
        SaveAs.setBackground(Color.yellow);
        SaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.SaveAs();
            }
        });
        add(SaveAs);
        JButton ScoreBoard = new JButton("ScoreBoard");
        ScoreBoard.setBackground(Color.black);
        ScoreBoard.setForeground(Color.white);
        ScoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.ScoreBoard();
            }
        });
        add(ScoreBoard);
    }

    public void Action(){
        this.father.getTimer().cancel();
        for(Block b : environment.getBlocks()){
            if(b instanceof Prizer){
                if(b.getTimer() != null) b.getTimer().cancel();
            }
        }
    }

    public void ScoreBoard(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(780,800);
        frame.setLayout(new GridLayout(1,1));
        File playersDirectory = new File("./src/Resources/Users");
        File[] players = playersDirectory.listFiles();
        JTable table = new JTable(players.length + 1,3);
        table.setValueAt("Name",0,0);
        table.setValueAt("Score",0,1);
        table.setValueAt("Max Score",0,2);
        int i = 1;
        for(File f : players){
            try {
                Scanner sc = new Scanner(f);
                table.setValueAt(f.getName(),i,0);
                table.setValueAt(sc.next(),i,1);
                table.setValueAt(sc.next(),i,2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        frame.add(table);
    }

    public void Resume(){
        father.setTimer();
        for(Block b : environment.getBlocks()){
            if(b instanceof Prizer){
                if(b.getTimer() == null) b.setTimer();
            }
        }
        father.setContentPane(environment);
        environment.setStartTime(System.currentTimeMillis());
    }

    public void Restart(){
        father.setContentPane(father.getStart());
    }

    public void Save(){
        father.saveGame(environment.getGameName());
    }

    public void SaveAs(){
        father.saveGame(father.getGame());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
