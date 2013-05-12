package com.locator.db;

import com.locator.models.MktRegionsData;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;

public class DB_MarketRegion {

	Database database;
	DB_Helper helper;
	Statement st;
	URI myURI;

	public DB_MarketRegion() {
		try {
			myURI = URI.create("file:///SDCard/Databases/Locator/" + "LocatorDatabase.db");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void createDB() {

		try {
			database = DatabaseFactory.openOrCreate(myURI);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void openDB() {
		try {
			database = DatabaseFactory.open(myURI);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void createTable() {
		try {
//			Statement st = database.createStatement("CREATE TABLE 'market_regions' ( " + "'sas_name' TEXT, " + "'contact_id' INTEGER )");
			Statement st = database.createStatement("CREATE TABLE 'market_regions' (" + "'id' INTEGER, " + "'sas_name' TEXT, " + "'contact_id' INTEGER )");
			st.prepare();
			st.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDB(MktRegionsData data) {
		try {
			Statement st = database.createStatement("INSERT INTO market_regions(id,sas_name,contact_id) " + "VALUES (?,?,?)");
			st.prepare();

			int id = data.getId();
			String strName = data.getStrSasName();
			int contact_id = data.getContact_id();

			st.bind(1, id);
			st.bind(2, strName);
			st.bind(3, contact_id);
			st.execute();
			st.reset();
		} catch (Exception e) {
			System.out.println("-----------------" + e.getMessage());
			// TODO: handle exception
		}
	}

	public void closeDB() {
		try {
			st.close();
			database.close();
		} catch (Exception e) {
			System.out.println("-----------------" + e.getMessage());
			// TODO: handle exception
		}
	}
}
