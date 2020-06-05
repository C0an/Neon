package cc.peko.neon;

import cc.peko.neon.listeners.NeonListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Neon extends JavaPlugin {

    @Getter private static Neon instance;

    @Override
    public void onEnable() {
        (instance = this).saveDefaultConfig();
        NeonConstants.setup(getConfig());
        Bukkit.getPluginManager().registerEvents(new NeonListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
