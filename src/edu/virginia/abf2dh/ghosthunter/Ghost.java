package edu.virginia.abf2dh.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.View;

public class Ghost implements Collideable{

	private float x;
	private float y;
	private float width;
	private RectF bounds;
	private RectF proximity;
	private float height;
	private Bitmap b;
	private boolean isAlive;
	private boolean isCollected;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		bounds.set(this.x, this.y, this.x+width, this.y+height);
		proximity.set(this.x+25, this.y+25, this.x+width+25, this.y+height+25); 
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		bounds.set(this.x, this.y, this.x+width, this.y+height);
		proximity.set(this.x+25, this.y+25, this.x+width+25, this.y+height+25); 
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public boolean isCollected(){
		return isCollected;
	}
	
	public void setCollected(boolean isCollected){
		this.isCollected = isCollected;
	}

	public Bitmap getBitmap() {
		return b;
	}
	
	public RectF getProximity() {
		return proximity;
	}
	
	@Override
	public boolean intersects(Collideable c, GameView v) {
		// TODO Auto-generated method stub
		return false;
	}
	

	public Ghost(float x, float y, View v) {
		super();
		this.x = x;
		this.y = y;
		this.b = (BitmapFactory.decodeResource(v.getResources(), R.drawable.ghost));
		this.width = b.getHeight(); 
		this.height = b.getWidth();
		this.bounds = new RectF(x,y, x+width, y+height);
		this.proximity = new RectF(x+25, y+25, x+width+25, y+height+25); 
		
		this.isAlive = true;
		this.isCollected = false;
	}

	
	public void die(View v){
		
		b = BitmapFactory.decodeResource(v.getResources(), R.drawable.coins);
		isAlive = false;
		this.isCollected = false;
		
	
	}
	
	
	public void erase(View v, User u){
		this.isCollected = true;
		u.addPoints();
		b = BitmapFactory.decodeResource(v.getResources(), R.drawable.blank);
	}

	@Override
	public RectF getBounds() {
		return bounds;
	}

	

}
