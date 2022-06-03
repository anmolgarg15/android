package com.example.womensecurity;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Db_Activity 
{
static final String Key_rowid = "id";
static final String Key_name = "person_name";
static final String Key_fname = "person_fname";
static final String Key_email = "person_email";
static final String Key_city = "person_city";
static final String Key_state = "person_state";
static final String Key_smob = "person_smob";
static final String Key_tomob1 = "person_tomob1";
static final String Key_tomob2 = "person_tomob2";

static final String Database_name = "my_db10";
static final String Database_Table = "people_table10";
static final int Database_Version = 1;

Dbhelper ourhelper;
final Context ourContext;
SQLiteDatabase ourdatabase;

private static class Dbhelper extends SQLiteOpenHelper
{
	public Dbhelper(Context context)
	{
		super(context,Database_name,null,Database_Version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + Database_Table + "(" + 
		Key_rowid + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
		Key_name + " TEXT NOT NULL," +
		Key_fname + " TEXT NOT NULL," +
		Key_email + " TEXT NOT NULL," +
		Key_city + " TEXT NOT NULL," + 
		Key_state + " TEXT NOT NULL," + 
		Key_smob + " TEXT NOT NULL," + 
		Key_tomob1 + " TEXT NOT NULL," + 
		Key_tomob2 + " TEXT NULL);");
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Database_Table);
        onCreate(db);	
	}
}
public Db_Activity(Context c)
{
	ourContext = c;
}
public Db_Activity open()
{
	ourhelper = new Dbhelper(ourContext);
	ourdatabase = ourhelper.getWritableDatabase();
	return this;
}
public void close()
{
	ourhelper.close();
}
public long entry(String name,String fname,String email,String city,String state,String smob,String tomob1,String tomob2)
{
	ContentValues cv = new ContentValues();
	cv.put(Key_name, name);
	cv.put(Key_fname,fname);
	cv.put(Key_email,email);
	cv.put(Key_city,city);
	cv.put(Key_state, state);
	cv.put(Key_smob, smob);
	cv.put(Key_tomob1, tomob1);
	cv.put(Key_tomob2, tomob2);
	return ourdatabase.insert(Database_Table, null, cv);
}
public String getRecord() {
	// TODO Auto-generated method stub
	String[] columns = new String[] {Key_rowid,Key_name,Key_fname,Key_email,Key_city,Key_state,Key_smob,Key_tomob1,Key_tomob2};
	Cursor c = ourdatabase.query(Database_Table, columns, null, null, null,null, null);
	String result = "";
	if(c.moveToFirst())
	{
		result = result + c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n" + c.getString(3)+ "\n" + c.getString(4)+ "\n" + c.getString(5)+ "\n" + c.getString(6)+ "\n" + c.getString(7)+ "\n" + c.getString(8)+ "\n";
	}
	return result;
}
	
public String getname()
{
	String[] columns = new String[] {Key_rowid,Key_name};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String name="";
	if(c!=null)
	{
		c.moveToFirst();
		name  = c.getString(1);
	}
	return name;
}
public String getfname()
{
	String[] columns = new String[] {Key_rowid,Key_fname};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String fname="";
	if(c!=null)
	{
		c.moveToFirst();
		fname  = c.getString(1);
	}
	return fname;
}
public String getemail()
{
	String[] columns = new String[] {Key_rowid,Key_email};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String email="";
	if(c!=null)
	{
		c.moveToFirst();
		email  = c.getString(1);
	}
	return email;
}
public String getcity()
{
	String[] columns = new String[] {Key_rowid,Key_city};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String city="";
	if(c!=null)
	{
		c.moveToFirst();
		city = c.getString(1);
	}
	return city;
}
public String getstate()
{
	String[] columns = new String[] {Key_rowid,Key_state};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String state="";
	if(c!=null)
	{
		c.moveToFirst();
		state = c.getString(1);
	}
	return state;
}
public String getsmob()
{
	String[] columns = new String[] {Key_rowid,Key_smob};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String smob="";
	if(c!=null)
	{
		c.moveToFirst();
		smob = c.getString(1);
	}
	return smob;
}
public String gettomob1()
{
	String[] columns = new String[] {Key_rowid,Key_tomob1};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String tomob1="";
	if(c!=null)
	{
		c.moveToFirst();
		tomob1 = c.getString(1);
	}
	return tomob1;
}
public String gettomob2()
{
	String[] columns = new String[] {Key_rowid,Key_tomob2};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String tomob2="";
	if(c!=null)
	{
		c.moveToFirst();
		tomob2 = c.getString(1);
	}
	return tomob2;
}
public void updateRecord(String name, String fname, String email, String city,
		String state, String smob, String tomob1, String tomob2) {
	// TODO Auto-generated method stub
	ContentValues cv = new ContentValues();
	cv.put(Key_name, name);
	cv.put(Key_fname, fname);
	cv.put(Key_email, email);
	cv.put(Key_city, city);
	cv.put(Key_state, state);
	cv.put(Key_smob, smob);
	cv.put(Key_tomob1, tomob1);
	cv.put(Key_tomob2, tomob2);
	ourdatabase.update(Database_Table, cv, null, null);

}
public String getemailid() {
	// TODO Auto-generated method stub
	String[] columns = new String[] {Key_email};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String emailid="";
	if(c!=null)
	{
		c.moveToFirst();
		emailid = c.getString(0);
	}
	return emailid;
}
public String getnumber1() {
	// TODO Auto-generated method stub
	String[] columns = new String[] {Key_tomob1};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String num1="";
	if(c!=null)
	{
		c.moveToFirst();
	num1 = c.getString(0);
	}
	return num1;

}
public String getnumber2() {
	// TODO Auto-generated method stub
	String[] columns = new String[] {Key_tomob2};
	Cursor c = ourdatabase.query(Database_Table, columns,null, null, null,null, null);
	String num2="";
	if(c!=null)
	{
		c.moveToFirst();
	num2 = c.getString(0);
	}
	return num2;

}
public Integer checkrecord() {
	// TODO Auto-generated method stub
	String[] columns = new String[] {Key_rowid};
	Cursor c = ourdatabase.query(Database_Table, columns, null, null, null,null, null);
	Integer cnt = c.getCount();
	return cnt;
}
}

