package DAOImplement;

import DAOInterface.DAOCourse;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import BasicClasses.Course;

public class DAOCourseImp implements DAOCourse
{
	private Connection conn= null;

	@Override
	public Connection getConnection() 
	{
		try
		{
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "jdbcProj";
			String password = "";
			conn = DriverManager.getConnection(url, username, password);
		}
		catch(SQLException e)
		{
	       System.out.println("Error in DaoImplement.Connection : " + e);		
		}
		return conn;
	}

	@Override
	public int insert(String c) {
		int recInserted=0;
	  try
	  {
		PreparedStatement ps = getConnection().prepareStatement
				("insert into courses(course_id, course_name)" +
						"values(seq_course_id.nextval, ?)");
		ps.setString(1, c);

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
	public int delete(int id) 
	{
	//	Course c=null;
		int delRec=0;
		try
		{
		  PreparedStatement ps = getConnection().prepareStatement
				("delete from courses where course_id= ?");
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
	public List<Course> selectAllCourses() {
		ResultSet rs=null;
		Course c=null;
		Statement statement=null;
		List<Course> ca = new ArrayList<>();
		try
		{
	        String select = "Select * from courses order by course_id";
     		statement = getConnection().createStatement();
		    rs =  statement.executeQuery(select) ;
		    while(rs.next())
		    {
		    	c = new Course();
		    	c.setCourseId(rs.getInt("Course_id"));
		    	c.setCourseName(rs.getString("Course_name"));
			    ca.add(c);
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
	public Course SelectByID(int id) 
	{
		Course c=null;
		try
		{
		  PreparedStatement ps = getConnection().prepareStatement
				("select * from courses where course_id= ?");
		  ps.setInt(1, id);
		  ResultSet rs= ps.executeQuery();
		  rs.next();
		  c= new Course();
		  c.setCourseId(rs.getInt("Course_id"));
		  c.setCourseName(rs.getString("Course_name"));
		  rs.close();
		  conn.close();
		  ps.close();
		}
		catch(Exception e)
		{
			System.out.println("Error in DAOCourseImp.SelectById : " + e);
		}
		return c;
	}

	@Override
	public int update(Course c) {
      int i=0;
	  try
	  {
		PreparedStatement ps = getConnection().prepareStatement
				("update courses set course_name= ?" +
						" where course_id= ?");

		ps.setString(1, c.getCourseName());
		ps.setInt(2, c.getCourseId());
		i= ps.executeUpdate();
		ps.close();
		conn.close();
	  }
	  catch(SQLException e){
		  System.out.println("Error in DAOCourseImp.update : " + e);
	  }

	  return i;
	}

}
