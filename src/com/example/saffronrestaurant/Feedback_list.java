package com.example.saffronrestaurant;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback_list extends Activity{
String[] fId, fUser_name, fEmail, fQuality, fService, fVarity, fMoney4val, fFeedback;
float fRatingAverage[];
	ProgressBar progressbar; 
	ListView list=null;
	TextView txtv1;
	LazyImageLoadAdapter adapter;
	List<NameValuePair> nameValuePairs;
	int indicator_cat =1;	
	String result = null;
	InputStream is = null;
	JSONObject json_data;
	JSONArray jArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_list);
		 list = (ListView)findViewById(R.id.list);
		 txtv1 = (TextView)findViewById(R.id.textView1);
	     progressbar = (ProgressBar)findViewById(R.id.progressbar_category);
	        new MyTask().execute();
	        
	}
	
	private class MyTask extends AsyncTask<Void , Void, Void > {
		   @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			//progressdialog = ProgressDialog.show(Category.this.getActivity(),"Processing","Rendering List");
		}
		protected void onPostExecute(Void feed) {
				//progressdialog.dismiss();
			  try{ 	
				  if(!result.contains("Record is not available")){
					  progressbar.setVisibility(View.GONE);
					  list.setVisibility(View.VISIBLE);
		    			adapter= new LazyImageLoadAdapter(jArray,com.example.saffronrestaurant.Feedback_list.this);
		    		    //Set adapter to listview
		    		    list.setAdapter(adapter);
				  }else{
					  progressbar.setVisibility(View.GONE);
					  txtv1.setVisibility(View.VISIBLE);
				  }
		   }catch(Exception e){
   			e.printStackTrace();
   		} 
	  }

		   @Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getDataFromDb(); 
			return null;
		}
	}
		public void getDataFromDb() {	
			//convert response to string
			try{
		        HttpClient httpclient = new DefaultHttpClient();
		        //HttpPost httppost = new HttpPost("http://10.0.2.2/saffron/feedback_list.php");
		        HttpPost httppost = new HttpPost("http://saffron.6te.net/saffron/feedback_list.php");
		        /*nameValuePairs = new ArrayList<NameValuePair>(1);
	   	        nameValuePairs.add(new BasicNameValuePair("indicator",Integer.toString(indicator_cat)));
	   	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));*/
	   	        
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		       System.out.println("returened result is :"+ is.toString());
		        Log.e("log_tag", "connection success ");
		     //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
		}
		catch(Exception e)
		{
		        Log.e("log_tag", "Error in http connection "+e.toString());
		        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

		}
			try
			{
			        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			        StringBuilder sb = new StringBuilder();
			        String line = null;
			        while ((line = reader.readLine()) != null) 
			        {
			                sb.append(line + "\n");
			              //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
			        }
			        is.close();
			        result=sb.toString();
					System.out.println("jason data :"+ result.toString());				
			}
			catch(Exception e)
			{
			       Log.e("log_tag", "Error converting result "+e.toString());
			    Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();
			}
			
			try {
				jArray = new JSONArray(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//parse json data
			/*try
			{	
				//JSONObject Jasonobject = new JSONObject(result);
				//JSONArray jArray = Jasonobject.getJSONArray("0");
		    
			for(int i=0;i<jArray.length()-1;i++){
				System.out.println("\n Id :"+fId[i]);
				System.out.println("\n Title :"+fUser_name[i]);
				System.out.println("\n Title :"+fEmail[i]);
				System.out.println("\n Type :"+fQuality[i]);
				System.out.println("\n Price :"+fVarity[i]);
				System.out.println("\n Image :"+fService[i]);
				System.out.println("\n Image :"+fMoney4val[i]);
				System.out.println("\n Image :"+fFeedback[i]);
			}	
			}
			catch(Exception e)
			{
			        Log.e("log_tag", "Error parsing data "+e.toString());
			        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
			}*/
			
		}// end of method

	public void OnItemClick(String mPosition){
		int n = Integer.parseInt(mPosition)-1;
		System.out.println("TEMP VALUE :"+mPosition);
		//Toast.makeText(fg.getActivity().getApplicationContext(),"Item Position : "+mPosition,Toast.LENGTH_LONG).show();
	/*	Intent i = new Intent(getApplicationContext(),this);
    	i.putExtra("id", fId[n]);
    	i.putExtra("name", fUser_name[n]);
    	i.putExtra("email",fEmail[n]);
    	i.putExtra("quality",fQuality[n]);
    	i.putExtra("varity",fVarity[n]);
    	i.putExtra("service",fService[n]);
    	i.putExtra("money4val",fMoney4val[n]);
    	i.putExtra("feedback",fFeedback[n]);
    	startActivity(i);*/
	}
}
