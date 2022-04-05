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

public class LavaRingParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Lava Ring";
    }

    @Override
    public String getDisplayName() {
        return "Lava Ring";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission("neon.cosmetic.lavaring");
    }

    @Override
    public boolean noPermissionHide() {
        return false;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "Did someone turn the heating up?", ChatColor.WHITE + "Summon a burning hot ring around you!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BLAZE_POWDER);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable = new BukkitRunnable() {
            private double radius = 1.5;
            private double angle = Math.PI;

            @Override
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                double x = (radius * Math.sin(angle));
                double z = (radius * Math.cos(angle));
                angle -= 0.1;

                Location loc = new Location(player.getWorld(), player.getLocation().getX() + x, player.getLocation().getY(), player.getLocation().getZ() + z);

                double y = loc.getY();
                for(int i = 0; i < 4; i++) {
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("dripLava",
                            (float) loc.getX() ,
                            (float) y,
                            (float) loc.getZ(),
                            0, 0, 0,
                            5, 0);
                    y = y+0.5;

                    getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));

                }



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
