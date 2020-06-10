package cc.peko.neon.menu;

import cc.peko.neon.cosmetics.CosmeticType;
import cc.peko.neon.menu.button.CosmeticButton;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import rip.protocol.plib.menu.Button;
import rip.protocol.plib.menu.Menu;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CosmeticMenu extends Menu {

    @Getter private CosmeticType cosmeticType;

    public CosmeticMenu() {
        super(ChatColor.BLUE + "Cosmetics");
    }

    public CosmeticMenu(CosmeticType cosmeticType) {
        super(ChatColor.BLUE + "Cosmetics - " + cosmeticType.getDisplayName());
        this.cosmeticType = cosmeticType;
        setAutoUpdate(true);
    }

    @Override
    public Map<Integer, Button> getButtons(Player var1) {
        Map<Integer, Button> buttons = new HashMap<>();
        AtomicInteger itemPosition = new AtomicInteger(1);

        if(getCosmeticType() == null) {
            for (CosmeticType types : CosmeticType.values()) {
                if(types.getCosmetics().isEmpty()) continue;

                buttons.put(itemPosition.getAndAdd(2), new Button() {
                    @Override
                    public String getName(Player var1) {
                        return ChatColor.BLUE + types.getDisplayName();
                    }

                    @Override
                    public List<String> getDescription(Player var1) {
                        return Collections.singletonList(ChatColor.GRAY + "View all the " + types.getDisplayName() + " cosmetics!");
                    }

                    @Override
                    public Material getMaterial(Player var1) {
                        return types.getIcon();
                    }

                    @Override
                    public void clicked(Player player, int slot, ClickType clickType) {
                        player.closeInventory();
                        new CosmeticMenu(types).openMenu(player);
                    }
                });
            }
        } else getCosmeticType().getCosmetics().forEach(iCosmetic -> buttons.put(itemPosition.getAndAdd(2), new CosmeticButton(cosmeticType, iCosmetic)));

        return buttons;
    }
}
