import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class EndPanel extends JPanel{
	private Image bgImage = new ImageIcon("img/rank.png").getImage();
	JButton gomain = new JButton(new ImageIcon("img/gomain.png"));
	JButton exit  = new JButton(new ImageIcon("img/exit.png"));
	JLabel index = null;
	JLabel[] ranking = new JLabel[2];
	String[] splitedStr = null;
	int count = 0;
	Menu f = null;
	
	public EndPanel(Menu f) {
		setLayout(null);
		this.f = f;
		
		allRank();
		
		gomain.setBounds(0, 665, 250, 90);
	    gomain.setOpaque(false);
	    gomain.setContentAreaFilled(false);
	    gomain.setBorderPainted(false);
		gomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.getContentPane().removeAll();
				f.getContentPane().setLayout(f.cards);
				f.getContentPane().add("One", f.home);
				f.getContentPane().add("Two", f.explain);
				f.getContentPane().add("Three", f.version);
				f.getContentPane().add("Four", f.level);
		        f.getCardLayout().show(f.getContentPane(), "One");
			}
		});
		gomain.setPressedIcon(new ImageIcon("img/gomainClick.png"));
		add(gomain);
		
		exit.setBounds(420, 665, 500, 90);
	    exit.setOpaque(false);
	    exit.setContentAreaFilled(false);
	    exit.setBorderPainted(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
		exit.setPressedIcon(new ImageIcon("img/exitClick.png"));
		add(exit);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, this.getWidth(),this.getHeight(),null);
    }
	
	public void allRank() {
		InputStreamReader in = null;
		FileInputStream fin = null;
		BufferedReader br = null;
		String line;
		
		try {
			fin = new FileInputStream("rank.txt");
			in = new InputStreamReader(fin,"MS949");
			br = new BufferedReader(in);
			
			while((line=br.readLine()) != null) {
				count++;
				splitedStr = null;
				splitedStr = line.split(":");
				index = new JLabel(Integer.toString(count));
				for (int i=0;i<splitedStr.length;i++) {
					splitedStr[i] = splitedStr[i].trim();
					ranking[i] = new JLabel();
					ranking[i].setText(splitedStr[i]);
				}
				
				if(f.infoPanel.name.getText().equals(ranking[0].getText())) {
					index.setFont(new Font("ariel", Font.PLAIN, 40));
					index.setForeground(Color.red);
					ranking[0].setFont(new Font("ariel", Font.PLAIN, 30));
					ranking[0].setForeground(Color.red);
					ranking[1].setFont(new Font("ariel", Font.PLAIN, 30));
					ranking[1].setForeground(Color.red);
				}
				else {
					index.setFont(new Font("ariel", Font.PLAIN, 30));
					ranking[0].setFont(new Font("ariel", Font.PLAIN, 20));
					ranking[1].setFont(new Font("ariel", Font.PLAIN, 20));
				}
				index.setBounds(250,165+count*35,150,150);
				ranking[0].setBounds(350,165+count*35,150,150);
				ranking[1].setBounds(450,165+count*35,150,150);
				add(index);
				add(ranking[0]);
				add(ranking[1]);
				if(count==10)
					break;
			}
			in.close();
			fin.close();
			br.close();
		}
		catch(IOException ex){
			System.out.println("IOException Error");
		}
		
	}
	
}