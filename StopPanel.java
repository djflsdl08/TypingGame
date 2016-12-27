import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class StopPanel extends JPanel{
	private Image bgImage = new ImageIcon("img/selectMenu.png").getImage();
	JButton gomain = new JButton(new ImageIcon("img/biggomain.png"));
	JButton exit  = new JButton(new ImageIcon("img/bigexit.png"));
	
	public StopPanel(Menu f) {
		setLayout(null);
		revalidate(); 
		repaint();
		gomain.setBounds(120, 380, 550, 90);
		gomain.setOpaque(false);
		gomain.setContentAreaFilled(false);
		gomain.setBorderPainted(false);
		gomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setJMenuBar(null);
				f.getContentPane().removeAll();
				f.getContentPane().setLayout(f.cards);
				f.getContentPane().add("One", f.home);
				f.getContentPane().add("Two", f.explain);
				f.getContentPane().add("Three", f.version);
				f.getContentPane().add("Four", f.level);
		        f.getCardLayout().show(f.getContentPane(), "One");
			}
		});
		gomain.setPressedIcon(new ImageIcon("img/biggomainClick.png"));
		add(gomain);
		
		exit.setBounds(120, 500, 550, 90);
	    exit.setOpaque(false);
	    exit.setContentAreaFilled(false);
	    exit.setBorderPainted(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
		exit.setPressedIcon(new ImageIcon("img/bigexitClick.png"));
		add(exit);
		
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, this.getWidth(),this.getHeight(),null);
    }
}
