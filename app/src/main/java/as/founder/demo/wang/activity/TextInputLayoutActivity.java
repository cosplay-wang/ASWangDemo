package as.founder.demo.wang.activity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import as.founder.demo.wang.R;

public class TextInputLayoutActivity extends AppCompatActivity {
    TextInputLayout passwordTextInputLayout,nameTextInputLayout;
    EditText passwordEditText;
    TextInputEditText nameTextInputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_text_layout);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        passwordTextInputLayout.setHint("请输入密码");
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检查实际是否匹配，由自己实现
                if (!s.toString().equals("密码")) {
                    passwordTextInputLayout.setError("请输入  密码   二字");
                } else {
                    passwordTextInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameTextInputLayout = (TextInputLayout) findViewById(R.id.name_text_layout);
        nameTextInputEditText = (TextInputEditText) findViewById(R.id.name_edit_input);
        nameTextInputLayout.setHint("请输入用户名");
        nameTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检查实际是否匹配，由自己实现
                if (!s.toString().equals("用户名")) {
                    nameTextInputEditText.setError("请输入  用户名   二字");
                    nameTextInputLayout.setError("请输入  用户名   二字");
                } else {
                    nameTextInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
