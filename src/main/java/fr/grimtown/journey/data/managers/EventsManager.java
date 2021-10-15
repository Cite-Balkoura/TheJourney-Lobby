package fr.grimtown.journey.data.managers;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import fr.grimtown.journey.LobbyPlugin;
import fr.grimtown.journey.data.classes.Event;

public class EventsManager {
    private static final Datastore DATASTORE = LobbyPlugin.getDatastore("master");

    /**
     * Get an Event by his name
     */
    public static Event getEvent(String eventName) {
        return DATASTORE.find(Event.class)
                .filter(Filters.eq("name", eventName))
                .first();
    }
}
