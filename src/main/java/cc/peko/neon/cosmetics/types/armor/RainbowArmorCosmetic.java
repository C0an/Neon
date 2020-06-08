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
        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        player.updateInventory();
    }

    @Override
    public void tick(Player player) {
        if(player.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        else player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        player.updateInventory();
    }

    @Override
    public void remove(Player player) {
        unselectCosmetic(player);
        player.getInventory().setArmorContents(new ItemStack[0]);
        player.updateInventory();
    }


}
