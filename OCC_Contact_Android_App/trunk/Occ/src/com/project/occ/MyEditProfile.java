package com.project.occ;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Provides activity to Edit profile.
 *
 */
public class MyEditProfile extends Activity implements OnClickListener{
	
	/**
	 * called when the activity is first created
	 * */
	Button myAdd, myImage; //update, edit, or add my profile button //setting image button
	EditText mName, mEmail, mNumber,
		mClass, mDays, mTime,
		mClass1, mDays1, mTime1,
		mClass2, mDays2, mTime2,
		mClass3, mDays3, mTime3; //entering my Information
	String updateMyName, updateMyEmail, updateMyRow, updateMyNumber; //values that are entered
	Bitmap bmp;
	ImageView myAddImage;
	
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
		setContentView(R.layout.myeditprofile);
		
		//Displays selected image.
		myAddImage = (ImageView) findViewById(R.id.myAddImage);
		
		//add/edit all the informations.
		myAdd = (Button) findViewById(R.id.mybtAdd);
		//open user's gallery.
		myImage = (Button) findViewById(R.id.mybtImage);
		
		//Text Fields.
		mName = (EditText) findViewById(R.id.myupdateName);
		mEmail = (EditText) findViewById(R.id.myupdateEmail);
		mNumber = (EditText) findViewById(R.id.myupdateNumber);
		
		mClass = (EditText) findViewById(R.id.myEditClass);
		mDays = (EditText) findViewById(R.id.myEditDays);
		mTime = (EditText) findViewById(R.id.myEditTime);
		
		mClass1 = (EditText) findViewById(R.id.myEditClass1);
		mDays1 = (EditText) findViewById(R.id.myEditDays1);
		mTime1 = (EditText) findViewById(R.id.myEditTime1);
		
		mClass2 = (EditText) findViewById(R.id.myEditClass2);
		mDays2 = (EditText) findViewById(R.id.myEditDays2);
		mTime2 = (EditText) findViewById(R.id.myEditTime2);
		
		mClass3 = (EditText) findViewById(R.id.myEditClass3);
		mDays3 = (EditText) findViewById(R.id.myEditDays3);
		mTime3 = (EditText) findViewById(R.id.myEditTime3);
		
		// Register a callback to be invoked when this view is clicked. 
		// If this view is not clickable, it becomes clickable.
		myAdd.setOnClickListener(this);
		myImage.setOnClickListener(this);
		
		//Bundle getString(), brings all the informations that were passed
		//from previous activity {@link MyProfileView}
		Bundle bundle = getIntent().getExtras();
		updateMyName = bundle.getString("myName");
		updateMyEmail = bundle.getString("myEmail");
		updateMyRow = bundle.getString("myRow");
		updateMyNumber = bundle.getString("myNumber");
		
		//Opens DATABASE {@link MyProfileData}
		MyProfileData myInfo = new MyProfileData(this);
		myInfo.open();
		long parsedLong = Long.parseLong(updateMyRow);
		byte[] dataImage = myInfo.getMyImage(parsedLong);
		myInfo.close();
		
		//StringBuilder removes '(', ')', and '-' from Phone number.
		StringBuilder builder = new StringBuilder(updateMyNumber);
		builder.deleteCharAt(0);
		builder.deleteCharAt(3);
		builder.deleteCharAt(6);
		
		myAddImage.setImageBitmap(getImage(dataImage));
		mName.setText(updateMyName);
		mEmail.setText(updateMyEmail);
		mNumber.setText(builder.toString());
	}

	/**GetImage from DATABASE {@link MyProfileData}
	 * 
	 * @param dImage- byte[] converting to Bitmap.
	 * @return- converted Bitmap.
	 */
	public static Bitmap getImage(byte[] dImage) {
		// TODO Auto-generated method stub
		return BitmapFactory.decodeByteArray(dImage, 0, dImage.length);
	}
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode
	 *  you started it with, the resultCode it returned, and any additional 
	 *  data from it.
	 *  
	 *  @param requestCode- The integer request code originally supplied to 
	 *  		startActivityForResult(), allowing you to identify
	 *  		 who this result came from.
	 *  @param resultCode- The integer result code returned by the child activity
	 *  		 through its setResult().
	 *  @param data- An Intent, which can return result data to the caller 
	 *  		(various data can be attached to Intent "extras").
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);

		//Convert image files (.png) to Bitmap, and display on interface.
		 if (requestCode == 1) 
	      {
	          if (intent != null && resultCode == RESULT_OK) 
	          {              
	              
	                Uri selectedImage = intent.getData();
	                
	                String[] filePathColumn = {MediaStore.Images.Media.DATA};
	                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	                cursor.moveToFirst();
	                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	                String filePath = cursor.getString(columnIndex);
	                cursor.close();
	              
	                if(bmp != null && !bmp.isRecycled())
	                {
	                    bmp = null;                
	                }
	                                
	                bmp = BitmapFactory.decodeFile(filePath);
	                myAddImage.setBackgroundResource(0);
	                myAddImage.setImageBitmap(bmp);              
	          }
	          else 
	          {
	              Log.d("Status:", "Photopicker canceled");            
	          }
	      }
	}

	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param v- The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		//Allow users to select new image.
		switch (v.getId()){
		case R.id.mybtImage:
			openGallery();
			break;
		
		//Update/Add profile information.
		//throws e- NullPointException Error.
		case R.id.mybtAdd:
			boolean works = true;
			try{
				myAddImage.buildDrawingCache();
				Bitmap bmap = myAddImage.getDrawingCache();
				byte[] data = getBytes(bmap);
				String newNumber = mNumber.getText().toString();
				String newName = mName.getText().toString();
				String newEmail = mEmail.getText().toString();
				String newRow = updateMyRow;
				long newlRow = Long.parseLong(newRow);
				
				String newClass = mClass.getText().toString();
				String newClass1 = mClass1.getText().toString();
				String newClass2 = mClass2.getText().toString();
				String newClass3 = mClass3.getText().toString();
				
				String newDays = mDays.getText().toString();
				String newDays1 = mDays1.getText().toString();
				String newDays2 = mDays2.getText().toString();
				String newDays3 = mDays3.getText().toString();
				
				String newTime = mTime.getText().toString();
				String newTime1 = mTime1.getText().toString();
				String newTime2 = mTime2.getText().toString();
				String newTime3 = mTime3.getText().toString();
				
				//throws an error message if Phone# is less than 10 digits.
				if (newNumber.length() < 10)
				{
					works = false;
					Dialog d = new Dialog(this);
					d.setTitle("Error!");
					TextView tView = new TextView(this);
					tView.setText("Minimum 10 digits on Phone#");
					d.setContentView(tView);
					d.show();
				}
				
				//If no errors, then continue adding.
				else if (works){
					MyProfileData updated = new MyProfileData(this);
					updated.open();
					updated.updateEntry(newlRow, newName, newEmail, newNumber, data);
					updated.close();
					
					ContactScheduleData myEntry = new ContactScheduleData(this);
					myEntry.open();
					myEntry.updateJustName(updateMyName, newName);
					myEntry.createEntry(newName, newClass, newDays, newTime);
					myEntry.createEntry(newName, newClass1, newDays1, newTime1);
					myEntry.createEntry(newName, newClass2, newDays2, newTime2);
					myEntry.createEntry(newName, newClass3, newDays3, newTime3);
					myEntry.close();
				}
			}catch (Exception e){
				works = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error!");
				TextView tView = new TextView(this);
				tView.setText(error);
				d.setContentView(tView);
				d.show();
			}finally{
				if (works){
					Toast.makeText(this, "Profile- " + 
							mName.getText().toString() + " Added/Edited", Toast.LENGTH_SHORT).show();
					finish();
					
					Intent i = new Intent("com.project.occ.TABS");
					startActivity(i);
				}
			}
			
			break;
		}
	}
	
	/**
	 * Calls onActivityResult.
	 */
	private void openGallery() {
		// TODO Auto-generated method stub
		Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
	}

	/**
	 * Called when selected image (bitmap) needs to be saved in 
	 * SQLite Database {@link MyProfileData}.
	 * 
	 * @param bmp2- Bitmap which is going be parsed.
	 * @return bytes[]
	 */
	private byte[] getBytes(Bitmap bmp2) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bmp2.compress(CompressFormat.PNG, 0, stream);
	    return stream.toByteArray();
	}
	
	
}
