//package com.locator.customClasses;
//
//import com.locator.utility.FilteredList;
//
//import net.rim.device.api.system.Bitmap;
//import net.rim.device.api.ui.Field;
//import net.rim.device.api.ui.Manager;
//import net.rim.device.api.ui.UiApplication;
//import net.rim.device.api.ui.XYEdges;
//import net.rim.device.api.ui.component.ObjectListField;
//import net.rim.device.api.ui.container.PopupScreen;
//import net.rim.device.api.ui.container.VerticalFieldManager;
//import net.rim.device.api.ui.decor.Border;
//import net.rim.device.api.ui.decor.BorderFactory;
//
//public class SearchScreen extends PopupScreen {
//	
//	public static VerticalFieldManager vfm;
//	private ObjectListField listField;
//	String[] array ;
//
//	public SearchScreen(String[] dummayArray) {	
//
//		super(new VerticalFieldManager(), Field.FOCUSABLE);
//		
//		array = dummayArray;
//
//		Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
//		XYEdges padding = new XYEdges(12, 12, 12, 12);
//		Border roundedBorder = BorderFactory.createBitmapBorder(padding,
//				borderBitmap);
//		this.setBorder(roundedBorder);
//
//		vfm = new VerticalFieldManager(
//				Manager.VERTICAL_SCROLL);	
//		
//		listField = new ObjectListField() {
//			protected boolean navigationClick(int status, int time) {
//				int index = listField.getSelectedIndex();
//				String string = listField.get(listField, index).toString();
//				FilteredList.editField.setText(string);
//
//				UiApplication.getUiApplication().invokeLater(new Runnable() {
//
//					public void run() {						
//						listField.set(array);
//						listField.setEmptyString("", 0);
//						invalidate();
//					}
//				});
//				return true;
//			}
//		};
//		listField.setRowHeight(30);
//		listField.set(dummayArray);
//		listField.setEmptyString("Type for suggestions", 0);
//
//		add(vfm);
//	}
//}
