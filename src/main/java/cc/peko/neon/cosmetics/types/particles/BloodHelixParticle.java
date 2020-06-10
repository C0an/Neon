package cc.peko.neon.cosmetics.types.particles;

import cc.peko.neon.Neon;
import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BloodHelixParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Blood Helix";
    }

    @Override
    public String getDisplayName() {
        return "Blood Helix";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.bloodhelix";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("The blood of your enemies surround you!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.REDSTONE);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable =new BukkitRunnable(){
            double phi = 0;
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                phi = phi + Math.PI / 8;
                double x, y, z;

                Location location1 = player.getLocation();
                for (double t = 0; t <= 2 * Math.PI; t = t + Math.PI / 16) {
                    for (double i = 0; i <= 1; i = i + 1) {
                        x = 0.4 * (2 * Math.PI - t) * 0.5 * cos(t + phi + i * Math.PI);
                        y = 0.5 * t;
                        z = 0.4 * (2 * Math.PI - t) * 0.5 * sin(t + phi + i * Math.PI);
                        location1.add(x, y, z);
                        player.getWorld().playEffect(location1, Effect.COLOURED_DUST, 1);
                        location1.subtract(x, y, z);
                    }

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
