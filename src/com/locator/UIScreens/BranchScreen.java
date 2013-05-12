package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.controller.Controller;
import com.locator.controller.MainScreenClass;
import com.locator.models.BranchData;
import com.locator.utility.BranchList;
import com.locator.webservice.ParseResult;

public class BranchScreen extends MainScreenClass {

	private ListField _lf;

	// String[] listArray = { "Commodity Food", "Processed Food",
	// "Advanced Material", "Die Casting", "Feed", "Renewable Energy" };

	String[] listArray;

	public BranchScreen(String Title) {
		super(Title);

		int size = ParseResult.mBranchDataArray.size();
		listArray = new String[size];
		for (int i = 0; i < size; i++) {
			System.out.println("");
			BranchData branchData = (BranchData) ParseResult.mBranchDataArray
					.elementAt(i);
			listArray[i] = branchData.getStrName();
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
						switch (index) {
						case 0:
							Controller.showScreen(new SubBranchScreen(
									"Commodity Food", index));
							break;
						case 1:
							Controller.showScreen(new SubBranchScreen(
									"Processed Food", index));
							break;
						case 2:
							Controller.showScreen(new SubBranchScreen(
									"Advanced Material", index));
							break;
						case 3:
							Controller.showScreen(new SubBranchScreen(
									"Die Casting", index));
							break;
						case 4:
							Controller.showScreen(new SubBranchScreen("Feed",
									index));
							break;
						case 5:
							Controller.showScreen(new SubBranchScreen(
									"Renewable Energy", index));
							break;
						}
					}
				});
				return true;
			}
		};
		BranchList _callback = new BranchList();
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
