package android.study.demo.activityandintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.study.demo.R;
import android.study.demo.configration.CommonConstants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 按home键时那个activity会保存里面的数据这个是一个activity数据的保存
 * 切换activity时先pause然后创建oncreate,onstart,onresum,最后才是stop
 * 当返回第一个activty时先pause然后onrestart,onstart,onresum,onstop,最后ondestrop
 * 所有的把自己pause(暂停) 生命周期弹出的是新窗体是Activity还是Dialog，弹出一个Dialog是通过启动一个新的activity来实现的，
 * 通过粘贴代码可以得出并不是能过弄那个焦点来启动Dialog,也并不是能过，Dialog不是通过启动一个新的activity来实现的，，通过定义一个样式来，
 * 
 * @author Administrator
 * 
 */
public class LifecycleActivity extends Activity {

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onPause");
	}

	private EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 没有设置layout也不会报错，为什么了因为没有设置view他默认有个根viewgroup我们可以有个小工具
		// 看他的层级数据结构，hiera....由这个tools可知里面默认有个framelayout
		// 和界面 有关的程序调试用这个工具
		setContentView(R.layout.layout_lifecycleactivity);
		mEditText = (EditText) findViewById(R.id.editTextLifecycle);
		// 優先恢復系統的保存，屏蔽系統恢復，只需要所super去了就行
		// 恢復是在什麽時候 調用呢是在onstart之后，onresume之前
		if (mEditText != null && savedInstanceState != null) {
			mEditText.setText(savedInstanceState.getString("yufengvaluetext"));
		}
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onCreate");

		Button _buttonShowDialog = (Button) findViewById(R.id.buttonShowDialog);
		_buttonShowDialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final CharSequence[] items = { "Red", "Green", "Blue" };

				//调用dialog
				/*AlertDialog.Builder builder = new AlertDialog.Builder(LifecycleActivity.this);
				builder.setTitle("Pick a color");
				builder.setItems(items, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				    }
				});
				AlertDialog alert = builder.create();
				alert.show()  ;*/
				
				//启动新的activity
				Intent _intent2 = new Intent(LifecycleActivity.this,
						ThirdActivity.class);
				startActivity(_intent2);
			}
		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//默認情況下生命週期不會調用這個方法，我們在manifest里面會有個，android:configChanges= "orientation";加上這個的時候會出現當我們的配置改變了之後 會調用這個函數
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onConfigurationChanged");
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
			Log.i(CommonConstants.LOGCAT_TAG_NAME,
					"onConfigurationChanged : Landscape");
		else {
			Log.i(CommonConstants.LOGCAT_TAG_NAME,
					"onConfigurationChanged : Portrait");
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onDestroy");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onRestart");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onRestoreInstanceState");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onResume");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}
	/**
	 * Bundle是捆和束的意思，這裡面也是那個hash數據結構，，
	 * 保存類型時就是以這個數據結構來保存，outState(保存是個導出，有可能是導出到xml等，用的時候加載
	 * 到activity里面，create時候可能導回來savedInstanceState，實際上還有個方法restore)
	 * 暫停之前先調用了onsaveInstanceState,但是返回時沒有調用Onrestore怎麼回事呢，但是當
	 * 前進程被意為中止 時會onreStore，但是實踐證明有時是不會調用的
	 * @param outState
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if (mEditText != null) {
			String _textValue = mEditText.getText().toString();
			outState.putString("yufengvaluetext", "yufeng:" + _textValue);
		}
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onSaveInstanceState");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onStop");
	}

}
