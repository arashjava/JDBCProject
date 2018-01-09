package MainPackage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import BasicClasses.Course;
import BasicClasses.Result;
import BasicClasses.Student;
import DAOImplement.DAOCourseImp;
import DAOImplement.DAOResultImp;
import DAOImplement.DAOStudentImp;

public class RunProj {
    public static String[] act= new String[3];

	public static void main(String[] args) {
	    act[0]= "Student";
	    act[1]= "Course";
	    act[2]= "Result";
	    
		int m1=1;
		int m2;
		try
		{
    		while (m1 != 4)
	    	{
     	    	m1 = menu1();
     			m2=0;
     	    	if (m1 >0 && m1<4 ) 
     	    		while (m2 != 6) 
     	    		{
     	    			m2= menu2(m1-1);
     	    			if (m2==1) SelectAllMain(m1);
     	    			if (m2==2) SelectByIdMain(m1);
     	    			if (m2==3) insertMain(m1);
     	    			if (m2==4) UpdateByIdMain(m1);
     	    			if (m2==5) DeleteByIdMain(m1);
     	    			
     	    		}
		    }
    		System.out.println("Good bye...");
		}
		catch(Exception e){
			System.out.println("Error in RunProj.main : "+ e);
		}
	}
	
    public static int menu1(){
    	int opt =0;
    	try
    	{
    	  Scanner sc = new Scanner(System.in);
    	  for (int i =0; i< act.length; i++)
    	  {   	  
    		  System.out.println(i+1 + "  " + act[i]); 
    	  }
   	      System.out.println("4- Exit ");
		  System.out.println("---------------------------"); 
		  System.out.println("Please select 1-4: "); 
  	      opt= sc.nextInt();

    	}
    	catch(InputMismatchException e)
    	{
    		System.out.println("Error in RunProj.manu1.... " + " Please select 1-4 !!!" );
    	}
    	
    	return opt;
    }

    public static int menu2(int m)
    {
      int opt =0;
      try
      {
        Scanner sc = new Scanner(System.in);
    	System.out.println("1- List of " + act[m]+"s");
    	System.out.println("2- One "+ act[m]);
    	System.out.println("3- Insert one "+ act[m]);
    	System.out.println("4- Update one "+ act[m]);
    	System.out.println("5- Delete one "+ act[m]);
    	System.out.println("6- Return to the previous menu ");
        System.out.println("---------------------------"); 
		System.out.println("Please select 1-6: "); 
	    opt= sc.nextInt();
      }
      catch(InputMismatchException e)
      	{
      		System.out.println("Error in RunProj.manu2.... " + " Please select 1-6 !!!" );
      	}

    	return opt;
    }

    public static void insertMain(int i)
    {

    	switch (i)
    	{
	  	  case 1: 
	  	  {
         	System.out.println("Insert is in progress ... ");
	   	    System.out.println("Please enter the first name  : ");
		    Scanner sc1= new Scanner(System.in);
	   	    System.out.println("Please enter the last name  : ");
		    Scanner sc2= new Scanner(System.in);
		    Student s = new Student();
	     	s.setFirstName(sc1.nextLine());
	     	s.setLastName(sc2.nextLine());
	         
	  		DAOStudentImp dc = new DAOStudentImp();
	  		if (dc.insert(s) > 0) 
	  		{ 
	  		   System.out.println("Record is inserted ...");
	  		   break;
	  	    }
	  	  }
    	  case 2: 
    	  {
   	     	System.out.println("Please enter the course name : ");
  	    	Scanner sc= new Scanner(System.in);
       		Course c = new Course();
       		c.setCourseName(sc.nextLine());
           
    		DAOCourseImp dc = new DAOCourseImp();
    		if (dc.insert(c.getCourseName()) > 0) 
    		{ 
    		   System.out.println("Record is inserted ...");
    		   break;
    	    }
    	  }
    	  
    	  case 3: 
    	  {
      		Result r = new Result();

      		System.out.println("Please enter the Student ID : ");
  	    	Scanner sc= new Scanner(System.in);
       		r.setStudentId(sc.nextInt());

       		System.out.println("Please enter the Course ID : ");
  	    	Scanner sc1= new Scanner(System.in);
       		r.setCourseId(sc1.nextInt());

       		System.out.println("Please enter Exam Midterm result : ");
  	    	Scanner sc2= new Scanner(System.in);
       		r.setExamMidterm(sc2.nextDouble());
           
       		System.out.println("Please enter Exam Final result : ");
  	    	Scanner sc3= new Scanner(System.in);
       		r.setExamFinal(sc3.nextDouble());

       		DAOResultImp dc = new DAOResultImp();
       		if (dc.insert(r) > 0) 
    		{ 
    		   System.out.println("Record is inserted ...");
    		   break;
    	    }
    	  }

    	}
    }
    	
        public static void SelectAllMain(int i1)
        {
               try
               {
                	System.out.println("List is in progree ... ");
                    System.out.println("-------- List of " + act[i1-1]+"s ---------");
                    System.out.println("----------------------------------------");
            	   	switch (i1)
                	{
	              	  case 1: 
	              	  {       		 
	              		 DAOStudentImp dc = new DAOStudentImp();
	                       List<Student> cs = dc.selectAllStudents();
	      		        for (Student s:cs)
	      		        {
	      		             System.out.println("ID: " + s.getStudentId()+ " ,Name: " + s.getFirstName() + " "+ s.getLastName());
	      		        }
	      		        break;
	              	  }
                	  case 2: 
                	  {       		 
                		 DAOCourseImp dc = new DAOCourseImp();
                         List<Course> cs = dc.selectAllCourses();
        		        for (Course c:cs)
        		        {
        		             System.out.println("ID: " + c.getCourseId()+ " ,Course: " + c.getCourseName());
        		        }
                        break;
                	  }
                	  
                   	  case 3: 
                	  {       		 
                		 DAOResultImp dc = new DAOResultImp();
                         List<Result> cs = dc.selectAllResults();
        		        for (Result r:cs)
        		        {
       		             System.out.println("ID: " + r.getResultId()+ " ,"
     		             		+ " with Student ID = " + r.getStudentId()
     		             		+ " and Course ID= : " + r.getCourseId());
    		             System.out.println("has the following results: " 
     		             		+ " Mid-term: " + r.getExamMidterm()
     		             		+ " ,Final: " + r.getExamFinal());
    	                    System.out.println("----------------------------------------");
    	                }
                        break;
                	  }
                	}
                    System.out.println("----------------------------------------");
                                 }
        	   catch (Exception e) 
               {
					System.out.println("Error in RunProj.SelectAllMain : " + e);
			   }
        		
        }
       
        public static void SelectByIdMain(int i1)
        {
             	System.out.println("Select by ID is in progress ... ");
     	    	System.out.println("Please enter the ID of " + act[i1-1] + " : ");
        	    Scanner sc= new Scanner(System.in);
       	    	int id = sc.nextInt();
       	    	
               try
               {
                    System.out.println("----------------------------------------");
            	   	switch (i1)
                	{
	              	  case 1: 
	              	  {       		 
	              		 DAOStudentImp dc = new DAOStudentImp();
	                     Student cs= dc.SelectByID(id);
      		             System.out.println("ID: " + cs.getStudentId()+ " ,Name: " + cs.getFirstName() + " "+ cs.getLastName());
                         break;
	              	  }
	            	  case 2: 
	            	  {       		 
	            		 DAOCourseImp dc = new DAOCourseImp();
	                     Course cs= dc.SelectByID(id);
	 		             System.out.println("Id: " + cs.getCourseId()+ " ,Course: " + cs.getCourseName());
	                     break;
	            	  }
	            	  
	            	  case 3: 
	            	  {       		 
	            		 DAOResultImp dc = new DAOResultImp();
	                     Result r= dc.SelectByID(id);
       		             System.out.println("ID: " + r.getResultId()+ " ,"
     		             		+ " with Student ID = " + r.getStudentId()
     		             		+ " and Course ID= : " + r.getCourseId());
    		             System.out.println("has the following results: " 
     		             		+ " Mid-term: " + r.getExamMidterm()
     		             		+ " ,Final: " + r.getExamFinal());
    	                    System.out.println("----------------------------------------");
	                     break;
	            	  }

                	}
                    System.out.println("----------------------------------------");
                                  }
        	   catch (Exception e) 
               {
					System.out.println("Error in RunProj.SelectByIdMain : " + e);
			   }
        		
        }

        public static void DeleteByIdMain(int i1)
        {
            	System.out.println("Delete is in progress ... ");
 	        	System.out.println("Please enter the ID of " + act[i1-1] + " : ");
        	    Scanner sc= new Scanner(System.in);
       	    	int id = sc.nextInt();
       	    	int recDeleted=0;
               try
               {
            	   	switch (i1)
                	{
	              	  case 1: 
	              	  {       		 
	              		 DAOStudentImp dc = new DAOStudentImp();
	              		 recDeleted= dc.delete(id);
	                     break;
	              	  }
	            	  case 2: 
	            	  {       		 
	            		 DAOCourseImp dc = new DAOCourseImp();
	            		 recDeleted= dc.delete(id);
	                     break;
	            	  }
	            	  
	            	  case 3: 
	            	  {       		 
	            		 DAOResultImp dc = new DAOResultImp();
	            		 recDeleted= dc.delete(id);
	                     break;
	            	  }

                	}
                    if (recDeleted>0) System.out.println(recDeleted + " record deleted...");
               }
        	   catch (Exception e) 
               {
					System.out.println("Error in RunProj.DeleteByIdMain : " + e);
			   }
        		
        }

        public static void UpdateByIdMain(int i)
        {
         	System.out.println("Update is in progress ... ");
         	System.out.println("Please enter ID of the " + act[i-1] + " : ");
         	Scanner sc= new Scanner(System.in);
       		int id = sc.nextInt();
         	int recUpdated=0;
         	boolean exist =false;  //  to check if the record exists
         	switch (i)
        	{
         	  case 1: 
        	  {
          		Student s = new Student();
          		s.setStudentId(id);
          		DAOStudentImp dc = new DAOStudentImp();
             		if (id== dc.SelectByID(id).getStudentId())     // means that record exists
               		{
             	       	System.out.println("Please enter the first name : ");
             	        Scanner sc1= new Scanner(System.in);
          				s.setFirstName(sc1.nextLine());
             	       	System.out.println("Please enter the last name : ");
             	        Scanner sc2= new Scanner(System.in);
          				s.setLastName(sc2.nextLine());
             		    recUpdated= dc.update(s);
              		}
               	else
               		System.out.println("Record with Id= " + id + " does not exist ...");
          		break;
          	  }
        	  case 2: 
        	  {
        		 
        		Course c = new Course();
        		c.setCourseId(id);
        		DAOCourseImp dc = new DAOCourseImp();
           		if (id== dc.SelectByID(id).getCourseId())     // means that record exists
             		{
           	       	   	System.out.println("Please enter the course name : ");
           	        	Scanner sc1= new Scanner(System.in);
        				c.setCourseName(sc1.nextLine());
           			    recUpdated= dc.update(c);
            		}
             	else
             		System.out.println("Record with Id= " + id + " does not exist ...");
        		break;
        	  }
        	  
         	  case 3: 
        	  {
          		Result r = new Result();
          		r.setResultId(id);
          		DAOResultImp dc = new DAOResultImp();
             		if (id== dc.SelectByID(id).getResultId())     // means that record exists
               		{
             	       	System.out.println("Please enter the Mid-term result : ");
             	        Scanner sc1= new Scanner(System.in);
          				r.setExamMidterm(sc1.nextDouble());
             	       	System.out.println("Please enter the final result : ");
             	        Scanner sc2= new Scanner(System.in);
          				r.setExamFinal(sc2.nextDouble());
             		    recUpdated= dc.update(r);
              		}
               	else
               		System.out.println("Record with Id= " + id + " does not exist ...");
          		break;
          	  }

        	}
    		if (recUpdated > 0)   System.out.println(recUpdated +" Record is Updated ...");
        }

}

    	
