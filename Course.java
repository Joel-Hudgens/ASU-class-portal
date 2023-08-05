// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Joel Hudgens
//    StudentID: 1224491216
//      Lecture: T, Th 10:30
//  Description: This Program uses a JavaFX GUI to simulate an ASU course Enrollment System


//This class is an object that will be used to hold course data
public class Course
{
    private String subject;
    private int courseNum;
    private String instructor;

    public Course()
    {
        subject = "?";
        courseNum = 0;
        instructor = "?";
    }

    public Course(String subject, int courseNum, String instructor)
    {
        this.subject = subject;
        this.courseNum = courseNum;
        this.instructor = instructor;
    }

    public String getSubject()
    {
        return subject;
    }

    public int getCourseNum()
    {
        return courseNum;
    }

    public String getInstructor()
    {
        return instructor;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setCourseNum(int courseNum)
    {
        this.courseNum = courseNum;
    }

    public void setInstructor(String instructor)
    {
        this.instructor = instructor;
    }

    public String toString()
    {
        return	"\nCourse #:\t\t" + subject + " " + courseNum +
        		"\nInstructor:\t"+ instructor + "\n";
    }
}