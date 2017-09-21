package as.founder.demo.wang.activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import as.founder.demo.wang.R;

public class OnsaveInatanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onsave_inatance);
        if(savedInstanceState != null){
            String hh = savedInstanceState.get("key").toString();
            Log.i("ddddddddd",hh);
            Toast.makeText(getApplicationContext(),"ddddddddddddddddd"+savedInstanceState.get("key").toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key","value");
        Log.i("ddddddddd","ddd");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            Toast.makeText(getApplicationContext(),savedInstanceState.get("key").toString(),Toast.LENGTH_SHORT).show();
            String hh = savedInstanceState.get("key").toString();
            Log.i("ddddddddd","dd:"+hh);
        }
    }
}
