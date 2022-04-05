package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
import cc.peko.neon.cosmetics.player.CosmeticPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Cosmetic implements Listener {

    public abstract String getName();
    public abstract String getDisplayName();
    public abstract CosmeticType getCosmeticType();
    public abstract boolean hasPermission(Player player);
    public abstract List<String> getDescription();

    public abstract ItemStack getIcon();

    public abstract void apply(Player player);
    public abstract void tick(Player player);
    public abstract void remove(Player player);

    public abstract boolean noPermissionHide();

    public void unselectCosmetic(Player player, boolean sendMessage) {
        if(sendMessage && player != null) player.sendMessage(NeonConstants.getUnselectedCosmetic().replace("%displayName%", getDisplayName()));
        Neon.getInstance().getCosmeticHandler().getPlayer(player).getSelectedCosmetics().remove(this);
        
    }

    public void unselectCosmetic(Player player) {
        unselectCosmetic(player, true);
    }

    public boolean hasSelected(Player player) {
        return Neon.getInstance().getCosmeticHandler().getPlayer(player).isSelected(this);
    }

    public List<Player> getNearbyPlayers(Player player, int radius, boolean ignoreVis) {
        List<Player> playerList = new ArrayList<>(Collections.singletonList(player));
        playerList.addAll(player.getNearbyEntities(radius, radius, radius).stream().filter(entity -> entity instanceof Player && (ignoreVis || (player.canSee((Player) entity)))).map(entity -> (Player)entity).collect(Collectors.toList()));
        return playerList;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cosmetic && ((Cosmetic)obj).getName().equals(this.getName()) && ((Cosmetic)obj).getCosmeticType() == this.getCosmeticType();
    }
}
