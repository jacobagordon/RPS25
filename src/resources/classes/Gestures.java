package resources.classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gestures {	
	private String name;
	private Hashtable<String, String> victory = new Hashtable<String, String>();
	JSONArray gesture_arr = getGesturesFromJSON();
	
	public Gestures(String name, int index) {
		JSONObject gesture = (JSONObject) gesture_arr.get(index);
		
		this.name = (String) gesture.get("name");
		
		JSONObject json_victories = (JSONObject) gesture.get("victory");
		Iterator<String> keys = json_victories.keySet().iterator();
		
		while(keys.hasNext()) {
			String current_key = keys.next();
			String value = (String) json_victories.get(current_key);
			this.victory.put(current_key, value);
		}
	}

	private JSONArray getGesturesFromJSON() {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = new JSONArray();
		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\jacwe\\Desktop\\Github\\RPS25\\src\\resources\\gestures\\gestures.json"));
		
			jsonArray = (JSONArray) jsonObject.get("gestures");
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
	
	public String victorious(String opponent) {
		return this.victory.get(opponent);
	}
	
	public Hashtable<String, String> getVictory() {
		return this.victory;
	}
}
