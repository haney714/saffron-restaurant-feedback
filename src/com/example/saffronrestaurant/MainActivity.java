package com.example.saffronrestaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import pack.coderzheaven.AndroidPHPConnectionDemo;
import pack.coderzheaven.RegisterActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button sub;
	RatingBar quality, varity, service, MFV;
	TextView repos;
	EditText feedback;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;
	String customer_name, email_address,qual, vari, ser , mValue, fBack;
	View v, v1;
	TextView txt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		quality = (RatingBar)findViewById(R.id.quality_Bar);
		varity = (RatingBar)findViewById(R.id.varity_Bar);
		service = (RatingBar)findViewById(R.id.service_Bar);
		MFV = (RatingBar)findViewById(R.id.MFV_Bar);
		feedback = (EditText)findViewById(R.id.editText1);
		sub = (Button) findViewById(R.id.submit);
		repos = (TextView) findViewById(R.id.response);
		 v = findViewById(R.id.tableLayout1);
		 v1 = findViewById(R.id.thankU);
		 txt1 = (TextView)findViewById(R.id.thankU);
		 
		Intent i= getIntent();
		customer_name= i.getStringExtra("name");
		email_address = i.getStringExtra("email");
		
		sub.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	        	qual = String.valueOf(quality.getRating());
	        	vari = String.valueOf(varity.getRating());
	        	ser = String.valueOf(service.getRating());
	        	mValue = String.valueOf(MFV.getRating());
	        	fBack = feedback.getText().toString();
	        	
	        	dialog =ProgressDialog.show(MainActivity.this, "", 
	                    "Sending Feedback...", true);
				 new Thread(new Runnable() {
					    public void run() {
					    	send_feedback(qual,vari,ser,mValue, fBack);					      
					    }
					  }).start();				
	        }
	        });
	}

private void send_feedback(String qual, String vari, String ser, String mValue,String fBack){
	
try {
	System.out.println(qual +":"+vari+":"+ser+":"+mValue+":"+fBack);	
		httpclient=new DefaultHttpClient();
		//httppost =new HttpPost("http://10.0.2.2/saffron/send_email.php");
		httppost =new HttpPost("http://saffron.6te.net/saffron/send_email.php");
		//adding data
		nameValuePairs =new ArrayList<NameValuePair>(7);
		nameValuePairs.add(new BasicNameValuePair("name",customer_name));  // $Edittext_value = $_POST['Edittext_value'];
		nameValuePairs.add(new BasicNameValuePair("email",email_address));
		nameValuePairs.add(new BasicNameValuePair("quality",qual));  // $Edittext_value = $_POST['Edittext_value'];
		nameValuePairs.add(new BasicNameValuePair("varity",vari)); 
		nameValuePairs.add(new BasicNameValuePair("service",ser));  // $Edittext_value = $_POST['Edittext_value'];
		nameValuePairs.add(new BasicNameValuePair("moneyForValue",mValue));
		nameValuePairs.add(new BasicNameValuePair("feedback",fBack));
		
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		//Execute HTTP Post Request
		//response=httpclient.execute(httppost);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		final String response = httpclient.execute(httppost, responseHandler);
		//String response = responseHandler.toString();
		//System.out.println(response);
		System.out.println("Response : " + response); 
		runOnUiThread(new Runnable() {
		    public void run() {
		    	//repos.setText("Response from PHP : " + response);
				dialog.dismiss();
				
				if(response.contains("Feedback Sending Successfull")){
					runOnUiThread(new Runnable() {
					    public void run() {
					    	Toast.makeText(MainActivity.this,"Feedback Sent", Toast.LENGTH_LONG).show();
					    v.setVisibility(View.GONE);
					    v1.setVisibility(View.VISIBLE);
					    }
					});
					
				   }
			     else{
					Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
					txt1.setText("Something Went wrong please try again later");
					v.setVisibility(View.GONE);
				    v1.setVisibility(View.VISIBLE);
				   }
		    }
		});
		
		
	}catch(Exception e){
		dialog.dismiss();
		System.out.println("Exception : " + e.getMessage());
	}//try ending
	
}
}
