package cc.peko.neon.command;

import cc.peko.neon.NeonConstants;
import org.bukkit.entity.Player;
import rip.protocol.plib.command.Command;

public class TestCommand {

    @Command(names = "test", permission = "")
    public static void test(Player player) {
        player.getInventory().addItem(NeonConstants.getCosmeticItem());
    }

}
