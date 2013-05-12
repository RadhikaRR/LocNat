package com.locator.customClasses;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.locator.UIScreens.LocationScreen;
import com.locator.constants.Constants;
import com.locator.utility.BorderLabelFieldImpl;

public class FilteredListPopUp extends PopupScreen implements
		FieldChangeListener {

	private ObjectListField listField;
	private EditField editField;
	private LabelField labelField;
	private String[] array;
	private String[] dummayArray = new String[0];
	private BorderLabelFieldImpl borderLabelFieldImpl;

	public FilteredListPopUp(String msg, String[] array) {

		super(new VerticalFieldManager(), Field.FOCUSABLE);

		this.array = array;

		Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		Border roundedBorder = BorderFactory.createBitmapBorder(padding,
				borderBitmap);
		this.setBorder(roundedBorder);

		HorizontalFieldManager labelHorizontalFieldManager = new HorizontalFieldManager();
		borderLabelFieldImpl = new BorderLabelFieldImpl(msg, USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.fontBold);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(0x000072BC);
		borderLabelFieldImpl.setMargin(1, 3, 10, 3);
		labelHorizontalFieldManager.add(borderLabelFieldImpl);
		add(labelHorizontalFieldManager);

		VerticalFieldManager vfm1 = new VerticalFieldManager();
		labelField = new LabelField("Search");
		editField = new EditField("", "", 50, USE_ALL_WIDTH) {
			protected void paintBackground(Graphics g) {
				g.clear();
				if (isFocus()) {
					g.setColor(0x000072BC);
					invalidate();
				} else {
					g.setColor(Color.BLACK);
				}
				g.drawRect(0, 0, getWidth(), getHeight());
			}

			protected void paint(Graphics graphics) {
				graphics.setColor(Color.WHITE);
				super.paint(graphics);
			}

			protected boolean navigationClick(int status, int time) {
				fieldChangeNotify(0);
				return true;
			}
		};
		editField.setChangeListener(this);
		vfm1.add(labelField);
		vfm1.add(editField);
		vfm1.add(new SeparatorField());
		vfm1.add(new SeparatorField());
		add(vfm1);

		VerticalFieldManager vfm2 = new VerticalFieldManager(
				Manager.VERTICAL_SCROLL);
		listField = new ObjectListField() {
			protected boolean navigationClick(int status, int time) {
				int index = listField.getSelectedIndex();
				String string = listField.get(listField, index).toString();

				LocationScreen.editSearch.setText(string);

				Screen screen = UiApplication.getUiApplication()
						.getActiveScreen();
				UiApplication.getUiApplication().popScreen(screen);
				return true;
			}
		};
		listField.set(array);
		vfm2.add(listField);
		add(vfm2);
	}

	public void fieldChanged(Field field, int context) {
		String text = editField.getText();
		reInitializeList(text);
	}

	private boolean compare(String string, String string2) {

		if (string.length() > string2.length()) {
			return false;
		}

		string = string.toLowerCase();
		string2 = string2.toLowerCase();
		/*
		 * Rahul exception may come:array out of bound exception when length of
		 * iccode ayyay element is less than 4 since getsearchindex is 4 .so
		 * handle later
		 */
		try {
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) != string2.charAt(i)) {
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	private void reInitializeList(String string) {
		Vector vector = new Vector();

		for (int i = 0; i < array.length; i++) {
			boolean match = compare(string, array[i]);
			if (match) {
				vector.addElement(array[i]);
			}
		}

		String[] newdummayArray = new String[vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			newdummayArray[i] = (String) vector.elementAt(i);
		}
		listField.set(newdummayArray);

		if (newdummayArray.length == 0) {
			listField.set(dummayArray);
			listField.setEmptyString("No Suggestions found", 0);
		}
		invalidate();
	}

	protected boolean onSave() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean onClose() {
		Screen screen = UiApplication.getUiApplication().getActiveScreen();
		UiApplication.getUiApplication().popScreen(screen);
		return true;
	}
}
