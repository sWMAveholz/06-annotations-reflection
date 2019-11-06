package de.thro.inf.prg3.a06.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class JokeAdapter extends TypeAdapter<Joke> {


	@Override
	public void write(JsonWriter out, Joke value) throws IOException {


	}

	@Override
	public Joke read(JsonReader in) throws IOException {

		Joke joke = new Joke();
		in.beginObject();
		String fieldname = null;

		in.nextName();
		in.nextString();
		in.nextName();
		in.beginObject();
		//in.nextName();


		while (in.hasNext()) {
			JsonToken token = in.peek();

			if (token.equals(JsonToken.NAME)) {
				//get the current token
				fieldname = in.nextName();
			}

			else if ("id".equals(fieldname)) {
				//move to next token
				token = in.peek();
				joke.setNumber(in.nextInt());
			}

			else if("joke".equals(fieldname)) {
				//move to next token
				token = in.peek();
				joke.setContent(in.nextString());
			}

			else if("categories".equals(fieldname)) {
				//move to next token
				token = in.peek();
				joke.setRubrics(new String[]{});
				in.beginArray();
				token = in.peek();
				while (in.peek().equals(JsonToken.STRING)){
					String s = in.nextString();
					token = in.peek();
				}
				in.endArray();
				in.endObject();
				in.endObject();
				break;
			}

			else {
				in.endObject();
			}
		}

		return joke;
	}
}
