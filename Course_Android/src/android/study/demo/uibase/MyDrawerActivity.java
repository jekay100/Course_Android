package android.study.demo.uibase;

import android.app.Activity;
import android.os.Bundle;
import android.study.demo.R;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.SlidingDrawer.OnDrawerCloseListener;

public class MyDrawerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_slidingdrawer)  ;
		SlidingDrawer drawer = (SlidingDrawer)findViewById(R.id.slidingDrawer1) ;
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				Toast.makeText(MyDrawerActivity.this, "Closed", 3000).show()  ;
			}
		})  ;
	}

}
