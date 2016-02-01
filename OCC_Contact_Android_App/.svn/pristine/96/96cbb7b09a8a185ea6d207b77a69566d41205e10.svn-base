package com.project.occ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author JiMin
 *
 * Provides activity that allows users to send email.
 */
public class Email extends Activity implements View.OnClickListener {

	/**
	 * called when the activity is first created
	 * */
	EditText personsEmail, subject, fromName, messages;
	String emailAdd, strSub, strFrom, strMsg, grabEmail; 
	Button sendEmail, cancelEmail;

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
			
			finish();
			Intent ourIntent = new Intent("com.project.occ.TABS");
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
		setContentView(R.layout.email);
		
		//Calls a function called initializeVars() which initializes all the variables.
		initializeVars();
		
		//Bundle.getString brings receiver's email from previous class.
		Bundle b = getIntent().getExtras();
		grabEmail = b.getString("sendEmail");
		
		personsEmail.setText(grabEmail);
		
		sendEmail.setOnClickListener(this);
		cancelEmail.setOnClickListener(this);
	}

	/**
	 * Initialize variables.
	 */
	private void initializeVars() {
		// TODO Auto-generated method stub
		personsEmail = (EditText) findViewById(R.id.etEmails);
		subject = (EditText) findViewById(R.id.etSubject);
		fromName = (EditText) findViewById(R.id.etFrom);
		messages = (EditText) findViewById(R.id.etMsgs);
		sendEmail = (Button) findViewById(R.id.startEmail);
		cancelEmail = (Button) findViewById(R.id.CancelEmail);
	}

	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param v- The view that was clicked.
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()){
		
		//Starts Email Activity.
		//All the informations are passed to email activity (gmail, outlook, etc.)
		//that are already installed in user's phone.
		case R.id.startEmail:
			try{
				convertEditTextVarsIntoStrings();
				String emailaddress[] = { emailAdd };
				String actMessage = "From: " + strFrom
						+ '\n' + strMsg;
				
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, strSub);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, actMessage);
				startActivity(emailIntent);
			}catch (Exception e){
				e.printStackTrace();
			}
			
		break;
		
		//Cancel email Activity.
		case R.id.CancelEmail:
			finish();		
			break;
		}
	}

	/**
	 * Convert all the texts from fields to Strings.
	 */
	private void convertEditTextVarsIntoStrings() {
		// TODO Auto-generated method stub
		emailAdd = personsEmail.getText().toString();
		strSub = subject.getText().toString();
		strFrom = fromName.getText().toString();
		strMsg = messages.getText().toString();
	}

	/**
	 * finishes activity.
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
