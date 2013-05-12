package com.locator.db;

import com.locator.models.ContactsDetailData;
import com.locator.models.MktRegionsData;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;

public class DB_ContactDetails {

	Database database;
	DB_Helper helper;
	Statement st;
	URI myURI;

	public DB_ContactDetails() {
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
			Statement st = database.createStatement("CREATE TABLE 'contacts' (" + "'id' INTEGER, " + "'Name' TEXT, "
					+ "'Phone' TEXT, " + "'telex' TEXT, " + "'email' TEXT, " + "'email_visible' INTEGER, "
					+ "'address_1' TEXT, " + "'address_2' TEXT, " + "'address_3' TEXT, " + "'address_4' TEXT, "
					+ "'address_5' TEXT, " + "'address_6' TEXT, " + "'address_7' TEXT, " + "'address_8' TEXT, "
					+ "'address_9' TEXT, " + "'address_10' TEXT)");
			st.prepare();
			st.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDB(ContactsDetailData data) {
		try {
			Statement st = database
					.createStatement("INSERT INTO contacts(id,Name,Phone,telex,email,email_visible,address_1,address_2,address_3,address_4,address_5,address_6,address_7,address_8,address_9,address_10) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			st.prepare();

			int id = data.getId();
			String Name = data.getStrName();
			String Phone = data.getPhone();
			String telex = data.getTelex();
			String email = data.getEmail();
			int email_visible = data.getEmail_visible();
			String address_1 = data.getAddress_1();
			String address_2 = data.getAddress_2();
			String address_3 = data.getAddress_3();
			String address_4 = data.getAddress_4();
			String address_5 = data.getAddress_5();
			String address_6 = data.getAddress_6();
			String address_7 = data.getAddress_7();
			String address_8 = data.getAddress_8();
			String address_9 = data.getAddress_9();
			String address_10 = data.getAddress_10();
			

			st.bind(1, id);
			st.bind(2, Name);
			st.bind(3, Phone);
			st.bind(4, telex);
			st.bind(5, email);
			st.bind(6, email_visible);
			st.bind(7, address_1);
			st.bind(8, address_2);
			st.bind(9, address_3);
			st.bind(10, address_4);
			st.bind(11, address_5);
			st.bind(12, address_6);
			st.bind(13, address_7);
			st.bind(14, address_8);
			st.bind(15, address_9);
			st.bind(16, address_10);
			
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
