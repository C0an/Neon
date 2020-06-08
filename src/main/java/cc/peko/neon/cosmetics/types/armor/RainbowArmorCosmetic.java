package cc.peko.neon.cosmetics.types.armor;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import rip.protocol.plib.util.ItemBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RainbowArmorCosmetic extends Cosmetic {

    private List<Color> colorList = Arrays.asList(
            Color.fromRGB(255, 0,0),
            Color.fromRGB(255, 68,0),
            Color.fromRGB(255, 111,0),
            Color.fromRGB(255, 171,0),
            Color.fromRGB(255, 255,0),
            Color.fromRGB(188, 255,0),
            Color.fromRGB(128, 255,0),
            Color.fromRGB(43, 255, 0),
            Color.fromRGB(0, 255,9),
            Color.fromRGB(0, 255,51),
            Color.fromRGB(0, 255, 111),
            Color.fromRGB(0, 255, 162),
            Color.fromRGB(0, 255, 230),
            Color.fromRGB(0, 239, 255),
            Color.fromRGB(0, 196, 255),
            Color.fromRGB(0, 173, 255),
            Color.fromRGB(0, 162, 255),
            Color.fromRGB(0, 137, 255),
            Color.fromRGB(0, 100, 255),
            Color.fromRGB(0, 77, 255),
            Color.fromRGB(0, 34, 255),
            Color.fromRGB(17, 0, 255),
            Color.fromRGB(37, 0, 255),
            Color.fromRGB(68, 0, 255),
            Color.fromRGB(89, 0, 255),
            Color.fromRGB(102, 0, 255),
            Color.fromRGB(124, 0, 255),
            Color.fromRGB(154, 0, 255),
            Color.fromRGB(222, 0, 255),
            Color.fromRGB(255, 0, 247),
            Color.fromRGB(255, 0, 247),
            Color.fromRGB(255, 0, 179),
            Color.fromRGB(255, 0, 128)
    );
    int lastSelected = 1;

    public RainbowArmorCosmetic() {
        Bukkit.getScheduler().runTaskTimer(Neon.getInstance(), () -> {
            if(lastSelected >= colorList.size()) lastSelected = 1;
            else lastSelected++;
        }, 0, 2L);
    }

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

    @Override
    public void tick(Player player) {
        Color color = getColor();
        player.getInventory().setHelmet(getColorArmor(Material.LEATHER_HELMET, color));
        player.getInventory().setChestplate(getColorArmor(Material.LEATHER_CHESTPLATE, color));
        player.getInventory().setLeggings(getColorArmor(Material.LEATHER_LEGGINGS, color));
        player.getInventory().setBoots(getColorArmor(Material.LEATHER_BOOTS, color));
    }

    @Override
    public void remove(Player player) {
        unselectCosmetic(player);
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    public Color getColor() {
        return colorList.get(lastSelected);
    }

    public ItemStack getColorArmor(Material m, Color c) {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setColor(c);
        i.setItemMeta(meta);
        return i;
    }

}
