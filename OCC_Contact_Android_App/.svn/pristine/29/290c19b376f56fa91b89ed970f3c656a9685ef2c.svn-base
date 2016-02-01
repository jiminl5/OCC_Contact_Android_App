package com.project.occ;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
//import android.app.Dialog;
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
//import android.widget.ImageButton;
import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author JiMin
 * 
 * Provides activity to Edit contacts.
 *
 */
public class Editcontact extends Activity implements OnClickListener{
	
	/**
	 * called when the activity is first created
	 * */
	Button bUpdate, bBringInfo, bUpdateSchedule, bPickPhoto, bAddMore;
	EditText uName, uEmail, uNumber, uClass, uDays, uTime;
	String updateName, updateEmail, updateRow, updateNumber,
		updateClass, updateDays, updateTime;
	ImageView iV;
	Bitmap bmp1;
	
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
		
		//Set the activity content to an explicit view.(XML layout)
		setContentView(R.layout.editcontact);
		
		//Shows current image.
		iV = (ImageView) findViewById(R.id.iV);
		
		//Update Information.
		bUpdate = (Button) findViewById(R.id.btUpdate);
		bBringInfo = (Button) findViewById(R.id.btBringInfo);
		bUpdateSchedule = (Button) findViewById(R.id.btEditSchedule);
		bPickPhoto = (Button) findViewById(R.id.btEditImage);
		
		//Adds more schedule
		bAddMore = (Button) findViewById(R.id.btAddmore);
		
		//Field to enter new information.
		uName = (EditText) findViewById(R.id.updateName);
		uEmail = (EditText) findViewById(R.id.updateEmail);
		uNumber = (EditText) findViewById(R.id.updateNumber);
		
		uClass = (EditText) findViewById(R.id.updateClass);
		uDays = (EditText) findViewById(R.id.updateDays);
		uTime = (EditText) findViewById(R.id.updateTime);
		
		// Register a callback to be invoked when this view is clicked. 
		// If this view is not clickable, it becomes clickable.
		bAddMore.setOnClickListener(this);
		bUpdate.setOnClickListener(this);
		bBringInfo.setOnClickListener(this);
		bUpdateSchedule.setOnClickListener(this);
		bPickPhoto.setOnClickListener(this);
		
		//Bundle getString(), brings all the informations that were passed
		//from previous activity {@link View}
		Bundle b = getIntent().getExtras();
		updateName = b.getString("uName");
		updateEmail = b.getString("uEmail");
		updateRow = b.getString("uRow");
		updateNumber = b.getString("uNumber");
		
		//Opens DATABASE {@link ContactData}
		ContactData info1 = new ContactData(this);
		info1.open();
		long parsedLong = Long.parseLong(updateRow);
		//Get image from DATABASE.
		byte[] dataImage1 = info1.getImage(parsedLong);
		
		info1.close();
		
		//StringBuilder removes '(', ')', and '-' from Phone number.
		StringBuilder builder = new StringBuilder(updateNumber);
		builder.deleteCharAt(0);
		builder.deleteCharAt(3);
		builder.deleteCharAt(6);
		
		iV.setImageBitmap(getImage1(dataImage1));
		uName.setText(updateName);
		uEmail.setText(updateEmail);
		uNumber.setText(builder.toString());
	}
	
	/**GetImage from DATABASE {@link ContactData}
	 * 
	 * @param dataImage2- byte[] converting to Bitmap.
	 * @return- converted Bitmap.
	 */
	public static Bitmap getImage1(byte[] dataImage2) {
		// TODO Auto-generated method stub
		return BitmapFactory.decodeByteArray(dataImage2, 0, dataImage2.length);
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
	              
	                if(bmp1 != null && !bmp1.isRecycled())
	                {
	                    bmp1 = null;                
	                }
	                                
	                bmp1 = BitmapFactory.decodeFile(filePath);
	                iV.setBackgroundResource(0);
	                iV.setImageBitmap(bmp1);              
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
		case R.id.btEditImage:
			openGallery1();
			break;
		
		//Update contact Information.
		case R.id.btUpdate:
			try{
				iV.buildDrawingCache();
				Bitmap bmap1 = iV.getDrawingCache();
				byte[] data1 = getBytes(bmap1);
				String newName = uName.getText().toString();
				String newEmail = uEmail.getText().toString();
				String newNumber = uNumber.getText().toString();
				String newRow = updateRow;
				long newlRow = Long.parseLong(newRow);
				
				//open contact database.
				ContactData updated = new ContactData(this);
				updated.open();
				updated.updateEntry(newlRow, newName, newEmail, newNumber, data1);
				updated.resArray();
				updated.close();
				
				//open schedule database.
				ContactScheduleData scheduleInfo = new ContactScheduleData(this);
				scheduleInfo.open();
				scheduleInfo.updateJustName(updateName, newName);
				scheduleInfo.close();

				Toast.makeText(this, newName + "("
						+ updateName + ")- Modified", Toast.LENGTH_SHORT).show();
				
				finish();
				Intent i = new Intent("com.project.occ.TABS");
				startActivity(i);
				
			}catch (Exception e){
				e.printStackTrace();
			}
		break;
		
		//Allow users to add more classes.
		case R.id.btAddmore:
			String newName2 = uName.getText().toString();
			
			String newClass2 = uClass.getText().toString();
			String newDays2 = uDays.getText().toString();
			String newTime2 = uTime.getText().toString();
			
			ContactScheduleData scheduleInfo = new ContactScheduleData(this);
			scheduleInfo.open();
			scheduleInfo.createEntry(newName2, newClass2, newDays2, newTime2);
			scheduleInfo.close();
			
			Toast.makeText(this,"Added - "
					+ newClass2, Toast.LENGTH_SHORT).show();
			
			finish();
			startActivity(getIntent());
			
			break;
		
		//Bring information of classes, and allow users to modify them.
		case R.id.btBringInfo:
			try{
				String keyClass = uClass.getText().toString();
				ContactScheduleData scheduleInfo1 = new ContactScheduleData(this);
				scheduleInfo1.open();
				long keyRow = Long.parseLong(scheduleInfo1.getClassRow(keyClass));
				uDays.setText(scheduleInfo1.getDays(keyRow));
				uTime.setText(scheduleInfo1.getTime(keyRow));
				scheduleInfo1.close();
				
				if (uDays.getText().toString().length()==0 && 
						uTime.getText().toString().length()==0) //Somehow isEmpty() is not working in this statement.
					Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(this, "Brought Info", Toast.LENGTH_SHORT).show();
				
			}catch (Exception e){
				e.printStackTrace();
			}
		break;
		
		//Finalize and edit/add class brought by {@link btBringInfo}
		case R.id.btEditSchedule:
			try{
				String newName1 = uName.getText().toString();
				String newClass1 = uClass.getText().toString();
				String newDays1 = uDays.getText().toString();
				String newTime1 = uTime.getText().toString();
				
				ContactScheduleData scheduleInfo2 = new ContactScheduleData(this);
				scheduleInfo2.open();
				long keyRow = Long.parseLong(scheduleInfo2.getClassRow(newClass1));
				scheduleInfo2.updateEntry(keyRow, newName1, newClass1, newDays1, newTime1);
				scheduleInfo2.close();
				
				Toast.makeText(this, "Updated Schedule"
						, Toast.LENGTH_SHORT).show();
				
				finish();
				
				Intent i1 = new Intent("com.project.occ.TABS");
				startActivity(i1);
			}catch (Exception e){
				e.printStackTrace();
			}
		break;
		
		}
		
	}

	/**
	 * Called when selected image (bitmap) needs to be saved in 
	 * SQLite Database {@link ContactData}.
	 * 
	 * @param bmp3- Bitmap which is going be parsed.
	 * @return bytes[]
	 */
	private byte[] getBytes(Bitmap bmp3) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bmp3.compress(CompressFormat.PNG, 0, stream);
	    return stream.toByteArray();
	}

	/**
	 * Calls onActivityResult.
	 */
	private void openGallery1() {
		// TODO Auto-generated method stub
		Intent photoPickerIntent1 = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent1.setType("image/*");
        startActivityForResult(photoPickerIntent1, 1);
	}
	
}
