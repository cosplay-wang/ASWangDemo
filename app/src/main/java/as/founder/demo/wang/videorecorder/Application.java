package as.founder.demo.wang.videorecorder;

import android.content.Context;
import android.content.Intent;

/**
 * Created by zhiwei.wang on 2016/9/14.
 * 先生_不靠谱
 */
public class Application extends android.app.Application {

    private static Application application;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 启动 Marvel service
        startService(new Intent(this, ScreenCaptureService.class));
    }

    public static Application getInstance() {
        return application;
    }
}


