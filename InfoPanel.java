import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel{
	JLabel text = new JLabel("Score");
	JLabel score = null;
	JLabel name = new JLabel("UserName");
	JLabel Goal = new JLabel("Goal");
	JLabel GoalScore = new JLabel("2000");
	
	public InfoPanel(String level) {
		setLayout(new BorderLayout());
		switch(level) {
		case "0" : GoalScore.setText("2000");break;
		case "2000" : GoalScore.setText("8000");break;
		case "8000" : GoalScore.setText("23000");break;
		}
		score = new JLabel(level);
		
		name.setFont(new Font("ariel", Font.PLAIN, 43));
		add(name);
		add(new NamePanel(),BorderLayout.NORTH);
		add(new ScorePanel(),BorderLayout.CENTER);
	}

	class NamePanel extends JPanel{
		private Image bgImage = new ImageIcon("img/sky.png").getImage();
		public NamePanel(){
			setLayout(new FlowLayout());
			name.setFont(new Font("ariel", Font.PLAIN, 43));
			add(name);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	class ScorePanel extends JPanel{
		private Image bgImage = new ImageIcon("img/info.png").getImage();
		
		public ScorePanel(){
			setLayout(null);
			text.setSize(100, 30);
			text.setLocation(10, 20);
			text.setFont(new Font("ariel", Font.PLAIN, 20));
			add(text);
			
			score.setSize(100, 30);
			score.setLocation(110, 20);
			score.setFont(new Font("ariel", Font.PLAIN, 20));
			add(score);
			
			Goal.setSize(100,30);
			Goal.setLocation(10,70);
			Goal.setFont(new Font("ariel", Font.PLAIN, 20));
			add(Goal);
			
			GoalScore.setSize(100,30);
			GoalScore.setLocation(110,70);
			GoalScore.setFont(new Font("ariel", Font.PLAIN, 20));
			add(GoalScore);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
