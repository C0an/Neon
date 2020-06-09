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

public class FlameRingParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Flame Ring";
    }

    @Override
    public String getDisplayName() {
        return "Flame Ring";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.flamering";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Burn.... BURN!", "Summon a hot ring around", "yourself to make stand out!");
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
                System.out.println("Helo we r runing");

                double x = (radius * Math.sin(angle));
                double z = (radius * Math.cos(angle));
                angle -= 0.1;
                Location loc = new Location(player.getWorld(), player.getLocation().getX() + x, player.getLocation().getY(), player.getLocation().getZ() + z);
                player.getWorld().playEffect(loc, Effect.HAPPY_VILLAGER, 1);

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
