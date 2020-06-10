package cc.peko.neon;

import cc.peko.neon.cosmetics.CosmeticHandler;
import cc.peko.neon.cosmetics.player.CosmeticPlayer;
import cc.peko.neon.listeners.NeonListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Neon extends JavaPlugin {

    @Getter private static Neon instance;
    @Getter private CosmeticHandler cosmeticHandler;
    @Getter @Setter private ForumPost forumPost;

    @Override
    public void onEnable() {
        (instance = this).saveDefaultConfig();
        NeonConstants.setup(getConfig());
        (cosmeticHandler = new CosmeticHandler()).setup();
        Bukkit.getPluginManager().registerEvents(new NeonListener(), this);
        new CacheForumPost().runTaskTimerAsynchronously(this, 0, 30 * 20);
    }

    @Override
    public void onDisable() {
        cosmeticHandler.getCosmeticPlayers().forEach(cosmeticPlayer -> cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.remove(cosmeticPlayer.getPlayer())));
        instance = null;
    }

}
