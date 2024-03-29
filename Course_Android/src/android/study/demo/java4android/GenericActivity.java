package android.study.demo.java4android;

import android.app.Activity;
import android.os.Bundle;
import android.study.demo.R;
import android.study.demo.configration.CommonConstants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GenericActivity extends Activity {

	class Eat<T>
	{
		public void eatFood(T foodType,int foodCount)
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "你吃了："+foodType.toString()+" "+foodCount+"��") ;
		}
	}
	
	class Rice{} ;
	class Meat{}  ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_helloword) ;
		Button buttonClick = (Button)findViewById(R.id.buttonHelloworld)  ;
		buttonClick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Eat<Rice> eatFood = new Eat<Rice>() ;
				eatFood.eatFood(new Rice(), 1)  ;
				Eat<Meat> eatFood2 = new Eat<Meat>() ;
				eatFood2.eatFood(new Meat(), 2)  ;
			}
		}) ;
	}
	
	
}
