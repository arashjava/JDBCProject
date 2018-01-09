package DAOImplement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BasicClasses.Result;
import DAOInterface.DAOResult;
public class DAOResultImp implements DAOResult{
	private Connection conn= null;
	
	@Override
	public Connection getConnection() {
		try
		{
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "jdbcProj";
			String password = "";
			conn = DriverManager.getConnection(url, username, password);
		}
		catch(SQLException e){
	       System.out.println("Error in DaoImplement.Connection : " + e);		
		}
		return conn;
	}


	@Override
	public int delete(int id) {
		int delRec=0;
		try
		{
		  PreparedStatement ps = getConnection().prepareStatement
				("delete from results where result_id= ?");
		  ps.setInt(1, id);
		  delRec= ps.executeUpdate();
		  ps.close();
		  conn.close();
		}
		catch(Exception e){
			System.out.println("Error in DAOCourseImp.delete : " + e);
		}
		return delRec;

	}


	@Override
	public int insert(Result r) {
		int recInserted=0;
		  try
		  {
			PreparedStatement ps = getConnection().prepareStatement
					("insert into results(result_id, student_id, course_id, exam_midterm, exam_final)" +
							"values(seq_result_id.nextval, ?,?,?,?)");
			ps.setInt(1, r.getStudentId());
			ps.setInt(2, r.getCourseId());
			ps.setDouble(3, r.getExamMidterm());
			ps.setDouble(4, r.getExamFinal());
			
			recInserted= ps.executeUpdate();
			ps.close();
			conn.close();
		  }
		  catch(SQLException e){
			  System.out.println("Error in DAOCourseImp.Insert : " + e);
		  }
		  return recInserted;
	}



	@Override
	public int update(Result r) {
	      int recUpdated=0;
		  try
		  {
			PreparedStatement ps = getConnection().prepareStatement
					("update results set exam_midterm=?, exam_final=? " +
							" where result_id= ?");

			ps.setDouble(1, r.getExamMidterm());
			ps.setDouble(2, r.getExamFinal());
			ps.setInt(3, r.getResultId());
			recUpdated= ps.executeUpdate();
			ps.close();
			conn.close();
		  }
		  catch(SQLException e){
			  System.out.println("Error in DAOCourseImp.update : " + e);
		  }

		  return recUpdated;
	}



	@Override
	public List<Result> selectAllResults() {
		ResultSet rs=null;
		Result r=null;
		Statement statement=null;
		List<Result> ca = new ArrayList<>();
		try
		{
	        String select = "Select * from results order by result_id";
     		statement = getConnection().createStatement();
		    rs =  statement.executeQuery(select) ;
		    while(rs.next())
		    {
		    	r = new Result();
		    	r.setResultId(rs.getInt("result_id"));
		    	r.setStudentId(rs.getInt("student_id"));
		    	r.setCourseId(rs.getInt("course_id"));
		    	r.setExamMidterm(rs.getDouble("exam_midterm"));
		    	r.setExamFinal(rs.getDouble("exam_final"));
			    ca.add(r);
		    }

			rs.close();
			statement.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Error in DAOCourseImp.SelectAllCourses : " + e);
		}
		return  ca;
	}



	@Override
	public Result SelectByID(int id) {
		Result r=null;
		try
		{
		  PreparedStatement ps = getConnection().prepareStatement
				("select * from results where result_id= ?");
		  ps.setInt(1, id);
		  ResultSet rs= ps.executeQuery();
		  rs.next();
		  r= new Result();
	    	r.setResultId(rs.getInt("result_id"));
	    	r.setStudentId(rs.getInt("student_id"));
	    	r.setCourseId(rs.getInt("course_id"));
	    	r.setExamMidterm(rs.getDouble("exam_midterm"));
	    	r.setExamFinal(rs.getDouble("exam_final"));
	
		  rs.close();
		  conn.close();
		  ps.close();
		}
		catch(Exception e){
			System.out.println("Error in DAOCourseImp.SelectById : " + e);
		}
		return r;
	}



}











