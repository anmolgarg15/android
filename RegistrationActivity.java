package com.example.womensecurity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity
{
	EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
	Button btn;
	
	String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_registration);
	
	Db_Activity cr = new Db_Activity(getApplicationContext());
	cr.open();
	Integer cnt = cr.checkrecord();
	cr.close();
	if(cnt>0)
	{
		Intent i = new Intent(getApplicationContext(),HomeActivity.class);
		finish();
		startActivity(i);
	}
	ed1 = (EditText)findViewById(R.id.editText1);
	ed2 = (EditText)findViewById(R.id.editText2);
	ed3 = (EditText)findViewById(R.id.editText3);
	ed4 = (EditText)findViewById(R.id.editText4);
	ed5 = (EditText)findViewById(R.id.editText5);
	ed6 = (EditText)findViewById(R.id.editText6);
	ed7 = (EditText)findViewById(R.id.editText7);
	ed8 = (EditText)findViewById(R.id.editText8);
	btn = (Button)findViewById(R.id.button1);
	
	btn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		String name,fname,email,city,state,smob,tomob1,tomob2;
		name = ed1.getText().toString();
		fname = ed2.getText().toString();
		email = ed3.getText().toString();
		city = ed4.getText().toString();
		state = ed5.getText().toString();
		smob = ed6.getText().toString();
		tomob1 = ed7.getText().toString();
		tomob2 = ed8.getText().toString();
		
		if(name.equals("") || fname.equals("") || email.equals("") || city.equals("") || state.equals("") || smob.equals("") || tomob1.equals("") || tomob2.equals(""))
        {
        	Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_LONG).show();
        }
		else if(smob.length()!=10 || tomob1.length()!=10 || tomob2.length()!=10)
		{
			Toast.makeText(getApplicationContext(), "All mobile number should be of 10 digits", Toast.LENGTH_LONG).show();
		}
        else
        {
        	Db_Activity inst = new Db_Activity(getApplicationContext());
    		inst.open();
    		inst.entry(name,fname,email,city,state,smob,tomob1,tomob2);
    		inst.close();
    		
			Toast.makeText(getApplicationContext(), "Successfully Register", Toast.LENGTH_LONG).show();

			ed1.setText("");
	        ed2.setText("");
	        ed3.setText("");
	        ed4.setText("");
	        ed5.setText("");
	        ed6.setText("");
	        ed7.setText("");
	        ed8.setText("");
        			
			Intent i = new Intent(RegistrationActivity.this,HomeActivity.class);
			startActivity(i);
			finish();
        }
		}
	});
}
}
