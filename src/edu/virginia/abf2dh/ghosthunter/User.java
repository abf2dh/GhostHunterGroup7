package edu.virginia.abf2dh.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.widget.Toast;

public class User implements Collideable{
	
	private float x;
	private RectF bounds;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		bounds.set(this.x, this.y, this.x+width, this.y+height);
	}

	public float getY() {
		
		return y;
	}

	public void setY(float y) {
		this.y = y;
		bounds.set(this.x, this.y, this.x+width, this.y+height);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public Bitmap getBitmap(){
		return b;
	}

	private float y;
	private float width;
	private float height;
	private Bitmap b;
	private int points;
	

	
	public User(GameView v){
		
		b = this.drawBitmap(v);
		x = 25;
		y = 25;
		width = b.getWidth();
		height = b.getHeight();
		points = 0;
		
		bounds = new RectF(x,y, x+width, y+height);
		
	}
	
	@Override
	public boolean intersects(Collideable c, GameView v) {
		
		return RectF.intersects(this.bounds, c.getBounds());
		
	}

	
	
	public Bitmap drawBitmap(GameView v){
		Bitmap b1 = BitmapFactory.decodeResource(v.getResources(), R.drawable.smileyface);
		return b1;
	}
	
	public void attack(Ghost g, GameView v) {
		
		if(g.isAlive()){
			this.die();
		}
		else{
			g.erase(v);
			points+=10;
			Toast.makeText(v.getContext(),"+10, Current Score = " + points, 
	                Toast.LENGTH_SHORT).show();
		}
	}
	
	public void die() {
		this.setX(25);
		this.setY(25);
		points-=10;
		
	}

	@Override
	public RectF getBounds() {
		return bounds;
		
	}
	
	

}
