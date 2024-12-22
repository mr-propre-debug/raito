package fr.raito.Managers;

import fr.raito.Models.Gift;


import java.util.List;
import java.util.Random;

public class GiftManager {
    private static final List<Gift> GIFTS = List.of(
            new Gift("Bouquet de fleurs", "Un joli bouquet de fleurs fraîches.", Gift.Rarity.COMMON),
            new Gift("Boîte de chocolats", "Une boîte de délicieux chocolats.", Gift.Rarity.COMMON),
            new Gift("Carte cadeau", "Une carte cadeau pour une boutique en ligne.", Gift.Rarity.UNCOMMON),
            new Gift("Livre antique", "Un livre rare avec une histoire fascinante.", Gift.Rarity.RARE),
            new Gift("Bague brillante", "Une bague ornée d'une pierre précieuse.", Gift.Rarity.VERY_RARE),
            new Gift("Épée légendaire", "Une arme mythique avec des pouvoirs secrets.", Gift.Rarity.MYTHICAL),
            new Gift("Couronne divine", "Un artefact légendaire porté par les dieux.", Gift.Rarity.LEGENDARY)
    );

    public static Gift getRandomGift() {
        Random random = new Random();
        double roll = random.nextDouble(); // Génère un nombre entre 0.0 et 1.0

        if (roll < 0.5) { // 50 % de chance pour des cadeaux communs
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.COMMON)
                    .findAny()
                    .orElseThrow();
        } else if (roll < 0.75) { // 25 % pour des cadeaux peu communs
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.UNCOMMON).
                    findAny()
                    .orElseThrow();
        } else if (roll < 0.9) { // 15 % pour des cadeaux rares
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.RARE)
                    .findAny()
                    .orElseThrow();
        } else if (roll < 0.98) { // 8 % pour des cadeaux très rares
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.VERY_RARE)
                    .findAny()
                    .orElseThrow();
        } else if (roll < 0.995) { // 1.5 % pour des cadeaux mythiques
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.MYTHICAL)
                    .findAny()
                    .orElseThrow();
        } else { // 0.5 % pour des cadeaux légendaires
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.LEGENDARY)
                    .findAny()
                    .orElseThrow();
        }
    }

    public static List<Gift> getAllGifts() {
        return GIFTS;
    }
}