import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;

public class About extends Command{
	public About() {
		this.name = "about";
	}

	public void execute(CommandEvent event) {
		Permission[] recommendedPerms = new Permission[] {Permission.MESSAGE_READ, Permission.MESSAGE_WRITE};
		String link = event.getJDA().asBot().getInviteUrl(recommendedPerms);
		String message = "Hello! I am HaGaon HaMachane! I am a discord bot that can quote from an assortment of Jewish Holy Texts!\n [Invite](" + link + ") me to your server!";
		SendVerse.sendEmbed("About", message, event);
		List<Guild> guilds = event.getJDA().getGuilds();
		for(Guild a : guilds)
			System.out.println(a.getName());

	}

}
