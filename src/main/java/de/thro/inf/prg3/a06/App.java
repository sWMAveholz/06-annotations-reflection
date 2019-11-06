package de.thro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.thro.inf.prg3.a06.model.Joke;
import de.thro.inf.prg3.a06.model.JokeAdapter;
import retrofit2.Call;
import retrofit2.Retrofit;

import javax.swing.plaf.basic.BasicTextUI;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	//private static BasicTextUI GsonConverterFactory;

	public static void main(String[] args) {
		// TODO fetch a random joke and print it to STDOUT
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Joke.class, new JokeAdapter());
		Gson gson = builder.create();

		//String jsonString = "{\"name\":\"Mahesh\", \"rollNo\":1}";
		String jsonString = "{ \"type\": \"success\", \"value\": { \"id\": 522, \"joke\": \"Chuck Norris can do a wheelie on a unicycle.\", \"categories\": [\"nerdy\",\"bla\"] } }";

		Joke joke = gson.fromJson(jsonString, Joke.class);
		System.out.println(joke.toString());
		String jsonJoke = gson.toJson(joke);
		System.out.println(jsonJoke);

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.icndb.com/").build();
		ICNDBApi service = retrofit.create(ICNDBApi.class);

		Call<Joke> response2= service.getRandomJoke();
		System.out.println(response2.toString());

	}

}
