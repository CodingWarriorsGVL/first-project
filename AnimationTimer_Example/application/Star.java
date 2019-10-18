/**
 * @author Brandon Lewis
 * @version 1.0
 * @since 2019-05-17
 */

package application;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Star {
  private Random gen = new Random();
  private Group root;
  private Circle circle;
  private Line line;
  private double x, y, z, r, rMax, viewX, viewY, lastX, lastY, lastZ, width, height, depth, speed;
  private boolean coinFlip;
  
  public Star() {
	root = Main.root;
	width = Main.root.getScene().getWidth();
	height = Main.root.getScene().getHeight();
	depth = Main.DEPTH;
	speed = Main.speed;
	rMax = Main.rMax;
	x = mapValue(gen.nextDouble(), 0, 1, -width/2, width/2);
	y = mapValue(gen.nextDouble(), 0, 1, -height/2, height/2);
	z = mapValue(gen.nextDouble(), 0, 1, 0.000000001, depth/2);
  }
  
  public static double mapValue(double x, double in_min, double in_max, double out_min, double out_max) {
	return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
  }
  
  public boolean flipCoin() {
	coinFlip = gen.nextBoolean();
	return coinFlip;
  }
  
  public void update() {
	speed = Main.speed;
	z -= speed;
	
	if (z > depth/2) {
	  z = 0.000000001;
	  lastZ = z;
//	  System.out.println("Star reset to behind camera");
//	  System.out.println("(x, y, z): "+"("+x+", "+y+", "+z+")");
	} else if (speed >= 0 && z <= 0 && (Math.abs(lastX) > width/2 || Math.abs(lastY) > height/2) ) {
	  z = depth/2;
	  x = mapValue(gen.nextDouble(), 0, 1, -width/2, width/2);
	  y = mapValue(gen.nextDouble(), 0, 1, -height/2, height/2);
	  lastZ = z;
//	  System.out.println("Star reset to beyond range of camera");
//	  System.out.println("(x, y, z): "+"("+x+", "+y+", "+z+")");
	}
	
  }
  
  public void show() {
	viewX = mapValue(x/z, 0, 1, 0, width/2);
	viewY = mapValue(y/z, 0, 1, 0, height/2);
	r = mapValue(z, 0, width/2, rMax, 0);
	lastX = mapValue(x/lastZ, 0, 1, 0, width/2);
	lastY = mapValue(y/lastZ, 0, 1, 0, height/2);
	lastZ = z;
	
	circle = new Circle(viewX, viewY, r, Color.WHITE);
	root.getChildren().add(circle);
	
	line = new Line(lastX, lastY, viewX, viewY);
	line.setStrokeWidth(r);
	line.setStroke(Color.WHITE);
	root.getChildren().add(line);
  }
  
  public void redraw() {
	viewX = mapValue(x/z, 0, 1, 0, width/2);
	viewY = mapValue(y/z, 0, 1, 0, height/2);
	r = mapValue(z, 0, width/2, rMax, 0);
	
	if ((Math.abs(viewX) <= width || Math.abs(viewY) <= height) &&
		(Math.abs(lastX) <= width || Math.abs(viewY) <= height) ) {
	  lastX = mapValue(x/lastZ, 0, 1, 0, width/2);
	  lastY = mapValue(y/lastZ, 0, 1, 0, height/2);
	} else {
	  lastX = viewX;
	  lastY = viewY;
	}
	
	
//	System.out.println("(sx, sy, z): "+"("+sx+", "+sy+", "+z+")");
	
	lastZ = z;
	
	circle.setCenterX(viewX);
	circle.setCenterY(viewY);
	circle.setRadius(r);
	
	if ( (Math.abs(viewX) <= width || Math.abs(viewY) <= height) ||
		(Math.abs(lastX) <= width || Math.abs(viewY) <= height) ) {
	  line.setStartX(viewX);
	  line.setStartY(viewY);
	  line.setEndX(lastX);
	  line.setEndY(lastY);
	  line.setStrokeWidth(r);
	}
	
  }
  
}
