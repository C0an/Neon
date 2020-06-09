package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @Getter
public enum CosmeticType {

    ARMOR("Armor", Material.LEATHER_CHESTPLATE),
    PETS("Pets", Material.BONE),
    GADGET("Gadgets", Material.BEACON),
    PARTICLE("Particles", Material.BLAZE_POWDER);

    private final String displayName;
    private final Material icon;

    public List<Cosmetic> getCosmetics() {
        return Neon.getInstance().getCosmeticHandler().getCosmetics().stream().filter(iCosmetic -> iCosmetic.getCosmeticType() == this).collect(Collectors.toList());
    }

}
