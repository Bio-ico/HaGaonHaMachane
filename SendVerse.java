import java.awt.Color;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class SendVerse {
	public static void sendEmbed(String name, String verseText, CommandEvent event) {
		String sURL = "https://cdn.discordapp.com/avatars/466676353907818516/c48b21b283307d9ff454ed221dc0aaa2.jpg?size=1024";
		name = name.substring(0,1).toUpperCase() + name.substring(1);
		event.reply(new EmbedBuilder()
				.setTitle(name)
				.setDescription(verseText)
				.setColor(Color.BLUE)
				.setFooter("HaGaon HaMachane v0.1", sURL)
				.build());
	}
}
