package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.constants.Constants;
import com.locator.controller.MainScreenClass;
import com.locator.models.SubBranchData;
import com.locator.utility.SubBranchList;
import com.locator.webservice.ParseResult;

public class SubBranchScreen extends MainScreenClass {

	private ListField _lf;

	String[] listArray ;

	public SubBranchScreen(String Title, int branchListItemIndex) {
		super(Title);

		int size = ParseResult.mSubBranchDataArray.size();
		listArray = new String[size];
		for (int i = 0; i < size; i++) {
			System.out.println("");
			SubBranchData subBranchData = (SubBranchData) ParseResult.mSubBranchDataArray
					.elementAt(i);
			listArray[i] = subBranchData.getStrName();
			System.out.println("--------------" + listArray[i]);
		}

		showList(branchListItemIndex);
		VerticalFieldManager vfm = new VerticalFieldManager();
		vfm.add(_lf);

		mainVFM.add(vfm);
	}

	public void showList(int branchListItemIndex) {

		// switch (branchListItemIndex) {
		// case 0:
		// listArray = Constants.listArrayCommodity;
		// break;
		// case 1:
		// listArray = Constants.listArrayProcessed;
		// break;
		// case 2:
		// listArray = Constants.listArrayAdvance;
		// break;
		// case 3:
		// listArray = Constants.listArraydie;
		// break;
		// case 4:
		// listArray = Constants.listArrayfeed;
		// break;
		// case 5:
		// listArray = Constants.listArrayRenewable;
		// break;
		// }

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						Constants.selectedBranch = listArray[index];						

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
						
//						Controller.showScreen(new HomeScreen("Search your contact"));
						System.out.println("");
					}
				});
				return true;
			}
		};
		SubBranchList _callback = new SubBranchList(listArray);
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
