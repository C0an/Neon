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

public class SparkOrbParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Spark Orb";
    }

    @Override
    public String getDisplayName() {
        return "Spark Orb";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission("neon.cosmetic.sparkorb");
    }

    @Override
    public boolean noPermissionHide() {
        return false;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "Electricity flows through and around you!", ChatColor.WHITE + "Shock others with your powerful orb.");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.FIREWORK);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable =new BukkitRunnable(){
            double phi = 0;
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();
                Location loc = player.getLocation();

                phi +=  Math.PI / 10;

                for(double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {
                    double r = 1.5;
                    double x = r*Math.cos(theta)*Math.sin(phi);
                    double y = r*Math.cos(phi)+1.5;
                    double z = r*Math.sin(theta)*Math.sin(phi);
                    loc.add(x, y, z);
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("crit",
                            (float) loc.getX() ,
                            (float) loc.getY(),
                            (float) loc.getZ(),
                            0, 0, 0,
                            1, 0);
                    getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));
                    loc.subtract(x, y, z);
                }
            }
        };
        runnable.runTaskTimer(Neon.getInstance(), 0, 6);
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
