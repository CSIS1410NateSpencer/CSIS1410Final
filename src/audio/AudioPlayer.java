/**
* @author Spencer Isaacson
* CSIS 1410-003 
* Assignment 08 (RockPaperScissors)
*/

package audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class AudioPlayer implements LineListener{
	ArrayList<Clip> playingClips = new ArrayList<>();
	
	public void play(String name) {
		try {
			BufferedInputStream bufferedStream = new BufferedInputStream(
					getClass().getResourceAsStream("/sfx/" + name));
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedStream);
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			playingClips.add(clip);
			clip.addLineListener(this);
			clip.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			 e.printStackTrace();
		}
	}
	
	public void stopAll() {
		while(playingClips.size() > 0) {
			playingClips.get(0).stop();
		}
	}
	
	public boolean isPlaying() {
		if(playingClips.isEmpty())
			return false;
		else return true;
	}

	@Override
	public void update(LineEvent e) {
		if(e.getType() == LineEvent.Type.STOP && playingClips.contains(e.getSource())) {
			playingClips.remove(e.getSource());
			((Clip)e.getSource()).close();
		}
	}
}
