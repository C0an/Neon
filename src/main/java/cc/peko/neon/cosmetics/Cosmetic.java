package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import org.bukkit.ChatColor;
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
    public abstract void tick(Player player);
    public abstract void remove(Player player);

    public boolean unselectCosmetic(Player player) {
        player.sendMessage(ChatColor.YELLOW + "You have unselected your " + getDisplayName() + ChatColor.YELLOW + " cosmetic.");
        return Neon.getInstance().getCosmeticHandler().getPlayer(player).getSelectedCosmetics().remove(this);
    }

    public boolean hasSelected(Player player) {
        return Neon.getInstance().getCosmeticHandler().getPlayer(player).isSelected(this);
    }

}
