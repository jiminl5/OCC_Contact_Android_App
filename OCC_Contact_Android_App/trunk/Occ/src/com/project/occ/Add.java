package com.project.occ;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Provides activity to add contacts.
 *
 */
public class Add extends Activity implements OnClickListener{
	
	/**
	 * called when the activity is first created
	 * */
	ImageButton bImage;
	Button bAddSchedule;
	EditText eName, eEmail, eClass, eDays, eTime,
	eClass1, eDays1, eTime1, eClass2, eDays2, eTime2,
	eClass3, eDays3, eTime3, ePhone;
	Bitmap bmp;

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
			
			//finishes current Activity
			finish();
			//After activity is finished, bring previous activity.
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
		
		//Set the activity content to an explicit view.(XML layout)
		setContentView(R.layout.add);

		//Adds schedule
		bAddSchedule = (Button) findViewById(R.id.btAddSchedule);
		//Adds image
		bImage = (ImageButton) findViewById(R.id.addImage);
		
		//Add name, email, phone number.
		eName = (EditText) findViewById(R.id.editName);
		eEmail = (EditText) findViewById(R.id.editEmail);
		ePhone = (EditText) findViewById(R.id.editNumber);
		
		//Add classes, days, times.
		eClass = (EditText) findViewById(R.id.editClass);
		eDays = (EditText) findViewById(R.id.editDays);
		eTime = (EditText) findViewById(R.id.editTime);
		
		eClass1 = (EditText) findViewById(R.id.editClass1);
		eDays1 = (EditText) findViewById(R.id.editDays1);
		eTime1 = (EditText) findViewById(R.id.editTime1);
		
		eClass2 = (EditText) findViewById(R.id.editClass2);
		eDays2 = (EditText) findViewById(R.id.editDays2);
		eTime2 = (EditText) findViewById(R.id.editTime2);
		
		eClass3 = (EditText) findViewById(R.id.editClass3);
		eDays3 = (EditText) findViewById(R.id.editDays3);
		eTime3 = (EditText) findViewById(R.id.editTime3);
		
		//set first row empty if user leaves the input field empty.
		eClass.setText("empty");
		eDays.setText("empty");	
		eTime.setText("empty");
		
		// Register a callback to be invoked when this view is clicked. 
		// If this view is not clickable, it becomes clickable.
		bAddSchedule.setOnClickListener(this);
		bImage.setOnClickListener(this);
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
	                bImage.setBackgroundResource(0);
	                bImage.setImageBitmap(bmp);              
	          }
	          else 
	          {
	              Log.d("Status:", "Photopicker canceled");            
	          }
	      }
	}

	/**
	 * Calls onActivityResult.
	 */
	private void openGallery() {
			// TODO Auto-generated method stub
			
			//Allow the user to select a particular kind of data and return it.
			Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
	        photoPickerIntent.setType("image/*");
	        startActivityForResult(photoPickerIntent, 1);
		}
		
	/**
	 * Called when a view has been clicked. (Buttons, ImageButtons, etc.)
	 * 
	 * @param v- The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){
		
		//Allow users to select image.
		case R.id.addImage:
			openGallery();
			break;
		
		//Finalize and add information entered to contact list.
		//throws e- NullPointException Error.
		case R.id.btAddSchedule:
			
			boolean works1 = true;
			try{
				bImage.buildDrawingCache();
				Bitmap bmap = bImage.getDrawingCache();
				byte[] data = getBytes(bmap);
				String name1 = eName.getText().toString();
				String email1 = eEmail.getText().toString();
				String phone1 = ePhone.getText().toString();
				
				String className = eClass.getText().toString();
				String days = eDays.getText().toString();
				String time = eTime.getText().toString();
				
				String className1 = eClass1.getText().toString();
				String days1 = eDays1.getText().toString();
				String time1 = eTime1.getText().toString();
				
				String className2 = eClass2.getText().toString();
				String days2 = eDays2.getText().toString();
				String time2 = eTime2.getText().toString();
				
				String className3 = eClass3.getText().toString();
				String days3 = eDays3.getText().toString();
				String time3 = eTime3.getText().toString();
				
				//throws an error message if name is empty.
				if (name1.length() == 0)
				{
					works1 = false;
					Dialog d = new Dialog(this);
					d.setTitle("Error!");
					TextView tView = new TextView(this);
					tView.setText("No Name!!");
					d.setContentView(tView);
					d.show();
				}
				
				//throws an error message if Phone# is less than 10 digits.
				else if (phone1.length() < 10)
				{
					works1 = false;
					Dialog d = new Dialog(this);
					d.setTitle("Error!");
					TextView tView = new TextView(this);
					tView.setText("Minimum 10 digits on Phone#");
					d.setContentView(tView);
					d.show();
				}
				
				else if (works1){
					ContactData cEntry = new ContactData(Add.this);
					cEntry.open();
					cEntry.createEntry(name1, email1, phone1, data);
					cEntry.close();
					
					ContactScheduleData sEntry = new ContactScheduleData(Add.this);
					sEntry.open();
					sEntry.createEntry(name1, className, days, time);
					sEntry.createEntry(name1, className1, days1, time1);
					sEntry.createEntry(name1, className2, days2, time2);
					sEntry.createEntry(name1, className3, days3, time3);
					sEntry.close();
				}
			}catch (Exception e){
				works1 = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error!");
				TextView tView = new TextView(this);
				tView.setText(error);
				d.setContentView(tView);
				d.show();
			}finally{
				if (works1){
					Toast.makeText(this, "Contact created- " + 
							eName.getText().toString(), Toast.LENGTH_SHORT).show();
					
					finish();
					
					Intent j = new Intent("com.project.occ.TABS");
					startActivity(j);
				}
			}
			
			break;
			
		}
	}

/**
 * Called when selected image (bitmap) needs to be saved in 
 * SQLite Database {@link ContactData}.
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
