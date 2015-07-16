package android.study.demo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.study.demo.R;
import android.study.demo.configration.CommonConstants;
import android.study.demo.service.BeginService.MyBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 先写服务service然后在再客户端service,客户端service
 * 服务启动过程首先是启动serviceManager这个binderService相当于个业务服务
 * 全要注册到serviceManager,serviceManager会提供Ibinder接口，他会为业务提供服务
 * 思考startService和BinderService的区别，什么时候用startService和BinderService
 * 如果既用到startService要用到BinderService只create一下
 * onBinderService会出现红色的是因为没有正常清掉那个serviceconnection
 * startService和启动它的Acitivity不同生共死BoundService和启动他的Activity同生共死
 * 如果当前进程关闭了，startService消亡了，进程的等级 rebind查一下这个
 * Bound和unBound成对出现如果只出现一个就会错，如果由绑定了就不会再调用onbind
 * 如果内存里面有服务也就是service的实例就不会调用create了只不过重复调用onstartCommand
 * 
 * @author Administrator
 * 
 */
public class BeginServiceActivity extends Activity {

	// 在设置里面可以看到正在运行的服务
	// stopself也可以自己把服务停止
	// 参数flag（STart_flag_redelivery用来表示如果返回的时候如果没杀死就从传，STARTflag_retry，原）
	@Override
	// handle里面是在主線程里面
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_begservice);
		Button buttonStart = (Button) findViewById(R.id.buttonStartBeginService);
		buttonStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 主要是這里面要實例化，所以BeginService.class必須要有無參構造函數
				Intent _intent = new Intent(BeginServiceActivity.this,
						BeginService.class);
				startService(_intent);
			}

		});

		// Bind
		Button buttonBind = (Button) findViewById(R.id.buttonBindBeginService);
		buttonBind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(BeginServiceActivity.this,
						BeginService.class);
				bindService(_intent, conn, Context.BIND_AUTO_CREATE);
			}

		});

		// Start Intent Service
		Button buttonIntent = (Button) findViewById(R.id.buttonStartIntentService);
		buttonIntent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(CommonConstants.LOGCAT_TAG_NAME, "Activity Threadid="
						+ Thread.currentThread().getId());
				Intent _intent2 = new Intent(BeginServiceActivity.this,
						BeginIntentService.class);
				// TODO Auto-generated method stub
				// 如果服務里面就是一個線程的話就停止不了，因為他一直在服務把界面所主了，服務在主線程里面了所以把那個界面給鎖了
				// 所以我們把服務寫在另外一個線程里面這樣就好了，，服務讓界面阻塞
				// 但是為什麽停止服務還打log數字了,所以我們在destory裡面打log后來發現也打log了，所以我們想到多線程不容易結束
				// 服務里面啟的子線程沒有停止，服務停了
				// stopService(mIntent);
				startService(_intent2);
			}

		});

	}

	private ServiceConnection conn = new ServiceConnection() {
		// String myNameString = ((MyBinder)service).helloWorld("yufeng");
		// Toast.makeText(YufengServiceActivity.this, myNameString,
		// 3000).show();
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			MyBinder binder = (MyBinder) service;
			BeginService bindService = binder.getService();
			// TODO Auto-generated method stub
			// bindService有三个参数：Intent,serviceconn（service连接对象，有两个重载方法，服务连接上了我们要做什么事，当连接断开了要怎么办，int
			// flag这个就是说）
			// startService通过activitynew了一个服务，bound是自己实例化了一个自己，接收到请求（serviceManager接收到就考虑要不要实例化）
			bindService.MyMethod();

		}

		// 当连接上的时候会出现说谁调用的他呢，会出现bound机制调用的他
		// onServiceConnected是在客户端实现的
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	};

}
