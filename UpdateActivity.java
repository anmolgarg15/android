package com.example.womensecurity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity
{
     EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
     Button btn;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_update);
        	ed1 = (EditText)findViewById(R.id.editText1);
        	ed2 = (EditText)findViewById(R.id.editText2);
        	ed3 = (EditText)findViewById(R.id.editText3);
        	ed4 = (EditText)findViewById(R.id.editText4);
        	ed5 = (EditText)findViewById(R.id.editText5);
        	ed6 = (EditText)findViewById(R.id.editText6);
        	ed7 = (EditText)findViewById(R.id.editText7);
        	ed8 = (EditText)findViewById(R.id.editText8);
        	btn = (Button)findViewById(R.id.button1);

        	
        	Db_Activity viewRecord = new Db_Activity(getApplicationContext());
			viewRecord.open();
			String name = viewRecord.getname();
			String fname = viewRecord.getfname();
			String email = viewRecord.getemail();
			String city = viewRecord.getcity();
			String state = viewRecord.getstate();
			String smob = viewRecord.getsmob();
			String tomob1 = viewRecord.gettomob1();
			String tomob2 = viewRecord.gettomob2();
			viewRecord.close();
			ed1.setText(name);
			ed2.setText(fname);
			ed3.setText(email);
			ed4.setText(city);
			ed5.setText(state);
			ed6.setText(smob);
			ed7.setText(tomob1);
			ed8.setText(tomob2);
					
        	btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String name,fname,email,city,state,smob,tomob1,tomob2;
					Db_Activity updateRecord = new Db_Activity(getApplicationContext());
					updateRecord.open();
					name = ed1.getText().toString();
					fname = ed2.getText().toString();
					email = ed3.getText().toString();
					city = ed4.getText().toString();
					state = ed5.getText().toString();
					smob = ed6.getText().toString();
					tomob1 = ed7.getText().toString();
					tomob2 = ed8.getText().toString();
					
					if(name.equals("") || fname.equals("") || email.equals("") || city.equals("") 
					|| state.equals("") || smob.equals("") || tomob1.equals("") || tomob2.equals(""))
			        {
			        	Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_LONG).show();
			        
			        }
					else
					{
						updateRecord.updateRecord( name,fname,email,city,state,smob,tomob1,tomob2);
						Toast.makeText(getApplicationContext(),"Record Updated Successfully",Toast.LENGTH_LONG).show();
					
						finish();
					}
					updateRecord.close();
				}
			});
       }
}
