import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Level extends JPanel {
	Vector<String> v = null;
	private Image bgImage = new ImageIcon("img/level.png").getImage();
	private JButton level1 = new JButton(new ImageIcon("img/level1.png"));
	private JButton level2 = new JButton(new ImageIcon("img/level2.png"));
	private JButton level3 = new JButton(new ImageIcon("img/level3.png"));
    private GamePanel gamePanel = null;
	private Menu F;
    
    public Level(Menu f,Vector<String> v) {
        setSize(800, 800);
        setLayout(null);    
        F = f;
        this.v = v;
        //level1
        level1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gamePanel = new GamePanel(v,1,f);
            	f.callfunction(f,gamePanel);
            }
        });
        level1.setBounds(120, 380, 550, 90);
        level1.setOpaque(false);
        level1.setContentAreaFilled(false);
        level1.setBorderPainted(false);
        level1.setPressedIcon(new ImageIcon("img/level1Click.png"));
        add(level1);
        //level2
        level2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gamePanel = new GamePanel(v,2,f);
            	f.callfunction(f,gamePanel);
            }
        });
        level2.setBounds(120, 480, 550, 90);
        level2.setOpaque(false);
        level2.setContentAreaFilled(false);
        level2.setBorderPainted(false);
        level2.setPressedIcon(new ImageIcon("img/level2Click.png"));
        add(level2);
        //level3
        level3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gamePanel = new GamePanel(v,3,f);
            	f.callfunction(f,gamePanel);
            }
        });
        level3.setBounds(120, 580, 550, 90);
        level3.setOpaque(false);
        level3.setContentAreaFilled(false);
        level3.setBorderPainted(false);
        level3.setPressedIcon(new ImageIcon("img/level3Click.png"));
        add(level3);
        setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }
}
