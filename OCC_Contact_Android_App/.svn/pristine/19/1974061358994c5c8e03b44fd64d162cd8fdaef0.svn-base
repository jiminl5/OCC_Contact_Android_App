package com.project.occ;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
//import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Provides activity to Edit contacts.
 *
 */
public class View extends Activity implements OnClickListener{
	
	String uName, uEmail, uRow, uSRow, uNumber;
	Button bEdit, bDel, bSendEmail, bClearSchedule, bCall;//, bEditS; //bClearS;
	ImageView iView;
	
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
			Intent i = new Intent("com.project.occ.TABS");
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
		setContentView(R.layout.view);
		
		//Shows current image.
		iView = (ImageView) findViewById(R.id.iView);
		
		//Display current information.
		TextView tView = (TextView) findViewById(R.id.resultName);
		TextView tView1 = (TextView) findViewById(R.id.resultEmail);
		TextView tView2 = (TextView) findViewById(R.id.resultNumber);

		Bundle b = getIntent().getExtras();
		
		String selectedName = b.getString("sName");

		String tempStr = selectedName;		
		
		ContactData info = new ContactData(this);
		info.open();
		String tempLong = info.getRow(tempStr);
		long parsedLong = Long.parseLong(tempLong);
		
		String dataName = info.getName(parsedLong);
		String dataEmail = info.getEmail(parsedLong);
		String dataNumber = info.getNumber(parsedLong);
		byte[] dataImage = info.getImage(parsedLong);
		
		info.close();
		
		setSelectedRow(tempLong);
		setSelectedName(dataName);
		setSelectedEmail(dataEmail);
		setSelectedNumber(dataNumber);
		
		iView.setImageBitmap(getImage(dataImage));
		tView.setText(getSelectedName());
		tView1.setText(getSelectedEmail());
		tView2.setText(getSelectedNumber());
		////////////////////////Schedule/////////////////////
		
		String resClass, resDays, resTime; //result class, days, time
		
		TextView tViewClass = (TextView) findViewById(R.id.class2);
		TextView tViewDays = (TextView) findViewById(R.id.days2);
		TextView tViewTime = (TextView) findViewById(R.id.time2);
		
		ContactScheduleData scheduleInfo = new ContactScheduleData(this);
		
		scheduleInfo.open();
		
		String tempLong1 = scheduleInfo.getNameRow(getSelectedName());
		//long parsedLong1 = Long.parseLong(tempLong1);
		resClass = scheduleInfo.resClass(getSelectedName());
		resDays = scheduleInfo.resDays(getSelectedName());
		resTime = scheduleInfo.resTime(getSelectedName());
		
		scheduleInfo.close();
		
		setScheduleRow(tempLong1);
		
		tViewClass.setText(resClass);
		tViewDays.setText(resDays);
		tViewTime.setText(resTime);
		
		//////////////////////////BUTTONS/////////////////////////
		bEdit = (Button) findViewById(R.id.btEdit);
		bDel = (Button) findViewById(R.id.btDel);
		bSendEmail = (Button) findViewById(R.id.btSendEmail);
		bClearSchedule = (Button) findViewById(R.id.btClearSchedule);
		bCall = (Button) findViewById(R.id.btCall);
		
		bCall.setBackgroundColor(Color.GREEN);
		bDel.setBackgroundColor(Color.RED);
		bSendEmail.setBackgroundColor(Color.rgb(255, 200, 0));
		bEdit.setBackgroundColor(Color.GRAY);
		
		bCall.setOnClickListener(this);
		bEdit.setOnClickListener(this);
		bDel.setOnClickListener(this);
		bSendEmail.setOnClickListener(this);
		bClearSchedule.setOnClickListener(this);
	}

	/**
	 * Use StringBuilder to enter '(', ')', and '-' to Phone Number;
	 * example, xxxxxxxxxx --> (xxx)xxx-xxxx
	 * 
	 * @param s- Phone Number.
	 */
	public void setSelectedNumber(String s) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder(s);
		builder.insert(0, '(');
		builder.insert(4, ')');
		builder.insert(8, '-');
		uNumber = builder.toString();
	}

	/**GetImage from DATABASE {@link ContactData}
	 * 
	 * @param dataImage1- byte[] converting to Bitmap.
	 * @return- converted Bitmap.
	 */
	public static Bitmap getImage(byte[] dataImage1) {
		// TODO Auto-generated method stub
		return BitmapFactory.decodeByteArray(dataImage1, 0, dataImage1.length);
	}

	public void setScheduleRow(String s) {
		// TODO Auto-generated method stub
		uSRow = s;
	}

	public void setSelectedRow(String s) {
		// TODO Auto-generated method stub
		uRow = s;
	}

	public void setSelectedEmail(String s) {
		// TODO Auto-generated method stub
		uEmail = s;
	}

	public void setSelectedName(String s) {
		// TODO Auto-generated method stub
		uName = s;
	}
	
	public String getSelectedRow()
	{
		return uRow;
	}
	
	public String getSelectedEmail()
	{
		return uEmail;
	}
	
	public String getSelectedName()
	{
		return uName;
	}

	public String getScheduleRow() {
		// TODO Auto-generated method stub
		return uSRow;
	}
	

	public String getSelectedNumber() {
		// TODO Auto-generated method stub
		return uNumber;
	}
	
	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param v- The view that was clicked.
	 */
	@Override
	public void onClick(android.view.View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId())
		{
		//Call selected contact.
		case R.id.btCall:
			try{
				StringBuilder builder = new StringBuilder(uNumber);
				builder.deleteCharAt(0);
				builder.deleteCharAt(3);
				builder.deleteCharAt(6);
				String telNumber = builder.toString();
				
				Intent intentCall = new Intent(Intent.ACTION_DIAL);
				
				intentCall.setData(Uri.parse("tel:" + telNumber));
				
				startActivity(intentCall);
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
		
		//Send Email to selected contact.
		case R.id.btSendEmail:
			try{
				Intent ourIntent = new Intent("com.project.occ.EMAIL");
				
				//ourIntent.putExtra("sendlName", getSelectedName());
				ourIntent.putExtra("sendEmail", getSelectedEmail());
				//ourIntent.putExtra("uRow", getSelectedRow());
				
				startActivity(ourIntent);
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
			
		//Edit current contact.
		case R.id.btEdit:
			try{
				finish();
				Intent ourIntent = new Intent("com.project.occ.EDITCONTACT");
				
				ourIntent.putExtra("uName", getSelectedName());
				ourIntent.putExtra("uEmail", getSelectedEmail());
				ourIntent.putExtra("uRow", getSelectedRow());
				ourIntent.putExtra("uNumber", getSelectedNumber());
				
				startActivity(ourIntent);
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
			
		//Delete current contact.
		case R.id.btDel:
			try{
				final String delRow = this.getSelectedRow();
				final String delName = this.getSelectedName();
				final long delRow1 = Long.parseLong(delRow);
				
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
				alertDialog.setTitle("Warning!");
				alertDialog.setMessage("Are you sure you want to delete?");
				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						/////////////Delete Contact Info/////////////
						ContactData delData = new ContactData(View.this);
						
						delData.open();
						delData.deleteEntry(delRow1);
						delData.close();
						
						//////////////Delete Schedule Info//////////////
						//long tempScheduleRow = delScheduleRow1;
						
						ContactScheduleData delData1 = new ContactScheduleData(View.this);
						delData1.open();
						delData1.deleteSchedule(delName);
						delData1.close();
						
						///////////////////////////////////////////////
						
						Toast.makeText(View.this, "Deleted- " + delName,
								Toast.LENGTH_SHORT).show();
						finish();
						Intent i = new Intent("com.project.occ.TABS"); //refreshing list
						startActivity(i);
					}
				});
				alertDialog.setNegativeButton("Cancel", null);
				
				alertDialog.create();
				alertDialog.show();
				
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
		
		//Clear Schedule
		case R.id.btClearSchedule:
			try{
				final String delName1 = this.getSelectedName();
		
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
				alertDialog.setTitle("Warning!");
				alertDialog.setMessage("Clear Entire Schedule?");
				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//////////////Clear Schedule Info//////////////
						ContactScheduleData delData2 = new ContactScheduleData(View.this);
						delData2.open();
						delData2.deleteSchedule(delName1);
						delData2.createEntry(delName1, "", "", "");
						delData2.close();
						Toast.makeText(View.this, "Cleared Schedule",
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
