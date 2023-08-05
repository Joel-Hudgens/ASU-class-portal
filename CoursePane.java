// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Joel Hudgens
//    StudentID: 1224491216
//      Lecture: T, Th 10:30
//  Description: This Program uses a JavaFX GUI to simulate an ASU course Enrollment System



import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

//This class is an HBox node which will hold everything we see on the GUI. It is a child of the root pane
public class CoursePane extends HBox {
	
    //GUI components
    private ArrayList<Course> courseList;
    private VBox checkboxContainer;

    //Instance Variables (Nodes used within this CoursePane)
    private Label labelLeft, labelRight, subjectLabel, courseNumLabel, instructorLabel, leftStatusLabel, rightStatusLabel;
    private Button add, drop;
    private ComboBox<String> subjectsDropdown;
    private TextField courseNumEntry, instructorEntry;
    private Pane leftPane, centerPane, rightPane;
    

    //constructor
    public CoursePane() {
    	
    	courseList = new ArrayList<>();
    	checkboxContainer = new VBox();
    	
    	//3 panes in CoursePane
    	//These panes will be children of (this) CoursePane
        leftPane = new Pane();
        centerPane = new Pane();
        rightPane = new Pane();
    	
    	// Create/position nodes in left pane
        labelLeft = new Label("Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));
        labelLeft.setTranslateX(5);
        labelLeft.setTranslateY(5);
        
        subjectLabel = new Label("Subject");
        subjectLabel.setTranslateX(20);
        subjectLabel.setTranslateY(130);
        
        courseNumLabel = new Label("Course Num");
        courseNumLabel.setTranslateX(20);
        courseNumLabel.setTranslateY(170);
        
        instructorLabel = new Label("Instructor");
        instructorLabel.setTranslateX(20);
        instructorLabel.setTranslateY(210);
        
        subjectsDropdown = new ComboBox<String>();
        subjectsDropdown.getItems().addAll("CSE","AME","ACC","BME","CHM","DAT","EEE");
        subjectsDropdown.setTranslateX(120);
        subjectsDropdown.setTranslateY(128);
        
        courseNumEntry = new TextField();
        courseNumEntry.setTranslateX(120);
        courseNumEntry.setTranslateY(170);
        courseNumEntry.setPrefWidth(90);
        
        instructorEntry = new TextField();
        instructorEntry.setTranslateX(120);
        instructorEntry.setTranslateY(210);
        instructorEntry.setPrefWidth(90);
        
        leftStatusLabel = new Label("No courses entered");
        leftStatusLabel.setTranslateY(350);
        leftStatusLabel.setTranslateX(10);
        
        //create/position nodes in Center Pane
        add = new Button("Add =>");
        add.setTranslateY(160);
        add.setTranslateX(10);
        add.setPrefWidth(80);
        
        drop = new Button("<= Drop");
        drop.setTranslateY(200);
        drop.setTranslateX(10);
        drop.setPrefWidth(80);
        
        
        //create/position nodes in Right Pane
        labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));
        labelRight.setTranslateX(5);
        labelRight.setTranslateY(5);
        
        rightStatusLabel = new Label("Total course enrolled: 0");
        rightStatusLabel.setTranslateY(350);
        rightStatusLabel.setTranslateX(10);
        
        checkboxContainer.setPadding(new Insets(8, 8, 8, 8));

        //set size & add nodes for left pane
        leftPane.setPrefSize(275, 350);
        leftPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-color: #FFD700; -fx-background-color: white; ");
        leftPane.getChildren().addAll(subjectLabel,courseNumLabel,instructorLabel,labelLeft,subjectsDropdown,leftStatusLabel,instructorEntry,courseNumEntry);
        
        //set size & add nodes for right pane
        rightPane.setPrefSize(215, 350);
        rightPane.setStyle("-fx-border-style: solid; -fx-border-width: 3; -fx-border-color: #FFD700; -fx-background-color: white;");
        rightPane.getChildren().addAll(labelRight,rightStatusLabel,checkboxContainer);
        
        //set size & add nodes for center pane
        centerPane.setPrefSize(100, 350);
        centerPane.getChildren().addAll(add,drop);
        
        //set attributes of this coursePane
        this.getChildren().addAll(leftPane,centerPane,rightPane); //Add three panes to this course pane
        this.setSpacing(5);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setStyle("-fx-background-color: #800000;");;
       

        //Register the GUI component with its cooresponding handler class
        drop.setOnAction(new ButtonHandler());
        add.setOnAction(new ButtonHandler());
        subjectsDropdown.setOnAction(new ComboBoxHandler());

    } //end of constructor

    //This method updates the checkBoxContainer after a course is added or dropped
    public void updateCheckBoxContainer(){
        checkboxContainer.getChildren().clear();                                   //1) Clear the checkboxContainer
        int numOfCourses = courseList.size();
        	//for each course in courseList
        	for (int i = 0; i < numOfCourses; i++) {								// FOR EACH COURSE IN courseList:
        		CheckBox checkbox = new CheckBox(courseList.get(i).toString());     //2) create a new check box for the course
        		checkbox.setOnAction(new CheckBoxHandler(courseList.get(i)));       //3) set its check box with a handler
        		checkboxContainer.getChildren().add(checkbox);                      //4) Add course to checkBoxContainer
        }
    }

    //This class handles the event of a user pressing the add or drop button
    //It also provides error handling if the user doesn't perform correct actions
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
    	Course course;
        public void handle(ActionEvent e)
        {
        	//needed variables
        	Object source = e.getSource();

            try {
				
            	//If the add button is pressed, then create these variables
            	//These should only be defined if add is pressed because if the user drops, then the textFields will be set to null
            	if (source == add) {
                	int courseNumInt = Integer.parseInt(courseNumEntry.getText());
                	String instructorName = instructorEntry.getText();
                	String courseSubject = subjectsDropdown.getValue();
                	
                	//If a textField is null, then give an error message
                	if (instructorEntry.getText().isEmpty() || courseNumEntry.getText().isEmpty()) {
                 	   leftStatusLabel.setText("At Least One field is Empty. Fill All Fields. ");
                        leftStatusLabel.setStyle("-fx-text-fill: red;");
                	   }
                	
                	// If the textFields are not null, and courseNumber is a valid Integer
                	else if ((courseNumInt > 99) 
            				&& (courseNumInt < 1000) 
            				&& (instructorName.equals("") == false) 
            				&& (courseNumEntry.getText().equals("") == false)) {
            		
						//If the course is a duplicate, then give an error message
						if (isADuplicate() == true) {
                		   leftStatusLabel.setText("Duplicate Course - Not Added");
                		   leftStatusLabel.setStyle("-fx-text-fill: red");
						}
						
						//If its not a duplicate, then add the course
						else {
						   //Add the Course
							course = new Course(courseSubject,courseNumInt,instructorName);
							courseList.add(course);
							updateCheckBoxContainer(); //update the checkBox container, since we have added a course
							rightStatusLabel.setText("Total Courses Enrolled: " + courseList.size()); //update course counter label
							leftStatusLabel.setText("Course Added Successfully");
							leftStatusLabel.setStyle("-fx-text-fill: green");
							
						}
						
						//Clear all the text fields and prepare for the next entry;
						instructorEntry.clear();
						courseNumEntry.clear();
            		} 
            	}

            	//checkBoxHandler automatically removes a course from the list if it is checked
            	//If the add button is pressed, call the update method
                else if (source == drop){
                	updateCheckBoxContainer();  
                	rightStatusLabel.setText("Total Courses Enrolled: " + courseList.size());
                		
                	}
            	
            } //end of try

            catch (NumberFormatException ex) {
                leftStatusLabel.setText("Error! Course Number Must be an Integer.");
                leftStatusLabel.setStyle("-fx-text-fill: red;");
            }
            
            catch (Exception exception) {
            	leftStatusLabel.setText("At Least One field is Empty. Fill All Fields. ");
            	leftStatusLabel.setStyle("-fx-text-fill: red;");  
            }
        } //end of handle() method
        
        //checks if the user entered course info already exist in courseList
        public boolean isADuplicate() {
        	boolean exists = false;
        	
        	for (int i = 0; i < courseList.size(); i++) {
				if (courseList.get(i).getCourseNum() == Integer.parseInt(courseNumEntry.getText())
						&& (courseList.get(i).getInstructor().equals(instructorEntry.getText()))
						&& (courseList.get(i).getSubject().equals(subjectsDropdown.getValue())) ) { 
         		   exists = true;
				}
			}
			return exists;
        }
        
    } //end of ButtonHandler class

    //This class not needed?
    private class ComboBoxHandler implements EventHandler<ActionEvent>
    {
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
		}
    }//end of ComboBoxHandler

    
    //This class removes A course from courseList if a checkBox is ticked
    private class CheckBoxHandler implements EventHandler<ActionEvent> {
        private int indexVal;

        //constructor
        public CheckBoxHandler(Course oneCourse)
        {
           indexVal = courseList.indexOf(oneCourse);
        }
        public void handle(ActionEvent e)
        {
        	courseList.remove(indexVal);
        	
        }
    }//end of CheckBoxHandler
} //end of CoursePane class
    