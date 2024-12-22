package fr.raito.Managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.raito.Models.Gift;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftDexManager {
    private static final String SAVE_FILE = "giftDex.json"; // Fichier de stockage
    private static final Map<String, List<Gift>> giftDex = new HashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Charger les données à partir du fichier JSON
    static {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try {
                Map<String, List<Gift>> savedData = objectMapper.readValue(file, new TypeReference<>() {});
                giftDex.putAll(savedData);
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement des données : " + e.getMessage());
            }
        }
    }

    // Ajouter un cadeau pour un utilisateur
    public static void addGift(User user, Gift gift) {
        String userId = user.getId();
        giftDex.computeIfAbsent(userId, k -> new ArrayList<>()).add(gift);
        saveToFile(); // Sauvegarder après ajout
    }

    // Récupérer les cadeaux d'un utilisateur
    public static List<Gift> getGifts(User user) {
        return giftDex.getOrDefault(user.getId(), new ArrayList<>());
    }

    // Sauvegarder les données dans le fichier JSON
    private static void saveToFile() {
        try {
            objectMapper.writeValue(new File(SAVE_FILE), giftDex);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }
}