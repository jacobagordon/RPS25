package main;

import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resources.classes.Gestures;


public class RPS25 extends Application {

	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Hashtable<String, Integer> names = new Hashtable<String, Integer>();
		Gestures[] gestures = getGestures(names);
		
		primaryStage.setTitle("RPS 25");
		button = new Button();
		button.setOnAction(e -> {
			Gestures player = gestures[ThreadLocalRandom.current().nextInt(0, 25)];
			Gestures enemy = gestures[ThreadLocalRandom.current().nextInt(0, 25)];
			determineWinner(player, enemy);
		});
		
		Image img = new Image("file:C:\\Users\\jacwe\\Desktop\\Github\\RPS25\\src\\resources\\images\\gestures\\Gun.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(80);
		view.setPreserveRatio(true);
		
		button.setGraphic(view);
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 1000, 500);
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
	
	private static void determineWinner(Gestures player, Gestures enemy) {
		
		System.out.println("You played: " + player.getName());
		System.out.println("They played: " + enemy.getName());
		
		if (player.victorious(enemy.getName()) != null) {
			System.out.println(player.victorious(enemy.getName()));
			System.out.println("You win!");
		}
		else if (enemy.victorious(player.getName()) != null) {
			System.out.println(enemy.victorious(player.getName()));
			System.out.println("Enemy wins.");
		}
		else {
			System.out.println("Tie!");
		}
	}
}
