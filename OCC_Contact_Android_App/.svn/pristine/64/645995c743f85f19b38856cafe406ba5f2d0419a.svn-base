package com.project.occ;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * 
 * @author JiMin
 * 
 * Displays menu interface (title page).
 *
 */
public class Testmenu extends Activity implements View.OnClickListener{
	
	/**
	 * called when the activity is first created
	 * */
	Button btMap, btContact, bNotePad, bBrowser;
	String temp;
	
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
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			//Ask user 1 more time about exiting the application.
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Quit");
			alertDialog.setMessage("Close Application?");
			alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					finish();
				}
			});
			alertDialog.setNegativeButton("Cancel", null);
			
			alertDialog.create();
			alertDialog.show();
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * Called when the activity is starting.
	 * This is where most initialization should go.
	 * 
	 * @param savedInstanceState- If the activity is being re-initialized
	 * 		 	after previously being shut down then this Bundle contains 
	 * 			the data it most recently supplied in onSaveInstanceState(Bundle).
	 * 			Note: Otherwise it is null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testmenu);
		
		//Initialize variables.
		initializeVars();
		
		// Register a callback to be invoked when this view is clicked. 
		// If this view is not clickable, it becomes clickable.
		btMap.setOnClickListener(this);
		btContact.setOnClickListener(this);	
		bNotePad.setOnClickListener(this);
		bBrowser.setOnClickListener(this);
	}

	/**
	 * Initialize all the variables.
	 */
	private void initializeVars() {
		// TODO Auto-generated method stub
		
		btMap = (Button) findViewById(R.id.btMap);
		btContact = (Button) findViewById(R.id.btContact);
		
		
		bNotePad = (Button) findViewById(R.id.btNotePad);
		bBrowser = (Button) findViewById(R.id.btBrowser);
	}

	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param v- The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch(v.getId()){
		case R.id.btMap:
			temp = "Map";
			
			try{
				finish();
				Class ourClass = Class.forName("com.project.occ." + temp);
				Intent ourIntent = new Intent(Testmenu.this, ourClass);
				startActivity(ourIntent);
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
			break;
			
		case R.id.btContact:
			temp = "Tabs";
			
			try{
				finish();
				Class ourClass = Class.forName("com.project.occ." + temp);
				Intent ourIntent = new Intent(Testmenu.this, ourClass);
				startActivity(ourIntent);
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
			break;
			
		case R.id.btBrowser:
			temp = "OccBrowser";
			
			try{
				finish();
				Class ourClass = Class.forName("com.project.occ." + temp);
				Intent ourIntent = new Intent(Testmenu.this, ourClass);
				startActivity(ourIntent);
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
			break;
			
		case R.id.btNotePad:
			temp = "NotesList";
			
			try{
				finish();
				Class ourClass = Class.forName("com.project.occ." + temp);
				Intent ourIntent = new Intent(Testmenu.this, ourClass);
				startActivity(ourIntent);
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
			break;
		}
	}

}
