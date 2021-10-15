package fr.grimtown.journey.event;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.grimtown.journey.LobbyPlugin;
import fr.grimtown.journey.data.classes.DataPlayer;
import fr.grimtown.journey.data.managers.DataPlayerManager;
import fr.milekat.utils.DateMileKat;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public record Listeners(JavaPlugin plugin) implements Listener {
    /**
     *      Send player to server
     */
    private void sendPlayerToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
        if (!(event.getClickedBlock()!=null && event.getClickedBlock().getState() instanceof Sign)) return;
        if (((Sign) event.getClickedBlock().getState()).line(0).
                equals(Component.text(plugin.getConfig().getString("lobby.sign")))) {
            DataPlayer data = DataPlayerManager.getDataPlayer(event.getPlayer().getUniqueId());
            if (LobbyPlugin.getEvent().isRunning()) {
                if (data == null) sendPlayerToServer(event.getPlayer(), "Journey_Universe_1");
                else sendPlayerToServer(event.getPlayer(), "Journey_Universe_" + data.getUniverse().level);
            } else {
                if (LobbyPlugin.getEvent().getEndDate().after(new Date())) {
                    event.getPlayer().sendMessage(plugin.getConfig().getString("lobby.event-start-in")
                            .replaceAll("<REAMING>", DateMileKat.reamingToString(LobbyPlugin.getEvent().getStartDate())));
                } else {
                    event.getPlayer().sendMessage(plugin.getConfig().getString("lobby.event-finished"));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.empty());
        event.getPlayer().teleport(new Location(event.getPlayer().getWorld(),
                plugin.getConfig().getDouble("lobby.spawn.x"),
                plugin.getConfig().getDouble("lobby.spawn.y"),
                plugin.getConfig().getDouble("lobby.spawn.z"),
                0,0));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.quitMessage(Component.empty());
    }

    @EventHandler
    public void onBreak(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockPlaceEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockDamageEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }
}
