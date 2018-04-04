package as.founder.demo.wang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadRunnableActivity extends AppCompatActivity {

	@BindView(R.id.runnable)
	TextView runnable;
	@BindView(R.id.callable)
	TextView callable;
    @OnClick ({R.id.runnable,R.id.callable})
	public void doClick(View view){
      switch(view.getId()){
		  case R.id.runnable:
		  	startRunnable();
		  	break;
		  case R.id.callable:
            startCallable();
		  	break;
	  }
	}

	private void startCallable() {
		//ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		ArrayList<FutureTask<String>> resultsT = new ArrayList<FutureTask<String>>();
		for(int i = 0; i < 5; i++){
			FutureTask<String> mFutureTask = new FutureTask<String>(new MyCallable(i));
			executor.submit(mFutureTask);
			resultsT.add(mFutureTask);
			/**
			 * future和futuretask的区别
			 */
			//results.add(executor.submit(new MyCallable(i)));//进行工作
		}
		for(FutureTask<String> future :resultsT){
			try {
				Log.e("MyCallable", "MyCallable" + future.get());
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				executor.shutdown();
			}
		}

	}

	private void startRunnable() {

		for(int i = 0; i < 5; i++){
			executor.execute(new MyRunnable());//进行工作
		}
		executor.shutdown();

	}
	ExecutorService executor = Executors.newCachedThreadPool();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_runnable);
		ButterKnife.bind(this);

	}


	class MyRunnable implements Runnable{
		private int taskCount = 5;
		@Override
		public void run() {
			if(taskCount >0){
				taskCount --;
				Log.e("MyRunnable","MyRunnable"+(5-taskCount));
			}

		}
	}
	class MyCallable implements Callable<String>{
		int id;

		public MyCallable(int id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			return ""+ id;
		}
	}

}
