/**
 * Android项目测试
 * @author yufeng
 * @company datong university
 * @date 2013-2-3
 * @version 1.0
 * 
 */
package android.study.demo.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.study.demo.R;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * HelloWorld方法
 * @author yufeng
 * @date 2013-2-3
 * @version 1.0
 * 我们做应用先要找出入口函数每个函数都要有个main函数
 * 原理：守护进程（应用服务器）加载我们的app（应用程序）
 *Viber這個軟件看看
 */
public class HelloWorld extends Activity {
	//初始化函数
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//这个函数就是里面传了main是个布局文件，
		//当前的主Activity就是设置了一下布局文件 
		//去android官网查setContentView
		setContentView(R.layout.layout_helloword);
		Log.v("YufengLog", "Yufeng Print v");
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);//這個打不出log來了，親，下面的也打不出來，
			//	Process.killProcess(Process.myPid());//這個真暴力連log也打不出來
				//可見application里面的drop是沒有調用，一般我們不調用讓系統自己回收
			}
		});
		
		//当前的类设置了一个 mainactivity
		//进程建立一个新的虚拟机，启动和管理我们的app，android里面没有main函数做为主入口，肯定会有个配置文件来作为我们的函数的入口
		//Mainfest也就是个主入口
		//配置文件里面的东西全由守护进程实例化成对像
		//写activity必须要进行配置，才可以
		//LAUNCHER是那个主入口，在配置文件里通过LAUNCHER来标志
		//所以在进程里面实例化那个.HelloAndroidActivity类，只有一个方法Oncreate，这个类是初始化，
		//程序是这样的有个activity,是个主activity把这个activity配置到mainfest里设置成启动activti，这样进程就知道加载哪个activity了
		//实际上守护进程在实例化那个类时用到了一个服务叫pacageManager类，把activity实例化的
		
		/**
		 * activity ,Mainfest,layout三者之间的关系，packagemanamge去识别配置文件找到主类，然后加载那个类，实例化的过程中去绑定我们的layout
		 * android进行run的时候发生了什么
		 * 1首先把项目编译成dex二进制文件然后打包成apk文件
		 * 2选择我们的target选择的是模拟器呀还是真机
		 * 3上传（uploading）
		 * 4,安装（apk）
		 * 5启动(启动acitivity)
		 */
		/**
		 * log的了解，
		 * logcat专门把log显示
		 * DDMS里面也集成 了logcat
		 * DDMS里面的文件管理可以通过要选中模拟器才能看见文件，pull把里面的文件弄出来
		 * Logcat里面的Level（DIDDI）这个就是个等级（状态），pid进程id,什么时候看到0就是由root启动，tag,发生的主体和进程哪个类，哪个进程 
		 * Message就是具体描述
		 * ASSERT通用的方法，Debug调试，ERROR错误，INFO信息，VERBOSE，WARE(警告);
		 * tag来seixuan器，他们下面三个是个and关系 
		 * 一号是删除seixuan器，
		 * 如何看log
		 * log是低层支持的要看源码
		 * Logger就是个软的设备驱动
		 * 这个是一层层的往下调用
		 * 1，程序出错了，看log,
		 * Android Runtime
		 * 2,我们调试程序看自己打的log
		 * 按照优先级别来看，如果程序出错，看红色，
		 * 如里自己打的，就追踪自己的tag
		 * 会产生log日志文件
		 * 1，程序异常退出，uncaused exception
		 * 2,程序强制关闭，Force Closed(简称FC)
		 * 3,程序无响应，Application No Respone(简称ANR)
		 *何时会产生ANR
		 *界面操作（按钮的点击）等等响应时间超过5s
		 *HandleMessage回调函数中执行超过10s
		 *data/log,在手机里面产生（为什么有的手机看不见）、
		 *手机里面有两种出模式：
		 *1development(开发模式)支持打log，
		 *2.user（用户模式 ）不再打log,不支持开发码
		 *log文件的组成：分为三块：系统的基本信息：内存信息等等，
		 *2事件信息，
		 *3，虚拟信息：进程线程的跟踪信息，
		 *4在里面输入（搜索ANC，FC等看看里面是什么了）
		 *在eclipse里的logcat还可以导入选中的log在下三角有个export\
		 *Android(当前进程就是正在运行的程序，按home那个进程没有退出只不过做为后台进程，不会消cpu但是会用内存)
		 *苹果也是一样，没有必要，那是由系统管理，他自动回收，windows不会他就变慢了
		 *基于linux和unix就没有必要杀一下
		 *我们做开发时要考虑：
		 *性能（更快，更灵敏）
		 *响应能力（拒绝ANR）（ANR是无响应有两个按钮等待和关闭，不是Force close）5s内没有响应，10s内没有出现handle处理完
		 *怎么避免IO操作(File,sql,NetWork)和复杂的运算写成异步，
		 *无缝的（很自然的玩），与第三方程序的交互（玩中间出现打电话），让用户感觉不是第三方的程序让别人感觉就是一体的，
		 *  前台后台的切换（如放音乐）
		 *  安全：对操作权限的考虑
		 *  android应用程序基本特性：
		 *  .apk是android应用程序安装格式
		 *  android操作系统 是基于linux的多用户操作系统，每一个应用程序基于不同的用户（不 是root）保证了你这个应用程序只有这个用户完全有权限，
		 *  userid要系统设定
		 *  每个进程有自己的虚拟机实例，所以每个应用程序相对独立
		 *  默认情况下，应用程序都有自己的linux进程，系统决定关闭谁
		 *  然而，也有例外：可以给两个应用程序共享同一个userid,让彼此之间共享资源
		 *  应用程序可以请求访问系统资源，比如通讯录，但是要有权限
		 *android开发的七个大件：
		 *Activity:是一个类，业务主体的类，是个界面，Activity通过布局管理添加多个View组件，
		 *通过setContentView(int)方法可以将视图呈现，
		 *在android里面Activity是个业务类，他通过View这个类及子类（xml文件）来前台显示把他们绑定在一起，呈现出来通过setContentView来绑定
		 *service运行在后台，处理看不见并且会有持续时间的事情，不生成ui，不可见，所以没有setContentView
		 *所有用户必须实现的Service必须继承Service类，在配置文件中进行注册
		 *Content Provider数据在Android中是私有的，所以提供了Provider提供多个程序间数据交互的机制，contentprovider显露出一些api进行操作
		 *
		 *Broadcast Receiver（）接收消息一种是系统消息，自己的消息，主要是对外部事件做出响应
		 *所有用户实现Receiver必须继承系统的BroadcastReceiver，也需要注册
		 *Broadcast是不可见，需要Ui,应用程序可以通过自己的Intent广播出去，其他程序通过Receiver获得进行时间处理
		 *四大组件要注册
		 *三大组件  离不开他
		 *Intent对Activity,Service BroadcastReceiver进行通信，参数传递
		 *Activity启动另外一个Acitivity时传传值传的就是Intent
		 *Intent是传递的一个参数，是活的，不是死的
		 * 通过Intent启动Activity,启动Service通过Intent 加入一个广播，执行一个查询，ContentResolver的query()
		 * Launcher&Widget(开机启动了的那个也是应用程序)源码在pacage/app
		 *  widget(就是个小插件时间开关等 )
		 *  Notification(提醒，)
		 *  上面就是这个七大件
		 *  根据Sourcecode了解Framework(这个里面全是那些什么类是弄的关于什么的等)framework里面的androidJ里面就是framework的图片 
		 *  
		 *  
		 */
		/**
		 * 应用程序的资源介绍：
		 * value资源：string,color,dimen尺寸（px像素绝对,in英寸，pt点距，mm毫米，dp密度无关像素这个是相对的不是绝对的，sp范围无关像素就更没有差异了与屏幕大小无关），array希望在values里面存少数数据,Location(我们在做多国语言时string里面做文件夹的命名values-de不知道怎么命名可以去官网查location没有的话就去Resources等)
		 * 长宽高dp,字体用sp其他的一般别用
		 * style & theme(android支持的图片png,jpg
		 * drawable里面通常也会有跟界面元素美化相关的xml,
		 * layout布局文件夹可以写的很死，也可能动态加载
		 * anim（在安卓里面动画也在xMl里面了）这个要好好查查调大小，旋转等
		 * menu();
		 * 代码中调用资源this.getResources().get....
		 * 在资源中访问资源：@资源类型(string)/资源id(hello)
		 * 主题是用？代替@提供了系统主题，
		 */
		/**
		 * AndroidManifest:
		 * 应用程序的全局配置文件
		 * 应用程序的入口配置，
		 * 元素全部小写，<manifest>这个相当于root结点<manifest><application>有，并且只能有一个
		 * 用到别名时必须放在后面
		 * android:没有特殊的意义，是在解析时用到
		 * 除了<manifest>的属性，其他都有android:前缀
		 * 在配置文件中元素就是个类，除了manifest
		 * 声明类的名称：包括了包的名称，比较长怎么办呢所以在manifest里面配置一个包在其他类里面共享这个包，
		 * 多值的话就用|，前面一样后面不一样的
		 * Dev Guide里面有个The AndroidManifest.xml
		 * 男孩通过非诚勿扰请女孩看电影
		 * 里面除了有四大组件之外还有library
		 * 和application平级的有什么了：permission,sdk,configuration(配置一些东西，是不是需要触摸屏)，feature(是否会用相机等，看源码),
		 * supports-screens支持屏幕大屏幕小屏幕等等
		 * compatible-screens屏幕兼容性一般不让用，
		 * android中用到的设计模式用到反射可以查查怎么用的
		 * Manifest的作用及使用举例，
		 * Android四大组件的定义：Activity,Service,BroadcastReceiver,ContentProvider
		 * 定义应用程序具备的权限，
		 * 定义Instrumatation做自动化测试
		 * 定义应用程序所需要的最小API版本
		 * 列出应用程序所需要的类库
		 * Mainfest解析的过程：打包安装时把manifest信息弄在一张索引表里面
		 * 系统启动的时候，PackageManagerService读取manifest信息，
		 * 应用程序启动时候 ，Launcher进行系统及判断，比如最小sdk等
		 * 在manifest里面有这么多activity什么时候 实例化，用到的时候 才去检测这个正确性，
		 * 上面的东西不要死记硬背
		 * 
		 */
		/**
		 * Android进程生命周期：
		 * 掌握Android进程的5个等级：
		 * 我在应用程序中，实然按了Back或者Home按钮，当前应用程序 关闭了吗，当前Activity关闭了吗
		 * Android的进程模式不建议我们直接关闭他
		 * 活动进程（Active Process前台进程 ，当前操作的进程，正处于ActivityResume()状态，正处于与服务交互的状态，正处于服务在前台运行的状态，（startForeground被调用）
		 * Service生命周期函数正在被执行（onCreate(),onStart(),onDestroy()））
		 * BroadcastReceiver正在执行onReceive()方法
		 * 可见进程（Visible Process）当前进程 不是焦点进程，比如弹出一个对话框，
		 * 服务进程（Service Process）正在运行的，不在上述两种状态的Service,
		 * 后台进程（Background Process）不可见状态的Activity进程，以没有任何相关Service运行的（onStop()被调用），系统有个后台进程列表，一般情况采用后进先出的原则来释放台台进程资源
		 * 空进程（Empty Process）没有运行任何主键的进程，保留这个进程主要是为了缓存的需要。。比方说程序启动了按back键退出Activity消王了，这时候那个进程就是个空进程，主要是为了启动快
		 * 上面的进程仔细思考，
		 * 按Home键退出成为Background Process
		 * 我的进程为何说是退不出去了，Android进程是如何产生的，消亡的，用adb shell查看
		 * Helloworld进程 是包，是sundy.demo,,命令ps
		 * 启用activity时用到了packagemanager，但是他又依赖packagemanagerService,当acitivty进程不存在时他会向守护进程说他会实例化个虚拟进程把activity装进去
		 *finish退出activity,
		 *退出进程 System.exit(0);系统进程正常退出
		 * Process.killProcess(Process.myPid());杀死 
		 */
	}
}
