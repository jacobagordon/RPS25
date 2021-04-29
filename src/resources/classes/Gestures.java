package resources.classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gestures {	
	private String name;
	private Hashtable<String, String> victory;
	//private BufferedImage image;
	JSONArray gesture_arr = getGesturesFromJSON();
	
	
	public Gestures(String name, int index) {
		JSONObject gesture = (JSONObject) gesture_arr.get(index);
		
		this.name = (String) gesture.get("name");
		
	}

	private JSONArray getGesturesFromJSON() {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = new JSONArray();
		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\jacwe\\Desktop\\Github\\RPS25\\src\\resources\\gestures\\gestures.json"));
		
			jsonArray = (JSONArray) jsonObject.get("gestures");
			//JSONObject rock = (JSONObject) jsonArray.get(0);
			//System.out.println(rock.get("name"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public String getName() {
		return this.name;
	}
}
