package graphics;

import finalProject.Direction;

import java.util.HashMap;
import java.util.Map;

public class AnimationSet {
	Map<Direction, Animation> animations;
	
	private AnimationSet(Map<Direction,Animation> animations) {
		this.animations = animations;
	}
	
	public static AnimationSet loadAnimations(String type, int numberOfSprites){
		Map<Direction,Animation> animations = new HashMap<>();
		for (Direction d : Direction.values()) {
			animations.put(d, new Animation("src/images/" + type + "_" + d + ".png",numberOfSprites));
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
