package com.example.saffronrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FeedbackView extends Activity{
TextView tvName, tvEmail, tvQuality, tvService, tvVarity, tvMoney4val, tvOverallRat, tvFeedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_view);
		
		tvName = (TextView)findViewById(R.id.saf_name);
		tvEmail = (TextView)findViewById(R.id.saf_email);
		tvQuality = (TextView)findViewById(R.id.saf_quality);
		tvService = (TextView)findViewById(R.id.saf_service);
		tvVarity = (TextView)findViewById(R.id.saf_varity);
		tvMoney4val = (TextView)findViewById(R.id.saf_money4val);
		tvOverallRat = (TextView)findViewById(R.id.saf_overall_rat);
		tvFeedback =(TextView)findViewById(R.id.saf_feedback);
		
		Intent i= getIntent();
		
		tvName.setText("Name : "+i.getStringExtra("name"));
		tvEmail.setText("Email Address : "+ i.getStringExtra("email"));
		tvQuality.setText("Quality : "+i.getStringExtra("quality"));
		tvService.setText("Service : "+ i.getStringExtra("service"));
		tvVarity.setText("Varity : "+ i.getStringExtra("varity"));
		tvMoney4val.setText("Money For value : "+ i.getStringExtra("money4val"));
		tvOverallRat.setText("Average Rating : "+i.getStringExtra("overallrat"));
		tvFeedback.setText("Feedback : "+ i.getStringExtra("feedback"));
	}

}
