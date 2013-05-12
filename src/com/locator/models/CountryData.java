package com.locator.models;

public class CountryData {
	int id;
	String country_ISO;
	String name;
	int web_region_id;
	int world_region_id;
	int market_region_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry_ISO() {
		return country_ISO;
	}
	public void setCountry_ISO(String country_ISO) {
		this.country_ISO = country_ISO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeb_region_id() {
		return web_region_id;
	}
	public void setWeb_region_id(int web_region_id) {
		this.web_region_id = web_region_id;
	}
	public int getWorld_region_id() {
		return world_region_id;
	}
	public void setWorld_region_id(int world_region_id) {
		this.world_region_id = world_region_id;
	}
	public int getMarket_region_id() {
		return market_region_id;
	}
	public void setMarket_region_id(int market_region_id) {
		this.market_region_id = market_region_id;
	}
}
