package cc.peko.neon.cosmetics;

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
    OTHER("Other", Material.RAILS);

    private final String displayName;
    private final Material icon;

    public List<ICosmetic> getCosmetics() {
        return CosmeticHandler.getCosmetics().stream().filter(iCosmetic -> iCosmetic.getCosmeticType() == this).collect(Collectors.toList());
    }

}
