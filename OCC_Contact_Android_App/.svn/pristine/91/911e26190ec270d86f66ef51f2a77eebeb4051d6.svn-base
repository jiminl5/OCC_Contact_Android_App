package com.project.occ;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Displays list view of contact list.
 *
 */
public class Menu extends ListActivity{

	/**
	 * called when the activity is first created
	 * */
	//Default Variables
	ArrayList<String> arMenu = new ArrayList<String>();

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
			//Pop ("Back Button");
			
			finish();
			Intent ourIntent = new Intent("com.project.occ.TESTMENU");
			startActivity(ourIntent);
			
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
		
		ContactData info = new ContactData(this);
		info.open();
		ArrayList<String> tempMenu = info.resArray();
		info.close();
		
		Collections.sort(tempMenu); //Sort list, #-A-a order
		arMenu.addAll(tempMenu);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, arMenu));
		
		//size = arMenu.size();
		//setSize(size);
	}
	
	/** 
	 * This method will be called when an item in the list is selected. 
	 * Subclasses should override. Subclasses can call 
	 * getListView().getItemAtPosition(position) if they need to access 
	 * the data associated with the selected item.
	 * 
	 * @param l- The listView where the click happened.
	 * @param v- The view that was clicked within the ListView.
	 * @param position- The position of the view in the list.
	 * @param id- The row id of the item that was clicked
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		try{
			finish();
			String name = (String) getListAdapter().getItem(position);
			Toast.makeText(this, "Selected: " + name, Toast.LENGTH_SHORT).show();
			
			Intent i = new Intent("com.project.occ.VIEW");
			
			//Put extra (clicked title, name) to intent, and pass this varaible
			//to next activity which was called by the intent.
			i.putExtra("sName", name);
			
			startActivity(i);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the Activity's 
	 * standard options menu. You should place your menu items in to menu.
	 * 
	 * @param menu- The options menu in which you place your items.
	 * @return- You must return true for the menu to be displayed;
	 * 		if you return false it will not be shown.
	 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater popUp = getMenuInflater();
		popUp.inflate(R.menu.startingpoint, menu);
		
		return true;
	}

	/**
	 * This hook is called whenever an item in your options menu is selected.
	 * The default implementation simply returns false to have the normal 
	 * processing happen (calling the item's Runnable or sending a message 
	 * to its Handler as appropriate). 
	 * 
	 * @param- The menu item that was selected.
	 * @return boolean- Return false to allow normal menu processing
	 * 		to proceed, true to consume it here.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		
		//Call activity com.project.occ.Add
		case R.id.adding:
			finish();
			Intent i = new Intent("com.project.occ.ADD");
			startActivity(i);
			
			break;
			
		//Exit Program.
		case R.id.exiting:
			finish();
			break;
		}
		
		return false;
	}
	
}
