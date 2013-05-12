package com.locator.db;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;

import com.locator.models.CountryData;

public class DB_CountryData {

	Database database;
	DB_Helper helper;
	Statement st;
	URI myURI;

	public DB_CountryData() {
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
			System.out.println("");
			// Statement st =
			// database.createStatement("CREATE TABLE 'market_regions' ( " +
			// "'sas_name' TEXT, " + "'contact_id' INTEGER )");
			Statement st = database.createStatement("CREATE TABLE 'countries' (" + "'id' INTEGER, " + "'country_ISO' TEXT, "
					+ "'name' TEXT, " + "'web_region_id' INTEGER, " + "'world_region_id' INTEGER, " + "'market_region_id' INTEGER)");
			st.prepare();
			st.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDB(CountryData data) {
		try {
			Statement st = database
					.createStatement("INSERT INTO countries(id,country_ISO,name,web_region_id,world_region_id,market_region_id)"
							+ "VALUES (?,?,?,?,?,?)");
			
			st.prepare();

			int id = data.getId();
			String country_ISO = data.getCountry_ISO();
			String name = data.getName();
			int web_region_id = data.getWeb_region_id();
			int world_region_id = data.getWorld_region_id();
			int market_region_id = data.getMarket_region_id();
			

			st.bind(1, id);
			st.bind(2, country_ISO);
			st.bind(3, name);
			st.bind(4, web_region_id);
			st.bind(5, world_region_id);
			st.bind(6, market_region_id);
			
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
