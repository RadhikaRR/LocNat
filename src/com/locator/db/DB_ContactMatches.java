package com.locator.db;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;

import com.locator.models.ContactMatchesData;

public class DB_ContactMatches {

	Database database;
	DB_Helper helper;
	Statement st;
	URI myURI;

	public DB_ContactMatches() {
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
			// Statement st =
			// database.createStatement("CREATE TABLE 'market_regions' ( " +
			// "'sas_name' TEXT, " + "'contact_id' INTEGER )");
			Statement st = database.createStatement("CREATE TABLE 'contact_matches' (" + "'id' INTEGER, " + "'country_ISO' TEXT, "
					+ "'contact_id' INTEGER, " + "'market_segment_id' INTEGER, " + "'process_technology_id' INTEGER, " + "'service_id' INTEGER, "
					+ "'product_id' INTEGER)");
			st.prepare();
			st.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDB(ContactMatchesData data) {
		try {
			Statement st = database
					.createStatement("INSERT INTO contact_matches(id,country_ISO,contact_id,market_segment_id,process_technology_id,service_id,product_id) "
							+ "VALUES (?,?,?,?,?,?,?)");
			st.prepare();

			int id = data.getId();
			String country_ISO = data.getCountry_ISO();
			int contact_id = data.getContact_id();
			int market_segment_id = data.getMarket_segment_id();
			int process_technology_id = data.getProcess_technology_id();
			int service_id = data.getService_id();
			int product_id = data.getProduct_id();
			

			st.bind(1, id);
			st.bind(2, country_ISO);
			st.bind(3, contact_id);
			st.bind(4, market_segment_id);
			st.bind(5, process_technology_id);
			st.bind(6, service_id);
			st.bind(7, product_id);
			
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
