package as.founder.demo.wang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/12/22.
 */

public class DialogPracticeF extends Dialog {
    public DialogPracticeF(Context context) {
        super(context);
    }

    public DialogPracticeF(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String positiveButtonText;
        private String negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public DialogPracticeF create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final DialogPracticeF dialog = new DialogPracticeF(context, R.style.Dialog);
            final View layout = inflater.inflate(R.layout.rename_dialog_layout, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final CheckBox rename_text_title_2_check = (CheckBox) layout.findViewById(R.id.rename_text_title_2_check);
            rename_text_title_2_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                }
            });
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.rename_lin_sure))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.rename_lin_sure))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                    if(rename_text_title_2_check.isChecked()){
                                        Toast.makeText(context,"选中了",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(context,"未选中了",Toast.LENGTH_SHORT).show();
                                    }
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.rename_lin_sure).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.rename_lin_false))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.rename_lin_false))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {


                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.rename_lin_false).setVisibility(
                        View.GONE);
            }
            // set the content message

            dialog.setContentView(layout);
            return dialog;
        }


    }

}
