package com.example.saffronrestaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

//Adapter class extends with BaseAdapter and implements with OnClickListener 
public class LazyImageLoadAdapter extends BaseAdapter implements OnClickListener{
    
    private static LayoutInflater inflater=null;
    ProgressDialog dialog = null;
    int flg;
    String[] data,fName;
    //Activity act;
    String[] fId, fUser_name, fEmail, fQuality, fService, fVarity, fMoney4val, fFeedback;
    float fRatingAverage[];

    JSONObject json_data;
    ViewHolder holder;
    Context context;
    @SuppressLint("NewApi")
	public LazyImageLoadAdapter(JSONArray jArray, Activity ac) {
    	context = ac;
    	inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	
    	try {
			String re=jArray.getString(jArray.length()-1);
		System.out.println("Jason Array Length"+jArray.length()+"\n");
	    fId= new String[jArray.length()-1];
	    System.out.println(fId.length);
	    fUser_name = new String[jArray.length()-1];
	    fEmail = new String[jArray.length()-1];
	    fQuality= new String[jArray.length()-1];
	    fVarity = new String[jArray.length()-1];
	    fService = new String[jArray.length()-1];
	    fMoney4val = new String[jArray.length()-1];
	    fFeedback = new String[jArray.length()-1];
	    fRatingAverage = new float[jArray.length()-1];
		   int flag=1;
		for(int i=0;i<jArray.length()-1;i++)
				{               	
			            	 json_data = jArray.getJSONObject(i);
			        	    
	        	              Log.i("log_tag","id: "+json_data.getInt("index")+
	        	                        ", User Name: "+json_data.getString("user_name")+
	        	                        ", Email :"+json_data.getString("email_address")+
	        	                        ", quality: "+json_data.getString("rat_quality") + 
	        	                        ", Varity :"+json_data.getInt("rat_varity")+
	        	                        ", Service :"+json_data.getString("rat_service")); 
	        	              
	        	              fId[i]=json_data.getString("index");
	        	              fUser_name[i]=json_data.getString("user_name");
	        	              fEmail[i]=json_data.getString("email_address");
	        	              fQuality[i]= json_data.getString("rat_quality");
	        	              fVarity[i]=json_data.getString("rat_varity");
	        	              fService[i]=json_data.getString("rat_service");
	        	              fMoney4val[i]=json_data.getString("rat_money_for_value");
	        	              fFeedback[i]=json_data.getString("feedback");
	        	              
	        	    fRatingAverage[i] = (Float.parseFloat(fQuality[i])+Float.parseFloat(fVarity[i])+Float.parseFloat(fService[i])+Float.parseFloat(fMoney4val[i]))/4;
				}
		
		for(int i=0;i<fId.length;i++){
			System.out.println("\n Id :"+fId[i]);
			System.out.println("\n Title :"+fUser_name[i]);
			System.out.println("\n Title :"+fEmail[i]);
			System.out.println("\n Type :"+fQuality[i]);
			System.out.println("\n Price :"+fVarity[i]);
			System.out.println("\n Image :"+fService[i]);
			System.out.println("\n Image :"+fMoney4val[i]);
			System.out.println("\n Image :"+fFeedback[i]);
			System.out.println("\n Average "+ fRatingAverage[i]);
		}
		      } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    }
		

    public int getCount() {
    	System.out.println("Data Length :"+ fId.length);
        return fId.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
         
        public TextView name;
        public TextView average;
    }
    
    @SuppressLint({ "NewApi", "InflateParams" })
	public View getView(int position, View convertView, ViewGroup parent) {
    	System.out.println("\n position "+ position);
        View vi=convertView;
        
        if(convertView==null){
             
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.listview_row,null);
             
            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.name = (TextView) vi.findViewById(R.id.fname);
            holder.average=(TextView)vi.findViewById(R.id.fAvg);   
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else 
            holder=(ViewHolder)vi.getTag();
       
    	 holder.name.setText(fUser_name[position]);
 	    holder.average.setText(String.valueOf(fRatingAverage[position]));
 	 
 	    /******** Set Item Click Listner for LayoutInflater for each row ***********/
        vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }
    
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	}
	
    
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
        
       OnItemClickListener(int position){
        	 mPosition = position;
        	// System.out.println("Item clicked onitemclick"+data[position]);
        }
        
        @SuppressLint("NewApi")
		@Override
        public void onClick(View arg0) {
        		System.out.println("it is clicked...........");
        	
        		Intent	i = new Intent(context, FeedbackView.class);
        		
        	i.putExtra("name", fUser_name[mPosition]);
        	i.putExtra("email",fEmail[mPosition]);
        	i.putExtra("quality", fQuality[mPosition]);
        	i.putExtra("service",fService[mPosition]);
        	i.putExtra("varity",fVarity[mPosition]);
        	i.putExtra("money4val",fMoney4val[mPosition]);
        	i.putExtra("overallrat",String.valueOf(fRatingAverage[mPosition]));
        	i.putExtra("feedback",fFeedback[mPosition]);
        	//System.out.println("activity name"+activity.getLocalClassName());
        	context.startActivity(i);	
        }               
    }   
}