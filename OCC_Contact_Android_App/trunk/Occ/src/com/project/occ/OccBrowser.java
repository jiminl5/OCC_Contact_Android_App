package com.project.occ;

import android.app.Activity;
//import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
//import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 * 
 * @author JiMin
 * 
 * Opens Blackboard, and also allows users to use it as an Internet browser.
 *
 */
public class OccBrowser extends Activity implements OnClickListener{

	EditText url;
	WebView browser;
	
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
			
			//If there is no previous page, then finish activity.
			if (browser.canGoBack())
			{
				browser.goBack();
				url.setText("");
			}
			
			else{
				finish();
				Intent ourIntent = new Intent("com.project.occ.TESTMENU");
				startActivity(ourIntent);
			}
			
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
		setContentView(R.layout.occbrowser);
		
		browser = (WebView) findViewById(R.id.wvBrowser);
		
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.requestFocus(View.FOCUS_DOWN);
		
		browser.setWebViewClient(new ourViewClient());
		try{
		browser.loadUrl("http://occ.blackboard.com");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		Button go = (Button) findViewById(R.id.bGo);
		
		url = (EditText) findViewById(R.id.etURL);
		
		url.setText("http://occ.blackboard.com");
		
		go.setOnClickListener(this);	
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
		popUp.inflate(R.menu.browswermenu, menu);
		
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
			
		case R.id.bBack:
			if (browser.canGoBack())
			{
				browser.goBack();
				url.setText("");
				browser.requestFocus(View.FOCUS_DOWN);
			}
			break;
			
		case R.id.bForward:
			if (browser.canGoForward())
			{
				browser.goForward();
				url.setText("");
				browser.requestFocus(View.FOCUS_DOWN);
			}
			break;
			
		case R.id.bRefresh:
			browser.reload();
			browser.requestFocus(View.FOCUS_DOWN);
			break;
			
		case R.id.bClearHistory:
			browser.clearHistory();
			url.setText("");
			break;
		}
		
		return false;
	}
	
	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param arg0- The view that was clicked.
	 */
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.bGo:
			String webSite = url.getText().toString();
			
			webSite.toLowerCase();
			
			//Add 'http://www.' front of the address, in case users
			//just enter 'example.com'
			if (webSite.startsWith("http://www."))
				browser.loadUrl(webSite);
			else if(webSite.startsWith("www."))
			{
				webSite = "http://" + webSite;
				browser.loadUrl(webSite);
			}
			else if(!webSite.contains("http://www."))
			{
				webSite = "http://www." + webSite;
				browser.loadUrl(webSite);
			}
			browser.requestFocus(View.FOCUS_DOWN);
			url.clearFocus();
			
			//hiding the Keyboard after using an EditText
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
			
		}
	}

	/**
	 * Uses Android API (android.webkit).
	 */
	private class ourViewClient extends WebViewClient{
		/**
		 * Give the host application a chance to take over the control 
		 * when a new url is about to be loaded in the current WebView. 
		 * If WebViewClient is not provided, by default WebView will ask 
		 * Activity Manager to choose the proper handler for the url. 
		 * If WebViewClient is provided, return true means the host 
		 * application handles the url, while return false means the 
		 * current WebView handles the url.
		 * 
		 * @param view- The WebView that is initiating the callback.
		 * @param url- The url to be loaded.
		 * 
		 * @return- True if the host application wants to leave the current
		 * 		WebView and handle the url itself, otherwise return false.
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

	}
	
}
