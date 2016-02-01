package com.project.occ;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides access to a database of personal information (not contact). 
 * Each information has a name, email, phone number
 * and image of user.
 */
public class MyProfileData {

	String[] array = new String[1];
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "my_name";
	public static final String KEY_EMAIL = "my_email";
	public static final String KEY_IMAGE = "my_image";
	public static final String KEY_NUMBER = "my_number";
	
	private static final String DATABASE_NAME = "ProfileDB";
	private static final String DATABASE_TABLE = "ProfileTable";
	private static final int DATABASE_VERSION = 1;
	
	private MyDbHelper myHelper;
	private final Context myContext;
	private SQLiteDatabase myDatabase;
	
	/**
     * This class helps open, create, and upgrade the database file.
     */
	private static class MyDbHelper extends SQLiteOpenHelper{

		public MyDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_EMAIL + " TEXT NOT NULL, " +
					KEY_NUMBER + " TEXT NOT NULL, " +
					KEY_IMAGE + " BLOB);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	public MyProfileData (Context c){
		myContext = c;
	}
	
	///OPEN MY DATA BASE///
	public MyProfileData open() throws SQLException{
		myHelper = new MyDbHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		myHelper.close();
	}
	
	/**INPUT INTO DATABASE
	 * 
	 * @param myName- user name.
	 * @param myEmail- user email.
	 * @param myNumber- user phone number.
	 * @param myImage- user image.
	 * @return- the row ID of the newly inserted row,
	 * 		 or -1 if an error occurred
	 */
	public long profileEntry(String myName, String myEmail, String myNumber,
			byte[] myImage){
		ContentValues cValue = new ContentValues();
		cValue.put(KEY_NAME, myName);
		cValue.put(KEY_EMAIL, myEmail);
		cValue.put(KEY_NUMBER, myNumber);
		cValue.put(KEY_IMAGE, myImage);
		return myDatabase.insert(DATABASE_TABLE, null, cValue); //Insert content entered
	}

	///// EXTRA FUNTIONS///
	
	/**Get informations on the first row of DATA_BASE TABLE.
	 * You only need first row, since personal data holds only one
	 * row of information.
	 * @return- row of data. It is not parsed from String to long.
	 */
	public String getFirstRow(){
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, 
				null, null, null, null, null);
		
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		c.moveToFirst();
		result = c.getString(iRow);
		
		return result;
	}
	
	/**Check if DATABASE_TABLE is empty.
	 * 
	 * @return- returns true if table is not empty; otherwise returns false.
	 */
	public boolean checkEmpty(){
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, 
				null, null, null, null, null);
		return (c.isFirst());
	}
	
	/**Get user's selected image
	 * 
	 * @param l- selected/current row.
	 * @return- image.
	 * @throws SQLException
	 */
	public byte[] getMyImage (long l) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			byte[] image = c.getBlob(4);
			return image;
		}
		return null;
	}
	
	/**Get user's name.
	 * 
	 * @param l- selected/current row.
	 * @return- name.
	 * @throws SQLException
	 */
	public String getMyName(long l) throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, 
				KEY_ROWID + "=" + l, null, null, null, null);
		
		if (c != null)
		{
			c.moveToFirst();
			String myName = c.getString(1);
			return myName;
		}	
		return null;
	}
	
	/**Get user's email
	 * 
	 * @param l- selected/current row.
	 * @return- email.
	 * @throws SQLException
	 */
	public String getMyEmail(long l) throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, 
				KEY_ROWID + "=" + l, null, null, null, null);
		
		if (c != null)
		{
			c.moveToFirst();
			String myEmail = c.getString(2);
			return myEmail;
		}	
		return null;
	}
	
	/**Get user's phone number.
	 * 
	 * @param l- selected/current row.
	 * @return- phone number.
	 * @throws SQLException
	 */
	public String getMyNumber(long l) throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, 
				KEY_ROWID + "=" + l, null, null, null, null);
		
		if (c != null)
		{
			c.moveToFirst();
			String myNumber = c.getString(3);
			return myNumber;
		}	
		return null;
	}
	
	/**Get row number that is under user's name.
	 * 
	 * @param s- user's name.
	 * @return- row number (not parsed).
	 * @throws SQLException
	 */
	public String getMyRow(String s) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL,
				KEY_NUMBER, KEY_IMAGE};
		
		Cursor c = myDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		if (c != null)
		{
			String myRow;
			c.moveToFirst();
			if (s.equals(c.getString(1)))
			{
				myRow = c.getString(0);
				return myRow;
			}
			else
			{
				while (!c.isAfterLast())
				{
					c.moveToNext();
					if (s.equals(c.getString(1)))
					{
						myRow = c.getString(0);
						return myRow;
					}
				}
			}
		}
		
		return null;
	}
	
	/**Update/Edit data.
	 * 
	 * @param lRow- current row.
	 * @param mName- modified name.
	 * @param mEmail- modified email.
	 * @param mNumber- modified number.
	 * @param mImage- modified image.
	 * @throws SQLException
	 */
	public void updateEntry(long lRow, String mName, String mEmail, String mNumber,
			byte[] mImage) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_EMAIL, mEmail);
		cvUpdate.put(KEY_NUMBER, mNumber);
		cvUpdate.put(KEY_IMAGE, mImage);
		
		myDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}

	/**Delete contact from data.
	 * 
	 * @param lRow1- current row.
	 * @throws SQLException
	 */
	public void deleteEntry(long lRow1) throws SQLException{
		// TODO Auto-generated method stub
		myDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
	
}
