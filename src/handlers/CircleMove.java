
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CircleMove extends Application {

    // Create pane

    CirclePane circlePane = new CirclePane();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create buttons and labels
        Button btEnlarge = new Button("+");
        Button btShrink = new Button("-");
        Label zoomLabel = new Label("Enlarge/Shrink");
        Button btLeft = new Button("<-");
        Button btRight = new Button("->");
        Button btUp = new Button("^");
        Button btDown = new Button("v");
        Label moveLabel = new Label("Move");

        // stick enlarge/shrink buttons to zoom pane
        VBox zoomBtnPane = new VBox();
        zoomBtnPane.getChildren().addAll(btEnlarge, btShrink);
        zoomBtnPane.setAlignment(Pos.CENTER);

        // add button pane and label to zoom pane
        VBox zoomPane = new VBox();
        zoomPane.getChildren().addAll(zoomBtnPane, zoomLabel);
        zoomPane.setAlignment(Pos.BOTTOM_CENTER);
        zoomPane.setSpacing(5.0);
//		zoomPane.setStyle("-fx-border-color: green;");

        // create pane for the movement keys
        BorderPane movePane = new BorderPane();
        movePane.setTop(btUp);
        movePane.setBottom(btDown);
        movePane.setLeft(btLeft);
        movePane.setRight(btRight);
//		movePane.setStyle("-fx-border-color: red;");

        // create spacing and positioning for movement keys
        BorderPane.setAlignment(btUp, Pos.CENTER);
        BorderPane.setAlignment(btDown, Pos.CENTER);

        // create vbox to hold enlarge/shrink items
        VBox moveItems = new VBox();
        moveItems.setAlignment(Pos.BOTTOM_CENTER);
        moveItems.getChildren().addAll(movePane, moveLabel);
        moveItems.setSpacing(zoomPane.getSpacing());
//		moveItems.setStyle("-fx-border-color: blue;");

        // create hbox to hold all control items
        HBox controlBox = new HBox();
        controlBox.setSpacing(25);
        controlBox.setAlignment(Pos.BOTTOM_CENTER);
        controlBox.getChildren().addAll(zoomPane, moveItems);
//		controlBox.setStyle("-fx-border-color: yellow;");

        // create and register handlers
        btEnlarge.setOnAction(e -> circlePane.enlarge());
        btShrink.setOnAction(e -> circlePane.shrink());
        btLeft.setOnAction(e -> circlePane.moveLeft());
        btRight.setOnAction(e -> circlePane.moveRight());
        btUp.setOnAction(e -> circlePane.moveUp());
        btDown.setOnAction(e -> circlePane.moveDown());

        // Align and place the circle pane and the controls
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(controlBox);
        BorderPane.setAlignment(circlePane, Pos.CENTER);
        BorderPane.setAlignment(controlBox, Pos.BOTTOM_CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 400, 400);

        primaryStage.setTitle("Control Circle and Move Demo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

		// now that the scene has been sized by Java, move circle near center of pane
        // adjust for control box height to actually center circle
        circlePane.setCenter(borderPane.getHeight() - controlBox.getHeight(), borderPane.getWidth());
    }

    // Create inner classes to handle ActionEvents from buttons
    
    
    class EnlargeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            circlePane.enlarge();
        }
    }

    class ShrinkHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            circlePane.shrink();
        }
    } 
	class LeftHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			//circlePane.moveLeft();
		}
	}
	
	class RightHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            circlePane.moveRight();
        }
    }

    class UpHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            circlePane.moveUp();
        }
    }

    class DownHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            circlePane.moveDown();
        }
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

// Create a Pane that contains a circle with methods for enlarging, shrinking, and
// moving the circle around the pane.
// By default, start the circle in the middle of the pane.
class CirclePane extends Pane {

    private Circle circle = new Circle(50);

    public CirclePane() {
        circle.setCenterX(0);
        circle.setCenterY(circle.getCenterX());
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
    }

    // enlarge the radius of the circle
    public void enlarge() {
        circle.setRadius(circle.getRadius() + 5);
    }

    // shrink the radius of the circle
    public void shrink() {
        circle.setRadius(circle.getRadius() > 5
                ? circle.getRadius() - 5 : circle.getRadius());
    }

    // move the circle's location to the left
    public void moveLeft() {
        circle.setCenterX(circle.getCenterX() > 10
                ? circle.getCenterX() - 10 : circle.getCenterX());
    }

    // move the circle's location to the right	
    public void moveRight() {
        circle.setCenterX(circle.getCenterX() < getWidth()
                ? circle.getCenterX() + 10 : circle.getCenterX());
    }

    // move the circle's location up	
    public void moveUp() {
        circle.setCenterY(circle.getCenterY() > 10
                ? circle.getCenterY() - 10 : circle.getCenterY());
    }

    // move the circle's location down	
    public void moveDown() {
        circle.setCenterY(circle.getCenterY() < getHeight()
                ? circle.getCenterY() + 10 : circle.getCenterY());
    }

    public void setCenter(double height, double width) {
        circle.setCenterX(width / 2.0);
        circle.setCenterY(height / 2.0);
    }
}
