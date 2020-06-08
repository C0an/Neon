package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.player.CosmeticPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rip.protocol.plib.util.ClassUtils;

import java.util.*;

public class CosmeticHandler implements Runnable, Listener {

    @Getter private final List<Cosmetic> cosmetics = new ArrayList<>();
    @Getter private final List<CosmeticPlayer> cosmeticPlayers = new ArrayList<>();

    public void setup() {
        try {
            for (Class<?> aClass : ClassUtils.getClassesInPackage(Neon.getInstance(), "cc.peko.neon.cosmetics.types")) {
                if(!Cosmetic.class.isAssignableFrom(aClass)) continue;
                Cosmetic cosmetic = (Cosmetic) aClass.newInstance();
                cosmetics.add(cosmetic);
                Bukkit.getPluginManager().registerEvents(cosmetic, Neon.getInstance());
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(this, Neon.getInstance());
        Bukkit.getScheduler().runTaskTimer(Neon.getInstance(), this, 0, 2);
    }

    @Override
    public void run() {
        cosmeticPlayers.forEach(cosmeticPlayer -> cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.tick(Bukkit.getPlayer(cosmeticPlayer.getUuid()))));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        cosmeticPlayers.add(new CosmeticPlayer(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        cosmeticPlayers.remove(getPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        cosmeticPlayers.remove(getPlayer(event.getPlayer()));
    }


    public CosmeticPlayer getPlayer(UUID uuid) {
        return cosmeticPlayers.stream().filter(cosmeticPlayer -> cosmeticPlayer.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public CosmeticPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }
}
