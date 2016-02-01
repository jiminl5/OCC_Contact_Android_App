package com.project.occ;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;  
import android.app.ListActivity;
import android.content.Intent;

public class Classes extends Activity {  
    
  private ListView mainListView ;  
  private ArrayAdapter<String> listAdapter ;  
  ArrayList<String> nameList = new ArrayList<String>(); 
  ArrayList<String> nameList1 = new ArrayList<String>(); 

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
  
  /** Called when the activity is first created. */  
  @Override  
  public void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.classes);  

    // Find the ListView resource.   
    mainListView = (ListView) findViewById( R.id.listNameView);  
  
    Bundle b = getIntent().getExtras();
    String receivedClass = b.getString("rClassName");
    
    nameList1.clear();
    
    ContactScheduleData className1 = new ContactScheduleData(this);
	className1.open();
	nameList1 = className1.getNameWithClass(receivedClass);
	className1.close();
    
	if (nameList1.size() == 0){
		Toast.makeText(this, "No Matches", Toast.LENGTH_SHORT).show();
		finish();
		Intent i = new Intent("com.project.occ.TABS");
		startActivity(i);
	}
	
	Collections.sort(nameList1);
	
    nameList.addAll(nameList1);  
      
    listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, nameList);  

    // Set the ArrayAdapter as the ListView's adapter.  
    mainListView.setAdapter( listAdapter );
    
    mainListView.setOnItemClickListener(new OnItemClickListener() {
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		String name = ((TextView) view).getText().toString();
    		finish();
    		
    		Intent i = new Intent("com.project.occ.VIEW");
    		
    		i.putExtra("sName", name);
    		
    		startActivity(i);
    	}
    });
  }  
  /*
  @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		try{
			finish();
			String name = (String) getListAdapter().getItem(position);
			Toast.makeText(this, "Selected: " + name, Toast.LENGTH_SHORT).show();
			
			Intent i = new Intent("com.project.occ.VIEW1");
			
			//Put extra (clicked title, name) to intent, and pass this varaible
			//to next activity which was called by the intent.
			i.putExtra("sName1", name);
			
			startActivity(i);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
  */
}  