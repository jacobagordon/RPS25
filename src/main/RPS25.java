package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import resources.classes.Gestures;


public class RPS25 {

	public static void main(String[] args) {
		
		Gestures[] gestures = getGestures();
		
	}
	
	private static Gestures[] getGestures() {
		String[] names = {"Gun", "Dynamite", "Nuke", "Lightning", "Devil",
				"Dragon", "Alien", "Water", "Bowl", "Air",
				"Moon", "Paper", "Sponge", "Wolf", "Cockroach",
				"Tree", "Man", "Woman", "Monkey", "Snake", 
				"Axe", "Scissors", "Fire", "Sun", "Rock"};
		Gestures[] gestures = new Gestures[25];
		
		for (int i = 0; i < 25; i++) {
			gestures[i] = new Gestures(names[i], i);
			System.out.println("Successfully got " + names[i]);
		}
		
		return gestures;
	}

}
