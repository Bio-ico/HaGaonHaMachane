import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class HaGaon {
	public static void main(String[] arguments) throws Exception
	{
		EventWaiter waiter = new EventWaiter();
		CommandClientBuilder builder = new CommandClientBuilder();
		builder.setOwnerId("455504351872548885");
		builder.addCommand(new Torah());
		builder.addCommand(new Gemara());
		builder.addCommand(new Mishnah());
		builder.addCommand(new PikeriAvot());
		builder.addCommand(new Tosefta());
		builder.addCommand(new Rashi());
		builder.addCommand(new About());
		builder.addCommand(new JPS());
		builder.setPrefix("!!");
		CommandClient client = builder.build();
		Object[] EventListers = {waiter,client};
		new JDABuilder(AccountType.BOT)
		// set the token
		.setToken("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")

		// set the game for when the bot is loading
		.setGame(Game.playing("loading..."))

		// add the listeners
		.addEventListener(EventListers)
		// start it up!
		.buildAsync();

	}
}