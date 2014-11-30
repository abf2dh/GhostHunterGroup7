package edu.virginia.abf2dh.ghosthunter;

import android.graphics.RectF;

public interface Collideable {

	public boolean intersects(Collideable c, GameView v);
	public float getX();
	public float getY();
	public RectF getBounds();
	
}
