// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Joel Hudgens
//    StudentID: 1224491216
//      Lecture: T, Th 10:30
//  Description: This Program uses a JavaFX GUI to simulate an ASU course Enrollment System

//This class sets up the GUI scene and window along with other settings 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Assignment6 extends Application
{
    public static final int WIDTH = 600, HEIGHT = 400;

    public void start(Stage stage)
    {
        StackPane root = new StackPane();
        CoursePane coursePane = new CoursePane(); 
        root.getChildren().add(coursePane); //CoursePane will be a child of the root pane

        Scene scene = new Scene(root, WIDTH, HEIGHT); 
        stage.setTitle("ASU Course Enrollment System");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false); //for simplicity, no adjusting window size.
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}