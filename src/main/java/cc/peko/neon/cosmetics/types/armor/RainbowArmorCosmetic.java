package cc.peko.neon.cosmetics.types.armor;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import net.frozenorb.qlib.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;

public class RainbowArmorCosmetic extends Cosmetic {

    private static List<Color> colorList = Arrays.asList(
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
    static int lastSelected = 1;

    public RainbowArmorCosmetic() {
        Bukkit.getScheduler().runTaskTimer(Neon.getInstance(), () -> {
            if(lastSelected >= colorList.size() - 1) lastSelected = 0;
            else lastSelected++;
        }, 0, 1);
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
    public boolean hasPermission(Player player) {
        return player.hasPermission("neon.cosmetic.rainbowarmor");
    }
    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "Stand out from the crowd and look", ChatColor.WHITE + "snazzy with your rainbow armor!");
    }

    @Override
    public ItemStack getIcon() {
        return ItemBuilder.of(Material.LEATHER_CHESTPLATE).color(getColor()).build();
    }

    @Override
    public void apply(Player player) {
    }

    @Override
    public void tick(Player player) {
        if(player == null || !player.isOnline()) return;

        Color color = getColor();

        ItemStack helmet = getColorArmor(Material.LEATHER_HELMET, color);
        ItemStack chestplate = getColorArmor(Material.LEATHER_CHESTPLATE, color);
        ItemStack leggings = getColorArmor(Material.LEATHER_LEGGINGS, color);
        ItemStack boots = getColorArmor(Material.LEATHER_BOOTS, color);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        player.updateInventory();
    }

    @Override
    public boolean noPermissionHide() {
        return false;
    }

    @Override
    public void remove(Player player) {
        unselectCosmetic(player);
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    public static Color getColor() {
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
