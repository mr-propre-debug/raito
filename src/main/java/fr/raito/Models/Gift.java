package fr.raito.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gift {
    private final String name;
    private final String description;
    private final Rarity rarity;

    public enum Rarity {
        COMMON("N"), UNCOMMON("R"), RARE("SR"), VERY_RARE("SSR"), MYTHICAL("UR"), LEGENDARY("LR");

        private final String label;

        Rarity(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    // Constructeur spécifique pour Jackson, avec des annotations pour les champs JSON
    @JsonCreator
    public Gift(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("rarity") Rarity rarity
    ) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - %s", name, rarity.getLabel(), description);
    }
}