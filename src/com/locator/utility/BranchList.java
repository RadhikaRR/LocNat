package com.locator.utility;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public class BranchList implements ListFieldCallback {

	private Vector listElements = new Vector();
	Bitmap image;

	Bitmap arrowImage = Bitmap.getBitmapResource("arrow.png");

	Bitmap imagee1 = Bitmap.getBitmapResource("commodityFood.png");
	Bitmap imagee2 = Bitmap.getBitmapResource("_processed_food.png");
	Bitmap imagee3 = Bitmap.getBitmapResource("_advanced_materials.png");
	Bitmap imagee4 = Bitmap.getBitmapResource("_die_casting.png");
	Bitmap imagee5 = Bitmap.getBitmapResource("_feed.png");
	Bitmap imagee6 = Bitmap.getBitmapResource("_renewable_energy.png");

	

	Bitmap[] bit = { imagee1, imagee2, imagee3, imagee4, imagee5, imagee6 };

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {

		Font font = g.getFont();
		int xPos = 30;
		int yPos = y + (list.getRowHeight() - imagee1.getHeight()) / 2;
		int yPosTxt = y + (list.getRowHeight() - font.getHeight()) / 2;

//		Bitmap imageBackground = Bitmap
//		.getBitmapResource("button_for_thumbnails_inactive.png");
//		if (list.getSelectedIndex() != index) {
//			g.drawBitmap(0, 0, 640, 80, imageBackground, 0, 0);
//		}

		if (g.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS)) {
			g.setBackgroundColor(0x005A6971);
			// g.drawRoundRect(0, 0, 630, 80, 15, 15);
			g.clear();
		} else {
			g.setColor(Color.WHITE);
			// g.drawRoundRect(0, 0, 630, 80, 15, 15);
		}

		for (int i = 0; i < bit.length; i++) {
			image = bit[index];
			g.drawBitmap(5, yPos + 3, image.getWidth(), image.getHeight(),
					image, 0, 0);
		}

		xPos = xPos + image.getWidth();
		String text = (String) listElements.elementAt(index);
		g.drawText(text, xPos, yPosTxt + 3);

		g.drawBitmap(580, yPos + 7, arrowImage.getWidth(),
				arrowImage.getHeight(), arrowImage, 0, 0);
	}

	public Object get(ListField list, int index) {
		return listElements.elementAt(index);
	}

	public int getPreferredWidth(ListField list) {
		return Display.getWidth();
	}

	public void insert(String toInsert, int index) {
		listElements.insertElementAt(toInsert, index);
	}

	public void erase() {
		listElements.removeAllElements();
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return listElements.indexOf(listField);
	}

}
