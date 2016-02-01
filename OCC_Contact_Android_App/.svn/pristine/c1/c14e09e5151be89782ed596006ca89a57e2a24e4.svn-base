package com.project.occ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Classmates extends Activity implements OnClickListener{

	EditText nameOfClass;
	Button bringNames;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			//finishes current Activity
			finish();
			//After activity is finished, bring previous activity.
			Intent ourIntent = new Intent("com.project.occ.TESTMENU");
			startActivity(ourIntent);
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.classmates);
		
		nameOfClass = (EditText) findViewById(R.id.nameOfClasses);
		bringNames = (Button) findViewById(R.id.BringNames);
		
		bringNames.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()){
		
		case R.id.BringNames:
			try{
				String temp = nameOfClass.getText().toString();
				finish();
				Intent newIntent = new Intent("com.project.occ.CLASSES");
				
				newIntent.putExtra("rClassName", temp);
				
				startActivity(newIntent);
				
				
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
		
		}
	}
}
