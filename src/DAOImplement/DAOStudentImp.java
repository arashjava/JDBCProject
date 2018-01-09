package DAOImplement;
import DAOInterface.DAOStudent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BasicClasses.Student;
public class DAOStudentImp implements DAOStudent {
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
				("delete from Students where student_id= ?");
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
	public int insert(Student s) {
		int recInserted=0;
		  try
		  {
			PreparedStatement ps = getConnection().prepareStatement
					("insert into students(student_id, first_name, last_name)" +
							"values(seq_student_id.nextval, ?,?)");
			ps.setString(1, s.getFirstName());
			ps.setString(2, s.getLastName());
			
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
	public int update(Student s) {
	      int recUpdated=0;
		  try
		  {
			PreparedStatement ps = getConnection().prepareStatement
					("update students set first_name= ?, last_name=? " +
							" where student_id= ?");

			ps.setString(1, s.getFirstName());
			ps.setString(2, s.getLastName());
			ps.setInt(3, s.getStudentId());
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
	public List<Student> selectAllStudents() {
		ResultSet rs=null;
		Student s=null;
		Statement statement=null;
		List<Student> ca = new ArrayList<>();
		try
		{
	        String select = "Select * from Students order by student_id";
     		statement = getConnection().createStatement();
		    rs =  statement.executeQuery(select) ;
		    while(rs.next())
		    {
		    	s = new Student();
		    	s.setStudentId(rs.getInt("student_id"));
		    	s.setFirstName(rs.getString("first_name"));
		    	s.setLastName(rs.getString("last_name"));
			    ca.add(s);
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
	public Student SelectByID(int id) {
		Student s=null;
		try
		{
		  PreparedStatement ps = getConnection().prepareStatement
				("select * from Students where student_id= ?");
		  ps.setInt(1, id);
		  ResultSet rs= ps.executeQuery();
		  rs.next();
		  s= new Student();
		  s.setStudentId(rs.getInt("student_id"));
		  s.setFirstName(rs.getString("first_name"));
		  s.setLastName(rs.getString("last_name"));
	
		  rs.close();
		  conn.close();
		  ps.close();
		}
		catch(Exception e){
			System.out.println("Error in DAOCourseImp.SelectById : " + e);
		}
		return s;
	}



}




