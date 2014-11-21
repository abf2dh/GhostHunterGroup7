package edu.virginia.abf2dh.ghosthunter;

import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends View {
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Bitmap getUser() {
		return user;
	}

	public void setUser(Bitmap user) {
		this.user = user;
	}

	public Bitmap getArrows() {
		return arrows;
	}

	public void setArrows(Bitmap arrows) {
		this.arrows = arrows;
	}

	public float x = 0.0f;
	public float y = 0.0f;
	Bitmap user;
	Bitmap arrows;
	
	
	private boolean upPressed = false;
	private boolean downPressed = false;
	
	public boolean getUpPressed() {
		return upPressed;
	}
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	@Override
	public void onDraw(Canvas canvas) {
		 user = BitmapFactory.decodeResource(getResources(), R.drawable.smileyface);
		 arrows = BitmapFactory.decodeResource(getResources(), R.drawable.keys);
		
		canvas.drawBitmap(user, x, y, new Paint());
		canvas.drawBitmap(arrows, 200.00f,  700.00f, new Paint());
		//invalidate();
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		
		if (event.getAction() == event.ACTION_UP){
			//all false
			return true;
		}
	
		
		//up
		if (event.getX()>200 && event.getX()<400 && event.getY()>700 && event.getY()<800){
				//y-=10;
			upPressed = true;
			//all others false*****
				
//			if (event.getAction() == event.ACTION_DOWN) {
//				upPressed = true;
//			}
//			else if (event.getAction() == event.ACTION_UP){
//				upPressed = false;
//			}
		}
		
		//down
		if (event.getX()>300 && event.getX()<500 && event.getY()>700 && event.getY()<800){
			if (event.getAction() == event.ACTION_DOWN) {
				downPressed = true;
				upPressed = false;
			}
			else if (event.getAction() == event.ACTION_UP){
				downPressed = false;
			}
			
			y+=10;
		
		}
		
		//left
		if (event.getX()>200 && event.getX()<400 && event.getY()>800 && event.getY()<900){
		
			x-=10;

		}
		
		//right
		if (event.getX()>300 && event.getX()<500 && event.getY()>800 && event.getY()<900){
			
			x+=10;
		}
		
	
		return true;
	}

}
