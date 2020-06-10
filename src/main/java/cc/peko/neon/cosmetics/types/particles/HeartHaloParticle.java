package cc.peko.neon.cosmetics.types.particles;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HeartHaloParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Heart Halo";
    }

    @Override
    public String getDisplayName() {
        return "Heart Halo";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.hearthalo";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "Fall in love with the hearts", ChatColor.WHITE + "that follows your head!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GOLDEN_APPLE);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable = new BukkitRunnable() {
            private double radius = 0.5;
            private double angle = Math.PI;

            @Override
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                double x = (radius * Math.sin(angle));
                double z = (radius * Math.cos(angle));
                angle -= 0.1;

                Location loc = new Location(player.getWorld(), player.getLocation().getX() + x, player.getLocation().getY(), player.getLocation().getZ() + z);

                double y = loc.getY() + 2;

                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("heart",
                        (float) loc.getX() ,
                        (float) y,
                        (float) loc.getZ(),
                        0, 0, 0,
                        1, 0);
                getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));
            }
        };
        runnable.runTaskTimer(Neon.getInstance(), 0, 1);
        ringMap.put(player.getUniqueId(), runnable.getTaskId());
    }

    @Override
    public void tick(Player player) {

    }

    @Override
    public void remove(Player player) {
        unselectCosmetic(player);
        Bukkit.getScheduler().cancelTask(ringMap.remove(player.getUniqueId()));
    }
}
