/*
 * Copyright (c) 2018 Daniel J. Kirsch
 * HaGaon HaMachane [https://github.com/djkirsch/HaGaonHaMachane]
 *
 * This file is part of HaGaon HaMachane.
 *
 * HaGaon HaMachane is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License
 * version 3 as published by the Free Software Foundation
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;

public class About extends Command{
	public About() {
		this.name = "sur";
	}

	public void execute(CommandEvent event) {
		Permission[] recommendedPerms = new Permission[] {Permission.MESSAGE_READ, Permission.MESSAGE_WRITE};
		String link = event.getJDA().asBot().getInviteUrl(recommendedPerms);
		String message = "Salut ! Je suis HaGaon HaMachane ! Je suis un bot discord qui peut citer un assortiment de textes sacrés juifs!\n [Invite](" + link + ") moi à votre serveur !\n[Trouve-moi sur Github !](https://github.com/djkirsch/HaGaonHaMachane)";
		SendVerse.sendEmbed("About", message, event);
	}

}
