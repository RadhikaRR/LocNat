package com.locator.utility;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public class SubBranchList implements ListFieldCallback {

	private Vector listElements = new Vector();
	Bitmap image;

	Bitmap arrowImage = Bitmap.getBitmapResource("arrow.png");
	Bitmap imagee2 = Bitmap.getBitmapResource("Rice.png");

	
//	Bitmap imagee1 = Bitmap.getBitmapResource("01_grain.png");
//	Bitmap imagee2 = Bitmap.getBitmapResource("02_rice.png");
//	Bitmap imagee3 = Bitmap.getBitmapResource("03_oilseeds.png");
//	Bitmap imagee4 = Bitmap.getBitmapResource("breweryDistillery.png");
//	Bitmap imagee5 = Bitmap.getBitmapResource("fruitsVegetables.png");
//	Bitmap imagee6 = Bitmap.getBitmapResource("malt.png");
//	Bitmap imagee7 = Bitmap.getBitmapResource("pulsesSpices.png");
	
//	Bitmap[] bit = { imagee1, imagee2, imagee3, imagee4, imagee5, imagee6,
//			imagee7 };
	
	
	Bitmap[] bit;
	
	public SubBranchList(String[] listArray) {
		
//		bit = new Bitmap[listArray.length];
//		
//		for(int i=0;i<listArray.length;i++){			
//			String stringName = listArray[i]+".png";
//			bit[i] = Bitmap.getBitmapResource(stringName);
//		}ok code
		
//		Bitmap imagee1 = Bitmap.getBitmapResource("01_grain.png");
//		Bitmap imagee2 = Bitmap.getBitmapResource("02_rice.png");
//		Bitmap imagee3 = Bitmap.getBitmapResource("03_oilseeds.png");
//		Bitmap imagee4 = Bitmap.getBitmapResource("breweryDistillery.png");
//		Bitmap imagee5 = Bitmap.getBitmapResource("fruitsVegetables.png");
//		Bitmap imagee6 = Bitmap.getBitmapResource("malt.png");
//		Bitmap imagee7 = Bitmap.getBitmapResource("pulsesSpices.png");
		
//		Bitmap[] bit = { imagee1, imagee2, imagee3, imagee4, imagee5, imagee6,
//				imagee7 };
	}

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {

		Font font = g.getFont();
		int xPos = 30;
//		int yPos = y + (list.getRowHeight() - bit[1].getHeight()) / 2;
		int yPos = y + (list.getRowHeight() - imagee2.getHeight()) / 2;
		int yPosTxt = y + (list.getRowHeight() - font.getHeight()) / 2;

		// Bitmap imageBackground = Bitmap
		// .getBitmapResource("button_for_thumbnails_inactive.png");
		// if (list.getSelectedIndex() != index) {
		// g.drawBitmap(0, 0, 640, 80, imageBackground, 0, 0);
		// }

		if (g.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS)) {
			g.setBackgroundColor(0x005A6971);
			// g.drawRoundRect(0, 0, 630, 80, 15, 15);
			g.clear();
		} else {
			g.setColor(Color.WHITE);
			// g.drawRoundRect(0, 0, 630, 80, 15, 15);
		}

//		for (int i = 0; i < bit.length; i++) {
//			image = bit[index];
//			g.drawBitmap(5, yPos + 3, image.getWidth(), image.getHeight(),
//					image, 0, 0);
//		}
		
		g.drawBitmap(5, yPos + 3, imagee2.getWidth(), imagee2.getHeight(),
				imagee2, 0, 0);

		xPos = xPos + imagee2.getWidth();
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
