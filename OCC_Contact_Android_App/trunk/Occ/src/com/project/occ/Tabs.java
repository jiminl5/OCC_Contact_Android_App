package com.project.occ;

import android.app.TabActivity;
import android.content.Intent;
//import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 
 * @author JiMin
 * 
 * Provides Tab Widget which allows users to see Contact menu and profile view.
 *
 */
public class Tabs extends TabActivity{

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
		setContentView(R.layout.tabs);
		
		TabHost tHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent().setClass(this, MyProfileView.class);
		
		spec = tHost.newTabSpec("profile").setIndicator("Profile")
				.setContent(intent);
		tHost.addTab(spec);
		
		intent = new Intent().setClass(this, Menu.class);
		
		spec = tHost.newTabSpec("contacts").setIndicator("Contacts")
				.setContent(intent);
		tHost.addTab(spec);
		
		
		intent = new Intent().setClass(this, Classmates.class);
		
		spec = tHost.newTabSpec("classmates").setIndicator("ClassMates")
				.setContent(intent);
		tHost.addTab(spec);
		
		tHost.setCurrentTab(1);
	}
	
}





















