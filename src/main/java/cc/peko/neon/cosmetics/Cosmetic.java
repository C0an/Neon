package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
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

    public void unselectCosmetic(Player player, boolean sendMessage) {
        if(sendMessage) player.sendMessage(NeonConstants.getUnselectedCosmetic().replace("%displayName%", getDisplayName()));
        Neon.getInstance().getCosmeticHandler().getPlayer(player).getSelectedCosmetics().remove(this);
    }

    public void unselectCosmetic(Player player) {
        unselectCosmetic(player, true);
    }

    public boolean hasSelected(Player player) {
        return Neon.getInstance().getCosmeticHandler().getPlayer(player).isSelected(this);
    }

}
