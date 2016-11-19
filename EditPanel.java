import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPanel extends JPanel {
	JTextField edit = new JTextField(20);
	JButton addButton = new JButton("add");
	JButton saveButton = new JButton("save");
	
	public EditPanel() {
		setBackground(Color.white);
		setLayout(new FlowLayout());
		add(edit);
		saveText();
		add(addButton);
		add(saveButton);
	}
	
	public void saveText() {
		addButton.setToolTipText("버튼을 클릭하면 입력한 단어가 추가됩니다.");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = edit.getText();
				edit.setText("");
				
			}
		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
