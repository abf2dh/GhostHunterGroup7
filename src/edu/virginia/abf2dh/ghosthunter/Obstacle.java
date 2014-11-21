package edu.virginia.abf2dh.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.RectF;


public class Obstacle extends RectF {
	

	public Obstacle(){
		super();
	}
	
	public Obstacle(int left, int top, int right, int bottom){
		super(left, top, right, bottom);
	}
	
	public void collision(GameView v){
		
		while (this.contains(v.getX(), v.getY())){
			
		}
			
		
	}
	
}
