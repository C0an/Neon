package cc.peko.neon.cosmetics.player;

import cc.peko.neon.cosmetics.Cosmetic;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class CosmeticPlayer {

    private final UUID uuid;
    private final List<Cosmetic> selectedCosmetics = new ArrayList<>();

    public CosmeticPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isSelected(Cosmetic cosmetic) {
        return selectedCosmetics.contains(cosmetic);
    }

    public boolean selectCosmetic(Cosmetic cosmetic) {
        getPlayer().sendMessage(ChatColor.YELLOW + "You have selected the " + cosmetic.getDisplayName() + ChatColor.YELLOW + " cosmetic.");
        cosmetic.apply(getPlayer());
        return selectedCosmetics.add(cosmetic);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}
