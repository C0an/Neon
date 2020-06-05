package cc.peko.neon.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Cosmetic implements Listener {

    public abstract String getName();
    public abstract String getDisplayName();
    public abstract CosmeticType getCosmeticType();
    public abstract String getPermission();
    public abstract List<String> getDescription();

    public abstract ItemStack getIcon();

    public abstract void apply(Player player);

}
