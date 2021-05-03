package resources.classes;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import resources.classes.Gestures;

public class ButtonGenerator {
	private double buttonLocations[][] = new double[25][2];
	private Button[] buttonWheel = new Button[25];
	
	public ButtonGenerator(Gestures[] gestures, StackPane layout) {
		String path = "file:C:\\Users\\jacwe\\Desktop\\Github\\RPS25\\src\\resources\\images\\gestures\\";
		
		for (int a=0; a<25; a++) {
			double theta = (Math.PI*2) / 25;
			double angle = theta * (a+1);
			
			buttonLocations[a][0] = 380 * Math.cos(angle);
			buttonLocations[a][1] = 380 * Math.sin(angle);
		}
		
		for (int i=0; i<25; i++) {
			final int choice = i;
			this.buttonWheel[i] = new Button();
			this.buttonWheel[i].setOnAction(e -> {
				Gestures player = gestures[choice];
				Gestures enemy = gestures[ThreadLocalRandom.current().nextInt(0, 25)];
				determineWinner(player, enemy, layout);
			});
			
			Image img = new Image(path + gestures[i].getName() + ".png");
			ImageView view = new ImageView(img);
			view.setFitHeight(80);
			view.setPreserveRatio(true);
			
			this.buttonWheel[i].getStyleClass().add("gesture-button");
			this.buttonWheel[i].setGraphic(view);
			this.buttonWheel[i].setTranslateX(buttonLocations[i][0]);
			this.buttonWheel[i].setTranslateY(buttonLocations[i][1]);
		}
	}
	
	private static void determineWinner(Gestures player, Gestures enemy, StackPane layout) {
		System.out.println("You played: " + player.getName());
		Text youPlayed = new Text("You played: " + player.getName());
		Text theyPlayed = new Text("They played: " + enemy.getName());
		Text outcome = new Text();
		
		
		System.out.println("They played: " + enemy.getName());
		if (layout.getChildren().contains(youPlayed)) {
			layout.getChildren().remove(0);
			layout.getChildren().remove(1);
			layout.getChildren().remove(2);
		}
		//layout.getChildren().remove(theyPlayed);
		//layout.getChildren().remove(outcome);
		
		if (player.victorious(enemy.getName()) != null) {
			System.out.println(player.victorious(enemy.getName()));
			System.out.println("You win!");
			outcome.setText(player.victorious(enemy.getName()));
		}
		else if (enemy.victorious(player.getName()) != null) {
			System.out.println(enemy.victorious(player.getName()));
			System.out.println("Enemy wins.");
			outcome.setText(enemy.victorious(player.getName()));
		}
		else {
			System.out.println("Tie!");
		}
		
		layout.getChildren().add(0, youPlayed);
		layout.getChildren().add(1, theyPlayed);
		layout.getChildren().add(2, outcome);
	}
	
	public int getNumberOfButtons() {
		return this.buttonWheel.length;
	}
	
	public Button getButton(int index) {
		return this.buttonWheel[index];
	}
}
