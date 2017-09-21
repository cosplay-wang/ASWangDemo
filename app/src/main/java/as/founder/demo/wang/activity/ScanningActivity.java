package as.founder.demo.wang.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.adapter.ScanningFileAdaper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScanningActivity extends AppCompatActivity {
    @BindView(R.id.scanning_tv)
    TextView scanningTV;
    @BindView(R.id.scanning_recy)
    RecyclerView scanRecyclerView;
    @BindView(R.id.activity_scanning)
    RelativeLayout activityView;

    List<String> textFileList = new ArrayList<>();
    ScanningFileAdaper adapter;
    Snackbar snackbar;
    Unbinder unbinder;

    @OnClick(R.id.scanning_tv)
    public void OnClick(View view) {
        snackbar.show();
        if (view.getId() == R.id.scanning_tv) {
            textFileList.clear();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    scanningTxtFile();
                    Message mess = handler.obtainMessage();
                    mess.what = 1;
                    handler.sendMessage(mess);
                }
            }).start();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // scanningTV.setText(msg.obj.toString());
            snackbar.dismiss();
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scanning);
        unbinder = ButterKnife.bind(this);
        snackbar = Snackbar.make(activityView, "扫描中", 5000000).setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();


            }
        });


        adapter = new ScanningFileAdaper(ScanningActivity.this, textFileList);
        adapter.setOnItemClickListener(new ScanningFileAdaper.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                Toast.makeText(getApplicationContext(), textFileList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new ScanningFileAdaper.OnItemLongClickListener() {
            @Override
            public void ItemLongClick(int position) {
                Toast.makeText(getApplicationContext(), "长按时间" + textFileList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        scanRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        scanRecyclerView.setAdapter(adapter);


    }

    public void scanningTxtFile() {
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        // recursiveMethod(rootPath + "/UCDownloads");
        recursiveMethod(rootPath);

    }

    public void recursiveMethod(String path) {

        File file = new File(path);
        File[] fileList = file.listFiles();

        if (fileList.length > 0) {


            for (int i = 0; i < fileList.length; i++) {

                if (fileList[i].isDirectory()) {
                    recursiveMethod(fileList[i].getAbsolutePath());
                } else {
                    String name = fileList[i].getName();
                    if (name.endsWith(".pdf") || name.endsWith(".ofd")) {
                        if (isAdd(textFileList, fileList[i].getParent())) {
                            textFileList.add(fileList[i].getParent());
                        }
                        textFileList.add(fileList[i].getName());
                    } else {
                    }
                }
            }
        }
    }

    public boolean isAdd(List<String> list, String path) {
        boolean isadd = true;
        if (list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(path) || list.get(j) == (path)) {
                    return false;
                }


            }
        } else {
            //list.add(path);
            isadd = true;
        }
        return isadd;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
