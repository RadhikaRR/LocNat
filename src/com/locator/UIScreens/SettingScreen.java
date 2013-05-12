package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.locator.controller.MainScreenClass;
import com.locator.utility.JustifiedHorizontalFieldManager;
import com.locator.utility.LabeledSwitch;

public class SettingScreen extends MainScreenClass {

	public SettingScreen(String Title) {
		super(Title);

		Bitmap switch_left = Bitmap.getBitmapResource("switch_left.png");
		Bitmap switch_right = Bitmap.getBitmapResource("switch_right.png");
		Bitmap switch_left_focus = Bitmap
				.getBitmapResource("switch_left_focus.png");
		Bitmap switch_right_focus = Bitmap
				.getBitmapResource("switch_right_focus.png");
		
		VerticalFieldManager vfmTrackingUpdate = new VerticalFieldManager(VERTICAL_SCROLL);

		VerticalFieldManager hfmTracking = new VerticalFieldManager(
				USE_ALL_WIDTH);
		LabeledSwitch callSwitch = new LabeledSwitch(switch_left, switch_right,
				switch_left_focus, switch_right_focus, "on", "off", true);
		JustifiedHorizontalFieldManager automaticTracking = new JustifiedHorizontalFieldManager(
				new LabelField("Automatic Tracking"), callSwitch, false,
				USE_ALL_WIDTH);
		automaticTracking.setPadding(5, 5, 5, 5);
		automaticTracking.setBorder(roundedBorder);
		hfmTracking.setBorder(roundedBorder);
		hfmTracking.add(automaticTracking);

		LabelField labelTracking = new LabelField(
				"When enabled, the location is automatically updated to the current location at the start of the Buhler Locator App",
				USE_ALL_WIDTH);
		hfmTracking.add(labelTracking);
//		mainVFM.add(hfmTracking);

//		mainVFM.add(new SeparatorField());

		VerticalFieldManager hfmUpdate = new VerticalFieldManager(
				USE_ALL_WIDTH);
		LabeledSwitch msgSwitch = new LabeledSwitch(switch_left, switch_right,
				switch_left_focus, switch_right_focus, "on", "off", false);
		JustifiedHorizontalFieldManager automaticUpdate = new JustifiedHorizontalFieldManager(
				new LabelField("Automatic Update"), msgSwitch, false,
				USE_ALL_WIDTH);
		automaticUpdate.setPadding(5, 5, 5, 5);
		automaticUpdate.setBorder(roundedBorder);
		hfmUpdate.setBorder(roundedBorder);
		hfmUpdate.add(automaticUpdate);

		LabelField labelUpdate = new LabelField(
				"When enabled, there will be automatically check for update at the start of the Buhler Location App",
				USE_ALL_WIDTH);
		hfmUpdate.add(labelUpdate);
//		mainVFM.add(hfmUpdate);
		
		vfmTrackingUpdate.add(hfmTracking);
		vfmTrackingUpdate.add(new SeparatorField());
		vfmTrackingUpdate.add(hfmUpdate);
		mainVFM.add(vfmTrackingUpdate);
	}
}
