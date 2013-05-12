package com.locator.UIScreens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.locator.constants.Constants;
import com.locator.controller.MainScreenClass;

public class FavouriteScreen extends MainScreenClass {

	BitmapField bitmapFieldLike;
	LabelField labelLike;
	LabelField labelUnLike;
	BitmapField bitmapFieldUnLike;
	CustomLayoutManagerLike hfmLike;
	CustomLayoutManagerUnLike hfmUnLike;
	VerticalFieldManager vfmLike;
	VerticalFieldManager vfmUnLike;

	public FavouriteScreen(String Title) {
		super(Title);

		vfmLike = new VerticalFieldManager();
		vfmLike.setBorder(roundedBorder);

		labelLike = new LabelField("Rahul Like");
		Bitmap bitmapLike = Bitmap.getBitmapResource("favourite_icon_active.png");
		bitmapFieldLike = new BitmapField(bitmapLike, Field.FIELD_RIGHT |Field.FOCUSABLE){
			protected boolean navigationClick(int status, int time) {
				UiApplication.getUiApplication().invokeLater(new Runnable() {					
					public void run() {
						System.out.println("");
						vfmUnLike.add(hfmLike);
						vfmLike.delete(hfmLike);
					}
				});
				return true;				
			};
		};		
		
		// hfmLike.add(labelLike);
		// hfmLike.add(bitmapFieldLike);
		hfmLike = new CustomLayoutManagerLike();
		hfmLike.setBorder(roundedBorder);
		hfmLike.setBackground(BackgroundFactory.createBitmapBackground(Constants.hfmBackgroundUnfocus));
		vfmLike.add(hfmLike);
		mainVFM.add(vfmLike);
		mainVFM.add(new LabelField("Your Last Entries"));
		

		vfmUnLike = new VerticalFieldManager();
		vfmUnLike.setBorder(roundedBorder);	
		
		labelUnLike = new LabelField("Rahul Not Like");
		Bitmap bitmapUnLike = Bitmap.getBitmapResource("favourite_icon_inactive.png");
		bitmapFieldUnLike = new BitmapField(bitmapUnLike, Field.FIELD_RIGHT|Field.FOCUSABLE){
			protected boolean navigationClick(int status, int time) {
				UiApplication.getUiApplication().invokeLater(new Runnable() {					
					public void run() {
						
					}
				});
				return true;
			};
		};	
		
		hfmUnLike = new CustomLayoutManagerUnLike();
		hfmUnLike.setBorder(roundedBorder);
		hfmUnLike.setBackground(BackgroundFactory.createBitmapBackground(Constants.hfmBackgroundUnfocus));
		vfmUnLike.add(hfmUnLike);
		mainVFM.add(vfmUnLike);
	}

	class CustomLayoutManagerLike extends Manager {
		protected CustomLayoutManagerLike() {
			super(Manager.NO_HORIZONTAL_SCROLL | Manager.NO_VERTICAL_SCROLL);
			add(labelLike);
			add(bitmapFieldLike);
		}

		protected void sublayout(int width, int height) {
			layoutChild(labelLike, width, height);
			setPositionChild(labelLike, 10, 20);

			layoutChild(bitmapFieldLike, width, height);
			setPositionChild(bitmapFieldLike, 500, 20);

			setExtent(Display.getWidth(), 60);
		}
	}

	class CustomLayoutManagerUnLike extends Manager {
		protected CustomLayoutManagerUnLike() {
			super(Manager.NO_HORIZONTAL_SCROLL | Manager.NO_VERTICAL_SCROLL);
			add(labelUnLike);
			add(bitmapFieldUnLike);
		}

		protected void sublayout(int width, int height) {
			layoutChild(labelUnLike, width, height);
			setPositionChild(labelUnLike, 10, 20);

			layoutChild(bitmapFieldUnLike, width, height);
			setPositionChild(bitmapFieldUnLike, 500, 20);

			setExtent(Display.getWidth(), 60);
		}
	}
}
