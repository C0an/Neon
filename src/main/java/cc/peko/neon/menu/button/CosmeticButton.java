package cc.peko.neon.menu.button;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import cc.peko.neon.cosmetics.player.CosmeticPlayer;
import lombok.AllArgsConstructor;
import net.frozenorb.qlib.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CosmeticButton extends Button {

    private final CosmeticType cosmeticType;
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
        return Material.DIRT;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        CosmeticPlayer cosmeticPlayer = Neon.getInstance().getCosmeticHandler().getPlayer(player);
        ItemStack itemStack = cosmetic.getIcon().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> loreList = new ArrayList<>();
        loreList.add(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------------------");
        loreList.addAll(cosmetic.getDescription());
        loreList.add("");
        loreList.add(cosmetic.hasPermission(player) ? (cosmeticPlayer.getSelectedCosmetics().contains(cosmetic) ? ChatColor.RED + "Click to deselect this Cosmetic!" : ChatColor.GREEN + "Click to select this Cosmetic!") : ChatColor.RED + "You do not have permission for this cosmetic!");
        loreList.add(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------------------");

        itemMeta.setDisplayName(cosmetic.getDisplayName());
        itemMeta.setLore(loreList);
        itemStack.setItemMeta(itemMeta);

        if(cosmeticPlayer.isSelected(cosmetic)) itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        return itemStack;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType) {
        CosmeticPlayer cosmeticPlayer = Neon.getInstance().getCosmeticHandler().getPlayer(player);

        if(!cosmetic.hasPermission(player)) {
            player.sendMessage(NeonConstants.getNoPermission());
            return;
        }

        Cosmetic selectedFromCategory = cosmeticPlayer.getCosmeticFromCategory(cosmeticType);
        if(selectedFromCategory == null) {
            if(cosmeticPlayer.isSelected(cosmetic)) cosmetic.remove(player);
            else cosmeticPlayer.selectCosmetic(cosmetic);
        }else {
            if(selectedFromCategory == cosmetic) {
                cosmetic.remove(player);
            }else {
                selectedFromCategory.remove(player);
                cosmeticPlayer.selectCosmetic(cosmetic);
            }
        }

    }
}
