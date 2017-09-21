package as.founder.demo.wang.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import as.founder.demo.wang.R;
import as.founder.demo.wang.dialog.DialogPracticeF;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.normal_dialog_2)
    TextView normal_2Dialog;
    @BindView(R.id.normal_dialog_3)
    TextView normal_3Dialog;
    @BindView(R.id.list_normal_dialog)
    TextView listDialog;
    @BindView(R.id.radiobutton_normal_dialog)
    TextView radiobuttonDialog;
    @BindView(R.id.checkbox_normal_dialog)
    TextView checkboxDialog;
    @BindView(R.id.customer_normal_dialog)
    TextView customerDialog;
    @OnClick({R.id.normal_dialog_2,R.id.normal_dialog_3,R.id.list_normal_dialog,R.id.radiobutton_normal_dialog,R.id.checkbox_normal_dialog,R.id.customer_normal_dialog})
     public void Onclick(View view){
        doOnClick(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    public void doOnClick(View view){
        if(view.getId() == R.id.normal_dialog_2){
            setNormal_2Dialog();
        }else if(view.getId() == R.id.normal_dialog_3){
            setNormal_3Dialog();
        }else if(view.getId() == R.id.list_normal_dialog){
            setlistDialog();
        }else if(view.getId() == R.id.radiobutton_normal_dialog){
            setsingleDialog();
        }else if(view.getId() == R.id.checkbox_normal_dialog){
            setMultichoiceDialog();
        }else if(view.getId() == R.id.customer_normal_dialog){
            setCustomerDialog();
        }
    }
    private void setNormal_2Dialog(){
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.tbug);//设置icon
        builder.setTitle("两个选项的正常的dialog");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });
        dialog = builder.create();
        dialog.show();

    }
    private void setNormal_3Dialog(){
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.tbug);//设置icon
        builder.setTitle("3个选项的正常的dialog");
        builder.setMessage("3个选项的正常的dialog");
        builder.setView(new EditText(this));
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which== Dialog.BUTTON_POSITIVE){
                    Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                }else if(which == Dialog.BUTTON_NEGATIVE){
                    Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
                }else if(which == Dialog.BUTTON_NEUTRAL){
                    Toast.makeText(DialogActivity.this,"忽略",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        };
        builder.setPositiveButton("queding",onClickListener);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                Toast.makeText(DialogActivity.this,"忽略",Toast.LENGTH_SHORT).show();
//            }
//        });
        dialog = builder.create();
        dialog.show();

    }
    private void setlistDialog(){
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.tbug);//设置icon
        builder.setTitle("列表的dialog");
       // builder.setMessage("dddddddddddddddddd");//在设置item时，不可以设置message，否则只显示message
        final String[] items = {"选项一","选项二","选项三"};
        builder.setItems(items, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(DialogActivity.this,items[which],Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });
        dialog = builder.create();
        dialog.show();

    }
    private void setsingleDialog(){
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.tbug);//设置icon
        builder.setTitle("单选的dialog");
        // builder.setMessage("dddddddddddddddddd");//在设置item时，不可以设置message，否则只显示message
        final String[] items = {"选项一","选项二","选项三"};
        builder.setSingleChoiceItems(items,1, new DialogInterface.OnClickListener(){//0代表默认选中的位置
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(DialogActivity.this,items[which],Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });
        dialog = builder.create();
        dialog.show();

    }
    private void setMultichoiceDialog(){
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.tbug);//设置icon
        builder.setTitle("多选的dialog");
        builder.setView(R.layout.listview_item_layout);
        // builder.setMessage("dddddddddddddddddd");//在设置item时，不可以设置message，否则只显示message
        final String[] items = {"选项一","选项二","选项三"};
        final boolean[] mulitems = {true,true,false};
        builder.setMultiChoiceItems(items, mulitems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(DialogActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });
        dialog = builder.create();
        dialog.show();

    }
    private void setCustomerDialog(){
        final DialogPracticeF.Builder builder = new DialogPracticeF.Builder(this);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                // 清除掉所有特殊字符
                //  String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？]";
                String regEx = "";
                Pattern p = Pattern.compile(regEx);

                //设置你的操作事项
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

        builder.create().show();

    }
}
