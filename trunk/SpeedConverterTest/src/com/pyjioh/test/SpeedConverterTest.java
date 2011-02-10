package com.pyjioh.test;

import com.pyjioh.SpeedConverter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.EditText;

public class SpeedConverterTest extends
		ActivityInstrumentationTestCase2<SpeedConverter> {

	private EditText editKmPerHour;
	private EditText editMeterPerSec;
	private SpeedConverter activity;

	public SpeedConverterTest() {
		super("com.pyjioh", SpeedConverter.class);
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		activity = getActivity();
		editKmPerHour = (EditText) activity
				.findViewById(com.pyjioh.R.id.editKmPerHour);
		editMeterPerSec = (EditText) activity
				.findViewById(com.pyjioh.R.id.editMetersPerSec);
	}

	public void testControlsCreated() {
		assertNotNull(activity);
		assertNotNull(editKmPerHour);
		assertNotNull(editMeterPerSec);
	}

	public void testControlsVisible() {
		ViewAsserts
				.assertOnScreen(editKmPerHour.getRootView(), editMeterPerSec);
		ViewAsserts
				.assertOnScreen(editMeterPerSec.getRootView(), editKmPerHour);
	}

	public void testStartingEmpty() {
		assertEquals("editKmPerHour is not empty", "", editKmPerHour.getText()
				.toString());
		assertEquals("editMeterPerSec is not empty", "", editMeterPerSec
				.getText().toString());
	}

	public void testConvertKmhToMs() {
		TouchUtils.tapView(this, editKmPerHour);
		sendKeys(KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_0,
				KeyEvent.KEYCODE_0);

		double meterPerSec;
		try {
			meterPerSec = Double.parseDouble(editMeterPerSec.getText()
					.toString());
		} catch (Exception e) {
			meterPerSec = -1;
		}

		assertTrue("100 km/h is not 27.778 m/s", meterPerSec > 27.7
				&& meterPerSec < 27.8);

	}

	public void testConvertMsToKmh() {
		TouchUtils.tapView(this, editMeterPerSec);
		sendKeys(KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_5);

		double meterPerSec;
		try {
			meterPerSec = Double
					.parseDouble(editKmPerHour.getText().toString());
		} catch (Exception e) {
			meterPerSec = -1;
		}

		assertTrue("15 m/s is not 54 km/h", meterPerSec == 54);
	}

	public void testGetError() {
		TouchUtils.tapView(this, editKmPerHour);
		sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_DEL);

		assertEquals("Must be Error!",
				activity.getString(com.pyjioh.R.string.errorMsg),
				editMeterPerSec.getText().toString());
	}
}
