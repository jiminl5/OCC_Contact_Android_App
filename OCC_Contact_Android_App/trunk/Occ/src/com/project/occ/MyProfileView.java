package com.project.occ;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Displays user profile.
 *
 */
public class MyProfileView extends Activity implements OnClickListener 
{
	
	/**
	 * called when the activity is first created
	 * */
	String myName, myEmail, myRow, myNumber;
	Button myEdit, myClear;
	ImageView myIV;
	byte[] b, myDataImage;
	
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
		if (keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			Intent i = new Intent("com.project.occ.TESTMENU");
			startActivity(i);
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
		setContentView(R.layout.myprofileview);
		
		//Edit information.
		myEdit = (Button) findViewById(R.id.myBtEdit);
		//Clear schedule.
		myClear = (Button) findViewById(R.id.myClearSchedule);
		
		//Shows current image.
		myIV = (ImageView) findViewById(R.id.myIV);
		
		//Shows current informations.
		TextView tvMyName = (TextView) findViewById(R.id.myresultName);
		TextView tvMyEmail = (TextView) findViewById(R.id.myresultEmail);
		TextView tvMyNumber = (TextView) findViewById(R.id.myresultNumber);
		TextView tvMyClass = (TextView) findViewById(R.id.myClass);
		TextView tvMyDays = (TextView) findViewById(R.id.myDays);
		TextView tvMyTime = (TextView) findViewById(R.id.myTime);
		
		//open DATABASE
		MyProfileData myInfo = new MyProfileData(this);
		myInfo.open();
	
		//Check if DATABASE {@link MyProfileData} is empty.
		//If it is empty than initialize all the displays.
		//For example, name: Empty, email: Empty, Phone#:0000000000
		//image: default android icon.
		if (!myInfo.checkEmpty()){
			
			Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bMap .compress(Bitmap.CompressFormat.PNG, 0, baos);
			byte[] b = baos.toByteArray(); 
			myInfo.profileEntry("Empty", "Empty", "0000000000", b);
		}
		
		//If DATABASE {@link MyProfileData} is not empty, then 
		// bring all the informations; and close DATABASE.
		String tempLong = myInfo.getFirstRow();
		long parsedLong = Long.parseLong(tempLong);

		String myDataName = myInfo.getMyName(parsedLong);
		String myDataEmail = myInfo.getMyEmail(parsedLong);
		String myDataNumber = myInfo.getMyNumber(parsedLong);
		myDataImage = myInfo.getMyImage(parsedLong);
		myInfo.close();
		
		ContactScheduleData myDataSchedule = new ContactScheduleData(this);
		myDataSchedule.open();
		String myDataClass = myDataSchedule.resClass(myDataName);
		String myDataDays = myDataSchedule.resDays(myDataName);
		String myDataTime = myDataSchedule.resTime(myDataName);
				
		setMyRow(tempLong);
		setMyName(myDataName);
		setMyEmail(myDataEmail);
		setMyNumber(myDataNumber);
		
		//Set all the displays.
		tvMyName.setText(getMyName());
		tvMyEmail.setText(getMyEmail());
		tvMyNumber.setText(getMyNumber());
		tvMyClass.setText(myDataClass);
		tvMyDays.setText(myDataDays);
		tvMyTime.setText(myDataTime);
		
		myIV.setImageBitmap(getImage(myDataImage));
		
		// Register a callback to be invoked when this view is clicked. 
		// If this view is not clickable, it becomes clickable.
		myEdit.setOnClickListener(this);
		myClear.setOnClickListener(this);
	}

	/**GetImage from DATABASE {@link MyProfileData}
	 * 
	 * @param myDImage- byte[] converting to Bitmap.
	 * @return- converted Bitmap.
	 */
	public static Bitmap getImage(byte[] myDImage) {
		// TODO Auto-generated method stub
		return BitmapFactory.decodeByteArray(myDImage, 0, myDImage.length);
	}
	
	public String getMyNumber(){
		return myNumber;
	}
	
	public String getMyEmail() {
		// TODO Auto-generated method stub
		return myEmail;
	}

	public String getMyName() {
		// TODO Auto-generated method stub
		return myName;
	}
	
	public String getMyRow(){
		return myRow;
	}
	
	private void setMyEmail(String s) {
		// TODO Auto-generated method stub
		myEmail = s;
	}

	private void setMyName(String s) {
		// TODO Auto-generated method stub
		myName = s;
	}
	private void setMyRow(String s){
		myRow = s;
	}
	
	/**
	 * Set Phone Number. StringBuiler places '('. ')', and '-' to 
	 * Phone Number; example, xxxxxxxxxx --> (xxx)xxx-xxxx
	 * 
	 * @param s- Phone Number.
	 */
	private void setMyNumber(String s){
		StringBuilder builder = new StringBuilder(s);
		builder.insert(0, '(');
		builder.insert(4, ')');
		builder.insert(8, '-');
		myNumber = builder.toString();
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
		
		//Call activity {@link MyEditProfile}
		case R.id.myBtEdit:
			try{
				finish();
				Intent i = new Intent("com.project.occ.MYEDITPROFILE");
				
				//Pass variables to next activity, com.project.occ.MyProfileEdit
				i.putExtra("myName", getMyName());
				i.putExtra("myEmail", getMyEmail());
				i.putExtra("myRow", getMyRow());
				i.putExtra("myNumber", getMyNumber());
				
				startActivity(i);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
			
		//Clear entire schedule under selected name.
		case R.id.myClearSchedule:
			try{
				final String delName1 = this.getMyName();
		
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
				alertDialog.setTitle("Warning!");
				alertDialog.setMessage("Clear Entire Schedule?");
				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//////////////Clear Schedule Info//////////////
						ContactScheduleData delData2 = new ContactScheduleData(MyProfileView.this);
						delData2.open();
						delData2.deleteSchedule(delName1);
						delData2.createEntry(delName1, "", "", "");
						delData2.close();
						Toast.makeText(MyProfileView.this, "Cleared Schedule",
								Toast.LENGTH_SHORT).show();
						
						finish();
						startActivity(getIntent());
					}
				});
				alertDialog.setNegativeButton("Cancel", null);
				
				alertDialog.create();
				alertDialog.show();
				
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
		}
		
	}
	
	
}
