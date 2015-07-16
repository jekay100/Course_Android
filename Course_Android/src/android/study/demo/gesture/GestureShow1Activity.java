package android.study.demo.gesture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.study.demo.R;
import android.view.Menu;

public class GestureShow1Activity extends Activity {

	private GestureLibrary myGestureLibrary;

	// private Gesture mGesture ;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_gesture_1);
		// myGestureLibrary = GestureLibraries.fromRawResource(this,
		// R.raw.gestures) ;
		myGestureLibrary = GestureLibraries.fromFile("/sdcard/gestures");
		if (!myGestureLibrary.load())
			finish();
		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		gestureView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						// TODO Auto-generated method stub
						// mGesture = gesture ;
						GestureRecognize.recognize(GestureShow1Activity.this,
								myGestureLibrary, gesture);
						// myGestureLibrary.addGesture("create", gesture) ;
						// myGestureLibrary.save() ;
						// Toast.makeText(GestureShow1Activity.this,
						// "create success", 3000).show() ;

					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Add Gesture");
		return super.onCreateOptionsMenu(menu);

	}

}
