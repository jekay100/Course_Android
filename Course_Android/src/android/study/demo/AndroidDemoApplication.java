package android.study.demo;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * 看一下application的生命周期
 * 首先右键看一下有哪些回调函数方法有4个重载函数，为什么现在有那么多了查一下这些函数有什么用了
 * 我們有了自己的app就不用系統的app,所以我們要更改manifest.xml
 * 应用程序的生命周期
 * application在设置时没有设置名字，因为默认设置成了那个系统级的android.app下的application，能不能继承application实现自己的程序 
 * @author Administrator
 *
 */
public class AndroidDemoApplication extends Application {

	@Override
	//配置改变，屏幕翻转时，就会被调用
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.i("yufenglog","app config changed");
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("yufenglog", "app created");
	}

	@Override
	//当内存低的时候他会调用这个函数
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Log.i("yufenglog", "app low memory");
	}

	@Override
	//当被系统回收时他会调用这个
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		Log.i("yufenglog","app terminate");
	}
/**
 * 工具StarUML（畫圖工具）
 * 官方定義：Acitivity是Android應用程序提供交互界面的一個重要組件，也是Android最重要的組件之一
 * 在官網的用戶嚮導里面有（Dev Guide），老師認為這樣定義不對，在廣義上他是個義務類，是承載應用程序的界面以及業務行為的基礎。
 * 類似于我們的JavaBean,包括我們的UI,Service....
 * 呈現了我們的界面但是界面本身不是Activity,Activity本身也不是界面
 * Intent and Intent Fiters官方定義也在用戶嚮導里了
 * Intent在中文是意圖的意思，如此我們認為Intent就是在不同組件之間傳遞值面設計的一種數據結構
 * 當然了為了傳值應該定義一些具體的屬性包括怎麼弄里面設置要傳遞的值
 * 
 * 
 * 
 */
	
	
}
