package cc.peko.neon;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import rip.protocol.plib.util.ItemBuilder;

import java.util.stream.Collectors;

public class NeonConstants {

    public static void setup(FileConfiguration config) {
        giveDelay = config.getInt("settings.giveDelay");
        enabled = config.getInt("settings.giveDelay") != -1;
        mongoCollection = config.getString("mongo.collection");

        itemSlot = config.getInt("item.slot");
        ItemStack itemStack = ItemBuilder.of(
                Material.valueOf(config.getString("item.material")),
                config.getInt("item.amount"))
                .name(ChatColor.translateAlternateColorCodes('&', config.getString("item.name")))
                .data((short) config.getInt("item.data"))
                .setLore(config.getStringList("item.description").stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList()))
                .build();
    }

    @Getter private static int giveDelay;
    @Getter private static int itemSlot;
    @Getter private static boolean enabled;
    @Getter private static String mongoCollection;
    @Getter private static ItemStack cosmeticItem;

}
