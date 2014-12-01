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
	private boolean stunned; 
	

	
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

	public int getPoints(){
		return points;
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
		if (g.isAlive() && RectF.intersects(g.getProximity(), this.bounds)){
			return true;
		}
		else 
			return false;
	}
	
	public boolean attack(Ghost g, GameView v) {
		
		if(g.isAlive()){
			
			this.die(v);
			return true;
			
			
			/*
			stunned = true;
			//this.minusPoints();
			this.die(v);
			*/
		}
		
		else if (!g.isCollected()){
			
			g.erase(v, this);
			Toast.makeText(v.getContext(),"You collected coins! Plus 10 Points. Your current score is: " + points, 
	                Toast.LENGTH_SHORT).show();
			
		}
		
		return false;
	}
	
	public void die(GameView v) {
		
		//this.minusPoints();
		//stunned = false;
		this.setX(25);
		this.setY(25);
		
		
		
		
		
		
	}

	@Override
	public RectF getBounds() {
		return bounds;
		
	}
	
	public void addPoints(){
		points+=30;
		
		
	}
	
	public void minusPoints(){
		
			points-=10;
	
	}

}
