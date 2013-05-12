package com.locator.controller;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;

import com.loactor.startup.SplashScreen;
import com.locator.UIScreens.HomeScreen;
import com.locator.connectionManager.Communicator;
import com.locator.constants.Constants;
import com.locator.customClasses.PopupSpinnerScreen;
import com.locator.webservice.ParseResult;

public class Controller extends UiApplication {

	String response;
	private byte[] postdata = null;

	public Controller() {
		showScreen(new SplashScreen());
		invokeLater(runnable);
	}

	Runnable runnable = new Runnable() {
		public void run() {

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Controller.showScreen(new PopupSpinnerScreen("Please Wait..."));
			Thread threadd = new Thread() {
				public void run() {
					try {
						System.out.println("");
						response = new Communicator(Constants.webURL, postdata)
								.communicate();
						System.out.println("----------------------" + response);
						
						ParseResult mParseResult=new ParseResult();
				    	boolean bStatus=mParseResult.parseMetaData(response);

						showScreen(new HomeScreen("Search your contact"));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			threadd.start();
		}
	};

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.enterEventDispatcher();
	}

	// public static void showScreen(Screen screen) {
	// if (screen == null) {
	// return;
	// }
	// synchronized (getEventLock()) {
	// getUiApplication().pushScreen(screen);
	// }
	// }

	public static void showScreen(Screen screen) {
		synchronized (UiApplication.getEventLock()) {
			Screen activeScreen = UiApplication.getUiApplication()
					.getActiveScreen();
			if (activeScreen != null) {
				Class screenClass = activeScreen.getClass();
				if (screen.getClass().equals(screenClass)) {
					UiApplication.getUiApplication().popScreen(activeScreen);
				}
			}
			UiApplication.getUiApplication().pushScreen(screen);
		}
	}
}
