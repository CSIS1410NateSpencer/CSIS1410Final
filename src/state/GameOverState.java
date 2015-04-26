package state;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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

public class GameOverState extends State {
	public GameOverState(Game game) {
		super(game);
	}

	private String one;
	private String two;
	Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/game_over.gif");
	List<Integer> scores;
	
	@Override
	public void begin() {
		scores = new ArrayList<>();
		try(Scanner s = new Scanner(new File("src/top_scores.txt"))){
			
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
		
		try (PrintWriter pw = new PrintWriter(new FileOutputStream("src/top_scores.txt"))) {
			for (Integer el : scores)
				pw.println(el);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(Game.input.isPressed(KeyEvent.VK_SPACE))
			Game.stateManager.next();
	}

	public void render(Graphics g) {

		g.drawImage(img1, 0, 0, null);

		g.setFont(new Font("arial", Font.BOLD, 60));
		int i = 0;
		for (Integer el : scores) {
			if (el == Game.score) {
				g.setColor(Color.YELLOW);
			}
			if (el != Game.score) {
				g.setColor(Color.WHITE);
			}
			i++;
			g.drawString(Integer.toString(el), 404, i * 70 + 280);
		}
	}
}
