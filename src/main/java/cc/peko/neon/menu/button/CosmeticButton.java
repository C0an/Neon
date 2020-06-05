package cc.peko.neon.menu.button;

import cc.peko.neon.cosmetics.ICosmetic;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.protocol.plib.menu.Button;

import java.util.List;

@AllArgsConstructor
public class CosmeticButton extends Button {

    private ICosmetic cosmetic;

    @Override
    public String getName(Player player) {
        return cosmetic.getDisplayName();
    }

    @Override
    public List<String> getDescription(Player player) {
        return null;
    }

    @Override
    public Material getMaterial(Player player) {
        return null;
    }

}
