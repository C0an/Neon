package cc.peko.neon.menu.button;

import cc.peko.neon.cosmetics.Cosmetic;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.protocol.plib.menu.Button;

import java.util.List;

@AllArgsConstructor
public class CosmeticButton extends Button {

    private final Cosmetic cosmetic;

    @Override
    public String getName(Player player) {
        return cosmetic.getDisplayName();
    }

    @Override
    public List<String> getDescription(Player player) {
        return cosmetic.getDescription();
    }

    @Override
    public Material getMaterial(Player player) {
        return cosmetic.getIcon().getType();
    }

    @Override
    public byte getDamageValue(Player player) {
        return (byte) cosmetic.getIcon().getDurability();
    }

    @Override
    public int getAmount(Player player) {
        return cosmetic.getIcon().getAmount();
    }
}
