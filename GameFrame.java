import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	private GamePanel gamePanel = new GamePanel();
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
//	private Vector<String> = 
	public GameFrame() {
		super("Typing Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		splitPane();
		makeMenu();
		makeToolBar();
		setVisible(true);
	}
	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		getContentPane().add(hPane, BorderLayout.CENTER);
		
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);		
		hPane.setDividerLocation(500);
		hPane.setEnabled(false); 
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane pPane = new JSplitPane();
		hPane.setRightComponent(pPane);
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(200);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(editPanel);
	}
	private void makeMenu() {
		JMenuBar mBar = new JMenuBar();
		setJMenuBar(mBar);
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new JMenuItem("Open"));
		fileMenu.add(new JMenuItem("Close"));
		mBar.add(fileMenu);
		
	}
	
	private void makeToolBar() {
		JToolBar tBar = new JToolBar();
		tBar.add(new JButton("open"));
		tBar.add(new JButton("close"));
		// 추가(YJ) Start
		tBar.addSeparator();
		JButton button = new JButton("Random");
		button.setToolTipText("랜덤으로 저장된 텍스트를 출력합니다.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=gamePanel.random();
				gamePanel.text.setText(str); 
			}
		});
		//
		JButton vector = new JButton("Vector");
		vector.setToolTipText("Vector의 모든 원소 출력");
		vector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					for(int i=0;i<gamePanel.v.size();i++) {
						System.out.println(gamePanel.v.get(i));
					}
					System.out.println("");
			}
		});
		tBar.add(button);
		//
		tBar.add(vector);
		// 추가(YJ) End
		getContentPane().add(tBar, BorderLayout.NORTH);
	}
	
}