import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class RankPanel extends JPanel {
	JLabel text = new JLabel("Rank");
	JLabel index = null;
	JLabel[] ranking = new JLabel[2];
	String[] splitedStr = null;
	int count = 0;
	boolean writeRank = false;
	
	public RankPanel(){
		setLayout(new BorderLayout());
		add(new textPanel(),BorderLayout.NORTH);
		add(new RankingPanel(),BorderLayout.CENTER);
	}
	
	class textPanel extends JPanel {
		private Image bgImage = new ImageIcon("img/sky.png").getImage();
		public textPanel() {
			setLayout(new FlowLayout());
			text.setFont(new Font("ariel", Font.PLAIN, 43));
			add(text);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	class RankingPanel extends JPanel {
		private Image bgImage = new ImageIcon("img/improve.png").getImage();
		public RankingPanel() {
			GridLayout grid = new GridLayout(5,3);
			setLayout(grid);
			
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
					index.setFont(new Font("ariel", Font.PLAIN, 20));
					ranking[0].setFont(new Font("ariel", Font.PLAIN, 20));
					ranking[1].setFont(new Font("ariel", Font.PLAIN, 20));
					add(index);
					add(ranking[0]);
					add(ranking[1]);
					if(count==5)
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
	
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(bgImage,0,0,this.getWidth(),this.getHeight(),this);
	    }
	}
	
	public void SaveRankFile(InfoPanel info) {
		HashMap<Integer,String> h = new HashMap<Integer,String>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		FileWriter fout = null;
		InputStreamReader in = null;
		FileInputStream fin = null;
		BufferedReader br = null;
		String line;
		
		try {
			fout = new FileWriter("rank.txt",true);
			if(!writeRank) {
				fout.write(info.name.getText()+":"+info.score.getText());
				fout.write("\n");
				writeRank = true;
			}
			fout.close();
		} catch(IOException e){
			System.out.println("IOException Error");
		}
		
		try {
			fin = new FileInputStream("rank.txt");
			in = new InputStreamReader(fin,"MS949");
			br = new BufferedReader(in);
			
			while((line=br.readLine()) != null){
				splitedStr = null;
				splitedStr = line.split(":");
				for (int i=0;i<splitedStr.length;i++) {
					h.put(Integer.parseInt(splitedStr[1]),splitedStr[0]);
				}
			}
			
			Set<Integer> keys = h.keySet();
			Iterator<Integer> it = keys.iterator();
			
			while(it.hasNext()) {
				Integer key = it.next();
				list.add(key);
			}
			
			in.close();
			fin.close();
			br.close();

			Collections.sort(list);
			Collections.reverse(list);
			fout = new FileWriter("rank.txt");
			
			Iterator<Integer> it2 = list.iterator();
	        while(it2.hasNext()) {
	              Integer e = it2.next();
	              fout.write(h.get(e)+":"+Integer.toString(e)+"\n");
	        }
			fout.close();
		} catch(IOException e){
			System.out.println("IOException Error");
		}
	}
}
