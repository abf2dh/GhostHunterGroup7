package edu.virginia.abf2dh.ghosthunter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashActivity extends ActionBarActivity{
	private Handler handler = new Handler();
	private Runnable launcher = new Runnable() {
		@Override
		public void run() {
			Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
			mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
            
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		handler.postDelayed(launcher, 10000);
		
		RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.layout_splash);
		splashLayout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				handler.removeCallbacks(launcher);
				Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
				mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
				
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
