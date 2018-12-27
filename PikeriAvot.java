import java.io.IOException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PikeriAvot extends Command {
	public PikeriAvot() {
		this.name = "PirkeiAvot";
		this.help = "Grabs a Pikeri Avot verse from Sefaria's API";
	}
	protected void execute(CommandEvent event) {
		String args = event.getArgs();
		String sURL = "https://www.sefaria.org/api/texts/";
		String book = "Pirkei_Avot";
		String verseText = "";
		int chapter = Integer.parseInt(args.substring(args.lastIndexOf(" ")+1,args.indexOf(':')));
		if(args.indexOf("-") == -1) {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1));
			try { verseText = TextProcessing.getVerse(book,chapter,line,sURL); }
			catch (IOException e) { e.printStackTrace(); }  
		}
		else {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1, args.indexOf('-')));
			int lineEnd = Integer.parseInt(args.substring(args.indexOf('-')+1));
			try { verseText = TextProcessing.getVerse(book,chapter,line,lineEnd,sURL); }
			catch (IOException e) { e.printStackTrace(); }
		}
		SendVerse.sendEmbed(args, verseText, event); 
	}
	
}