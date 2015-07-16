package android.study.demo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/**
 * bindService是异步的没有阻塞屏幕，只不过是一点点异步多点一下就坏了
 * BoundService分為三類本地Service(Local Service)(發送消息的Service，AIDL)
 * BoundService是與Binder機制有關的
 * android框架上有kernel內核,內核裡面有字符設備驅動，是個軟驅動，這個驅動負責進程之
 * 間通信*（google也改了這個），上面有一曾，具體的binder框架(這個就是第二層)，配合
 * 這servicemanager,再上面就是具體的binder類，framwork這一層（Binder類
 * 和IBinder接口）
 * 有三個進程進程一想用進程二的方法，內存地址，比如線程A訪問B就像訪問自己一樣
 * 把一個進程弄成客服端c,一個弄成服務端s,
 * 1,c->s發送請求，
 * 2.s接收找到內存管理
 * 3，s返回
 * 4.c得到結果，上面就是一個機制，linux就是這個機制
 * 怎麼用Binder了
 * 在服務器（在另一個線程）里面定義了個IBinder接口，把接口暴露出來，在客戶端（一個線程
 * c）發出一個請求（不能直接訪問內存，比如要調用hello程序，那麼那個binder服務線程
 * 就會做一個yinse对象返回来就相当于自己的，这个就是个装程序的过程就是句柄就是引用就
 * 是引用）
 * 有时服务就在当前进程了指针
 * 上面就是进程间的通信，用到了Binder，startActivity里面也用到了binder机制，
 * 因为有可能会调用不同进程的activity,利用binder实现值的传递，
 * @author Administrator
 *
 */
/**
 * 在service里面的那个manifest里面android:process=“：”必要的时候可以建立一个新的线程
 * 
 * @author Administrator
 *
 */
public class BeginIntentService extends IntentService {
	
	//必須要實現
	//還要加個無參構造函數
	//要求我們必須實現構造函數
	//service.msc
	/**
	 * 在windwos里面也有關於遠程服務的（遠程進程）
	 */
	/**
	 * IntentService,異步處理服務，新開了一個線程：HandlerThread,
	 * 在線程中發消息，然後接收，處理完成后，會清除線程，并且關掉服務，，
	 * 在做一次性操作時，發短消息發就發了，下載就下載了下載時先開個線程
	 * @param name
	 */
	public BeginIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public BeginIntentService()
	{
		super("hello");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onCreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onStartCommand");
		//返回时有粘性的如果杀死的话就从新执行
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onBind");
		return super.onBind(intent);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("yufenglog", "onHandleIntent");
		for(int i = 0; i < 100; i++)
		{
			try {
				Thread.sleep(1000);
				Log.i("yufenglog", i+"");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
