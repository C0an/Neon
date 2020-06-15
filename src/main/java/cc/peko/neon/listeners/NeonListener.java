package cc.peko.neon.listeners;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
import cc.peko.neon.menu.CosmeticMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class NeonListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!NeonConstants.isEnabled()) return;
        Bukkit.getScheduler().runTaskLater(Neon.getInstance(), () -> {
            player.getInventory().setItem(NeonConstants.getItemSlot(), NeonConstants.getCosmeticItem());
            player.updateInventory();
        }, NeonConstants.getGiveDelay() * 20);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if(itemStack == null || itemStack.getType() == Material.AIR || !itemStack.isSimilar(NeonConstants.getCosmeticItem())) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(Neon.getInstance().getCosmeticHandler().getPlayer(player).getAvailableCosmetics().isEmpty()) player.sendMessage(NeonConstants.getNoCosmetic());
        else new CosmeticMenu().openMenu(player);
    }

}
