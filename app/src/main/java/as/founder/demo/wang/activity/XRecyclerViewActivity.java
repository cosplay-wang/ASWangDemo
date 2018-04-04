package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import as.founder.demo.wang.R;

public class XRecyclerViewActivity extends AppCompatActivity {
    int[] intarrays = {1,121,563,85,20,51,62,45};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
        bubbleSort();
    }
    private void bubbleSort(){
        for(int i=0;i<intarrays.length;i++){
            for(int j=i+1;j<intarrays.length;j++){
                     if(intarrays[i]>intarrays[j]){
                         int temp = intarrays[i];
                         intarrays[i] = intarrays[j];
                         intarrays[j] = temp;
                     }
            }
        }
        for(int i=0;i<intarrays.length;i++){
            Log.e("intarrays",intarrays[i]+"");
        }
    }


}
