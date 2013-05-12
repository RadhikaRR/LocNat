package com.locator.UIScreens;

import net.rim.device.api.lbs.maps.MapDimensions;
import net.rim.device.api.lbs.maps.MapFactory;
import net.rim.device.api.lbs.maps.model.MapDataModel;
import net.rim.device.api.lbs.maps.model.MapLocation;
import net.rim.device.api.lbs.maps.model.MapPoint;
import net.rim.device.api.lbs.maps.model.Mappable;
import net.rim.device.api.lbs.maps.ui.MapAction;
import net.rim.device.api.lbs.maps.ui.RichMapField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.connectionManager.Communicator;
import com.locator.constants.Constants;
import com.locator.controller.Controller;
import com.locator.controller.MainScreenClass;
import com.locator.customClasses.FilteredListPopUp;
import com.locator.models.CountryData;
import com.locator.utility.BitmapButtonField;
import com.locator.webservice.ParseResult;

public class LocationScreen extends MainScreenClass {
	
	RichMapField _map;

	public static EditField editSearch;

	BitmapButtonField buttonLocate;

	String[] listCountry;

	public LocationScreen(String Title) {
		super(Title);

		int size = ParseResult.mCountryDataArray.size();
		listCountry = new String[size];
		for (int i = 0; i < size; i++) {
			CountryData countryData = (CountryData) ParseResult.mCountryDataArray
					.elementAt(i);
			listCountry[i] = countryData.getName();
			System.out.println("--------------" + listCountry[i]);
		}

		Bitmap hfmBG = Bitmap
				.getBitmapResource("button_for_thumbnails_inactive.png");

		Bitmap bitmapLocate = Bitmap
				.getBitmapResource("locate_symbol_inactive.png");
		Bitmap bitmapLocateSelect = Bitmap
				.getBitmapResource("locate_symbol_active.png");
		buttonLocate = new BitmapButtonField(bitmapLocate, bitmapLocateSelect,
				DrawStyle.HCENTER);

		HorizontalFieldManager hfmSearchBar = new HorizontalFieldManager(
				USE_ALL_WIDTH);
		hfmSearchBar.setBackground(BackgroundFactory
				.createBitmapBackground(hfmBG));

		HorizontalFieldManager hfmSearch = new HorizontalFieldManager() {
			protected void sublayout(int maxWidth, int maxHeight) {
				super.sublayout(Display.getWidth(), Display.getHeight());
				setExtent(
						(Display.getWidth() - (Display.getWidth() * 30 / 100)),
						40);
			}
		};
		Bitmap bitmapSearch = Bitmap.getBitmapResource("search_symbol.png");
		BitmapField searchField = new BitmapField(bitmapSearch);

		editSearch = new EditField("", "", 15, Field.FOCUSABLE
				| EditField.NO_NEWLINE) {
			public void paint(Graphics g) {
				if (getTextLength() == 0) {
					g.setColor(0x00a0a0a0);
					g.drawText("Click to Search Country", 0, 0);
				}
				invalidate();
				super.paint(g);
			}

			protected boolean navigationClick(int status, int time) {
				Controller.showScreen(new FilteredListPopUp("Search Country",
						listCountry));
				return true;
			}
		};
		hfmSearch.add(searchField);
		hfmSearch.add(editSearch);
		hfmSearch.setBorder(roundedBorder);

		HorizontalFieldManager hfmLocate = new HorizontalFieldManager();
		ButtonField btnSearch = new ButtonField("Go");
		btnSearch.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {

				try {
					System.out.println("--------------------button clicked");
					String countryname = editSearch.getText();
					if (countryname != null || countryname != "") {
						String URLaddress = "http://maps.googleapis.com/maps/api/geocode/json?address="
								+ countryname + "&sensor=false";

						String response = new Communicator(URLaddress, null)
								.communicate();

						System.out.println("----------------------" + response);

						ParseResult mParseResult = new ParseResult();
						mParseResult.parseMetaData(response);
						System.out.println("");
						double lattitude = Constants.lat;
						double longitude = Constants.lng;
						setMap(lattitude, longitude);
					}

				} catch (Exception e) {
					System.out.println("-------------" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		ButtonField btnOK = new ButtonField("OK");
		btnOK.setChangeListener(new FieldChangeListener() {			
			public void fieldChanged(Field field, int context) {
				Constants.selectedLocation = editSearch.getText();						

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
		
		hfmLocate.add(btnSearch);
		hfmLocate.add(btnOK);
		hfmLocate.add(buttonLocate);

		hfmSearchBar.add(hfmSearch);
		hfmSearchBar.add(hfmLocate);

		mainVFM.add(hfmSearchBar);		
		
		 _map = MapFactory.getInstance().generateRichMapField();		
//		 _map.getMapField().setDimensions(new MapDimensions(Display.getWidth(), Display.getHeight() - 100));
		 _map.getMapField().setDimensions(new MapDimensions(Display.getWidth(), 200));
		
		 MapAction action = _map.getAction();
		 action.setCenterAndZoom(new MapPoint(18.5204303000, 73.8567437000),0);
		
		 mainVFM.add(_map);
	}
	
	public void setMap(double lat, double lon) {
		System.out.println("");

		 MapDataModel model = _map.getModel();
		 MapLocation location = new MapLocation(lat, lon, null, null);
		
		 int headOfficeId = model.add((Mappable) location, "Rahul");
		 model.tag(headOfficeId, "WEBO");
		
		 model.setVisibleNone();
		 model.setVisible("WEBO");
		
		 _map.getMapField().update(true);
	}
}
