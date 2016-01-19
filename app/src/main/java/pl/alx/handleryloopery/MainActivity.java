package pl.alx.handleryloopery;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	Thread thread;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.guzik).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("test", "jestem w watku " + Thread.currentThread() + ", zaraz zawolam post na handlerze");
				handler.post(new Runnable() {
					@Override
					public void run() {
						Log.i("test", "jestem w watku " + Thread.currentThread() + ", obsluguja post na handlerze...");
						try { Thread.sleep(3000); } catch (InterruptedException e) { }
						Log.i("test", "jestem w watku " + Thread.currentThread() + ", skonczylem obslugiwac post na handlerze");
					}
				});
			}
		});
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Log.i("test", "jestem w watku " + Thread.currentThread() + ", przygotowuje loopera");
				Looper.prepare();
				handler = new Handler();
				Looper.loop();
			}
		});
		thread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
