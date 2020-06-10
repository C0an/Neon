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

public class MusicOrbParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Music Orb";
    }

    @Override
    public String getDisplayName() {
        return "Music Orb";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.musicorb";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "You surround yourself with the", ChatColor.WHITE + "fresh new beats and share it with others!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.NOTE_BLOCK);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable =new BukkitRunnable(){
            double phi = 0;
            Location loc = player.getLocation();
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                phi +=  Math.PI / 10;

                for(double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {
                    double r = 1.5;
                    double x = r*Math.cos(theta)*Math.sin(phi);
                    double y = r*Math.cos(phi)+1.5;
                    double z = r*Math.sin(theta)*Math.sin(phi);
                    loc.add(x, y, z);
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("note",
                            (float) loc.getX() ,
                            (float) loc.getY(),
                            (float) loc.getZ(),
                            0, 0, 0,
                            1, 0);
                    getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));
                    loc.subtract(x, y, z);
                }

//                double x, y, z;
//
//                Location location1 = player.getLocation();
//                for (double t = 0; t <= 2 * Math.PI; t = t + Math.PI / 16) {
//                    for (double i = 0; i <= 1; i = i + 1) {
//                        x = 0.4 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
//                        y = 0.5 * t;
//                        z = 0.4 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
//                        location1.add(x, y, z);
//
//                        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("reddust",
//                                        (float) location1.getX() ,
//                                        (float) location1.getY(),
//                                        (float) location1.getZ(),
//                                        255, 0, 0,
//                                        1, 0);
//                        getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));
//                        location1.subtract(x, y, z);
//                    }
//
//                }

            }
        };
        runnable.runTaskTimer(Neon.getInstance(), 0, 2);
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
