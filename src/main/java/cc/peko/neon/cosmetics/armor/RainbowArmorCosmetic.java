package cc.peko.neon.cosmetics.armor;

import cc.peko.neon.cosmetics.CosmeticType;
import cc.peko.neon.cosmetics.ICosmetic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RainbowArmorCosmetic implements ICosmetic {

    @Override
    public String getName() {
        return "Rainbow Armor";
    }

    @Override
    public String getDisplayName() {
        return "Rainbow Armor";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.ARMOR;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.rainbowarmor";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Stand out from the crowd", "and look snazzy with your", "rainbow armor!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LEATHER_CHESTPLATE);
    }

    @Override
    public void apply(Player player) {

    }



}
