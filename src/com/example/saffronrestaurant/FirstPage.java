package com.example.saffronrestaurant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstPage extends Activity{
EditText name, email;
Button next_btn,feedback_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_page);
		
		name = (EditText)findViewById(R.id.name);
		email = (EditText)findViewById(R.id.email);
		next_btn = (Button)findViewById(R.id.next_btn);
		feedback_btn = (Button)findViewById(R.id.fdback_btn);
		
		next_btn.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    String name1 = name.getText().toString();
			    String email1 = email.getText().toString();
			    
				Intent i = new Intent(getApplicationContext(),MainActivity.class);
			    if(name1.isEmpty() || email1.isEmpty()){
			    	Toast.makeText(getApplicationContext(), "Please Fill Proper Information", Toast.LENGTH_LONG).show();
			    }
			    else{
			      i.putExtra("name", name.getText().toString());
			      i.putExtra("email", email.getText().toString());
			      startActivity(i);
			    }
			}
		});
		
		feedback_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent fdb = new Intent(getApplicationContext(),Feedback_list.class);
				startActivity(fdb);
			}
		});
	}
	

}
