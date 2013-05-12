package com.locator.utility;

import java.util.Vector;

import com.locator.constants.Constants;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public class HomeList implements ListFieldCallback {

	private Vector listElements = new Vector();
	Bitmap image;

	Bitmap arrowImage = Bitmap.getBitmapResource("arrow.png");

	Bitmap imagee1 = Bitmap.getBitmapResource("select_your_branch_icon.png");
	Bitmap imagee2 = Bitmap
			.getBitmapResource("select_process_technology_icon.png");
	Bitmap imagee3 = Bitmap.getBitmapResource("select_your_location.png");

	Bitmap[] bit = { imagee1, imagee2, imagee3 };

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {

		Font font = g.getFont();
		int xPos = 30;
		int yPos = y + (list.getRowHeight() - imagee1.getHeight()) / 2;
		int yPosTxt = y + (list.getRowHeight() - font.getHeight()) / 2;

		if (g.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS)) {
			g.setBackgroundColor(0x005A6971);
			// g.setBackgroundColor(0x005A6971);
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

		g.setFont(Constants.fontBold);
		xPos = xPos + image.getWidth();
		String text = (String) listElements.elementAt(index);
		g.drawText(text, xPos, yPosTxt);
		g.setFont(Constants.fontVerySmall);
		
//		for (int i = 0; i < bit.length; i++) {			
//			xPos = 30 + image.getWidth();
//			String textSubText = Constants.selectedBranch;
//			g.drawText(textSubText, xPos, yPosTxt + 25);
//		}
		
		xPos = 30 + image.getWidth();
		
		
		switch (index) {
		case 0:	
			String textSubText = Constants.selectedBranch;	
			g.drawText(textSubText, xPos, yPosTxt + 25);
			break;

		case 1:
			String textProcessText = Constants.selectedProcess;	
			g.drawText(textProcessText, xPos, yPosTxt + 25);
			break;
			
		case 2:
			String textLocationText = Constants.selectedLocation;	
			g.drawText(textLocationText, xPos, yPosTxt + 25);
			break;
		}

		g.drawBitmap(580, yPos + 3, arrowImage.getWidth(),
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
