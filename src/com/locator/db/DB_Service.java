package com.locator.db;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;

import com.locator.models.ServicesData;

public class DB_Service {

	Database database;
	DB_Helper helper;
	Statement st;
	URI myURI;

	public DB_Service() {
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
			Statement st = database.createStatement("CREATE TABLE 'services' ( " + "'Name' TEXT, " + "'id' INTEGER )");
			st.prepare();
			st.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDB(ServicesData data) {
		try {
			Statement st = database.createStatement("INSERT INTO services(Name,id) " + "VALUES (?,?)");
			st.prepare();

			String strName = data.getStrName();
			int id = data.getId();

			st.bind(1, strName);
			st.bind(2, id);
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
