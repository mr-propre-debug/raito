package fr.raito.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping" -> {
                ping(event);
            }
        }
    }

    public void ping(SlashCommandInteractionEvent event) {
       var user = event.getOption("user").getAsUser();
       event.reply("Pong " + user.getAsMention()).queue();
    }
//    int i = (int)(Math.random() * 100);
}
