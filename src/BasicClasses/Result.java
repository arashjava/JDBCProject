package BasicClasses;


public class Result {

private int resultId;
private int courseId;
private int studentId;
private double examMidterm;
private double examFinal;

public Result() {
}

public int getResultId() {
    return resultId;
}

public void setResultId(int resultId) {
    this.resultId = resultId;
}

public int getCourseId() {
    return courseId;
}

public void setCourseId(int courseId) {
    this.courseId = courseId;
}

public int getStudentId() {
    return studentId;
}

public void setStudentId(int studentId) {
    this.studentId = studentId;
}

public double getExamMidterm() {
    return examMidterm;
}

public void setExamMidterm(double examMidterm) {
    this.examMidterm = examMidterm;
}

public double getExamFinal() {
    return examFinal;
}

public void setExamFinal(double examFinal) {
    this.examFinal = examFinal;
}

} // end class Result