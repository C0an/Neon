package cc.peko.neon.listeners;

import cc.peko.neon.Neon;
import cc.peko.neon.NeonConstants;
import cc.peko.neon.menu.CosmeticMenu;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import rip.protocol.plib.util.TimeUtil;

public class NeonListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!NeonConstants.isEnabled()) return;
        Bukkit.getScheduler().runTaskLater(Neon.getInstance(), () -> {
            player.getInventory().setItem(NeonConstants.getItemSlot(), NeonConstants.getCosmeticItem());
            player.updateInventory();
        }, NeonConstants.getGiveDelay() * 20);

        player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------------------------------------");
        player.sendMessage(ChatColor.YELLOW + "Welcome, " + player.getDisplayName() + ChatColor.YELLOW + "to the Yes Network!");
        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "Website: " + ChatColor.WHITE + "www.protocol.rip");
        player.sendMessage(ChatColor.YELLOW + "Store: " + ChatColor.WHITE + "store.protocol.rip");
        player.sendMessage(ChatColor.YELLOW + "TeamSpeak: " + ChatColor.WHITE + "ts.protocol.rip");
        player.sendMessage("");
        FancyMessage fancyMessage = new FancyMessage("Announcement: ").color(ChatColor.YELLOW);
        fancyMessage
                .then(Neon.getInstance().getForumPost().getTitle())
                .color(ChatColor.WHITE)
                .link(Neon.getInstance().getForumPost().getDirectURL())
                .tooltip(
                        ChatColor.YELLOW + "Author: " + ChatColor.WHITE + Neon.getInstance().getForumPost().getAuthor(),
                        ChatColor.YELLOW + "Posted " + ChatColor.WHITE + TimeUtil.millisToRoundedTime(Neon.getInstance().getForumPost().getCreatedAt()) + ChatColor.YELLOW + " ago",
                        "",
                        ChatColor.GREEN + "Click to read the post!"
                );
        fancyMessage.send(player);
        player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------------------------------------");

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
