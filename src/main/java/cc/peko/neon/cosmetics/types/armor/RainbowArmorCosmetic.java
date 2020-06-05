package cc.peko.neon.cosmetics.types.armor;

import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rip.protocol.plib.util.ItemBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RainbowArmorCosmetic extends Cosmetic {

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
        return ItemBuilder.of(Material.LEATHER_CHESTPLATE).color(Color.RED).name(getDisplayName()).setLore(getDescription()).build();
    }

    @Override
    public void apply(Player player) {

    }



}
