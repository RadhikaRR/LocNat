package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.constants.Constants;
import com.locator.controller.MainScreenClass;
import com.locator.models.ProcessData;
import com.locator.utility.ProcessList;
import com.locator.webservice.ParseResult;

public class ProcessTechScreen extends MainScreenClass {

	private ListField _lf;

//	private String[] listArray = { "Automation", "Grain & Material Handling",
//			"Ooptical Sotring", "Cleaning", "Sifting & Granding",
//			"Milling,Grinding & Flaking", "Refining" };
	
	private String[] listArray;

	public ProcessTechScreen(String Title) {
		super(Title);
		
		int size = ParseResult.mProcessDataArray.size();
		listArray = new String[size];
		for (int i = 0; i < size; i++) {
			System.out.println("");
			ProcessData processData = (ProcessData) ParseResult.mProcessDataArray
					.elementAt(i);
			listArray[i] = processData.getStrName();
			System.out.println("--------------" + listArray[i]);
		}

		showList();
		VerticalFieldManager vfm = new VerticalFieldManager();
		vfm.add(_lf);

		mainVFM.add(vfm);
	}

	public void showList() {

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();

				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						Constants.selectedProcess = listArray[index];
						
						UiApplication ui = UiApplication.getUiApplication();
						int screenCount = ui.getScreenCount();
						for (int i = 0; i < screenCount; i++) {
							Screen activeScreen = ui.getActiveScreen();
							if (activeScreen instanceof HomeScreen) {
								break;
							} else {
								ui.popScreen(activeScreen);
							}
						}
					}
				});
				return true;
			}
		};
		ProcessList _callback = new ProcessList();
		_lf.setCallback(_callback);

		Bitmap imageBackground = Bitmap
				.getBitmapResource("button_for_thumbnails_inactive.png");
		_lf.setBackground(BackgroundFactory
				.createBitmapBackground(imageBackground));

		for (int i = 0; i < listArray.length; i++) {
			_lf.insert(i);
			_callback.insert(listArray[i], i);
		}
		_lf.setRowHeight(100);
		_lf.setPadding(2, 0, 2, 0);
	}
}
