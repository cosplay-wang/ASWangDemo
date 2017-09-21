package as.founder.demo.wang.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by zhiwei.wang on 2016/11/8.
 * 先生_不靠谱
 */

public class DBmanager {
    public  static DBmanager instance = null;
    private SQLiteDatabase db;
    private DBhelper dBhelper;


    public DBmanager getInstance(){
        if(instance == null){
            instance = new DBmanager();
        }
        return instance;
    }


    public void init(Context context) {
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }
    public void insertDBtestClassTable(){

        String sql = "create table " + DBtestClass.class.getSimpleName().toLowerCase()+"(id int,name char[50],order char[50])";
        db.execSQL(sql);

    }
}
