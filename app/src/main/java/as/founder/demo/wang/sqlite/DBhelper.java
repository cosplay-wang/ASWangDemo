package as.founder.demo.wang.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhiwei.wang on 2016/11/8.
 * 先生_不靠谱
 */

public class DBhelper extends SQLiteOpenHelper{
    private final static String DBName = "sqlite.db";
    private final static int DBVersion = 1;

    public DBhelper(Context context) {
        super(context, DBName, null, DBVersion);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createSql = "create table if not exits "

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
