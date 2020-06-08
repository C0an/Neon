package cc.peko.neon.cosmetics.player;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
import cc.peko.neon.cosmetics.Cosmetic;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public void selectCosmetic(Cosmetic cosmetic) {
        getPlayer().sendMessage(NeonConstants.getSelectedCosmetic().replace("%displayName%", cosmetic.getDisplayName()));
        cosmetic.apply(getPlayer());
        selectedCosmetics.add(cosmetic);
    }

    public List<Cosmetic> getAvailableCosmetics() {
        return Neon.getInstance().getCosmeticHandler().getCosmetics().stream().filter(cosmetic -> getPlayer().hasPermission(cosmetic.getPermission())).collect(Collectors.toList());
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}
