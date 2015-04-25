package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import entities.Enemy;
import finalProject.Game;

public class GameOver extends Component {
	private String one;
	private String two;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Image img1 = Toolkit.getDefaultToolkit().getImage(
				"src/images/game_over.gif");
		g2d.drawImage(img1, 0, 0, this);

		ArrayList<Integer> scores = new ArrayList<Integer>();

		try {
			Scanner s = new Scanner(new File("src/top_scores.txt"));
			while (s.hasNext()) {
				if (s.hasNextInt()) {
					scores.add(s.nextInt());
				} else {
					s.next();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scores.add(Game.score);

		Collections.sort(scores);
		Collections.reverse(scores);

		g2d.setFont(new Font("arial", Font.BOLD, 60));
		int i = 0;
		for (Integer el : scores) {
			if (el == Game.score) {
				g2d.setColor(Color.YELLOW);
			}
			if (el != Game.score) {
				g2d.setColor(Color.WHITE);
			}
			i++;
			g2d.drawString(Integer.toString(el), 404, i * 70 + 280);
		}
		g2d.finalize();
		
		
//		 PrintWriter pw;
//		try {
//			pw = new PrintWriter(new FileOutputStream("src/top_scores.txt"));
//			for (Integer el : scores)
//		        pw.println(el);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		    
		    
		

	}
	
	
}
