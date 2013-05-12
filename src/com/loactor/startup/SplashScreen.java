package com.loactor.startup;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.MainScreen;

import com.locator.constants.Constants;

public class SplashScreen extends MainScreen {

	private Bitmap resizebitmap;
	private BitmapField bitmapField;

	public SplashScreen() {
		show();
	}

	private void show() {

//		resizebitmap = Utils.resizeBitmap(Constants.bitmapSplash,
//				Display.getWidth(), Display.getHeight(), false);
//		bitmapField = new BitmapField(resizebitmap);
		
		bitmapField = new BitmapField(Constants.bitmapSplash);
		add(bitmapField);
	}

	public boolean onClose() {
		System.exit(0);
		return true;
	}
}
