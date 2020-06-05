package cc.peko.neon.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ICosmetic extends Listener {

    String getName();
    String getDisplayName();
    CosmeticType getCosmeticType();
    String getPermission();
    List<String> getDescription();

    ItemStack getIcon();

    void apply(Player player);

}
