/**
 * Lesson5 Service�����İ���
 */
package android.study.demo.service;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.study.demo.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * startService和BoundService的本质区别，BoundService有个客服端和服务端的区别，是借住于那个Binder来传递
 * 我们边看小说边听歌可能歌被系统杀死，所以我们要提高service的优先级（也就是在前台运行自己的服务）
 * Native Service可以顺便查一下NDK(JNA就是java调用里面的C++);
 * 服务与用户怎么交互，第一个toast第二个是状态栏，toast只不过生成了个对象
 * 服务与多线程之间的选择：时间不长的直接开个线程下载，服务就是那些长时间的与界面没有关系的
 * Service是一个新的进程还是新的线程（查一下，）
 * @author Administrator
 *
 */
public class MusicServiceActivity extends Activity {

	private Intent intent  ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_musicservice)  ;
		//��Activity��ֱ�Ӳ�������
		Button button1 = (Button)findViewById(R.id.buttonPlayMusic)  ;
		button1.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MediaPlayer mp = MediaPlayer.create(MusicServiceActivity.this, android.study.demo.R.raw.maria) ;
				mp.start() ;
			}
			
		}) ;
		
		//��Service���������ֲ���
		Button button2 = (Button)findViewById(R.id.buttonStartMusic)  ;
		button2.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(MusicServiceActivity.this,MusicService.class) ;
				startService(intent)  ;
			}
			
		}) ;
		
		
		Button button3 = (Button)findViewById(R.id.buttonStopMusic)  ;
		button3.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(intent != null)
				{
					stopService(intent) ;
				}
			}
			
		}) ;
		
		
	}

}
