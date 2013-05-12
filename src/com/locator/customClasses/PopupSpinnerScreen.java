package com.locator.customClasses;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.locator.constants.Constants;
import com.locator.utility.SpinnerField;

public class PopupSpinnerScreen extends PopupScreen {

	private VerticalFieldManager verticalFieldManager;

	public PopupSpinnerScreen(String info) {

		super(new HorizontalFieldManager());
		
		this.setBorder(Constants.transRoundedBorder);

		verticalFieldManager = new VerticalFieldManager(FIELD_HCENTER | FIELD_VCENTER);

		if (!(info.equals("") || info.equals(null))) {
			verticalFieldManager.add(new LabelField(info, FIELD_HCENTER));
			verticalFieldManager.add(new LabelField());
		}

		verticalFieldManager.add(new SpinnerField());

		add(verticalFieldManager);
	}
}