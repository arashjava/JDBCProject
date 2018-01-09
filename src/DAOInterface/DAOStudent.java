package DAOInterface;
import java.sql.Connection;
import java.util.List;

import BasicClasses.Course;
import BasicClasses.Student;

public interface DAOStudent {
	 Connection getConnection();
	    
	    int insert(Student c);
	    
	    int delete(int id);
	    int update(Student c);
	    List<Student> selectAllStudents();
	    
	    Student SelectByID(int id);
}



