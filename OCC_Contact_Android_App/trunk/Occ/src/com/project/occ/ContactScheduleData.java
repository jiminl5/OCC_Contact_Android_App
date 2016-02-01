package com.project.occ;

//import java.util.ArrayList;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides access to a database of contact schedule and personal. 
 * Each information has a name, name of class, day of class 
 * and time of class.
 */
public class ContactScheduleData {
	ArrayList<String> nameList = new ArrayList<String>();
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "user_name";
	public static final String KEY_CLASS = "class_name";
	public static final String KEY_DAYS = "class_days";
	public static final String KEY_TIME = "class_time";
	
	private static final String DATABASE_NAME = "ScheduleDB";
	private static final String DATABASE_TABLE = "contactTable";
	private static final int DATABASE_VERSION = 1;
	
	private ScheduleDbHelper scheduleHelper;
	private final Context scheduleContext;
	private SQLiteDatabase scheduleDatabase;
	
	/**
     * This class helps open, create, and upgrade the database file.
     */
	private static class ScheduleDbHelper extends SQLiteOpenHelper{

		public ScheduleDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_CLASS + " TEXT NOT NULL, " +
					KEY_DAYS + " TEXT NOT NULL, " +
					KEY_TIME + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public ContactScheduleData(Context c){
		scheduleContext = c;
	}
	
	///////////OPEN DATABASE////////
	public ContactScheduleData open() throws SQLException{
		scheduleHelper = new ScheduleDbHelper(scheduleContext);
		scheduleDatabase = scheduleHelper.getWritableDatabase();
		return this;
	}
	
	// Close DATABASE
	public void close(){
		scheduleHelper.close();
	}
	
	/**Input into DATABASE
	 * 
	 * @param name- contact name.
	 * @param className- name of class.
	 * @param days- class dates.
	 * @param time- class times.
	 * @return- the row ID of the newly inserted row,
	 * 		 or -1 if an error occurred
	 */
	public long createEntry(String name, String className, String days, String time){
		ContentValues cValue = new ContentValues();
		cValue.put(KEY_NAME, name);
		cValue.put(KEY_CLASS, className);
		cValue.put(KEY_DAYS, days);
		cValue.put(KEY_TIME, time);
		return scheduleDatabase.insert(DATABASE_TABLE, null, cValue);
	}
	
	/**Bring selected name from menu.
	 * 
	 * @param l- specific/selected row.
	 * @return- selected name.
	 * @throws SQLException
	 */
	public String getName(long l) throws SQLException{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}
	
	/**Bring row of selected name.
	 * 
	 * @param s- specific/selected name.
	 * @return- row of selected name.
	 * @throws SQLException
	 */
	public String getNameRow(String s) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		String row = "";
		
		if (c != null)
		{
			c.moveToFirst();
			if (s.equals(c.getString(1)))
			{
				row = c.getString(0);
				return row;
			}
			else
			{
				while (!c.isAfterLast())
				{
					c.moveToNext();
					if (s.equals(c.getString(1)))
					{
						row = c.getString(0);
						return row;
					}
				}
			}
		}
		
		return row;
	}
	
	/**Check whether DATABASE_TABLE is empty or not; 
	 * if empty,bring the row (long) of the name located at the last row.
	 * 
	 * @param l- specific/selected row.
	 * @return- name located at the last row.
	 * @throws SQLException
	 */
	public boolean checkLastNameRow(long l) throws SQLException{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		//boolean check;// = true;
		long result;
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		

			c.moveToLast();
			result = Long.parseLong(c.getString(iRow));
		return (result == l);
	}
	
	/**Bring specific row of the class, which is under selected name.
	 * 
	 * @param s- specific/selected name.
	 * @return- specific row.
	 * @throws SQLException
	 */
	public String getClassRow(String s) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		if (c != null)
		{
			String row;
			c.moveToFirst();
			if (s.equals(c.getString(2)))
			{
				row = c.getString(0);
				return row;
			}
			else
			{
				while (!c.isAfterLast())
				{
					c.moveToNext();
					if (s.equals(c.getString(2)))
					{
						row = c.getString(0);
						return row;
					}
				}
			}
		}
		
		return null;
	}
	
	/**Bring days of class. 
	 * 
	 * @param l- specific/selected row.
	 * @return- class days.
	 * @throws SQLException
	 */
	public String getDays(long l) throws SQLException{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(3);
			return name;
		}
		return null;
	}
	
	/**Bring time of class.
	 * 
	 * @param l- specific/selected row.
	 * @return- class time.
	 * @throws SQLException
	 */
	public String getTime(long l) throws SQLException{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(4);
			return name;
		}
		return null;
	}
	
	/**Bring classes that are under selected name.
	 * 
	 * @param keyName- specific/selected name.
	 * @return- whole class.
	 * @throws SQLException
	 */
	public String resClass(String keyName) throws SQLException{ //result Class
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String tempStr = "";
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iClass = c.getColumnIndex(KEY_CLASS);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			tempStr = c.getString(iName);
			if (tempStr.equals(keyName)){
				result = result + c.getString(iClass) + "\n";
			}
		}
		String adjusted = result.replaceAll("(?m)^[ \t]*\r?\n", "");
				
		return adjusted;
	}
	
	/**Bring class days that are under selected name.
	 * 
	 * @param keyName- specific/selected name.
	 * @return- class days.
	 * @throws SQLException
	 */
	public String resDays(String keyName) throws SQLException{ //result Days
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String tempStr = "";
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iDays = c.getColumnIndex(KEY_DAYS);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			tempStr = c.getString(iName);
			if (tempStr.equals(keyName)){
				result = result + c.getString(iDays) + "\n";
			}
		}
		String adjusted = result.replaceAll("(?m)^[ \t]*\r?\n", "");
		
		return adjusted;
	}
	
	/**Bring class times that are under selected name.
	 * 
	 * @param keyName- specific/selected name.
	 * @return- class times.
	 * @throws SQLException
	 */
	public String resTime(String keyName) throws SQLException{ //result Time
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String tempStr = "";
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iTime = c.getColumnIndex(KEY_TIME);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			tempStr = c.getString(iName);
			if (tempStr.equals(keyName)){
				result = result + c.getString(iTime) + "\n";
			}
		}
		String adjusted = result.replaceAll("(?m)^[ \t]*\r?\n", "");
		
		return adjusted;
	}
	
	/**Update/Edit data.
	 * 
	 * @param lRow- current row.
	 * @param mName- modified name.
	 * @param mClass- modified class.
	 * @param mDays- modified days.
	 * @param mTime- modified time.
	 * @throws SQLException
	 */
	public void updateEntry(long lRow, String mName, String mClass, String mDays, String mTime) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_CLASS, mClass);
		cvUpdate.put(KEY_DAYS, mDays);
		cvUpdate.put(KEY_TIME, mTime);
		scheduleDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}
	
	/**Update/Edit just the name.
	 * 
	 * @param oldName- old name.
	 * @param mName- modified new name.
	 * @throws SQLException
	 */
	public void updateJustName(String oldName, String mName) throws SQLException{
		// TODO Auto-generated method stub
		if(!mName.equals(oldName)){
			ContentValues cvUpdate = new ContentValues();
			cvUpdate.put(KEY_NAME, mName);
			scheduleDatabase.update(DATABASE_TABLE, cvUpdate, KEY_NAME + " = '" 
					+ oldName +"'", null);
		}
	}

	/**Delete contact from data.
	 * 
	 * @param lRow1- current row.
	 * @throws SQLException
	 */
	public void deleteEntry(long lRow1) throws SQLException{
		// TODO Auto-generated method stub
		scheduleDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
	
	/**Delete schedules under selected name.
	 * 
	 * @param name- specific/selected name.
	 * @throws SQLException
	 */
	public void deleteSchedule(String name) throws SQLException{
		scheduleDatabase.delete(DATABASE_TABLE, KEY_NAME + " = '" 
				+ name +"'", null);
	}

	public ArrayList<String>getNameWithClass(String className) throws SQLException{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_CLASS,
				KEY_DAYS, KEY_TIME};
		Cursor c = scheduleDatabase.query(DATABASE_TABLE, columns, null
				, null, null, null, null);
		
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iClass = c.getColumnIndex(KEY_CLASS);
		
		if (c != null)
		{
			c.moveToFirst();
			while(!c.isAfterLast())
			{
				if (c.getString(iClass).equals(className))
				{
					result = c.getString(iName);
					nameList.add(result);
				}
				c.moveToNext();
			}
		}
		return nameList;
	}
	
}
