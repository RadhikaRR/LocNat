package com.locator.constants;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class Constants {
	
	public static double lat;
	public static double lng;
	
	public static final String webURL = "http://buhlerapi.weboapps.com/get_data.php";
	
	public static Bitmap transBorderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
	public static XYEdges transPadding = new XYEdges(12, 12, 12, 12);
	public static Border transRoundedBorder = BorderFactory.createBitmapBorder(transPadding, transBorderBitmap);
	
	public static Bitmap spinnerBitmap = Bitmap.getBitmapResource("spinner.png");
	
	public static Bitmap hfmBackgroundUnfocus = Bitmap.getBitmapResource("button_for_thumbnails_inactive.png");
	public static Bitmap hfmBackgroundFocus = Bitmap.getBitmapResource("button_for_thumbnails_active.png");
	
	public static String selectedBranch = null;
	public static String selectedProcess = null;
	public static String selectedLocation = null;
		
	public static Bitmap bitmapSplash = Bitmap
			.getBitmapResource("splash_screen.png");
	public static Bitmap bitmapBackground = Bitmap
			.getBitmapResource("black_background.png");
	public static Bitmap titleBitmap = Bitmap
			.getBitmapResource("select_button_grey_active.png");

	public static Font font = Font.getDefault().derive(Font.PLAIN, 6,
			Ui.UNITS_pt);
	public static Font fontVerySmall = Font.getDefault().derive(Font.PLAIN, 5,
			Ui.UNITS_pt);
	public static Font fontBold = Font.getDefault().derive(Font.BOLD, 7,
			Ui.UNITS_pt);
	public static Font fontBold9 = Font.getDefault().derive(Font.BOLD, 9,
			Ui.UNITS_pt);
	public static Font fontBold6 = Font.getDefault().derive(Font.BOLD, 6,
			Ui.UNITS_pt);
	public static Font fontBold5 = Font.getDefault().derive(Font.BOLD, 5,
			Ui.UNITS_pt);
	public static Font fontUnderline = Font.getDefault().derive(
			Font.UNDERLINED | Font.BOLD, 7, Ui.UNITS_pt);

	public static String[] listArrayCommodity = { "Grain", "Rice", "Oil Seed",
			"Malt", "Brewery and Distellery", "Pulses and Spices",
			"Fruits and Vegetable" };

	public static String[] listArrayProcessed = { "Pasta", "Cereals",
			"Ingredients", "Chocolate", "Cocoa", "Nuts",
			"Coffee" };

	public static String[] listArrayAdvance = { "Inks", "Coatings",
			"Chemicals_and_cosmetics", "Electronic_materials",
			"Performance_additives", "Pet", "Recycled_pet" };

	public static String[] listArraydie = { "Aerospace", "Automotive",
			"Electronics", "Other_technical_applications",
			"Aerospace", "Automotive", "Other_technical_applications" };

	public static String[] listArrayfeed = { "Animal_feed",
			"Animal_feed", "Aqua_feed", "Aqua_feed", "Pet_food",
			"Pet_food", "Animal_feed" };

	public static String[] listArrayRenewable = { "Bioethanol",
			"Biomass_pellets", "Biomass_pellets", "Bioethanol",
			"Biomass_pellets", "Bioethanol", "Bioethanol" };
}
