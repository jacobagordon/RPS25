package main;

import java.util.Hashtable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import resources.classes.Gestures;
import resources.classes.ButtonGenerator;


public class RPS25 extends Application {

	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Hashtable<String, Integer> names = new Hashtable<String, Integer>();
		Gestures[] gestures = getGestures(names);
		ButtonGenerator buttonGen = new ButtonGenerator(gestures);
		
		primaryStage.setTitle("RPS 25");
		StackPane layout = new StackPane();
		
		for (int i=0; i<buttonGen.getNumberOfButtons(); i++) {
			layout.getChildren().add(buttonGen.getButton(i));
		}
		
		Scene scene = new Scene(layout, 1000, 1000);
		scene.getStylesheets().add(getClass().getResource("rps.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private static Gestures[] getGestures(Hashtable<String, Integer> gesture_dict) {
		String[] names = {"Gun", "Dynamite", "Nuke", "Lightning", "Devil",
				"Dragon", "Alien", "Water", "Bowl", "Air",
				"Moon", "Paper", "Sponge", "Wolf", "Cockroach",
				"Tree", "Man", "Woman", "Monkey", "Snake", 
				"Axe", "Scissors", "Fire", "Sun", "Rock"};
		Gestures[] gestures = new Gestures[25];
		
		for (int i = 0; i < 25; i++) {
			gestures[i] = new Gestures(names[i], i);
			gesture_dict.put(names[i], i);
		}
		
		return gestures;
	}
}
