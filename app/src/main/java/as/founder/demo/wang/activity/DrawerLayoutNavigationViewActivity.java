package as.founder.demo.wang.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;

import as.founder.demo.wang.R;

public class DrawerLayoutNavigationViewActivity extends AppCompatActivity {
    NavigationView navigationitem;
    DrawerLayout activity_drawer_layout_navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_navigation_view);




        activity_drawer_layout_navigation_view = (DrawerLayout) findViewById(R.id.activity_drawer_layout_navigation_view);
        navigationitem = (NavigationView) findViewById(R.id.navigation_view);
        navigationitem.setItemIconTintList(null);
        navigationitem.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item_one:
                        Snackbar.make(findViewById(R.id.activity_drawer_layout_navigation_view),item.getTitle(),Snackbar.LENGTH_SHORT).
                                setAction("YOUBIAN", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).setDuration(5000).show();


                        break;
                    case R.id.item_two:
                        Snackbar.make(findViewById(R.id.activity_drawer_layout_navigation_view),item.getTitle(),Snackbar.LENGTH_SHORT).
                                setAction("YOUBIAN", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).setDuration(5000).show();
                        break;
                    case R.id.item_three:
                        item.setVisible(false);	// true 为显示，false 为隐藏
                        break;
                }
                item.setChecked(true);
                activity_drawer_layout_navigation_view.closeDrawers();
                return false;
            }
        });

    }
}
