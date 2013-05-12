package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.constants.Constants;
import com.locator.controller.Controller;
import com.locator.controller.MainScreenClass;
import com.locator.utility.HomeList;

public class HomeScreen extends MainScreenClass {

	private ListField _lf;

	String[] listArray = { "Select your branch", "Select Process technology",
			"Select your location" };

	public HomeScreen(String Title) {
		super(Title);

		ButtonField btnContact = new ButtonField("Show your service contact",
				FIELD_HCENTER) {
			protected void layout(int width, int height) {
				setExtent(width, 80);
			}
		};
		btnContact.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (Constants.selectedBranch != null
						&& Constants.selectedProcess != null
						&& Constants.selectedLocation != null) {
					Controller.showScreen(new ContactScreen("Your Contact"));
				}
			}
		});

		showList();
		VerticalFieldManager vfm = new VerticalFieldManager();
		vfm.add(_lf);

		vfm.add(btnContact);

		mainVFM.add(vfm);
	}

	public void showList() {

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						switch (index) {
						case 0:
							Controller.showScreen(new BranchScreen(
									"Select Your Branch"));
							break;
						case 1:
							Controller.showScreen(new ProcessTechScreen(
									"Select Process Technology"));
							break;
						case 2:
							Controller.showScreen(new LocationScreen("MAP"));
							break;
						}
					}
				});
				return true;
			}
		};
		HomeList _callback = new HomeList();
		_lf.setCallback(_callback);

		Bitmap imageBackground = Bitmap
				.getBitmapResource("button_for_thumbnails_inactive.png");
		_lf.setBackground(BackgroundFactory
				.createBitmapBackground(imageBackground));

		for (int i = 0; i < listArray.length; i++) {
			_lf.insert(i);
			_callback.insert(listArray[i], i);
		}
		_lf.setRowHeight(80);
		_lf.setPadding(2, 0, 2, 0);
	}
}
