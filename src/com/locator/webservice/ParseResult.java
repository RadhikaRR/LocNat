package com.locator.webservice;

import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.loactor.startup.SplashScreen;
import com.locator.db.DB_ContactDetails;
import com.locator.db.DB_ContactMatches;
import com.locator.db.DB_CountryData;
import com.locator.db.DB_MarketRegion;
import com.locator.db.DB_Service;
import com.locator.models.BranchData;
import com.locator.models.BranchDataModel;
import com.locator.models.ContactMatchesData;
import com.locator.models.ContactMatchesDataModel;
import com.locator.models.ContactsDetailData;
import com.locator.models.ContactsDetailDataModel;
import com.locator.models.CountryData;
import com.locator.models.CountryDataModel;
import com.locator.models.MktRegionsData;
import com.locator.models.MktRegionsDataModel;
import com.locator.models.ProcessData;
import com.locator.models.ProcessDataModel;
import com.locator.models.ServicesData;
import com.locator.models.ServicesDataModel;
import com.locator.models.SubBranchData;
import com.locator.models.SubBranchDataModel;

public class ParseResult {

	public static final int BRANCHES = 0;
	public static final int SUB_BRANCHES = 1;
	public static final int PROCESS = 2;
	public static final int SERVICES = 3;
	public static final int MKT_REG = 4;
	public static final int CONTACT = 5;
	public static final int CONT_MATCHES = 6;
	public static final int COUNTRY = 7;

	SplashScreen mContext;

	public static Vector mBranchDataArray;
	public static Vector mSubBranchDataArray;
	public static Vector mProcessDataArray;
	public static Vector mServiceDataArray;
	public static Vector mMktRegionsDataArray;
	public static Vector mContactsDetailArray;
	public static Vector mContMatchesDataArray;
	public static Vector mCountryDataArray;

	public ParseResult() {

	}

	public boolean parseMetaData(String strJsonReponse) {
		ProcessDataModel mProcessDataModel = null;
		ServicesDataModel mServicesDataModel = null;
		MktRegionsDataModel mktRegionsDataModel = null;
		ContactsDetailDataModel mContactsDetailDataModel = null;
		ContactMatchesDataModel mContactMatchesDataModel = null;
		CountryDataModel mCountryDataModel = null;

		boolean status = false;

		try {
			JSONObject metaJsonObject = new JSONObject(strJsonReponse);

			String strStatus = metaJsonObject.getString("status");
			String strMsg = metaJsonObject.getString("message");

			String strData = metaJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			String strTag = null;
			for (int i = 0; i < mJsonArray.length(); i++) {
				System.out.println("");
				// Log.i("TAG",mJsonArray.getJSONObject(i).toString());
				switch (i) {
				case BRANCHES:
					strTag = mJsonArray.getJSONObject(i).getString("Branches");
					parseBranchesData(strTag, true);
					break;
				case SUB_BRANCHES:
					strTag = mJsonArray.getJSONObject(i).getString(
							"Sub_branches");
					parseSubBranchesData(strTag, false);
					break;
				case PROCESS:
					strTag = mJsonArray.getJSONObject(i).getString(
							"Process_technologies");
					mProcessDataModel = parseProcessData(strTag);
					// ProcessDataModel mGlbProcessDataModel =
					// mProcessDataModel;
					break;
				case SERVICES:
					strTag = mJsonArray.getJSONObject(i).getString("Services");
					mServicesDataModel = parseServicesData(strTag);
					break;
				case MKT_REG:
					strTag = mJsonArray.getJSONObject(i).getString(
							"Market_regions");
					mktRegionsDataModel = parseMktRegionsData(strTag);
					break;
				case CONTACT:
					strTag = mJsonArray.getJSONObject(i).getString("Contacts");
					mContactsDetailDataModel = parseContactsDetailData(strTag);
					break;
				case CONT_MATCHES:
					strTag = mJsonArray.getJSONObject(i).getString(
							"Contact_matches");
					mContactMatchesDataModel = parseContMatchesData(strTag);
					break;
				case COUNTRY:
					strTag = mJsonArray.getJSONObject(i).getString("Countries");
					mCountryDataModel = parseCountryData(strTag);
					break;
				default:
					break;
				}
			}
			status = true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	private void parseBranchesData(String branchJson, boolean bIsFromBranches) {
		BranchDataModel mBranchDataModel = null;

		JSONObject branchJsonObject;
		try {
			branchJsonObject = new JSONObject(branchJson);
			mBranchDataModel = new BranchDataModel();

			mBranchDataModel.setStrStatus(branchJsonObject.getString("status"));
			mBranchDataModel.setStrMessage(branchJsonObject
					.getString("message"));

			String strData = branchJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			// Vector mBranchDataArray = new Vector();
			// mBranchDataArray.addElement(BranchData);

			// mBranchDataModel.mBranchDataArray = new
			// ArrayList<BranchData>(mJsonArray.length());

			mBranchDataArray = new Vector(mJsonArray.length());

			BranchData mBranchData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mBranchData = new BranchData();
				mBranchData.setStrName(mJsonArray.getJSONObject(i).getString(
						"name"));
				mBranchData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mBranchDataArray.insertElementAt(mBranchData, i);

			}
			
//			System.out.println("");
//			DB_Branches db_branch = new DB_Branches();
//			db_branch.createDB();
//			db_branch.openDB();
//			db_branch.createTable();
//			
//			for(int i=0;i<mBranchDataArray.size();i++) {
//				db_branch.insertDB((BranchData) mBranchDataArray.elementAt(i));
//			}
//			
//			db_branch.closeDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mBranchDataModel.mBranchDataArray.size()));

		// -------------Store in DB-----------------------
		// BranchesDbAdapter mBranchesDbAdapter=null;
		//
		// if(bIsFromBranches){
		// mBranchesDbAdapter=new
		// BranchesDbAdapter(mContext,BranchesDbAdapter.TABLE_BRANCHES);
		// }else{
		// mBranchesDbAdapter=new
		// BranchesDbAdapter(mContext,BranchesDbAdapter.TABLE_SUB_BRANCHES);
		// }
		//
		// mBranchesDbAdapter.deleteAllAssociated();
		// try {
		// mBranchesDbAdapter.beginTransaction();
		//
		// for(int i=0;i<mBranchDataModel.mBranchDataArray.size();i++) {
		// Log.i("LOCATOR", "Id for test res " +
		// mBranchDataModel.mBranchDataArray.get(i).getStrName()+String.valueOf(mBranchDataModel.mBranchDataArray.get(i).getId()));
		// mBranchesDbAdapter.createAssociated(mBranchDataModel.mBranchDataArray.get(i));
		// }
		// mBranchesDbAdapter.succeedTransaction();
		// } finally {
		// mBranchesDbAdapter.endTransaction();
		// }
		// ----------------------------------------------------------

	}

	private void parseSubBranchesData(String branchJson, boolean bIsFromBranches) {
		SubBranchDataModel mBranchDataModel = null;

		JSONObject branchJsonObject;
		try {
			branchJsonObject = new JSONObject(branchJson);
			mBranchDataModel = new SubBranchDataModel();

			mBranchDataModel.setStrStatus(branchJsonObject.getString("status"));
			mBranchDataModel.setStrMessage(branchJsonObject
					.getString("message"));

			String strData = branchJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			// Vector mBranchDataArray = new Vector();
			// mBranchDataArray.addElement(BranchData);

			// mBranchDataModel.mBranchDataArray = new
			// ArrayList<BranchData>(mJsonArray.length());

			mSubBranchDataArray = new Vector(mJsonArray.length());

			SubBranchData mBranchData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mBranchData = new SubBranchData();
				mBranchData.setStrName(mJsonArray.getJSONObject(i).getString(
						"name"));
				mBranchData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mSubBranchDataArray.insertElementAt(mBranchData, i);

			}
			
//			System.out.println("");
//			DB_SubBranch db_Subbranch = new DB_SubBranch();
//			db_Subbranch.createDB();
//			db_Subbranch.openDB();
//			db_Subbranch.createTable();
//			
//			for(int i=0;i<mSubBranchDataArray.size();i++) {
//				db_Subbranch.insertDB((SubBranchData) mSubBranchDataArray.elementAt(i));
//			}
//			
//			db_Subbranch.closeDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mBranchDataModel.mBranchDataArray.size()));

		// -------------Store in DB-----------------------
		// BranchesDbAdapter mBranchesDbAdapter=null;
		//
		// if(bIsFromBranches){
		// mBranchesDbAdapter=new
		// BranchesDbAdapter(mContext,BranchesDbAdapter.TABLE_BRANCHES);
		// }else{
		// mBranchesDbAdapter=new
		// BranchesDbAdapter(mContext,BranchesDbAdapter.TABLE_SUB_BRANCHES);
		// }
		//
		// mBranchesDbAdapter.deleteAllAssociated();
		// try {
		// mBranchesDbAdapter.beginTransaction();
		//
		// for(int i=0;i<mBranchDataModel.mBranchDataArray.size();i++) {
		// Log.i("LOCATOR", "Id for test res " +
		// mBranchDataModel.mBranchDataArray.get(i).getStrName()+String.valueOf(mBranchDataModel.mBranchDataArray.get(i).getId()));
		// mBranchesDbAdapter.createAssociated(mBranchDataModel.mBranchDataArray.get(i));
		// }
		// mBranchesDbAdapter.succeedTransaction();
		// } finally {
		// mBranchesDbAdapter.endTransaction();
		// }
		// ----------------------------------------------------------

	}

	private static ProcessDataModel parseProcessData(String processJson) {
		ProcessDataModel mProcessDataModel = null;

		JSONObject processJsonObject;
		try {
			processJsonObject = new JSONObject(processJson);
			mProcessDataModel = new ProcessDataModel();

			mProcessDataModel.setStrStatus(processJsonObject
					.getString("status"));
			mProcessDataModel.setStrMessage(processJsonObject
					.getString("message"));

			String strData = processJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mProcessDataArray = new Vector(mJsonArray.length());
			ProcessData mProcessData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mProcessData = new ProcessData();
				mProcessData.setStrName(mJsonArray.getJSONObject(i).getString(
						"name"));
				mProcessData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mProcessDataArray.insertElementAt(mProcessData, i);

			}
			
//			System.out.println("");
//			DB_Process db_Process = new DB_Process();
//			db_Process.createDB();
//			db_Process.openDB();
//			db_Process.createTable();
//			
//			for(int i=0;i<mProcessDataArray.size();i++) {
//				db_Process.insertDB((ProcessData) mProcessDataArray.elementAt(i));
//			}
//			
//			db_Process.closeDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mProcessDataModel.mProcessDataArray.size()));

		return mProcessDataModel;
	}

	private static ServicesDataModel parseServicesData(String serviceJson) {
		ServicesDataModel mServicesDataModel = null;

		JSONObject branchJsonObject;
		try {
			branchJsonObject = new JSONObject(serviceJson);
			mServicesDataModel = new ServicesDataModel();

			mServicesDataModel.setStrStatus(branchJsonObject
					.getString("status"));
			mServicesDataModel.setStrMessage(branchJsonObject
					.getString("message"));

			String strData = branchJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mServiceDataArray = new Vector(mJsonArray.length());
			ServicesData mServicesData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mServicesData = new ServicesData();
				mServicesData.setStrName(mJsonArray.getJSONObject(i).getString(
						"name"));
				mServicesData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mServiceDataArray.insertElementAt(mServicesData, i);
			}
			
//			System.out.println("");
//			DB_Service db_Service = new DB_Service();
//			db_Service.createDB();
//			db_Service.openDB();
//			db_Service.createTable();
//			
//			for(int i=0;i<mServiceDataArray.size();i++) {
//				db_Service.insertDB((ServicesData) mServiceDataArray.elementAt(i));
//			}			
//			db_Service.closeDB();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mServicesDataModel.mBranchDataArray.size()));

		return mServicesDataModel;
	}

	private static MktRegionsDataModel parseMktRegionsData(String mktRegJson) {
		MktRegionsDataModel mktRegionsDataModel = null;

		JSONObject mktJsonObject;
		try {
			mktJsonObject = new JSONObject(mktRegJson);
			mktRegionsDataModel = new MktRegionsDataModel();

			mktRegionsDataModel.setStrStatus(mktJsonObject.getString("status"));
			mktRegionsDataModel.setStrMessage(mktJsonObject
					.getString("message"));

			String strData = mktJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mMktRegionsDataArray = new Vector(mJsonArray.length());
			MktRegionsData mktRegionsData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mktRegionsData = new MktRegionsData();

				mktRegionsData.setStrSasName(mJsonArray.getJSONObject(i)
						.getString("sas_name"));
				mktRegionsData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mktRegionsData.setContact_id(mJsonArray.getJSONObject(i)
						.getInt("contact_id"));

				mMktRegionsDataArray.insertElementAt(mktRegionsData, i);
			}
			
//			System.out.println("");
//			DB_MarketRegion db_Market = new DB_MarketRegion();
//			db_Market.createDB();
//			db_Market.openDB();
//			db_Market.createTable();
//			
//			for(int i=0;i<mMktRegionsDataArray.size();i++) {
//				db_Market.insertDB((MktRegionsData) mMktRegionsDataArray.elementAt(i));
//			}			
//			db_Market.closeDB();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mktRegionsDataModel.mMktRegionsDataArray.size()));

		return mktRegionsDataModel;
	}

	private static ContactsDetailDataModel parseContactsDetailData(
			String contactJson) {
		ContactsDetailDataModel mContactsDetailDataModel = null;

		JSONObject mktJsonObject;
		try {
			mktJsonObject = new JSONObject(contactJson);
			mContactsDetailDataModel = new ContactsDetailDataModel();

			mContactsDetailDataModel.setStrStatus(mktJsonObject
					.getString("status"));
			mContactsDetailDataModel.setStrMessage(mktJsonObject
					.getString("message"));

			String strData = mktJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mContactsDetailArray = new Vector(mJsonArray.length());
			ContactsDetailData mContactsDetailData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mContactsDetailData = new ContactsDetailData();

				mContactsDetailData.setId(mJsonArray.getJSONObject(i).getInt(
						"id"));
				mContactsDetailData.setStrName(mJsonArray.getJSONObject(i)
						.getString("name"));
				mContactsDetailData.setPhone(mJsonArray.getJSONObject(i)
						.getString("phone"));
				mContactsDetailData.setTelex(mJsonArray.getJSONObject(i)
						.getString("telex"));
				mContactsDetailData.setEmail(mJsonArray.getJSONObject(i)
						.getString("email"));
				mContactsDetailData.setEmail_visible(mJsonArray
						.getJSONObject(i).getInt("email_visible"));
				mContactsDetailData.setAddress_1(mJsonArray.getJSONObject(i)
						.getString("address_1"));
				mContactsDetailData.setAddress_2(mJsonArray.getJSONObject(i)
						.getString("address_2"));
				mContactsDetailData.setAddress_3(mJsonArray.getJSONObject(i)
						.getString("address_3"));
				mContactsDetailData.setAddress_4(mJsonArray.getJSONObject(i)
						.getString("address_4"));
				mContactsDetailData.setAddress_5(mJsonArray.getJSONObject(i)
						.getString("address_5"));
				mContactsDetailData.setAddress_6(mJsonArray.getJSONObject(i)
						.getString("address_6"));
				mContactsDetailData.setAddress_7(mJsonArray.getJSONObject(i)
						.getString("address_7"));
				mContactsDetailData.setAddress_8(mJsonArray.getJSONObject(i)
						.getString("address_8"));
				mContactsDetailData.setAddress_9(mJsonArray.getJSONObject(i)
						.getString("address_9"));
				mContactsDetailData.setAddress_10(mJsonArray.getJSONObject(i)
						.getString("address_10"));

				mContactsDetailArray.insertElementAt(mContactsDetailData, i);
			}
			
//			System.out.println("");
//			DB_ContactDetails db_Contact = new DB_ContactDetails();
//			db_Contact.createDB();
//			db_Contact.openDB();
//			db_Contact.createTable();
//			
//			for(int i=0;i<mContactsDetailArray.size();i++) {
//				db_Contact.insertDB((ContactsDetailData) mContactsDetailArray.elementAt(i));
//			}			
//			db_Contact.closeDB();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mContactsDetailDataModel.mContactsDetailArray.size()));

		return mContactsDetailDataModel;
	}

	private static ContactMatchesDataModel parseContMatchesData(
			String conMatchJson) {
		ContactMatchesDataModel mContactMatchesDataModel = null;

		JSONObject mktJsonObject;
		try {
			mktJsonObject = new JSONObject(conMatchJson);
			mContactMatchesDataModel = new ContactMatchesDataModel();

			mContactMatchesDataModel.setStrStatus(mktJsonObject
					.getString("status"));
			mContactMatchesDataModel.setStrMessage(mktJsonObject
					.getString("message"));

			String strData = mktJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mContMatchesDataArray = new Vector(mJsonArray.length());
			ContactMatchesData mContactMatchesData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mContactMatchesData = new ContactMatchesData();

				mContactMatchesData.setId(mJsonArray.getJSONObject(i).getInt(
						"id"));
				mContactMatchesData.setCountry_ISO(mJsonArray.getJSONObject(i)
						.getString("country_ISO"));
				mContactMatchesData.setContact_id(mJsonArray.getJSONObject(i)
						.getInt("contact_id"));
				mContactMatchesData.setMarket_segment_id(mJsonArray
						.getJSONObject(i).getInt("market_segment_id"));
				mContactMatchesData.setProcess_technology_id(mJsonArray
						.getJSONObject(i).getInt("process_technology_id"));
				mContactMatchesData.setService_id(mJsonArray.getJSONObject(i)
						.getInt("service_id"));
				mContactMatchesData.setProduct_id(mJsonArray.getJSONObject(i)
						.getInt("product_id"));

				mContMatchesDataArray.insertElementAt(mContactMatchesData, i);

			}
			
//			System.out.println("");
//			DB_ContactMatches db_ContactMatches = new DB_ContactMatches();
//			db_ContactMatches.createDB();
//			db_ContactMatches.openDB();
//			db_ContactMatches.createTable();
//			
//			for(int i=0;i<mContMatchesDataArray.size();i++) {
//				db_ContactMatches.insertDB((ContactMatchesData) mContMatchesDataArray.elementAt(i));
//			}			
//			db_ContactMatches.closeDB();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mContactMatchesDataModel.mContMatchesDataArray.size()));

		return mContactMatchesDataModel;
	}

	private static CountryDataModel parseCountryData(String countryJson) {
		CountryDataModel mCountryDataModel = null;

		JSONObject mktJsonObject;
		try {
			mktJsonObject = new JSONObject(countryJson);
			mCountryDataModel = new CountryDataModel();

			mCountryDataModel.setStrStatus(mktJsonObject.getString("status"));
			mCountryDataModel.setStrMessage(mktJsonObject.getString("message"));

			String strData = mktJsonObject.getString("data");
			JSONArray mJsonArray = new JSONArray(strData);

			mCountryDataArray = new Vector(mJsonArray.length());
			CountryData mCountryData;
			for (int i = 0; i < mJsonArray.length(); i++) {

				mCountryData = new CountryData();

				mCountryData.setId(mJsonArray.getJSONObject(i).getInt("id"));
				mCountryData.setCountry_ISO(mJsonArray.getJSONObject(i)
						.getString("country_ISO"));
				mCountryData.setName(mJsonArray.getJSONObject(i).getString(
						"name"));
				mCountryData.setWeb_region_id(mJsonArray.getJSONObject(i)
						.getInt("web_region_id"));
				mCountryData.setWorld_region_id(mJsonArray.getJSONObject(i)
						.getInt("world_region_id"));
				mCountryData.setMarket_region_id(mJsonArray.getJSONObject(i)
						.getInt("market_region_id"));

				mCountryDataArray.insertElementAt(mCountryData, i);

			}
			
			System.out.println("");
			DB_CountryData db_CountryData = new DB_CountryData();
			db_CountryData.createDB();
			db_CountryData.openDB();
			db_CountryData.createTable();
			
			for(int i=0;i<mCountryDataArray.size();i++) {
				System.out.println("");
				db_CountryData.insertDB((CountryData) mCountryDataArray.elementAt(i));
			}			
			db_CountryData.closeDB();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.i("count",String.valueOf(mCountryDataModel.mCountryDataArray.size()));

		return mCountryDataModel;
	}
}
