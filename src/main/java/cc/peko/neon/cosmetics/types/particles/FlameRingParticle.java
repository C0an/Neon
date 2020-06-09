package cc.peko.neon.cosmetics.types.particles;

import cc.peko.neon.cosmetics.Cosmetic;
import cc.peko.neon.cosmetics.CosmeticType;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class FlameRingParticle extends Cosmetic {

    private final Map<UUID, Double> ringMap = new HashMap<>();

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
        ringMap.put(player.getUniqueId(), (double) 0);
    }

    @Override
    public void tick(Player player) {
        double alpha = ringMap.put(player.getUniqueId(), ringMap.get(player.getUniqueId()) + Math.PI / 16);
        Location loc = player.getLocation();
        Location firstLocation = loc.clone().add( Math.cos( alpha ), Math.sin( alpha ) + 1, Math.sin( alpha ) );
        Location secondLocation = loc.clone().add( Math.cos( alpha + Math.PI ), Math.sin( alpha ) + 1, Math.sin( alpha + Math.PI ) );
        player.playEffect(firstLocation, Effect.FLAME, 0);
        player.playEffect(secondLocation, Effect.FLAME, 0);
    }

    @Override
    public void remove(Player player) {
        unselectCosmetic(player);
        ringMap.remove(player.getUniqueId());
    }
}
