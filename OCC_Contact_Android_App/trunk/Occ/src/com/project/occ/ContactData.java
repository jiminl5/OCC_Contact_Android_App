package com.project.occ;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides access to a database of contact (not personal). 
 * Each information has a name, email, a phone number 
 * and a selected image.
 */
public class ContactData {

	ArrayList<String> arList = new ArrayList<String>();
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_EMAIL = "persons_email";
	public static final String KEY_IMAGE = "image_data";
	public static final String KEY_NUMBER = "persons_number";
	
	private static final String DATABASE_NAME = "ContactDB";
	private static final String DATABASE_TABLE = "contactTable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	/**
     * This class helps open, create, and upgrade the database file.
     */
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
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
			// Get the database
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public ContactData(Context c){
		ourContext = c;
	}
	
	////OPEN DATA BASE////
	public ContactData open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	/////Close DATA BASE///
	public void close(){
		ourHelper.close();
	}

	/** Input into database.
	 * 
	 * @param name- contact name.
	 * @param email- contact email.
	 * @param number- contact number.
	 * @param image- contact image.
	 * 
	 * @return- the row ID of the newly inserted row,
	 * 		 or -1 if an error occurred 
	 */
	public long createEntry(String name, String email, String number, byte[] image) {
		// TODO Auto-generated method stub
		ContentValues cValue = new ContentValues();
		cValue.put(KEY_NAME, name);
		cValue.put(KEY_EMAIL, email);
		cValue.put(KEY_NUMBER, number);
		cValue.put(KEY_IMAGE, image);
		return ourDatabase.insert(DATABASE_TABLE, null, cValue); //insert content entered
	}
	
	/**Set title (name) of contact and display on the list view {@link Menu}.
	 * 
	 * @return list view.
	 * @throws SQLException
	 */
	public ArrayList<String> resArray() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		
		// Get the database and run the query
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = c.getString(iName);
			arList.add(result);
		}
				
		return arList;
	}
	
	/** Bring the last long of row.
	 * 
	 * @return- last long of row.
	 * @throws SQLException
	 */
	public long resRow() throws SQLException{ //result Name
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		long result = 1;
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = c.getLong(iRow);
		}
				
		return result;
	}
	
	/**Bring every single name in the database.
	 * 
	 * @return- names that are in the database.
	 * @throws SQLException
	 */
	public String resName() throws SQLException{ //result Name
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

		int iName = c.getColumnIndex(KEY_NAME);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = c.getString(iName);
		}
				
		return result;
	}

	/**Bring every email in the database.
	 * 
	 * @return emails that are in the database.
	 * @throws SQLException
	 */
	public String resEmail() throws SQLException{ //result Name
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

		int iEmail = c.getColumnIndex(KEY_EMAIL);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = c.getString(iEmail);
		}
				
		return result;
	}
	
	/**Bring specific image (selected image from gallery).
	 * 
	 * @param l- specific/selected row.
	 * @return- specific/selected image.
	 * @throws SQLException
	 */
	public byte[] getImage (long l) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			byte[] image = c.getBlob(4);
			return image;
		}
		return null;
	}
	
	/**Bring specific name chosen by user.
	 * 
	 * @param l- specific/selected row.
	 * @return- specific/selected name.
	 * @throws SQLException
	 */
	public String getName(long l) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	/**Bring specific email chosen by user.
	 * 
	 * @param l- specific/selected row.
	 * @return- specific/selected Email.
	 * @throws SQLException
	 */
	public String getEmail(long l) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String email = c.getString(2);
			return email;
		}
		return null;
	}
	
	/**Bring specific number chosen by user.
	 * 
	 * @param l- specific/selected row.
	 * @return- specific/selected Phone number.
	 * @throws SQLException
	 */
	public String getNumber(long l) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String email = c.getString(3);
			return email;
		}
		return null;
	}
	
	/**Bring specific long of row.
	 * 
	 * @param s- specific/selected name.
	 * @return- specific/selected row.
	 * @throws SQLException
	 */
	public String getRow(String s) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_NUMBER, KEY_IMAGE};
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
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

	/**Update/Edit data.
	 * 
	 * @param lRow- selected/current row.
	 * @param mName- modified name.
	 * @param mEmail- modified email.
	 * @param mNumber- modified number.
	 * @param mImage- modified image.
	 * @throws SQLException
	 */
	public void updateEntry(long lRow, String mName, String mEmail, String mNumber, byte[] mImage) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_EMAIL, mEmail);
		cvUpdate.put(KEY_NUMBER, mNumber);
		cvUpdate.put(KEY_IMAGE, mImage);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}

	/**Delete contact from data.
	 * 
	 * @param lRow1- selected/current row.
	 * @throws SQLException
	 */
	public void deleteEntry(long lRow1) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
	
}
