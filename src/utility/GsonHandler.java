package utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ostalo.User;

public class GsonHandler {
	
	public static void main(String args[]) {
		
		Gson gsonobject=new GsonBuilder().create();
		User u=new User("test");
		String S=gsonobject.toJson(u);
		System.out.println(S);
		System.out.println(gsonobject.fromJson(S, User.class));
		
		
		
	}	
}
