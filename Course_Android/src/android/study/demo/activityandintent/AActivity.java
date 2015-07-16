package android.study.demo.activityandintent;

//www.mobidever.com

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.study.demo.R;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * acitivity在manifest里面的配置（高级配置）
 * android:allowTaskReparenting（允许任务重定向）(email里面的进程有个链接，打开启动另一个浏览器进程，)
 * 如果设置成true的话他原来的那个连接还在了，默认是不在了
 * android:alwaysRetainTaskstate如果设置成true就会保存那个activity的状态（即使被杀死了）
 * android:cleaarTaskOnLauch(在lauch上切换进去就清了)
 * finishOnTaskLaunch(在一次载入如果有那个任务就会关掉)
 * 在这些里面可能会出现优先级的问题
 * hardwareAccelerated(设置成任务加速功能);
 * multiprocess，永不永许在其他进程里面（把拨号的那个设置成true就会拨号的那个弄在了自己的任务里了）
 * nohistory,表示从当前出去时就会把它结束了
 * excludeFromRecents(表示在最近的历史中不包括他，近期任务里面)这个还比较常用
 * process:可以改我们的进程取一个别名（：开头的话他可能根据需要把当前activity放在一个新的进程里面）
 * '；
 * stateNotNeeded(当前activity里面的状态不需要)
 * screenOrientation="sensor"//根据感应器走
 * launchMode(执行模式，有四种有什么区别看源码好好查一下百度)，singleTop如果activity b不在顶端的时会创建实例，如果在顶端，不需要实例
 * singleTask，只要任务里面有了就不会被实例化，并且如果所有的任务栈上有其他activity会被毁了
 * singleInstance进程里面的唯一性如果有的话直接就用了，
 * 如果设置成true独立成Taskstack,并且仅有自己一个，孤家寡人，
 * 这个是就是要退出到一个程序，这个要好好练，好好查一下，有点重要，
 * android:exported是不是被其他进程使用本activity,一般情况下是永许的
 * android:windowSoftInputMode="";
 * 上面的这个是表示软键盘显示与否
 * 如果没有实例化调用onNewIntent();
 * @author Administrator
 *
 */
/**
 * Android提供的专有Activity(MapActivity,ListActivity,ExpandableListActivity,
 * TabActivity) HoneyComb以上版本开始引入，Fragments界面片段， Loaders界面异步加载机制
 * 
 * @author Administrator
 * 
 */
public class AActivity extends Activity {
	private static int mIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_task);
		Log.i("yufeng log", "A TaskId = " + getTaskId() + "index" + mIndex);
		++mIndex;
		Button _buttonStartA = (Button) findViewById(R.id.buttonStartA);
		_buttonStartA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(AActivity.this, AActivity.class);
				startActivity(_intent);
			}
		});
		Button _buttonStartB = (Button) findViewById(R.id.buttonStartB);
		_buttonStartB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(AActivity.this, BActivity.class);
				startActivity(_intent);
			}
		});
	}

}
