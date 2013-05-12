package com.locator.db;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.Statement;

public class DB_Helper {

	private static final String DATABASE_NAME = "locator";
	private static final int DATABASE_VERSION = 1;

	Database db;
	Statement st;

	public DB_Helper() {

	}

	public Statement createTable() {
		try {
			// Android requires _id
			String createSql = "CREATE TABLE Branches (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, " + "branch_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Sub_branches (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT NOT NULL, "
					+ "branch_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Process_technologies (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, " + "process_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Services (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT NOT NULL, "
					+ "service_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Market_regions (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "sas_name TEXT NOT NULL, " + "mkt_reg_id INTEGER NOT NULL," + "contact_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "contact_name TEXT NOT NULL, " + "contact_id INTEGER NOT NULL," + "phone TEXT NOT NULL, "
					+ "telex TEXT NOT NULL, " + "email TEXT NOT NULL, " + "address_1 TEXT NOT NULL, "
					+ "address_2 TEXT NOT NULL, " + "address_3 TEXT NOT NULL, " + "address_4 TEXT NOT NULL, "
					+ "address_5 TEXT NOT NULL, " + "address_6 TEXT NOT NULL, " + "address_7 TEXT NOT NULL, "
					+ "address_8 TEXT NOT NULL, " + "address_9 TEXT NOT NULL, " + "address_10 TEXT NOT NULL, "
					+ "email_visible INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Contact_matches (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "cont_match_id INTEGER NOT NULL," + "country_ISO TEXT NOT NULL, "
					+ "contact_id INTEGER NOT NULL," + "market_segment_id INTEGER NOT NULL,"
					+ "process_technology_id INTEGER NOT NULL," + "service_id INTEGER NOT NULL,"
					+ "product_id INTEGER NOT NULL);";
			db.createStatement(createSql);

			createSql = "CREATE TABLE Countries (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "country_id INTEGER NOT NULL," + "country_ISO TEXT NOT NULL, " + "country_name INTEGER NOT NULL,"
					+ "web_region_id INTEGER NOT NULL," + "world_region_id INTEGER NOT NULL,"
					+ "market_region_id INTEGER NOT NULL);";
			db.createStatement(createSql);

		} catch (Exception e) {
		}
		return st;
	}
}
