package fr.raito.Listeners;
import fr.raito.Managers.GiftManager;
import fr.raito.Managers.GiftDexManager;
import fr.raito.Models.Gift;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

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
            case "giftdex" -> {
                giftDex(event);
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

        if(user.getId().equals(initiator.getId())) {
            event.reply("Comment ça mon reuf ?")
                    .setEphemeral(true)
                    .queue();
        }else {
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
    }
    public void giftDex(SlashCommandInteractionEvent event) {
        var user = event.getUser(); // Utilisateur qui a déclenché la commande
        var gifts = GiftDexManager.getGifts(user); // Récupérer ses cadeaux

        if (gifts.isEmpty()) {
            event.reply("Vous n'avez reçu aucun cadeau pour le moment.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        // Regrouper les cadeaux et compter leur occurrence
        Map<String, Integer> giftCounts = new HashMap<>();
        Map<String, Gift> giftDetails = new HashMap<>(); // Pour stocker les détails originaux d'un type de cadeau

        for (Gift gift : gifts) {
            giftCounts.put(gift.getName(), giftCounts.getOrDefault(gift.getName(), 0) + 1);
            giftDetails.putIfAbsent(gift.getName(), gift); // Sauvegarder le cadeau original pour les propriétés
        }

        // Construire un message listant tous les cadeaux
        StringBuilder message = new StringBuilder("Voici vos cadeaux :\n");
        for (Map.Entry<String, Integer> entry : giftCounts.entrySet()) {
            Gift gift = giftDetails.get(entry.getKey());
            int count = entry.getValue();

            // Ajouter une ligne pour chaque type de cadeau
            message.append(String.format("- **%s** [%s] x%d: %s\n",
                    gift.getName(),
                    gift.getRarity().getLabel(),
                    count,
                    gift.getDescription()));
        }

        // Répondre avec la liste des cadeaux
        event.reply(message.toString())
                .queue();
    }
//    int i = (int)(Math.random() * 100);
}
