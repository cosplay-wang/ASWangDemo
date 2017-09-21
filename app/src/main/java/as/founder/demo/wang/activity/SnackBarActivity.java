package as.founder.demo.wang.activity;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import as.founder.demo.wang.R;

public class SnackBarActivity extends AppCompatActivity {
    Snackbar snackbar;
    RelativeLayout activitySnackar;
    Button snackButton,button2;
    CoordinatorLayout layoutRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        snackButton = (Button) findViewById(R.id.snackbar);
        activitySnackar = (RelativeLayout) findViewById(R.id.activity_snack_bar);
        layoutRoot = (CoordinatorLayout) findViewById(R.id.layoutRoot);

        button2 = (Button) findViewById(R.id.button2);
        snackbar =  Snackbar.make(layoutRoot,"snackBar",Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getApplicationContext(),"dddddd",Toast.LENGTH_SHORT).show();
                                }
                            })
                .setActionTextColor(Color.RED)
                .setDuration(4000);
        snackbar.getView().setBackgroundColor(Color.GREEN);

        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snackbar = Snackbar.make(layoutRoot,"snackBar",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // Toast.makeText(getApplicationContext(),"dddddd",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(Color.RED)

                        .setDuration(4000);
                snackbar.getView().setBackgroundColor(Color.BLUE);
                snackbar.show();

            }
        });

    }
}
