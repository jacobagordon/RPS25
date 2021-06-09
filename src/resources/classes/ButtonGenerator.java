package resources.classes;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import resources.classes.Gestures;

public class ButtonGenerator {
	private double buttonLocations[][] = new double[25][2];
	private Button[] buttonWheel = new Button[25];
	Text player = new Text("");
	Text enemy = new Text("");
	Text outcome = new Text("");
	Text winner = new Text("");
	Text cSLabel = new Text("Current Selection:");
	Text currentSelection = new Text("None");
	
	public ButtonGenerator(Gestures[] gestures, StackPane layout) {
		String path = "file:C:\\Users\\jacwe\\Desktop\\Github\\RPS25\\src\\resources\\images\\gestures\\";
		
		configureText();
		
		layout.getChildren().add(player);
		layout.getChildren().add(enemy);
		layout.getChildren().add(outcome);
		layout.getChildren().add(winner);
		layout.getChildren().add(currentSelection);
		layout.getChildren().add(cSLabel);
		
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
			this.buttonWheel[i].setTooltip(new Tooltip(gestures[i].getName()));
		
			this.buttonWheel[i].setOnMouseEntered(e -> {
				this.buttonWheel[choice].getStyleClass().add("gesture-button-hovered");
				this.currentSelection.setText(gestures[choice].getName());
			});
	        this.buttonWheel[i].setOnMouseExited(e -> {
	        	this.buttonWheel[choice].getStyleClass().remove("gesture-button-hovered");
	        	this.currentSelection.setText("None");
	        });
	    }
	}
	
	private void determineWinner(Gestures player, Gestures enemy, StackPane layout) {
		this.player.setText("You played: " + player.getName());
		this.enemy.setText("They played: " + enemy.getName());
		
		if (player.victorious(enemy.getName()) != null) {
			this.outcome.setText(player.victorious(enemy.getName()));
			this.winner.setText("You Win!!");
		}
		else if (enemy.victorious(player.getName()) != null) {
			this.outcome.setText(enemy.victorious(player.getName()));
			this.winner.setText("Opponent Wins");
		}
		else {
			this.outcome.setText("Tie!");
			this.winner.setText("Play again");
		}
	}

	private void configureText() {
		this.enemy.setTranslateY(25);
		this.outcome.setTranslateY(50);
		this.winner.setTranslateY(75);
		
		this.currentSelection.setTranslateY(375);
		this.currentSelection.setTranslateX(350);
		this.cSLabel.setTranslateY(350);
		this.cSLabel.setTranslateX(350);
		
		this.player.setFill(Color.BEIGE);
		this.enemy.setFill(Color.BEIGE);
		this.winner.setFill(Color.BEIGE);
		this.outcome.setFill(Color.BEIGE);
		this.currentSelection.setFill(Color.BEIGE);
		this.cSLabel.setFill(Color.BEIGE);
	}
	
	public int getNumberOfButtons() {
		return this.buttonWheel.length;
	}
	
	public Button getButton(int index) {
		return this.buttonWheel[index];
	}
}
