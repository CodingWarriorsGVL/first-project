/**
 * @author Brandon Lewis
 * @version 1.0
 * @since 2019-05-17
 */

package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Main extends Application {
  public final static int WIDTH = 800, HEIGHT = 800, DEPTH = 800;
  public static double speed = 0.5, rMax = 1.5;
  public static Group root;
  public Scene scene;
  
  private int maxSpeed = 10, starCount = 800;
  private Star[] stars;
  
  public void start(Stage primaryStage) {
	root = new Group();
	scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
	scene.setOnScroll(this::processScroll);
	scene.setOnMouseEntered(this::processMouseEnter);
	scene.setOnMouseExited(this::processMouseExit);
	scene.setOnMouseDragged(this::processMouseDrag);
	scene.setOnMouseClicked(this::processMouseClick);
	
	stars = new Star[starCount];
	for (int i = 0; i < stars.length; i++) {
	  stars[i] = new Star();
	}
	
	root.setTranslateX(WIDTH/2);
	root.setTranslateY(HEIGHT/2);
	
	for (int i = 0; i < stars.length; i++) {
	  stars[i].update();
	  stars[i].show();
	}
	
//	final long startNanoTime = System.nanoTime();
	new AnimationTimer() {
	  public void handle(long currentNanoTime) {
//		double t = (currentNanoTime - startNanoTime) / 1000000000.0;
		
		for (int i = 0; i < stars.length; i++) {
		  stars[i].update();
		  stars[i].redraw();
		}
		
	  }
	}.start();

	primaryStage.setTitle("Starfield");
	primaryStage.setScene(scene);
	primaryStage.show();
  }
  
  public void processScroll(ScrollEvent e) {
	double deltaY = e.getDeltaY()/10;
	if (speed + deltaY > maxSpeed) {
	  speed = maxSpeed;
	} else if (speed + deltaY < -maxSpeed) {
	  speed = -maxSpeed;
	} else {
	  speed += deltaY;
	}
//	System.out.println("speed: " + speed);
  }
  
  public void processMouseEnter(MouseEvent e) {
	//scene.setCursor(Cursor.NONE);
	scene.setCursor(Cursor.CROSSHAIR);
  }
  
  public void processMouseExit(MouseEvent e) {
	scene.setCursor(Cursor.DEFAULT);
  }
  
  public void processMouseClick(MouseEvent e) {
	System.out.println(e.getButton());
	if (e.getButton().equals(MouseButton.SECONDARY)) {
	  root.setTranslateX(WIDTH/2);
	  root.setTranslateY(HEIGHT/2);
	}
  }
  
  public void processMouseDrag(MouseEvent e) {
	double centerX = e.getX(), centerY = e.getY();
	root.setTranslateX(centerX);
	root.setTranslateY(centerY);
  }
  
}