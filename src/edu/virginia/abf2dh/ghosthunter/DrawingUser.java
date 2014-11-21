package edu.virginia.abf2dh.ghosthunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class DrawingUser extends View {
	
	Bitmap user;
	float x, y;
	
	public DrawingUser(Context context) {
		super(context);
		x = 0;
		y = 0;
		
		user = BitmapFactory.decodeResource(getResources(), R.drawable.smileyface);
		
	}
	
	
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint p = new Paint();
		
		canvas.drawBitmap(user, x, y, p);
		
		
		
		
		
		
	}

}
