package fr.raito.Managers;

import fr.raito.Models.Gift;


import java.util.List;
import java.util.Random;

public class GiftManager {
    private static final List<Gift> GIFTS = List.of(
            new Gift("Insecte mort", "C'est l'intention qui compte.", Gift.Rarity.COMMON),
            new Gift("Os luisant", "Un cadeau de votre ami gothique.", Gift.Rarity.COMMON),
            new Gift("Origami", "Ce fut compliqué à plier mais c'est fait !", Gift.Rarity.COMMON),
            new Gift("Papillon dans un cadre", "C'est une jolie décoration !", Gift.Rarity.COMMON),

            new Gift("Glace à la fraise", "Il ne restait que à la fraise.", Gift.Rarity.UNCOMMON),
            new Gift("Cookie magique", "En le mangeant, vous deviendrez meilleur !", Gift.Rarity.UNCOMMON),
            new Gift("Dessin", "Un très beau dessin de la part de votre ami/e.", Gift.Rarity.UNCOMMON),
            new Gift("Lingot d'or", "Ok, j'avoue c'est pas du vrai or mais ça brille !", Gift.Rarity.UNCOMMON),

            new Gift("Chapeau arc en ciel", "On dirait qu'il va falloir discuter avec votre ami.", Gift.Rarity.RARE),
            new Gift("Diamond Pickaxe", "J'ai miné du diamant exprès pour cette pioche !", Gift.Rarity.RARE),
            new Gift("Oeuf de Dragon", "Vous aurez un compagnon rien qu'à vous !", Gift.Rarity.RARE),

            new Gift("Peluche licorne", "C'est une peluche magique qui possède le pouvoir de créer des paillettes dans votre vie.", Gift.Rarity.VERY_RARE),
            new Gift("La tenue de vos rêves", "On vous a confondu avec une Barbie mais au moins, vous avez la classe !", Gift.Rarity.VERY_RARE),

            new Gift("Nuit de rêve à l'hôtel de rêve", "On dirait que celui ou celle qui vous a offert cela souhaite passer une nuit amicale avec vous.", Gift.Rarity.MYTHICAL),
            new Gift("Étoile", "Vous êtes aussi charismatique qu'une étoile.", Gift.Rarity.MYTHICAL),

            new Gift("Cerveau", "C'est utile au fonctionnement humain.", Gift.Rarity.LEGENDARY)

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
        } else if (roll < 0.85) { // 15 % pour des cadeaux rares
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.RARE)
                    .findAny()
                    .orElseThrow();
        } else if (roll < 0.92) { // 8 % pour des cadeaux très rares
            return GIFTS.stream().filter(g -> g.getRarity() == Gift.Rarity.VERY_RARE)
                    .findAny()
                    .orElseThrow();
        } else if (roll < 0.985) { // 1.5 % pour des cadeaux mythiques
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