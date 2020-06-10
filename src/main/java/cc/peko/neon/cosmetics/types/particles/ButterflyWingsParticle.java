package cc.peko.neon.cosmetics.types.particles;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class ButterflyWingsParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Butterfly Wings";
    }

    @Override
    public String getDisplayName() {
        return "Butterfly Wings";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.butterflywings";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "come up with a creative lore!", ChatColor.WHITE + "yes ");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.MONSTER_EGG);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable =new BukkitRunnable(){
            double phi = 0;
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                final Location loc = player.getEyeLocation().subtract(0.0, 0.3, 0.0);
                loc.setPitch(0.0f);
                loc.setYaw(player.getEyeLocation().getYaw());
                final Vector v1 = loc.getDirection().normalize().multiply(-0.3);
                v1.setY(0);
                loc.add(v1);

                for (double i = -10.0; i < 6.2; i += 0.2) {
                    final double var = Math.sin(i / 12.0);
                    double v = Math.exp(Math.cos(i)) - 2.0 * Math.cos(4.0 * i) - Math.pow(var, 5.0);
                    final double x = Math.sin(i) * v / 2.0;
                    final double z = Math.cos(i) * v / 2.0;
                    final Vector v2 = new Vector(-x, 0.0, -z);
                    rotateAroundAxisX(v2, (loc.getPitch() + 90.0f) * 0.017453292f);
                    rotateAroundAxisY(v2, -loc.getYaw() * 0.017453292f);

                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("reddust",
                            (float) loc.clone().add(v2).getX() ,
                            (float) loc.clone().add(v2).getY(),
                            (float) loc.clone().add(v2).getZ(),
                            0, 0, 255,
                            1, 0);

                    getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));

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

    public Vector rotateAroundAxisX(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }
}
