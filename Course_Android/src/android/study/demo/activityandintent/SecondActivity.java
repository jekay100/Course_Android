package android.study.demo.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.study.demo.R;
import android.study.demo.R.id;
import android.study.demo.R.layout;
import android.study.demo.configration.CommonConstants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * aCtivity就是个业务控制类，控件是view,窗口是个M
 * 
 * @author Administrator
 * 
 */
public class SecondActivity extends Activity {

	private EditText et;
	private Intent getIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onCreate");
		setContentView(R.layout.layout_activity2);
		// 怎么接收那个activity里面传过来的值呢
		// 所以我们要看当前上下文有什么方法，所以用this
		// mIntent = this.getIntent();
		// String _yufengValue;
		// if(mIntent != null)
		// {
		// _yufengValue =
		// mIntent.getExtras().getString("yufeng.demo.activity.YUFENGKEY");
		// Toast.makeText(this, mIntent.getDataString(),
		// Toast.LENGTH_LONG).show();
		// //怎么往activity里面返回一个值去了
		// }
		// 执行业务逻辑
		Button button24 = (Button) findViewById(R.id.button24);
		button24.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(SecondActivity.this,
						ThirdActivity.class);
				startActivity(_intent);
			}

		});
		getIntent = this.getIntent();
		String getValue = getIntent.getStringExtra("yufengintent");
		et = (EditText) findViewById(R.id.editText23);
		et.setText(getValue);
		// Close Button
		Button buttonClose = (Button) findViewById(R.id.button2Close);
		buttonClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String setValue = et.getText().toString();
				// 要返回一个值，所以我们要把当前activity关闭
				// this.putExtra();不能这样做this的作用
				// 把新值弄到Intent里面去，
				getIntent.putExtra("yufengintentreturn", setValue);
				// this表示这个类的方法，不用this说明这个不在一起
				setResult(1234, getIntent);

				finish();

			}

		});
		// 返回值，返回值的方法在新的activity里进行，接收的在旧的activity
		// 看源码，查看一下startActivity的机制
		// Intent之间的通信用到的进程间的通信，因为
		// Activity有四种LaunchMode，Activity可以存在于不同的进程之间
		// 有的Activity进程是被共享，所以不知道activity在哪个进程里面就有了，进程之间通信
		/**
		 * 男孩不知道女孩的公寓，贴了个寻人启事，通过manifest查找符合条件， 一下找出许多女孩了，所以我们就用到intent-filter,
		 */

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onPause");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "Second - onStop");
	}

}
