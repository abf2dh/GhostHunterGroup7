package edu.virginia.abf2dh.ghosthunter;

import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {
	
	//Sets up the ghosts and the user. 
	private ArrayList<Ghost> ghosts;
		Ghost ghost1 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost2 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost3 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost4 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost5 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost6 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost7 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost8 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
		Ghost ghost9 = new Ghost((float)Math.random()*700,(float) Math.random()*700, this);
	private User user = new User(this);

	//Delay - helps regulate the random movement of the ghosts.
	private int delay;
	
	//killMode - tells if kill mode is off (user can move freely) or on (user can kill ghosts on tap)
	private boolean killMode;
	
	//kill - bitmap of Kill Mode button.
	private Bitmap kill =(BitmapFactory.decodeResource(this.getResources(), R.drawable.killoff));
	
	//private int killCount;
	
	
	//Constructors
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
		
		//killCount = 2;
		
		//Adds ghosts to ghost array. 
		ghosts = new ArrayList<Ghost>();
			ghosts.add(ghost1);
			ghosts.add(ghost2);
			ghosts.add(ghost3);
			ghosts.add(ghost4);
			ghosts.add(ghost5);
			ghosts.add(ghost6);
			ghosts.add(ghost7);
			ghosts.add(ghost8);
			ghosts.add(ghost9);
		
		//Attempt to regenerate ghosts after killed (didn't work)
		/*
		for (int i = 0; i<killCount; i++){
			ghosts.add(new Ghost((float)Math.random()*700,(float) Math.random()*700, this));
		}
		*/
		
		//Draws the user. 
		canvas.drawBitmap(user.getBitmap(), user.getX(), user.getY() , new Paint());
		
		//Determines which kill button to draw based on Kill boolean. 
		setKill(killMode);
		canvas.drawBitmap(kill, 50, 800 ,new Paint());
		
		//Increments everytime onDraw is called.
		delay++;
		
		//Draws the ghosts. 
		for(Ghost g: ghosts)
		{
			
			canvas.drawBitmap(g.getBitmap(), g.getX(), g.getY(), new Paint());
			
			//Checks for user-ghost intersection. 
			//Attack method in user will either cause user or ghost to die.
			
			if(user.intersects(g, this)){
				user.attack(g, this);
			}
			
			//Sets random x and y coordinates for ghosts. 
			randomMovement(g, delay);
			
		}
		
		
	
		invalidate();
	}
	
	//Sets the button to corresponding kill mode. 
	public void setKill(Boolean b){
		if (b)
			kill = BitmapFactory.decodeResource(this.getResources(), R.drawable.killon);
		else
			kill = BitmapFactory.decodeResource(this.getResources(), R.drawable.killoff);
	}
	
	//Checks if any Collideable object is within the bounds of the frame.
	public boolean withinBounds(Collideable c){
		if (c.getX()<700 && c.getX()>25 && c.getY()>25 && c.getY()<750)
			return true;
		return false;
	}
	
	//Allows user movement with touch input. 
	//If killMode=false, user can move freely. 
	//If killMode=true, user cannot move, but can double tap ghosts to turn them into coins.
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		
		
		float xCord = event.getX();
		float yCord = event.getY();
		
		float xBound = 700;
		float yBound = 750;
		
		//Kill Mode Button.
		if (event.getAction() == android.view.MotionEvent.ACTION_UP)
		{
		if(xCord>75 && xCord<575 && yCord>800) {
			if (killMode){
				killMode = false;
			}
				
			else if (!killMode) {
				killMode = true;
			}
		}
		}
		
		//Turns ghosts into coins if tapped.
		if (killMode){
		
			
			for(Ghost g: ghosts)
			{
				float ghostXBound = g.getX() + g.getWidth();
				float ghostYBound = g.getY() + g.getHeight();
				
				if (xCord>g.getX() && xCord<ghostXBound && yCord>g.getY() && yCord<ghostYBound){
					g.die(this);
					//killCount++;
					
				}
				
			}
			
			
			
			
		}
		
		//Moves user to touch location. 
		else if (xCord<xBound && xCord>25 && yCord>25 && yCord<yBound){
		user.setX(xCord);
		user.setY(yCord);
		}
		
		
		
		return true;
	}
	
	//Moves the ghosts to a random location near current location. 
	public void randomMovement(Ghost g, int delay){
		float x = 0;
		float y = 0;
		
		
			if(g.isAlive()){
				if (withinBounds(g)){
					if (delay%5==0){
		
							x = g.getX() + (float) (Math.random()*20);
							y = g.getY() + (float) (Math.random()*20);
		
					}
		
					else if (delay%10==0){
			
							x = g.getX() - (float) (Math.random()*100);
							y = g.getY() - (float) (Math.random()*100);
			
					}
		
					else {
							x = g.getX();
							y = g.getY();
					}
		
				}
				else{
					x = (float) (Math.random()*700);
					y = (float) (Math.random()*700);
				}
				
		}
			else {
				x = g.getX();
				y = g.getY();
			}
		
		
		g.setX(x);
		g.setY(y);

		
	
	}
	

}
