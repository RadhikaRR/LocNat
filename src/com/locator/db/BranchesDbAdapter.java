//package com.locator.db;
//import net.rim.device.api.database.Cursor;
//import net.rim.device.api.io.messaging.Context;
//
//import org.json.me.StringWriter;
//
//import com.locator.models.BranchData;
//
//public class BranchesDbAdapter extends DbAdapter {
//	private String mDbName;
//	
//	public static final String TABLE_BRANCHES="Branches";
//	public static final String TABLE_SUB_BRANCHES="Sub_branches";
//	
//	
//	public static final int NAME =1;
//	public static final int  ID=2;
//	
//    public BranchesDbAdapter(Context context,String DbName) {
//        super(context);
//        this.mDbName=DbName;
//        setDbName();
//        setDbColumns();
//    }
//    
//    protected void setDbName() {
//    	 this.dbName = mDbName;
//    }
//    
//    protected void setDbColumns() {
//        this.dbColumns = new String[] { "_id", "name", "branch_id" };
//    }    
//    
//    ContentValues createContentValues(BranchData branches) {
//        ContentValues values = new ContentValues();
//        
//        values.put("branch_id", branches.getId());
//        values.put("name", branches.getStrName());
//   
//        return values;
//    }
//    
//    public long create(BranchData branches) {
//        ContentValues initialValues = createContentValues(branches);
//        return super.create(initialValues);
//    }
//    
//    public Boolean createAssociated(BranchData branches) {
//        try {
//            this.create(branches);
//
//            return true;
//        } catch ( Exception e ) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String stackString = sw.toString();
//            Log.e("LOCATOR", "Exception in branchesDbAdapter::saveAssociated: " + e.getMessage() +
//                    " Exception type: " + e.getClass().toString() + " " + stackString);
//            return false;
//        }        
//    }
//    
//    //Completely flush the db
//    public void deleteAllAssociated() {
//        try {
//            db.beginTransaction();
//            this.delete();
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();            
//        }
//    }
//    
//    public void deleteAssociated(Integer branchId) {
//        try {
//            db.beginTransaction();
//        
//            this.delete("branch_id=" + branchId);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();            
//        }
//
//    }
//    
//    public BranchData getResult(Integer branchId) {
//        BranchData branches = null;
//        Cursor c = this.fetchAll("branch_id=" + branchId.toString(), "1");
//        
//        if ( c.moveToFirst() ) {
//            
//            branches = getResultParamsFromCursor(c);
//        }
//        c.close();
//        return branches;
//    }
//    
//    //made this function public because we want to call fetchAll from branchesFragment to fetch list of all brancheses
//    public BranchData getResultParamsFromCursor(net.rim.device.api.database.Cursor c) {
//    	String [] columnNames = new String[] { "name", "branch_id"};
//    	BranchData branchesParams = new BranchData();
//    	Integer columnIndex;
//
//    	for ( String columnName: columnNames ) {
//    		columnIndex = c.getColumnIndex(columnName);
//
//    		switch(columnIndex){
//    		case ID:
//    			branchesParams.setId(c.getInt(columnIndex));
//    			break;
//    		case NAME:
//    			branchesParams.setStrName(c.getString(columnIndex));
//    			break;
//    		}
//    	}
//    	return branchesParams;
//    }
//
//    public boolean update(long rowId, BranchData branches) {
//        ContentValues updateValues = createContentValues(branches);
//        return super.update(rowId, updateValues);
//    }
//    
//}            