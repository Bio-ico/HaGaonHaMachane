import java.io.IOException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class JPS extends Command {
	public JPS() {
		this.name = "JPS";
		this.help = "Grabs a Tanakh verse from Sefaria's API, using modern JPS";
	}
	protected void execute(CommandEvent event) {
		String args = event.getArgs();
		String sURL = "https://www.sefaria.org/api/texts/";
		String book = args.substring(0,args.lastIndexOf(" "));
		String translation = "Tanakh: The Holy Scriptures, published by JPS";
		String verseText = "";
		int chapter = Integer.parseInt(args.substring(args.lastIndexOf(" ")+1,args.indexOf(':')));
		if(args.indexOf("-") == -1) {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1));
			try {verseText = TextProcessing.getVerse(book,chapter,line,translation,sURL); } 
			catch (IOException e) {	e.printStackTrace(); }
		}
		else {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1, args.indexOf('-')));
			int lineEnd = Integer.parseInt(args.substring(args.indexOf('-')+1));
			try { verseText = TextProcessing.getVerse(book,chapter,line,lineEnd,translation,sURL); }
			catch (IOException e) { e.printStackTrace(); }
		}
		SendVerse.sendEmbed(args, verseText, event);  
}
}