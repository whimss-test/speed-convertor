package com.pyjioh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class SpeedConverter extends Activity {
	final String LOG = "SpeedConverter";
	private EditText editKmPerHour;
	private EditText editMeterPerSec;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editKmPerHour = (EditText) findViewById(R.id.editKmPerHour);
		editMeterPerSec = (EditText) findViewById(R.id.editMetersPerSec);

		// setup listener for Kilometers per hour EditText
		editKmPerHour.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_UP) {
					try {
						double kmPerHour = Double.parseDouble(editKmPerHour
								.getText().toString());
						double meterPerSec = kmPerHour * 0.2777777777777778;
						editMeterPerSec.setText(new Double(meterPerSec)
								.toString());
					} catch (NumberFormatException e) {
						editMeterPerSec.setText(R.string.errorMsg);
						Log.d(LOG, "e:" + e);
					}
					return true;
				}
				return false;
			}
		});

		// setup listener for Meters per second EditText
		editMeterPerSec.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_UP) {
					try {
						double meterPerSec = Double.parseDouble(editMeterPerSec
								.getText().toString());
						double kmPerHour = meterPerSec * 3.6;
						editKmPerHour.setText(new Double(kmPerHour).toString());
					} catch (NumberFormatException e) {
						editKmPerHour.setText(R.string.errorMsg);
						Log.d(LOG, "e:" + e);
					}
					return true;
				} 
				return false;
			}
		});
	}
}