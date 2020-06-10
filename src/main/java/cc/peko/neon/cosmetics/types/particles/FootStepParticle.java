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

public class FootStepParticle extends Cosmetic {

    private final Map<UUID, Integer> ringMap = new HashMap<>();

    @Override
    public String getName() {
        return "Foot Steps";
    }

    @Override
    public String getDisplayName() {
        return "Foot Steps";
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    @Override
    public String getPermission() {
        return "neon.cosmetic.footstep";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(ChatColor.WHITE + "Trace your steps and find", ChatColor.WHITE + "your way back to where you started!");
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CHAINMAIL_BOOTS);
    }

    @Override
    public void apply(Player player) {
        BukkitRunnable runnable = new BukkitRunnable() {

            Location loc = player.getLocation();

            @Override
            public void run() {
                if(player == null || !player.isOnline()) this.cancel();

                if(loc == player.getLocation()) return;
                else loc = player.getLocation();

                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("footstep",
                        (float) loc.getX() ,
                        (float) loc.getY(),
                        (float) loc.getZ(),
                        0, 0, 0,
                        1, 0);

                getNearbyPlayers(player, 50, false).stream().map(entity -> (CraftPlayer)entity).forEach(craftPlayer -> craftPlayer.getHandle().playerConnection.sendPacket(packet));
            }
        };
        runnable.runTaskTimer(Neon.getInstance(), 0, 4);
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
