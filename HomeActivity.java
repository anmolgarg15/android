package com.example.womensecurity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	MediaRecorder mr;
	MediaPlayer mp;
	String path = "";
	Gpstracker gps;
	String filename="";
	SmsManager sm;
	Button btn1;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	btn1 = (Button)findViewById(R.id.button1);
	btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
				getLocation();
		}
	});
}

public void getLocation()
{
	gps = new Gpstracker(HomeActivity.this);
	
	if(gps.canGetLocation())
	{
		double latitude = gps.getLatitude();
		double longitude = gps.getLongitude();
		
		LocationAddress locationAddress = new LocationAddress();
		locationAddress.getAddressFromLocation(latitude,longitude,getApplicationContext(),new GeocoderHandler());
	}
	else
	{
		gps.showSettingsAlert();
	}
	

}
	
@Override
public boolean onCreateOptionsMenu(Menu menu)
{
	getMenuInflater().inflate(R.menu.activity_home, menu);
	return super.onCreateOptionsMenu(menu);
}
public boolean onOptionsItemSelected(MenuItem item)
{
	Integer i = item.getItemId();
			
	if(i == R.id.item1)
	{
	    	 Intent j = new Intent(getApplicationContext(),UpdateActivity.class);
	    	 startActivity(j);
			
	}
	
	if(i == R.id.item2)
	{
		mr.stop();
		mr.release();
		Toast.makeText(getApplicationContext(), "Audio Recording Stop and Save Successfully.",Toast.LENGTH_LONG).show();
		
             File fileLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),filename);
             Uri path = Uri.fromFile(fileLocation);
             Intent emailIntent = new Intent(Intent.ACTION_SEND);
             emailIntent.setType("vnd.android.cursor.dir/email");
             Db_Activity email = new Db_Activity(getApplicationContext());
             email.open();
             String str = email.getemailid();
             email.close();
             String to[] = {str};
             emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
             emailIntent.putExtra(Intent.EXTRA_STREAM,path);
             emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your Subject");
             startActivity(emailIntent);
             Toast.makeText(getApplicationContext(), "Email Prepare to Send",Toast.LENGTH_LONG).show();
	}
	if(i == R.id.item3)
	{
	    	 finish();
			
	}
	
	if(i == R.id.item4)
	{
		 File soundDir = new File(Environment.getExternalStorageDirectory(), "womensecurity");
	            try {
	                //making directories
	                soundDir.mkdirs();
	                FileOutputStream sound = new FileOutputStream(soundDir.getPath() + "/ws.pdf");
	                InputStream is = getResources().openRawResource(R.raw.ws);
	                int a = is.available();
	                byte[] buf = new byte[a];
	                is.read(buf, 0, a);
	                sound.write(buf);
	                sound.flush();
	                sound.close();
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return false;
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return false;
	            }
		


                File pdfFile = new File(Environment.getExternalStorageDirectory() + "/womensecurity/ws.pdf"); 
                Uri path = Uri.fromFile(pdfFile);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW); 
                 pdfIntent.setDataAndType(path, "application/pdf"); 
                 pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                 try{
                      startActivity(pdfIntent);
                 }catch(ActivityNotFoundException e){ 
                      Toast.makeText(getApplicationContext(), "No Application available to view PDF File", Toast.LENGTH_LONG).show();
}


	}

	return false;

}

private class  GeocoderHandler extends Handler
{
	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub
		String locationAddress;
		switch (message.what) {
		case 1:
			Bundle bundle = message.getData();
			locationAddress = bundle.getString("address");
			break;

		default:
			locationAddress = null;
			break;
		}
		Toast.makeText(getApplicationContext(), "Location Tracked.", Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(), locationAddress, Toast.LENGTH_LONG).show();
		
		Db_Activity viewRecord = new Db_Activity(getApplicationContext());
		viewRecord.open();
		String name = viewRecord.getname();
		viewRecord.close();
		
		sm = SmsManager.getDefault();
    	Db_Activity num = new Db_Activity(getApplicationContext());
    	num.open();
    	String mob1 = num.getnumber1();
    	String mob2 = num.getnumber2();
    	num.close();
    /*	Intent i = new Intent(android.content.Intent.ACTION_VIEW);
    	i.putExtra("address",mob1 + ";" + mob2);
    	i.putExtra("sms_body",locationAddress);
    	i.setType("vnd.android-dir/mms-sms");
    	startActivity(i);  */
		sm.sendTextMessage(mob1,null,name+", need help! \n" + locationAddress ,null, null);
		sm.sendTextMessage(mob2,null,name+", need help! \n" + locationAddress ,null, null);
		Toast.makeText(getApplicationContext(), "Location sent by SMS", Toast.LENGTH_LONG).show();
		startrecording();
	}
	
}

public void startrecording()
{
	Random r = new Random();
	Integer n = r.nextInt(9999999-99)+99;
	filename = "a"+n+".3gp";
	path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+filename;
	mr = new MediaRecorder();
	mr.setAudioSource(MediaRecorder.AudioSource.MIC);
	mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	mr.setOutputFile(path);
	try {
		mr.prepare();
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mr.start();
	Toast.makeText(getApplicationContext(), "Audio Recording Started",Toast.LENGTH_LONG).show();
	Toast.makeText(getApplicationContext(), "Please visit MENU to stop the recording.",Toast.LENGTH_LONG).show();
}
}