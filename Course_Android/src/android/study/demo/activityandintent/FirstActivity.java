package android.study.demo.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.study.demo.R;
import android.study.demo.configration.CommonConstants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onCreate");
		if (savedInstanceState != null) {
			Log.i("restore state in onCreate:",
					savedInstanceState.getString("yufengdemo"));
		}

		setContentView(R.layout.layout_activity1);
		Log.v("yufenglog", "print verbose");
		Log.d("yufenglog", "print debug");
		Log.i("yufenglog", "print Info");
		Log.w("yufenglog", "print Warn");
		Log.e("yufenglog", "print Error");
		try {
			throw new Exception("dfd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("yufenglog", e.getMessage());
		}

		// ����SecondActivity��ť
		Button button23 = (Button) findViewById(R.id.button23);
		button23.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(FirstActivity.this,
						SecondActivity.class);
				_intent.putExtra("yufengintent", "��ã��ܸ�����ʶ��");
				_intent.putExtra("yufengintent1", "��ã��ܸ�����ʶ��1");
				// MainActivity.this.startActivity(_intent) ;
				FirstActivity.this.startActivityForResult(_intent, 1234);
			}

		});

		// ���Ű�ť
		Button buttonDial = (Button) findViewById(R.id.buttonDial);
		buttonDial.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent();
				_intent.setAction(Intent.ACTION_DIAL);
				_intent.setData(Uri.parse("tel:15608071871"));
				startActivity(_intent);
			}

		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onPause");
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
		// super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			Log.i("restore state:", savedInstanceState.getString("yufengdemo"));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("yufengdemo", "�ҵ�����,����ʡ��һ����...");
		Log.i("save state:", "�ҵ�����,����ʡ��һ����...");
		// super.onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "onResume");
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1234) {
			String returnValue = data.getStringExtra("yufengintentreturn");
			Toast.makeText(this, "����ֵ��" + returnValue, 4000).show();
		}

	}
	/**
	 * 講一個故事：有一個男生喜歡一個女生，知道地址看電影，男孩就是那個Activity就是那個主角
	 * 男孩子去學校先註冊了，才到我們學校不是黑戶在Activity里面相當于在Manifest里面註冊回到公寓，，
	 * 實際上就是個begin這個相當于新事物的開始就有想法了想交女朋友了，洗臉了（綁定界面，並且呈現，
	 * setContentView()）寫情書，就是我們的業務邏輯， 1在Android里面先建立一個Activity，定義屬性和內部方法，
	 * 2註冊acitivity到manifest里面， 3在啟動函數（oncreate）里面,實現業務， 3.1界面的定義（界面的呈現）
	 * 3.2界面的綁定 setContentView(); 结论： Activity构造的时候调用了attach,绑定了一个window,
	 * 2,Activity setContentView()有三个重载方法, 是windows.setContentView();
	 * Activity是个控制器他绑定了个窗体，窗体本身是不可见的他加了一些控件，控件和窗体关联，窗体和Activity关联，
	 * 看log时总是有个dispath（不确定），这个就是看是谁发出来的
	 * 往界面里面加入控件的方法第一个是addview,第二个是inflater像压力ben一样把xml压缩成view
	 * 创建Activity及相关视图文件Layout 配置AndroidManifest.xml 重载onCreate();
	 * 绑定Activity和Layout(View) 为layout添加那些组件，
	 * UI的基类是View，ViewGroup是个容器控件，（ListView,Table全可以是容器控件）
	 * 继承关系View->TextView->Button 在onCreate()实现初始化业务逻辑（事件坚挺等）
	 * 
	 * @author Administrator
	 * 
	 * 
	 * 
	 *         public class ActivityCourseActivity extends Activity {
	 * @Override protected void onCreate(Bundle savedInstanceState) { // TODO
	 *           Auto-generated method stub super.onCreate(savedInstanceState);
	 *           // fill_parent是以前2.1之前的东西，
	 *           //setContentView(R.layout.layout_activity1); //查看源码
	 *           //setContentView
	 *           ()->PolicyManager->Policy->PhoneWindow->(Window,
	 *           LayoutInflater,View,ViewGroup) //既然Activity实际是window来呈现的视图
	 *           //就用inflater. //this是当前activity的方法
	 *           //得到窗体，然后设置视图，视图从哪里来了，得到压力笨，里面肯定有个方法肯定是压的过程
	 *           this.getWindow().setContentView
	 *           (this.getLayoutInflater().inflate(R.layout.layout_activity1,
	 *           null));//第二个参数是压在哪个目录之下，不知道弄在哪个目录下所以用null Button _button =
	 *           (Button)this.findViewById(R.id.buttonYufeng);
	 *           _button.setText("Text Change!"); _button.setOnClickListener(new
	 *           OnClickListener() {
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 *           stub Log.i("YufengLog", "Button1 clicked!");
	 *           //下面的那个上下文就不是this了，当前上下文，这个this就代表onclickListener在（在这个类里面了）
	 *           //Toast.makeText(ActivityCourseActivity.this,
	 *           "Button clicked!", 3000).show();
	 *           //启动一个activity需要先定义传入的一个Intent意图 // Intent _intent = new
	 *           Intent(ActivityCourseActivity.this,RoseActivity.class); Intent
	 *           _intent = new Intent(); //找action时找出好些activity里面的action一样的
	 *           _intent.setAction("yufeng.demo.activity.WATCHMOVIE");
	 *           //下面的那个字符串：//之前必须是有东西在manifest里面设置看谁的data可以得到
	 *           _intent.setData(Uri.parse("http://www.mobidever.com"));
	 *           //我们也要在manifest里面匹配data看谁的data能得到
	 *           //怎么往里面传值根据set里面的提示总是感觉不全所以用刚才提示的hash结构只能用get了
	 *           //下面的不行extras里面没有值
	 *           //_intent.getExtras().putString("yufeng.demo.activity.YUFENGKEY"
	 *           , "你好");//为了保证key的唯一可以加上包名
	 *           _intent.putExtra("yufeng.demo.activity.YUFENGKEY", "你好");
	 *           //去新的activity里面接收 //startActivity(_intent);
	 *           //返回值的方法在新的activity里进行，接收的方法在旧的activity //下面的这个方法肯定是异步的
	 *           //因为当有结果返回时要调用onActivityResult说明下面的这个是异步的，这个是什么意思呢
	 *           //requestCode请求码，实际上返回时也会用到，为了表示唯一性，
	 *           startActivityForResult(_intent, 123);
	 * 
	 *           } });
	 *           //往windows里面填加新控件，思路怎么解决，往windows里面添加控件，先定义个控件，然后往里面添加，添加要找到父控件
	 *           Button _newButton = new
	 *           Button(this);//要传上下文，就传入activity里的上下文，表示当前button与activity有关
	 *           _newButton.setText("new Button");
	 *           _newButton.setOnClickListener(new OnClickListener() {
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 *           stub Intent _intent = new Intent(); //设置Action一定要设置data
	 *           //_intent.setAction(Intent.ACTION_CALL);
	 *           _intent.setAction(Intent.ACTION_DIAL);
	 *           _intent.setData(Uri.parse("tel:12593"));
	 *           startActivity(_intent); } });
	 *           //把newbutton添加到视图里面，添加到容器控件里面，不能添加到textView
	 *           //添加到linearLayout里面，要得到linearlayout这个父控件，所以有id
	 *           //往里面添加有多种方法第一种是找到id， //当前我们acitity和windows有没有关于添加的 //局部函数用下划线
	 *           LinearLayout _l1 =
	 *           (LinearLayout)findViewById(R.id.linearlayout1);
	 *           //_l1.addView(_newButton); _l1.addView(_newButton,
	 *           LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); } //
	 *           所有看到的全是类，界面主键，android基类是什么了， //请说一下Activity,view,windows之间的关系
	 *           //请说一下Activity是界面吗 //如何动态编码来控制界面，建立界面树的概念 //LinearLayout,
	 *           //如何在activity里面添加事件，1添加事件主体发生对象2事件的监听函数设置，3事件的逻辑代码，（人物，时间，地点）;
	 *           /** Activity与Activity之间的切换，那个男孩去了女孩的公寓送了一个电影票就是意图（意图就是想去看电影），
	 *           这个目的就是发出邀请（StartActivity）（邀请的类别，），（如果成功，女孩就会启动）就会有个意图对象，
	 *           女孩有个两个情况要么同意要么拒绝， 如果同意，女孩回去也会打扮（洗心革面的感觉），然后一起看电影，
	 *           看完之后女孩就说我很开心(returnResult)，然后一起回家，
	 *           大概看一下Intent,传数据时会有些协议（Data的格式
	 *           ,）Action,Categorty（Intent的类别），Extras(Bundle就是像一个hash结构，getput,)
	 * 
	 *           //事件函数一般与on开头 //这个函数是用业接收返回值的
	 * 
	 * 
	 *           下面的code是让我们进行判断的
	 * 
	 *           protected void onActivityResult(int requestCode, int
	 *           resultCode, Intent data) { // TODO Auto-generated method stub
	 *           if(resultCode == 123) { String _resultValue =
	 *           data.getExtras().getString("yufeng.demo.activity.YUFENGKEY");
	 *           Toast.makeText(this, _resultValue, Toast.LENGTH_LONG).show(); }
	 *           super.onActivityResult(requestCode, resultCode, data); }
	 * 
	 **/
}