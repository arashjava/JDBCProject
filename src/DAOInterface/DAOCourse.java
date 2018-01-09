package DAOInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import BasicClasses.Course;

public interface DAOCourse
{
    Connection getConnection();
    
    int insert(String c);
    
    int delete(int id);
    int update(Course c);
    
    List<Course> selectAllCourses();

    
    Course SelectByID(int id);
	 
}
