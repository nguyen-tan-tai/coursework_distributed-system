package utils;

import java.sql.*;

public class DBConnect {
	public Connection conn;
	public Statement stm;
	public PreparedStatement pstm;
	public CallableStatement cstm;
	public ResultSet rs = null;
	public String result;
	boolean error;
	String sql;
	public DBConnect() {
		if(conn==null){
		      try {
		        Class.forName(DBDriver.driver);
		        conn = DriverManager.getConnection(DBDriver.database, DBDriver.username, DBDriver.pwd);
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
	    }
	}
	public DBConnect(String host, String n, String u, String p) {
		if(conn==null){
		      try {
		        Class.forName(DBDriver.driver);
		        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + n, u, p);
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
	    }
	}

	public void setAutoCommit(boolean in){
		try {
			conn.setAutoCommit(in);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addBatch(String in){
		try {
			stm.addBatch(in);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int[] executeBatch(){
		try {
			return stm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createStatement(){
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Statement getStatement(){
		return stm;
	}

	public boolean selectSql(String sql) {
	    boolean error = true;
	    try{
	      stm = conn.createStatement();
	      rs = stm.executeQuery(sql);
	    } catch(SQLException e){
	      result = e.toString();
	      error = false;
	    }
	    return error;
	}


	public boolean updateSql(String sql) {
	    boolean error = true;
	    try{
	      stm = conn.createStatement();
	      stm.executeUpdate(sql);
	    } catch(SQLException e){
	      result = e.toString();
	      error = false;
	    }
	    return error;
	}

	public void openTable(String tablename) {
		 String sql = "select * from " + tablename;
		 selectSql(sql);
	}	

	//for prepare statement
	public void prepareSelect(String sql){
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	//end pstm
	
	 public CallableStatement getCallableStatement(String ProcedureName){
		    CallableStatement cs = null;
		    try{
		      cs = conn.prepareCall(ProcedureName);
		    }
		    catch(SQLException e){
		      result = e.toString();
		    }
		    return cs;
	 }
	  
	 public boolean runQueryProcedure(CallableStatement cs){
		    boolean error = true;
		    try{
		      rs = cs.executeQuery();
		    }catch(SQLException e){
		    	System.out.println(e.toString());
		      error = false;
		    }
		    return error;
	  }
	  
	 public boolean runUpdateProcedure(CallableStatement cs){
		    boolean error = true;
		    try{
		    	cs.executeUpdate();
		    }catch(SQLException e){
		    	System.out.println(e.toString());
		    	error = false;
		    }
		    return error;
	  }
	 
     public void closeStm(){
         if(rs != null) {
             try {
                 rs.close();
             } catch (SQLException ex) {
                 stm = null;                
                 ex.printStackTrace();
             }
         }
      }
     
      public void closePstm(){        
         if (pstm != null) {
             try {
                 pstm.close();
             } catch (SQLException ex) {
                 pstm = null;
                 ex.printStackTrace();
             }
         } 
      }
      public void closeCs(){        
         if (cstm != null) {
             try {
                 cstm.close();
             } catch (SQLException ex) {
                 cstm = null;
                 ex.printStackTrace();
             }
         } 
      }
      public void closeRs(){
         if (rs != null) {
             try {
                 rs.close();
             } catch (SQLException ex) {
                 rs = null;
                 ex.printStackTrace();
             }
         }          
      }
      public void closeCon(){
         try {
             conn.close();
         } catch (SQLException ex) {
             conn = null;
             ex.printStackTrace();
         }
      }
     public void closeAll() {
         closeRs();
         closeStm();
         closePstm();
         closeCs();
         closeCon();
     }	 
}	  