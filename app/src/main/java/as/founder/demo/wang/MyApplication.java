package as.founder.demo.wang;

import com.kingja.loadsir.core.LoadSir;

import as.founder.demo.wang.videorecorder.Application;

/**
 * Created by zhiwei.wang on 2017/11/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()


//                .addCallback(new CustomCallback())
//                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
