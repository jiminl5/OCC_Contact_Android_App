package com.project.occ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * 
 * @author JiMin
 * 
 * Displays Logo.
 *
 */
public class Splash extends Activity {

	/**
	 * called when the activity is first created
	 * */
	boolean check;
	
	/**
	 * Called when a new hardware key event occurs.
	 * 
	 * @param keyCode- A key code that presents the button pressed, 
	 * 		from KeyEvent
	 * @param event- The KeyEvent object that defines the button action.
	 * 
	 * @return If you handled the event, return true. 
	 * 		If you want to allow the event to be handled by 
	 * 		the next receiver, return false.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//If user presses back button on the splash screen,
		//then finishes entire program.
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();	
			check = true;
			return check;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * Called when the activity is starting.
	 * This is where most initialization should go.
	 * 
	 * @param NewBundle- If the activity is being re-initialized
	 * 		 	after previously being shut down then this Bundle contains 
	 * 			the data it most recently supplied in onSaveInstanceState(Bundle).
	 * 			Note: Otherwise it is null.
	 */
	@Override
	protected void onCreate(Bundle NewBundle) {
		// TODO Auto-generated method stub
		super.onCreate(NewBundle);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){		
			public void run(){
				try{
					//Splash opens for 2 seconds.
					sleep(2000);
					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					if (check)
						finish();
					else if (!check){
						Intent openStartingPoint = new Intent("com.project.occ.TESTMENU"); //action name
						startActivity(openStartingPoint);
					}
				}
			}
		};
		timer.start();
	}

	/**
	 * Finish Activity
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
