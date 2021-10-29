package fr.grimtown.journey.data.managers;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import fr.grimtown.journey.LobbyPlugin;
import fr.grimtown.journey.data.classes.DataPlayer;

import java.util.UUID;

public class DataPlayerManager {
    private static final Datastore DATASTORE = LobbyPlugin.getDatastore(LobbyPlugin.getEvent().getDatabase());

    public static boolean hasData(UUID uuid) {
        return DATASTORE.find(DataPlayer.class)
                .filter(Filters.eq("uuid", uuid))
                .first()!=null;
    }

    /**
     * Get game data of player by his UUID
     */
    public static DataPlayer getDataPlayer(UUID uuid) {
        return DATASTORE.find(DataPlayer.class)
                .filter(Filters.eq("uuid", uuid))
                .first();
    }

    /**
     * Save a new Progression or save an existing if finished
     */
    public static void save(DataPlayer dataPlayer) {
        DATASTORE.save(dataPlayer);
    }


    /**
     * Update the lastNameSeen of uuid
     */
    public static void updateUsername(UUID uuid, String lastNameSeen) {
        DATASTORE.find(DataPlayer.class)
                .filter(Filters.eq("uuid", uuid))
                .update(UpdateOperators.set("lastNameSeen", lastNameSeen))
                .execute();
    }

}
