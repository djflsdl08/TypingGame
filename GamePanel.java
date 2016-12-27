import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.omg.CORBA.SystemException;

import java.util.*;

public class GamePanel extends JPanel {
	MovingText[] text = null;
	Vector<String> v = null;
	JTextField input = new JTextField(30);
	int level;
	private Menu f = null;
	//int interrupt = 
	
	public GamePanel(Vector<String> v,int level,Menu f) {
		this.v = v;
		this.level = level;
		this.f = f;
		setLayout(new BorderLayout());
		setSize(500,600);
		String num="0";
		switch(level) {
		case 1: num="0";break;
		case 2: num="2000";break;
		case 3: num="8000";break;
		}
		f.infoPanel = new InfoPanel(num);
		String username = JOptionPane.showInputDialog("Enter your username.\n If you cancel, you'll be given a random name.");
		if(username!=null) {
			if(username.length()==0)
				f.infoPanel.name.setText("none");
			else
				f.infoPanel.name.setText(username);
		}
		else
			f.infoPanel.name.setText("none");
		//f.endPanel = new EndPanel(f);
		add(new GameGroundPanel(),BorderLayout.CENTER);
		add(new InputPanel(), BorderLayout.SOUTH);
	}
	
	class GameGroundPanel extends JPanel {
		private Image bgImage = new ImageIcon("img/background.png").getImage();
		public GameGroundPanel() {
			//String str = random();
			setLayout(null);
			int r = (int)(Math.random()*3)+level;
			text = new MovingText[r];
			for(int i=0;i<r;i++) {
				String str = random();
				int x = (int)(Math.random()*250)+100;	// x Location
				int y = (int)(Math.random()*150)+10;	// y Location
				int delay = (int)(Math.random()*500)+350-(level*100);	// delay
				text[i] = new MovingText(str,delay);
				text[i].setSize(200, 100);
				text[i].setFont(new Font("ariel", Font.PLAIN, 18));
				text[i].setLocation(x, y);
				add(text[i]);
			}
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
		
	class MovingText extends JLabel implements Runnable {
		int delay = 500;
	
		public MovingText(String text,int delay) {
			super(text);
			this.delay = delay;
			Thread th = new Thread(this);
			th.start();
		}
		public void run() {
			while(true) {
				try {
					Thread.sleep(delay);
				}catch(InterruptedException e) {}

				if(this.getY() > this.getParent().getParent().getHeight()-50) {
					int x = (int)(Math.random()*250)+100;	// x Location
					int y = (int)(Math.random()*150)+10;	// y Location
					setText(random());
					setLocation(x,y);
					if(getText()!=""){
						int n = 0;
						String goal = "";
						switch(level) {
						case 1: n=100; goal="2000"; break;
						case 2: n=200; goal="8000";break;
						case 3: n=300; goal="23000";break;
						}
						f.infoPanel.score.setText(Integer.toString(Integer.parseInt(f.infoPanel.score.getText())-n));
						f.infoPanel.GoalScore.setText(goal);
						if(level==1 && Integer.parseInt(f.infoPanel.score.getText()) < (-500)
								||level==2 && Integer.parseInt(f.infoPanel.score.getText()) < 1000
								||level==3 && Integer.parseInt(f.infoPanel.score.getText()) < 7000) {
							f.getContentPane().removeAll();
							f.rankPanel.SaveRankFile(f.infoPanel);
							//f.getContentPane().add(f.endPanel);
							f.getContentPane().add(new EndPanel(f));
							f.getContentPane().revalidate(); 
							f.getContentPane().repaint();
							break;
						}
					}
				}
				if(this.getY()+230>this.getParent().getParent().getHeight())
					setForeground(Color.red);
				else
					setForeground(Color.black);
				setLocation(this.getX(),this.getY()+10);
			}
		}
	}
	
	class InputPanel extends JPanel {
		private Image bgImage = new ImageIcon("img/ground.png").getImage();
		public InputPanel() {
			setLayout(new FlowLayout());
			add(input);
			String userStr = input.getText();
			input.addKeyListener(new MyKeyListener());
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	class MyKeyListener extends KeyAdapter {
		String userStr = null;
		public void keyPressed(KeyEvent e) {
			userStr = input.getText();
			if(e.getKeyChar()==KeyEvent.VK_ENTER) {
				input.setText("");
				
				for(int i=0;i<text.length;i++) {
					if(userStr.equals(text[i].getText())){
						int x = (int)(Math.random()*250)+100;	// x Location
						int y = (int)(Math.random()*150)+10;	// y Location
						text[i].setText(random());
						text[i].setLocation(x,y);
						int m = 0;
						switch(level) {
						case 1: m=100; break;
						case 2: m=200;break;
						case 3: m=300;break;
						}
						f.infoPanel.score.setText(Integer.toString(Integer.parseInt(f.infoPanel.score.getText())+m));
						if(level==1 && Integer.parseInt(f.infoPanel.score.getText())>2000) {
							int result = JOptionPane.showConfirmDialog(null, "You have completed level1! Do you want to continue? ","Confirm",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
								f.getContentPane().removeAll();
								f.rankPanel.SaveRankFile(f.infoPanel);
								//f.getContentPane().add(f.endPanel);
								f.getContentPane().add(new EndPanel(f));
								f.getContentPane().revalidate(); 
								f.getContentPane().repaint();
							}
							else {
								level = 2;
								f.infoPanel.GoalScore.setText("8000");
							}
						}
						else if(level==2 && Integer.parseInt(f.infoPanel.score.getText())>8000) {
							int result = JOptionPane.showConfirmDialog(null, "You have completed level2! Do you want to continue? ","Confirm",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
								f.getContentPane().removeAll();
								f.rankPanel.SaveRankFile(f.infoPanel);
								//f.getContentPane().add(f.endPanel);
								f.getContentPane().add(new EndPanel(f));
								f.getContentPane().revalidate(); 
								f.getContentPane().repaint();
							}
							else {
								level = 3;
								f.infoPanel.GoalScore.setText("23000");
							}
						}
						else if(level==3 && Integer.parseInt(f.infoPanel.score.getText())>23000) {
							int result = JOptionPane.showConfirmDialog(null, "You have completed level3! Do you want to continue? ","Confirm",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
								f.getContentPane().removeAll();
								f.rankPanel.SaveRankFile(f.infoPanel);
								//f.getContentPane().add(f.endPanel);
								f.getContentPane().add(new EndPanel(f));
								f.getContentPane().revalidate();
								f.getContentPane().repaint();
							}
							else {
								level = 3;
								f.infoPanel.GoalScore.setText("�닞");
							}
						}
					}
				}
			}
		}
	}
	public String random() {
		String str ="";
		if(v.size()!=0) {
			int r = (int)(Math.random()*v.size());
			str = v.get(r);
		}
		return str;
	}
}