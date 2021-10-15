package fr.grimtown.journey.data.managers;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import fr.grimtown.journey.LobbyPlugin;
import fr.grimtown.journey.data.classes.DataPlayer;

import java.util.UUID;

public class DataPlayerManager {
    private static final Datastore DATASTORE = LobbyPlugin.getDatastore(LobbyPlugin.getEvent().getDatabase());

    /**
     * Get game data of player by his UUID
     */
    public static DataPlayer getDataPlayer(UUID uuid) {
        return DATASTORE.find(DataPlayer.class)
                .filter(Filters.eq("uuid", uuid))
                .first();
    }
}
