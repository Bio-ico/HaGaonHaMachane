import java.io.IOException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Mishnah extends Command {
	public Mishnah() {
		this.name = "mishnah";
		this.help = "Grabs a Mishnash verse from Sefaria's API";
	}
	protected void execute(CommandEvent event) {
		String args = event.getArgs();
		String sURL = "https://www.sefaria.org/api/texts/mishnah_";
		String tract = args.substring(0,args.lastIndexOf(" "));
		String verseText = "";
		int page = Integer.parseInt(args.substring(args.lastIndexOf(" ")+1,args.indexOf(':')));
		if(args.indexOf("-") == -1) {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1));
			try {verseText = TextProcessing.getVerse(tract,page,line,sURL); } 
			catch (IOException e) {	e.printStackTrace(); }
		}
		else {
			int line = Integer.parseInt(args.substring(args.indexOf(':')+1, args.indexOf('-')));
			int lineEnd = Integer.parseInt(args.substring(args.indexOf('-')+1));
			try { verseText = TextProcessing.getVerse(tract,page,line,lineEnd,sURL); }
			catch (IOException e) { e.printStackTrace(); }
		}
		SendVerse.sendEmbed(args, verseText, event);
	}
}
