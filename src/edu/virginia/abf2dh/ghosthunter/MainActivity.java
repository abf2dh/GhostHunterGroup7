package edu.virginia.abf2dh.ghosthunter;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;
import android.media.MediaPlayer;

/*This is a Test Change*/
/*Another Test Comment*/

public class MainActivity extends ActionBarActivity {
	
	private GameView gameView;
	
	long secondsPerFrame = 17L;
	private Handler gameHandler = new Handler();
	private Runnable gameLoop = new Runnable() {
		@Override
		public void run() {
			//update position, etc.
			
			gameView.invalidate();
			gameHandler.postDelayed(this, secondsPerFrame);//do it again 
		}
	};
	
	MediaPlayer backgroundmusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		backgroundmusic = MediaPlayer.create(MainActivity.this, R.raw.ghostmusic);			
		backgroundmusic.setLooping(true);
		backgroundmusic.start();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		gameHandler.post(gameLoop);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		gameHandler.removeCallbacks(gameLoop);
		backgroundmusic.release();
		finish();
	}


	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			MainActivity activity = (MainActivity) getActivity();
			activity.gameView = (GameView) rootView.findViewById(R.id.gameview);
			
			return rootView;
		}
	}
}
