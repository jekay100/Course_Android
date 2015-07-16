package android.study.demo.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.study.demo.configration.CommonConstants;
import android.util.Log;

/**
 * //停止服務的方法有兩種 //加參數與不加參數都停止不了 //即使停止了那個onstartcommand也要執行完
 * 
 * 
 * 有些用时比较长得操作我们希望他在后台运行（男孩不用每天提醒自己送花），不耽误当前的操作 常见的比如：访问网络，文件IO操作，大数据的数据库任务，播放音乐
 * Service在后台运行， 不与用户进行交互，在默认情况下，Service运行在应用程序进程的主线程中，如果需要在Service中处理一些网络连接
 * 等耗时操作，那么应该将这些任务放在单独的线程中处理，避免阻塞用户界面 启动服务按home键服务还在 服务没有启动新的进程（退出进程时服务没有了）
 * 看线程在DDMS里面下三角里面 服务不是一个新的进程也不是一个新线程，他与当前进程绑定的， Service的分类： 启动方式分类：
 * startService来启动(一但启动，就运行在后台，即使启动他的对象（Activity）都销毁了，这里不是说进程，进程毁了服务也毁了但是也有例外
 * 通常网络上传或下载，操作完成后自动停止，通常只启动，不返回值，)
 * bindService来绑定（提供客户端服务器接口来启动，发送请求，得到返回值，甚至通过IPC来通讯
 * ，一个服务可以被多个调用者绑定，只要有一个绑定者，服务运行，所有绑定者都退出了，服务退出，） 按服务性质分：不同进程也可以就本地服务和远程服务（Local
 * service,Remote Service） r按实现方法分类：Java Service,
 * NativeService(源生服务用C++写的)ActivityManager等也会用到服务
 * 
 * @author Administrator
 * 
 */
public class BeginService extends Service {

	// 首先要在服务端漏出一个接口，最终要实现他（有两种方法第一写一个Binder类让他实现那个接口，第二种是继承Binder类）
	// 接口对象
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//暴露接口，暴露给客户端之后就要调用了
		return myBinder;
	}

	public void MyMethod() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(1000);
						Log.i(CommonConstants.LOGCAT_TAG_NAME,
								"Binding BeginService :" + i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	@SuppressLint("NewApi")
	// 在新版本里面会有onstartCommand2.0好像是这个版本，在以前版本里面是onstart
	@Override
	// 當服務被調用時會調用下面這個方法，下面的那個參數就是我們startservice里面傳的那個intent這個能猜到吧
	// flags是表示，在官網里面看也行，startId每個服務都有唯一的一個id
	// 上面的為什麽停止不了服務是因為這個是個啟動的過程它一直在啟動
	/**
	 * flag是与系统有关的，要理解首先要理解返回值
	 * 返回值：start_sticky(启动个粘度)如果杀死就从新启动
	 * start_redeliver_intent(这个也是恢复，并且恢复intent)
	 * 但是我们总在服务里面开一些线程，而这些线程不好操作，所以android给我们一些好的方法IntentService,也是以start启动的他不重载onstartcommad,而是重载了onHandleIntent
	 * 
	 */
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// 证明是主线程
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(1000);
				Log.i(CommonConstants.LOGCAT_TAG_NAME,
						"Starting BeginService :" + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 采用多线程
		/*
		 * new Thread(new Runnable(){
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * for(int i=0;i<100;i++) { try { Thread.sleep(1000) ;
		 * Log.i(CommonConstants.LOGCAT_TAG_NAME, "Starting BeginService :"+i) ;
		 * } catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } }
		 * 
		 * }).start() ;
		 */
		return super.onStartCommand(intent, flags, startId);

	}

	// 这里面有两条生命线，一个是start方式启动的service
	// 在service里面定义我们到底要提供什么要的服务
	// 假如我在这里面提供了一个helloYufeng的方法
	// 定义IBinder接口实现
	// 下面只是那个类只是service的子类，我们要调用service里面的方法怎么办所以要又写一个方法返回这个类service类的实例
	public class MyBinder extends Binder {

		public BeginService getService() {
			return BeginService.this;
		}

		public void helloSundy() {

		}
	}

	private MyBinder myBinder = new MyBinder();

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		// 这里面会报错，因为不是一直异步，只有一点异步，可以那那个上面的给小点等完了再点，呵呵
		Log.i("yufenglog", "onUnbind");
		return super.onUnbind(intent);
	}

}
