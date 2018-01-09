package DAOInterface;

import java.sql.Connection;
import java.util.List;
import BasicClasses.Result;

public interface DAOResult {
	 Connection getConnection();
	    
	    int insert(Result c);
	    
	    int delete(int id);
	    int update(Result r);
	    
	    List<Result> selectAllResults();
	    
	    Result SelectByID(int id);
}









