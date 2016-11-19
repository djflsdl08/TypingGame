import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	JLabel text;
	JTextField input = new JTextField(40);
	Vector<String> v = new Vector<String>();
	
	public GamePanel() {
		v.add("YeJin");
		v.add("Hi");
		v.add("Hello");
		v.add("Java");
		v.add("Web");
		v.add("Computer");
		v.add("Dream");
		v.add("Snack");
		v.add("HTML5");
		v.add("Show");
		setLayout(new BorderLayout());
		add(new JGameGroundPanel(), BorderLayout.CENTER);
		add(new JInputPanel(), BorderLayout.SOUTH);
	}
	
	class JGameGroundPanel extends JPanel {
		public JGameGroundPanel() {
			//추가(YJ) Start
			String str=random();
			text = new JLabel(str);
			//추가(YJ) End
			setLayout(null);
			text.setSize(100, 30);
			text.setLocation(100, 10);
			add(text);
		}
	}
	
	class JInputPanel extends JPanel {
		public JInputPanel() {
			setLayout(new FlowLayout());
			add(input);
		}
	}
	
	public String random() {
		String str;
		int r = (int)(Math.random()*v.size());
		str = v.get(r);
		return str;
	}
}