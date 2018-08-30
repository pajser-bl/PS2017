package utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHandler {
	
	GsonBuilder gB;
	Gson gH;
	public GsonHandler() {
		gH=new Gson();
		gB=new GsonBuilder();
	}
	
}
