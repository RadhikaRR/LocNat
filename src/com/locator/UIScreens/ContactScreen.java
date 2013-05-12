package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.constants.Constants;
import com.locator.controller.MainScreenClass;
import com.locator.utility.ProcessList;

public class ContactScreen extends MainScreenClass {

	private ListField _lf;
	private String[] listArray = { "Main Contact", "Customer Service",
			"Automation Support", "Spare Parts", "Sales" };

	public ContactScreen(String Title) {
		super(Title);

		HorizontalFieldManager hfm = new HorizontalFieldManager();
		hfm.setBorder(roundedBorder);

		Bitmap bitContact = Bitmap
				.getBitmapResource("profile_image_placeholder.png");
		BitmapField BitThumbnail = new BitmapField(bitContact);
		hfm.add(BitThumbnail);

		VerticalFieldManager vfmContactInformation = new VerticalFieldManager();
		LabelField labelTitle = new LabelField("Buhler AG Australia");
		labelTitle.setFont(Constants.fontBold);
		LabelField labelSubTitle = new LabelField(
				"Unit 1/128 Rooks Road\nVermont Victoria\nAustralia");
		vfmContactInformation.add(labelTitle);
		vfmContactInformation.add(labelSubTitle);
		hfm.add(vfmContactInformation);

		mainVFM.add(hfm);

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
