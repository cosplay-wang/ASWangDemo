package as.founder.demo.wang.asynctask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * Created by zhiwei.wang on 2017/2/24.
 */

public class AStask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
