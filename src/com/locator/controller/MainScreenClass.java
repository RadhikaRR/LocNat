package com.locator.controller;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.locator.UIScreens.FavouriteScreen;
import com.locator.UIScreens.HomeScreen;
import com.locator.UIScreens.SettingScreen;
import com.locator.constants.Constants;
import com.locator.utility.BitmapButtonField;
import com.locator.utility.Utils;

public class MainScreenClass extends MainScreen {

	Bitmap resizebitmap;
	String response;
	LabelField label;
	BitmapButtonField contact, favourite, setting;
	HorizontalFieldManager hfm;
	protected Border roundedBorder;

	protected VerticalFieldManager mainVFM;

	public MainScreenClass(String Title) {
		super(NO_VERTICAL_SCROLL | NO_VERTICAL_SCROLLBAR);

		Bitmap borderBitmap = Bitmap.getBitmapResource("rounded-border.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);

		resizebitmap = Utils.resizeBitmap(Constants.bitmapBackground,
				Display.getWidth(), Display.getHeight(), false);

		this.getMainManager().setBackground(
				BackgroundFactory.createBitmapBackground(resizebitmap));

		hfm = new HorizontalFieldManager(USE_ALL_WIDTH | FIELD_HCENTER) {
			protected void sublayout(int maxWidth, int maxHeight) {
				super.sublayout(Display.getWidth(), Display.getHeight());
				setExtent(Display.getWidth(), 70);

				int x = (int) ((getWidth() - label.getWidth()) * 0.50);
				int y = (int) ((getHeight() - label.getHeight()) * 0.50);

				// set the position for the field
				setPositionChild(label, x, y);
			}
		};

		hfm.setBackground(BackgroundFactory
				.createBitmapBackground(Constants.titleBitmap));

		label = new LabelField(Title, LabelField.FIELD_HCENTER);
		label.setFont(Constants.fontBold9);
		hfm.add(label);
		// CustomTitlebar titleBarField = new
		// CustomTitlebar("Search Your Contact", Color.WHITE, 0x000072BC,
		// Constants.titleBitmap, Field.USE_ALL_WIDTH);
		// vfm.add(titleBarField);
		setTitle(hfm);

		Bitmap contactBitmap = Bitmap.getBitmapResource("contact_inactive.png");
		Bitmap contactFocBitmap = Bitmap
				.getBitmapResource("contact_active.png");

		Bitmap favouriteBitmap = Bitmap
				.getBitmapResource("favourites_inactive.png");
		Bitmap favouriteFocBitmap = Bitmap
				.getBitmapResource("favourites_active.png");

		Bitmap settingBitmap = Bitmap
				.getBitmapResource("settings_inactive.png");
		Bitmap settingFocBitmap = Bitmap
				.getBitmapResource("settings_active.png");

		contact = new BitmapButtonField(contactBitmap, contactFocBitmap,
				DrawStyle.HCENTER);
		favourite = new BitmapButtonField(favouriteBitmap, favouriteFocBitmap,
				DrawStyle.HCENTER);
		setting = new BitmapButtonField(settingBitmap, settingFocBitmap,
				DrawStyle.HCENTER);

		mainVFM = new VerticalFieldManager(VERTICAL_SCROLL) {
			protected void sublayout(int maxWidth, int maxHeight) {
				super.sublayout(Display.getWidth(), Display.getHeight());
				setExtent(640, 280);
			}
		};
		// mainVFM.setBorder(roundedBorder);
		add(mainVFM);

		CustomBottomTAB ingrBtnLayout = new CustomBottomTAB();
		// ingrBtnLayout.setBorder(roundedBorder);
		add(ingrBtnLayout);

		FieldChangeListener listener = new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == contact) {
					Controller
							.showScreen(new HomeScreen("Select Your Contact"));
				} else if (field == favourite) {
					Controller.showScreen(new FavouriteScreen("Favourites"));
				} else {
					Controller.showScreen(new SettingScreen("Settings"));
				}
			}
		};
		contact.setChangeListener(listener);
		favourite.setChangeListener(listener);
		setting.setChangeListener(listener);
	}

	class CustomBottomTAB extends Manager {
		protected CustomBottomTAB() {
			super(Manager.NO_HORIZONTAL_SCROLL | Manager.NO_VERTICAL_SCROLL);

			add(contact);
			add(favourite);
			add(setting);
		}

		protected void sublayout(int width, int height) {
			layoutChild(contact, Display.getWidth(), Display.getHeight());
			setPositionChild(contact,
					(Display.getWidth() - contact.getWidth()) * 5 / 100, 0);

			layoutChild(favourite, Display.getWidth(), Display.getHeight());
			setPositionChild(favourite,
					(Display.getWidth() - favourite.getWidth()) * 54 / 100, 0);

			layoutChild(setting, Display.getWidth(), Display.getHeight());
			setPositionChild(setting,
					(Display.getWidth() - setting.getWidth()) * 100 / 100, 0);

			setExtent(620, setting.getHeight());
		}
	}
}
