package com.locator.utility;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class BorderLabelFieldImpl extends LabelField {
	private int fontColor = 0;
	private int bgColor = -1;
	Border b1, b2;

	public BorderLabelFieldImpl() {
		super();
	}

	public BorderLabelFieldImpl(Object text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public BorderLabelFieldImpl(Object text, long style) {
		super(text, style);
		// TODO Auto-generated constructor stub
	}

	public BorderLabelFieldImpl(Object text, int string, int length, long style) {
		super(text, string, length, style);
	}

	public int getFontColor() {
		return fontColor;
	}

	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	// protected void paint(Graphics graphics) {
	//
	// XYRect extent = graphics.getClippingRect();
	// if (bgColor != -1) {
	// graphics.setColor(bgColor);
	// // graphics.fillRect(extent.x, extent.y, extent.width,
	// // extent.height);
	// // graphics.fillRect(extent.x, extent.y, getWidth(),
	// // getHeight());//rr
	// graphics.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
	// // XYEdges bEdges = new XYEdges(8, 8, 8, 8);
	// // b1 = BorderFactory.createRoundedBorder(bEdges, Color.RED,
	// Border.STYLE_SOLID);
	// // this.setBorder(b1);
	// }
	// graphics.setColor(fontColor);
	//
	// super.paint(graphics);
	//
	// // graphics.setColor(Color.BLACK);
	// graphics.setColor(Color.WHITE);
	// // graphics.drawRect(extent.x, extent.y, extent.width,
	// // extent.height);
	// // graphics.drawRect(extent.x, extent.y, getWidth(),
	// // getHeight());//rr
	// graphics.drawRoundRect(extent.x, extent.y, getWidth(), getHeight(), 14,
	// 14);
	//		
	// // XYEdges bEdges = new XYEdges(8, 8, 8, 8);
	// // b2 = BorderFactory.createRoundedBorder(bEdges, Color.WHITE,
	// Border.STYLE_SOLID);
	// // this.setBorder(b2);
	//
	// // XYEdges bEdges = new XYEdges(8, 8, 8, 8);
	// // b1 = BorderFactory.createRoundedBorder(bEdges, Color.RED,
	// // Border.STYLE_SOLID);
	// //
	// // //XYEdges bEdges = new XYEdges(8, 8, 8, 8);
	// // b2 = BorderFactory.createRoundedBorder(bEdges, Color.WHITE,
	// // Border.STYLE_SOLID);
	// //
	// // if (isFocus()) {
	// // this.setBorder(b1);
	// //
	// // } else {
	// // this.setBorder(b2);
	// // }
	// // super.paint(graphics);
	// }

	protected void paint(Graphics graphics) {
		XYRect extent = graphics.getClippingRect();
		if (bgColor == -1) {

			graphics.setColor(bgColor);
			// graphics.fillRect(extent.x, extent.y, extent.width,
			// extent.height);
			// graphics.fillRoundRect(0, 0, getWidth(), getHeight(),20,20);
			graphics.drawRect(extent.x, extent.y, extent.width, extent.height);
		}
		graphics.setColor(fontColor);

		super.paint(graphics);

		graphics.setColor(Color.BLACK);
		
		invalidate();
	}
}
