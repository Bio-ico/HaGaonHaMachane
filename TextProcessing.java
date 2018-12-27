import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TextProcessing {
	public static String fixText(String text) {
		text = text.replaceAll("(?i)<i[^>]*>", "");
		text = text.replaceAll("(?i)</i[^>]*>", "");
		text = text.replaceAll("(?i)<b[^>]*>", "");
		text = text.replaceAll("(?i)</b[^>]*>", "");
		text = text.replaceAll("(?i)<strong[^>]*>", "");
		text = text.replaceAll("(?i)</strong[^>]*>", "");
		text = text.replaceAll(" +", " ");
		return text;
	}
	public static String getVerse(String book, int chapter, int verse, int verseEnd, String sURL) throws IOException{
		if(book.indexOf(' ') != 0) {
			book = book.replaceAll(" ", "_");
		}
		String text = "";
		String sURLoriginal = sURL;
		for (int i = verse; i <= verseEnd; i++) {
			sURL = sURLoriginal;
			sURL = sURL + book + "." + chapter + "." + i + "?context=0";
			URL url = new URL(sURL);
			URLConnection request = url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser(); 
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
			JsonObject rootobj = root.getAsJsonObject(); 
			if(text.length() + rootobj.get("text").getAsString().length() > 2048)
				break;
			text = text + rootobj.get("text").getAsString() + " ";
		}
		text = TextProcessing.fixText(text);
		return text;
	}
	public static String getVerse(String book, int chapter, int verse, String sURL) throws IOException {
		if(book.indexOf(' ') != 0) {
			book = book.replaceAll(" ", "_");
		}
		sURL = sURL + book + "." + chapter + "." + verse + "?context=0";
		URL url = new URL(sURL);
		URLConnection request = url.openConnection();
		request.connect();
		JsonParser jp = new JsonParser(); 
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
		JsonObject rootobj = root.getAsJsonObject(); 
		String text = rootobj.get("text").getAsString();
		text = TextProcessing.fixText(text);
		return text;
	}
	public static String getVerse(String book, int chapter, int verse, int verseEnd, String translation, String sURL) throws IOException{
		if(book.indexOf(' ') != 0) {
			book = book.replaceAll(" ", "_");
		}
		if(translation.indexOf(' ') != 0) {
			translation = translation.replaceAll(" ", "_");
		}
		String text = "";
		String sURLoriginal = sURL;
		for (int i = verse; i <= verseEnd; i++) {
			sURL = sURLoriginal + book + "." + chapter + "." + i + "?context=0" + "&ven=" + translation ;
			URL url = new URL(sURL);
			URLConnection request = url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser(); 
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
			JsonObject rootobj = root.getAsJsonObject(); 
			if(text.length() + rootobj.get("text").getAsString().length() > 2048)
				break;
			text = text + rootobj.get("text").getAsString() + " ";
		}
		text = TextProcessing.fixText(text);
		return text;
	}
	public static String getVerse(String book, int chapter, int verse, String translation, String sURL) throws IOException {
		if(book.indexOf(' ') != 0) {
			book = book.replaceAll(" ", "_");
		}
		if(translation.indexOf(' ') != 0) {
			translation = translation.replaceAll(" ", "_");
		}
		sURL = sURL + book + "." + chapter + "." + verse + "?context=0" + "&ven=" + translation ;
		URL url = new URL(sURL);
		URLConnection request = url.openConnection();
		request.connect();
		JsonParser jp = new JsonParser(); 
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
		JsonObject rootobj = root.getAsJsonObject(); 
		String text = rootobj.get("text").getAsString();
		text = TextProcessing.fixText(text);
		return text;
	}
}
