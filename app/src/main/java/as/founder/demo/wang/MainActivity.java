package as.founder.demo.wang;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.activity.AIDLActivity;
import as.founder.demo.wang.activity.AlarmManagerActivityl;
import as.founder.demo.wang.activity.AppBarLayoutActivity;
import as.founder.demo.wang.activity.BluetoochActivity;
import as.founder.demo.wang.activity.BrightnessActivity;
import as.founder.demo.wang.activity.BrokenLineActivity;
import as.founder.demo.wang.activity.CanvasActivity;
import as.founder.demo.wang.activity.CircleImageViewActivity;
import as.founder.demo.wang.activity.CollectionSortActivity;
import as.founder.demo.wang.activity.CoordinatorLayoutActivity;
import as.founder.demo.wang.activity.CustomerViewActivity;
import as.founder.demo.wang.activity.CustomerViewGroupActivity;
import as.founder.demo.wang.activity.DialogActivity;
import as.founder.demo.wang.activity.Dragger2Activity;
import as.founder.demo.wang.activity.DrawCusActivity;
import as.founder.demo.wang.activity.DrawerLayoutNavigationViewActivity;
import as.founder.demo.wang.activity.EditTextWithTextViewActivity;
import as.founder.demo.wang.activity.FrescoImageLoader;
import as.founder.demo.wang.activity.HtmlAppActivity;
import as.founder.demo.wang.activity.JointImageActivity;
import as.founder.demo.wang.activity.JsonCompareActivity;
import as.founder.demo.wang.activity.LeaderActivity;
import as.founder.demo.wang.activity.LifeCycleActivity;
import as.founder.demo.wang.activity.ListActivityDown;
import as.founder.demo.wang.activity.ListViewcehuaActivity;
import as.founder.demo.wang.activity.ListviewItemScrollViewActivity;
import as.founder.demo.wang.activity.LoadingActivity;
import as.founder.demo.wang.activity.LyricsActivity;
import as.founder.demo.wang.activity.MarQueeActivity;
import as.founder.demo.wang.activity.MatrixActivity;
import as.founder.demo.wang.activity.MediaRecorderActivity;
import as.founder.demo.wang.activity.MessengerActivity;
import as.founder.demo.wang.activity.OKHTTP;
import as.founder.demo.wang.activity.OnsaveInatanceActivity;
import as.founder.demo.wang.activity.PageTurningActivity;
import as.founder.demo.wang.activity.PopWindowActivity;
import as.founder.demo.wang.activity.PullToRefreshActivity;
import as.founder.demo.wang.activity.ReadViewActivity;
import as.founder.demo.wang.activity.ReaderActivity;
import as.founder.demo.wang.activity.RealLyricsActivity;
import as.founder.demo.wang.activity.RecyclverViewActivity;
import as.founder.demo.wang.activity.RecyclverviewDownActivity;
import as.founder.demo.wang.activity.ScanningActivity;
import as.founder.demo.wang.activity.ScreenDerectionActivity;
import as.founder.demo.wang.activity.SnackBarActivity;
import as.founder.demo.wang.activity.SrrollViewImageviewActivity;
import as.founder.demo.wang.activity.SurfaceViewActivity;
import as.founder.demo.wang.activity.SwipeRefreshLayoutAC;
import as.founder.demo.wang.activity.TTSActivity;
import as.founder.demo.wang.activity.TextActivity;
import as.founder.demo.wang.activity.TextInputLayoutActivity;
import as.founder.demo.wang.activity.TextViewSpannableActivity;
import as.founder.demo.wang.activity.ThreadRunnableActivity;
import as.founder.demo.wang.activity.TimerWidgetActivity;
import as.founder.demo.wang.activity.TouchActivity;
import as.founder.demo.wang.activity.TouchView2ViewGroupActivity;
import as.founder.demo.wang.activity.TxtActivity;
import as.founder.demo.wang.activity.ViewDragHelper1Activity;
import as.founder.demo.wang.activity.ViewDragHelper2Activity;
import as.founder.demo.wang.activity.ViewPagerPageActivity;
import as.founder.demo.wang.activity.VolleyAC;
import as.founder.demo.wang.activity.VolleyImageLoader;
import as.founder.demo.wang.activity.Web2JS;
import as.founder.demo.wang.activity.XRecyclerViewActivity;
import as.founder.demo.wang.adapter.MainListviewAdapter;
import as.founder.demo.wang.customerviewgroup.CustomerLinearlayoutActivity;
import as.founder.demo.wang.customerviewgroup.FlowActivity;
import as.founder.demo.wang.ijkplayer.IjkPlayerActivity;
import as.founder.demo.wang.videorecorder.ScreenCaptureActivity;
import as.founder.demo.wang.wyyyyPlayView.WyyyyViewActivity;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    MainListviewAdapter listViewAdapter;
    List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main(null);
        dataList =  getData();
        listView = (ListView) findViewById(R.id.listview);
        listViewAdapter = new MainListviewAdapter(dataList,this);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int dpi = metrics.densityDpi;
        Log.e("dpi",dpi +":"+metrics.widthPixels+"-------"+metrics.heightPixels+"--"+dpi);

        getMemoryLimited(this);
        maopao();
        String appVersion;
        PackageManager manager = this.getPackageManager();
        try { PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            appVersion = info.versionName +"版本code" + info.versionCode; //版本名
            Log.i("versionCode",appVersion);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }

    private void maopao(){
        int  a[] = {10,11,12,0,1,3,4,5,6,7};

        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]>a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        for(int i=0;i<a.length;i++){
            Log.i("ufdfisff","的结果："+a[i]);
        }

    }

    public void getMemoryLimited(Activity context)
    {
        ActivityManager activityManager =(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        System.out.println(activityManager.getMemoryClass());
        System.out.println(activityManager.getLargeMemoryClass());
        System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024));
        Log.i("ufdfisff","2<<3的结果："+(2<<3));
    }

    private List<String>  getData(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("OKHTTP请求数据");
        stringList.add("Volley");
        stringList.add("Volley加载图片");
        stringList.add("Freaco加载图片");
        stringList.add("SwipeRefreshLayout上下拉刷新");
        stringList.add("文字排版");
        stringList.add("读取本地txt文件");
        stringList.add("自定义view");
        stringList.add("listview的下拉原理");
        stringList.add("view的下拉原理");
        stringList.add("alarmManager定时器，android的");

        stringList.add("圆角imageview");
        stringList.add("绘制基本图形");
        stringList.add("蓝牙操作");
        stringList.add("listview中item是scrollview");
        stringList.add("自定义viewgroup");
        stringList.add("自定义viewgroup练习linearlayout（垂直）");
        stringList.add("自定义viewgroup练习流式标签");
        stringList.add("mediarecorder音视频录制");
        stringList.add("录制屏幕");
        stringList.add("SurfaceView练习");
        stringList.add("CollasingTollBarLayout练习");
        stringList.add("取代Toast的SnackBar");
        stringList.add("输入框TextInputLayout");
        stringList.add("DrawerLayout+NavigationView");
        stringList.add("touch缩放滑动");
        stringList.add("扫描本地指定后缀文件");
        stringList.add("app的引导页,改为自动滚动的广告页（用位置变换）");
        stringList.add("Collection.sort的排序");
        stringList.add("read Viewpager 翻页");
        stringList.add("scrollview 套imageview");

        stringList.add("textview的高亮");
        stringList.add("自定义位置的矩形");
        stringList.add("所谓的自定义edit");
        stringList.add("recyclverview的上下拉 ofd搜索");
        stringList.add("dialog的几种形式");
        stringList.add("android的亮度调节");
        stringList.add("android的屏幕方向");
        stringList.add("recyclverview下拉刷新");
        stringList.add("android显示时间的控件");
        stringList.add("拼接图片");
        stringList.add("touch事件的传递");
        stringList.add("通过html页面打开Android本地的app，通过这个方法获取网页带过来的数据");
        stringList.add("web2js");
        stringList.add("Dragger2注解框架");
        stringList.add("listview侧滑");
        stringList.add("listview侧滑2viewDraghelper");
        stringList.add("viewDraghelper2");
        stringList.add("画折线图");
       // stringList.add("recyclverView流式布局");
        stringList.add("onsaveianatance");
        stringList.add("加载歌词每行一句居中，超过一行的分行--红豆live");
        stringList.add("跑马灯");
        stringList.add("加载中的发光动画");
        stringList.add("翻页效果");
        stringList.add("ViewPager翻页效果");
        stringList.add("json比较");
        stringList.add("生命周期的复习");
        stringList.add("翻页的demo");
        stringList.add("TTS的原生demo");
        stringList.add("ijkplayer的使用");
        stringList.add("网易云音乐的播放界面");
        stringList.add("歌词界面");
        stringList.add("播放底部");
        stringList.add("popwindow");
        stringList.add("matrix相关");
        stringList.add("AppBarLayout");
        stringList.add("Messenger进程间通信");
        stringList.add("AIDL 进程间通信");
        stringList.add("线程可以执行的任务种类");
        return stringList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent  intent  = new Intent();
        Toast.makeText(getApplicationContext(),"dddddddd:"+position,Toast.LENGTH_LONG).show();
        switch(position){
            case 0:
                intent.setClass(getApplicationContext(), OKHTTP.class);
                break;
            case 1:
                intent.setClass(getApplicationContext(), VolleyAC.class);
                break;
            case 2:
                intent.setClass(getApplicationContext(), VolleyImageLoader.class);
                break;
            case 3:
                intent.setClass(getApplicationContext(), FrescoImageLoader.class);
                break;
            case 4:
                intent.setClass(getApplicationContext(), SwipeRefreshLayoutAC.class);
                break;
            case 5:
                intent.setClass(getApplicationContext(), TextActivity.class);
                break;
            case 6:
                intent.setClass(getApplicationContext(), TxtActivity.class);
                break;
            case 7:
                intent.setClass(getApplicationContext(), CustomerViewActivity.class);
                break;
            case 8:
                intent.setClass(getApplicationContext(), ListActivityDown.class);
                break;
            case 9:
                intent.setClass(getApplicationContext(), PullToRefreshActivity.class);
                break;
            case 10:
                intent.setClass(getApplicationContext(), AlarmManagerActivityl.class);
                break;
            case 11:
                intent.setClass(getApplicationContext(), CircleImageViewActivity.class);
                break;
            case 12:
                intent.setClass(getApplicationContext(), CanvasActivity.class);
                break;
            case 13:
                intent.setClass(getApplicationContext(), BluetoochActivity.class);
                break;
            case 14:
                intent.setClass(getApplicationContext(), ListviewItemScrollViewActivity.class);
                break;
            case 15:
                intent.setClass(getApplicationContext(), CustomerViewGroupActivity.class);
                break;
            case 16:
                intent.setClass(getApplicationContext(), CustomerLinearlayoutActivity.class);
                break;
            case 17:
                intent.setClass(getApplicationContext(), FlowActivity.class);
                break;
            case 18:
                intent.setClass(getApplicationContext(), MediaRecorderActivity.class);
                break;
            case 19:
                intent.setClass(getApplicationContext(), ScreenCaptureActivity.class);
                break;
            case 20:
                intent.setClass(getApplicationContext(), SurfaceViewActivity.class);
                break;
            case 21:
                intent.setClass(getApplicationContext(), CoordinatorLayoutActivity.class);
                break;
            case 22:
                intent.setClass(getApplicationContext(), SnackBarActivity.class);
                break;
            case 23:
                intent.setClass(getApplicationContext(), TextInputLayoutActivity.class);
                break;
            case 24:
                intent.setClass(getApplicationContext(), DrawerLayoutNavigationViewActivity.class);
                break;
            case 25:
                intent.setClass(getApplicationContext(), TouchActivity.class);
                break;
            case 26:
                intent.setClass(getApplicationContext(), ScanningActivity.class);
                break;
            case 27:
                intent.setClass(getApplicationContext(), LeaderActivity.class);
                break;
            case 28:
                intent.setClass(getApplicationContext(), CollectionSortActivity.class);
                break;
            case 29:
                intent.setClass(getApplicationContext(),ReadViewActivity.class);
                break;
            case 30:
                intent.setClass(getApplicationContext(),SrrollViewImageviewActivity.class);
                break;
            case 31:
                intent.setClass(getApplicationContext(),TextViewSpannableActivity.class);
                break;
            case 32:
                intent.setClass(getApplicationContext(),DrawCusActivity.class);
                break;
            case 33:
                intent.setClass(getApplicationContext(),EditTextWithTextViewActivity.class);
                break;
            case 34:
                intent.setClass(getApplicationContext(),RecyclverViewActivity.class);
                break;
            case 35:
                intent.setClass(getApplicationContext(),DialogActivity.class);
                break;
            case 36:
                intent.setClass(getApplicationContext(),BrightnessActivity.class);
                break;
            case 37:
                intent.setClass(getApplicationContext(),ScreenDerectionActivity.class);
                break;
            case 38:
                intent.setClass(getApplicationContext(),RecyclverviewDownActivity.class);
                break;
            case 39:
                intent.setClass(getApplicationContext(),TimerWidgetActivity.class);
                break;
            case 40:
                intent.setClass(getApplicationContext(),JointImageActivity.class);
                break;
            case 41:
                intent.setClass(getApplicationContext(),TouchView2ViewGroupActivity.class);
                break;
            case 42:
                intent.setClass(getApplicationContext(),HtmlAppActivity.class);
                break;
            case 43:
                intent.setClass(getApplicationContext(),Web2JS.class);
                break;
            case 44:
                intent.setClass(getApplicationContext(),Dragger2Activity.class);
                break;
            case 45:
                intent.setClass(getApplicationContext(),ListViewcehuaActivity.class);
                break;
            case 46:
                intent.setClass(getApplicationContext(),ViewDragHelper1Activity.class);
                break;
            case 47:
                intent.setClass(getApplicationContext(),ViewDragHelper2Activity.class);
                break;
            case 48:
                intent.setClass(getApplicationContext(),BrokenLineActivity.class);
                break;
            case 49:
                intent.setClass(getApplicationContext(),OnsaveInatanceActivity.class);
                break;
            case 50:
                intent.setClass(getApplicationContext(),LyricsActivity.class);
                break;
            case 51:
                intent.setClass(getApplicationContext(),MarQueeActivity.class);
                break;
            case 52:
                intent.setClass(getApplicationContext(),LoadingActivity.class);
                break;
            case 53:
                intent.setClass(getApplicationContext(),PageTurningActivity.class);
                break;
            case 54:
                intent.setClass(getApplicationContext(),ViewPagerPageActivity.class);
                break;
            case 55:
                intent.setClass(getApplicationContext(),JsonCompareActivity.class);
                break;
            case 56:
                intent.setClass(getApplicationContext(),LifeCycleActivity.class);
                break;
            case 57:
                intent.setClass(getApplicationContext(),ReaderActivity.class);
                break;
            case 58:
                intent.setClass(getApplicationContext(), TTSActivity.class);
                break;
            case 59:
                intent.setClass(getApplicationContext(), IjkPlayerActivity.class);
            case 60:
                intent.setClass(getApplicationContext(), WyyyyViewActivity.class);
                break;

            case 61:
                intent.setClass(getApplicationContext(), RealLyricsActivity.class);
                break;
            case 62:
                intent.setClass(getApplicationContext(), XRecyclerViewActivity.class);
                break;
            case 63:
                intent.setClass(getApplicationContext(), PopWindowActivity.class);
            case 64:
                intent.setClass(getApplicationContext(), MatrixActivity.class);
                break;
            case 65:
                intent.setClass(getApplicationContext(), AppBarLayoutActivity.class);
                break;
            case 66:
                intent.setClass(getApplicationContext(), MessengerActivity.class);
                break;
            case 67:
                intent.setClass(getApplicationContext(), AIDLActivity.class);
                break;
            case 68:
                intent.setClass(getApplicationContext(), ThreadRunnableActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
       //8 overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
    public static void main(String[] args) {
        String a = "Programming";
        String b = new String("Programming");
        String c = "Program" + "ming";

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a.intern() == b.intern());
    }
}
