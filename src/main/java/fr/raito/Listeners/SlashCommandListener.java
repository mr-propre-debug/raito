package fr.raito.Listeners;
import fr.raito.Managers.GiftManager;
import fr.raito.Managers.GiftDexManager;
import fr.raito.Models.Gift;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping" -> {
                ping(event);
            }
            case "gift" -> {
                gift(event);
            }
            case "dexgift" -> {
                dexGift(event);
            }

        }
    }

    public void ping(SlashCommandInteractionEvent event) {
       var user = event.getOption("user").getAsUser();
       event.reply("Pong " + user.getAsMention()).queue();
    }

    public void gift(SlashCommandInteractionEvent event) {
        var user = event.getOption("user").getAsUser(); // Utilisateur recevant le cadeau
        var initiator = event.getUser(); // Utilisateur initiant la commande
        var gift = GiftManager.getRandomGift(); // Cadeau aléatoire

        // Enregistrer le cadeau dans le système GiftDex pour le destinataire
        GiftDexManager.addGift(user, gift);

        // Construire le message cadeau avec la rareté incluse
        String message = String.format("%s vous offre un cadeau : **%s** [%s] !\n%s",
                initiator.getName(),
                gift.getName(),
                gift.getRarity().getLabel(), // Ajout du label de la rareté
                gift.getDescription());

        // Envoyer un message privé au destinataire
        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage(message))
                .queue();

        // Répondre à l'utilisateur initiateur
        event.reply("Le cadeau a été envoyé à " + user.getAsMention() + " !")
                .setEphemeral(true)
                .queue();
    }
    public void dexGift(SlashCommandInteractionEvent event) {
        var user = event.getUser(); // Utilisateur qui a déclenché la commande
        var gifts = GiftDexManager.getGifts(user); // Récupérer ses cadeaux

        if (gifts.isEmpty()) {
            event.reply("Vous n'avez reçu aucun cadeau pour le moment.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        // Construire un message listant tous les cadeaux
        StringBuilder message = new StringBuilder("Voici vos cadeaux :\n");
        for (Gift gift : gifts) {
            message.append(String.format("- **%s** [%s]: %s\n", gift.getName(), gift.getRarity().getLabel(), gift.getDescription()));
        }

        // Répondre avec la liste des cadeaux
        event.reply(message.toString())
                .queue();
    }
//    int i = (int)(Math.random() * 100);
}
