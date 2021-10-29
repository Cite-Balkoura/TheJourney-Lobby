package fr.grimtown.journey;

import dev.morphia.Datastore;
import fr.grimtown.journey.data.MongoDB;
import fr.grimtown.journey.data.classes.Event;
import fr.grimtown.journey.data.managers.EventsManager;
import fr.grimtown.journey.event.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class LobbyPlugin extends JavaPlugin {
    /* Core */
    private static Plugin plugin;
    /* Configs */
    public static boolean DEBUG_ERRORS = true;
    /* MongoDB */
    private static HashMap<String, Datastore> datastoreMap = new HashMap<>();
    /* Event */
    private static Event mcEvent;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        /* MongoDB */
        datastoreMap = MongoDB.getDatastoreMap(this.getConfig());
        /* Event load */
        mcEvent = EventsManager.getEvent(this.getConfig().getString("lobby.event-name"));
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(this), this);
    }

    /**
     * Get plugin
     */
    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * Get loaded Event shortcut
     */
    public static Event getEvent() { return mcEvent; }

    /**
     * MongoDB Connection (Morphia Datastore) to query
     */
    public static Datastore getDatastore(String dbName) {
        return datastoreMap.get(dbName).startSession();
    }
}
