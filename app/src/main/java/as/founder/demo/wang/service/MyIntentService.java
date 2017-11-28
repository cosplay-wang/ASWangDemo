package as.founder.demo.wang.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * Created by zhiwei.wang on 2017/11/15.
 */

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Node  head = null;
    }
}
