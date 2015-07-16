package android.study.demo.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.study.demo.R;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//按home键时弄到了onstop，在DDMS里面也可以停进程的
//生命周期图描述一下，根主考官要笔画图，描述
//在布局里面添加了個editText，官home鍵退出，還是用ddms殺死進程 都會記錄里面的值，殺死進程沒有調用destory
//狀態的保存和恢復機制，save這個用到了官網的源碼
//界面改變，按住ctrl+f12,橫豎屏
//橫豎屏先保存狀態save，然後pause,onstop,然後oncreate等，有時候我們不需要ondestory,假如音樂播放，等我們播放了一半時候，雖然恢復了但是還是感覺一閃的不
//sufu,所以我們要學習配置（onConfigrationChanged），配置會涉及到語言配置等等，
//配置涉及的方面：屏幕翻轉，語言的切換
//有時候 我們要恢復一些複雜的東西那就要用到了配置
//有時候我們會用onRetainNonConfigurationInstance()返回一個我們需要的的對象，（這里不要用與Context關聯對象，比如，View，否則會引起內存洩露）然後在oncreate里面調用getLastNonConfigurationInstance(),得到了數據，
//自己處理配置的改變比如我們不要那個Ondestory,
//比如不需要ondetory掉用，所以我們要用onConfig,當我們的配置改變時就會觸發，那個函數在另外一個類里面了
/**
* activity的任務站，在短信里面點網址就打開瀏覽器了，這是個無分連接，這些任務有順序關係，就是任務棧，
* 一個任務是什麽就是不按back和home時就是一個任務
* task就是由多個activity組成的業務邏輯，yazhan,hechuzhan,每個activity都會出現，有時候我們要所中間的弄出去我們也是可以做到的
* 怎麼區分不同的任務呢，：進程有個pid,線程有個tid,用戶有個uid,任務有個taskid,可以調用gettaskid
* @author Administrator
*
*/
public class BActivity extends Activity{

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.i("yufenglog","BActivity onNewIntent"+mIndex+"  TaskId="+getTaskId())  ;
	}
	private static int mIndex = 1 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_task)  ;
		Log.i("yufenglog","BActivity Create"+mIndex+"  TaskId="+getTaskId())  ;
		++mIndex  ;
		Button _buttonStartA = (Button)findViewById(R.id.buttonStartA)  ;
		_buttonStartA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(BActivity.this,AActivity.class)  ;
				startActivity(_intent) ;
			}
		}) ;
		
		
		Button _buttonStartB = (Button)findViewById(R.id.buttonStartB)  ;
		_buttonStartB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(BActivity.this,BActivity.class)  ;
				startActivity(_intent) ;
				
			}
		}) ;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//drawable里面还能定义形状和动画，我把drawable里面定义的形状作为样式的背影布局，在manifest里面指定activity的主题
		Log.i("yufenglog","BActivity Destory"+mIndex+"  TaskId="+getTaskId())  ;
	}

}
