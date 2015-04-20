package graphics;

import finalProject.Direction;

import java.util.HashMap;
import java.util.Map;

public class AnimationSet {
	Map<Direction, Animation> animations;
	
	private AnimationSet(Map<Direction,Animation> animations) {
		this.animations = animations;
	}
	
	public static AnimationSet getAnimations(String type,int width, int height, int numberOfSprites){
		Map<Direction,Animation> animations = new HashMap<>();
		for (int i = 0; i < Direction.values().length; i++) {
			Direction d = Direction.values()[i];
			animations.put(d, new Animation("src/images/" + type + "_" + d + ".png",width,height,numberOfSprites));
		}
		return new AnimationSet(animations);
	}
	
	public Animation get(Direction d) {
		return animations.get(d);
	}

	public void replace(Direction right, Animation animation) {
		animations.put(right, animation);
	}
}
