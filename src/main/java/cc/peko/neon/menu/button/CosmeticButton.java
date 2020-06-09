package cc.peko.neon.menu.button;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import cc.peko.neon.cosmetics.player.CosmeticPlayer;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import rip.protocol.plib.menu.Button;

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

        if(cosmeticPlayer.isSelected(cosmetic)) itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        return itemStack;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType) {
        CosmeticPlayer cosmeticPlayer = Neon.getInstance().getCosmeticHandler().getPlayer(player);

        if(!player.hasPermission(cosmetic.getPermission())) {
            player.sendMessage(ChatColor.RED + "You do not have permission to this cosmetic.");
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
                cosmetic.remove(player);
                cosmeticPlayer.selectCosmetic(cosmetic);
            }
        }

    }
}
