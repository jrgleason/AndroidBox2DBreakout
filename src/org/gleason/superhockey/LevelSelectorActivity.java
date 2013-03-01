package org.gleason.superhockey;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class LevelSelectorActivity extends Activity implements OnClickListener {
	LinearLayout myGallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_level);
//		myGallery = (LinearLayout)findViewById(R.id.mygallery);
//		for(int i = 0 ; i<myGallery.getChildCount();i++){
//			View v = myGallery.getChildAt(i);
//			v.setOnClickListener(this);
//		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.level1:
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
		}
		String test = "test";
	}

}
