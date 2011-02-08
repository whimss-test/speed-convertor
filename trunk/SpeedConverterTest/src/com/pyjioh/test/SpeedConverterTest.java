package com.pyjioh.test;

import com.pyjioh.SpeedConverter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

public class SpeedConverterTest extends
		ActivityInstrumentationTestCase2<SpeedConverter> {

	private EditText editKmPerHour;
	private EditText editMeterPerSec;
	private SpeedConverter activity;

	public SpeedConverterTest(String name) {
		super("com.pyjioh", SpeedConverter.class);
		setName(name);
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

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testControlsCreated() {
		assertNotNull(activity);
		assertNotNull(editKmPerHour);
		assertNotNull(editMeterPerSec);
	}

	@SmallTest
	public void testControlsVisible() {
		ViewAsserts
				.assertOnScreen(editKmPerHour.getRootView(), editMeterPerSec);
		ViewAsserts
				.assertOnScreen(editMeterPerSec.getRootView(), editKmPerHour);
	}

	@SmallTest
	public void testStartingEmpty() {
		assertEquals("editKmPerHour is not empty", "", editKmPerHour.getText()
				.toString());
		assertEquals("editMeterPerSec is not empty", "", editMeterPerSec
				.getText().toString());
	}

	@SmallTest
	public void testKmPerHourToMeterPerSec() {
		editKmPerHour.clearComposingText();
		editMeterPerSec.clearComposingText();

		TouchUtils.tapView(this, editKmPerHour);
		sendKeys("1");

		double meterPerSec;
		try {
			meterPerSec = Double.parseDouble(editMeterPerSec.getText()
					.toString());
		} catch (Exception e) {
			meterPerSec = -1;
		}

		assertTrue("100 km/h is not 27.778 m/s: " + meterPerSec,
				meterPerSec > 27.7 && meterPerSec < 27.8);
	}
}
