import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Gemara extends Command {
	public Gemara() {
		this.name = "gemara";
		this.help = "Uses Sefaria's API to post a Gemara quote";
	}

	@Override
	protected void execute(CommandEvent event) {
		String args = event.getArgs();
		String tract = args.substring(0,args.lastIndexOf(" "));
		String page = args.substring(args.lastIndexOf(" ")+1,args.indexOf(':'));
		String verseText = "";
		if(args.indexOf("-") == -1) {
			int verse = Integer.parseInt(args.substring(args.indexOf(':')+1));
			try { verseText = getVerse(tract,page,verse); } 
			catch (IOException e) {	e.printStackTrace(); }
		}
		else {
			int verse = Integer.parseInt(args.substring(args.indexOf(':')+1, args.indexOf('-')));
			int verseEnd = Integer.parseInt(args.substring(args.indexOf('-')+1));
			try { verseText = getVerse(tract,page,verse,verseEnd); }
			catch (IOException e) { e.printStackTrace(); }
		}
		SendVerse.sendEmbed(args, verseText, event);
	}

	public String getVerse(String tract, String page, int verse, int verseEnd) throws IOException {
		if(tract.indexOf(' ') != 0) {
			tract = tract.replaceAll(" ", "_");
		}
		String sURL = "https://www.sefaria.org/api/texts/";
		String text = "";
		for (int i = verse; i <= verseEnd; i++) {
			sURL = sURL + tract + "." + page + "." + i + "?context=0";
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

	public String getVerse(String tract, String page, int verse) throws IOException {
		if(tract.indexOf(' ') != 0) {
			tract = tract.replaceAll(" ", "_");
		}
		String sURL = "https://www.sefaria.org/api/texts/";
		String text = "";
		sURL = sURL + tract + "." + page + "." + verse + "?context=0";
		URL url = new URL(sURL);
		URLConnection request = url.openConnection();
		request.connect();
		JsonParser jp = new JsonParser(); 
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
		JsonObject rootobj = root.getAsJsonObject(); 
		text = text + rootobj.get("text").getAsString() + " ";
		text = TextProcessing.fixText(text);
		return text;
	}
}