package edu.virginia.abf2dh.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.widget.Toast;

public class User implements Collideable{
	
	private float x;
	private RectF bounds;
	private float y;
	private float width;
	private float height;
	private Bitmap b;
	private int points;
	

	
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
	
	public void setBitmap(Bitmap b){
		this.b = b; 
	}

	
	public User(GameView v){
		
		b = BitmapFactory.decodeResource(v.getResources(), R.drawable.smileyface);
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

	public boolean isClose(Ghost g){
		if (RectF.intersects(g.getProximity(), this.bounds)){
			return true;
		}
		else 
			return false;
	}
	
	public void attack(Ghost g, GameView v) {
		
		if(g.isAlive()){
			this.die(v);
		}
		
		else if (!g.isCollected()){
			
			g.erase(v, this);
			Toast.makeText(v.getContext(),"You collected coins! Plus 10 Points. Your current score is: " + points, 
	                Toast.LENGTH_SHORT).show();
			
		}
		
	}
	
	public void die(GameView v) {
		this.setX(25);
		this.setY(25);
		
		this.minusPoints();
		Toast.makeText(v.getContext(),"You died! Minus 10 Points. Your current score is: " + points, 
                Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public RectF getBounds() {
		return bounds;
		
	}
	
	public void addPoints(){
		points+=10;
		
		
	}
	
	public void minusPoints(){
		points-=10;
	}

}
