package as.founder.demo.wang.singleTon;

import android.app.AlertDialog;
import android.app.Dialog;

import as.founder.demo.wang.MainActivity;

/**
 * Created by zhiwei.wang on 2017/12/1.
 */

public class SingleTon {
    private static class SingleTonHolder{
        private static  final  SingleTonHolder instance = new SingleTonHolder();
    }
    public static SingleTonHolder getSingleTonHolder(){
        return  SingleTonHolder.instance;
    }

    /**
     * 最重要的一步，构造方法私有化，不可以被new
     */
    private SingleTon (){
        Dialog dialog = new Dialog(new MainActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(new MainActivity());

    }


}
