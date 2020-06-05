package cc.peko.neon.cosmetics;

import cc.peko.neon.Neon;
import lombok.Getter;
import org.bukkit.Bukkit;
import rip.protocol.plib.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public class CosmeticHandler {

    @Getter private static final List<Cosmetic> cosmetics = new ArrayList<>();

    public static void setup() {
        try {
            for (Class<?> aClass : ClassUtils.getClassesInPackage(Neon.getInstance(), "cc.peko.neon.cosmetics")) {
                if(!Cosmetic.class.isAssignableFrom(aClass)) continue;
                Cosmetic cosmetic = (Cosmetic) aClass.newInstance();
                cosmetics.add(cosmetic);
                Bukkit.getPluginManager().registerEvents(cosmetic, Neon.getInstance());
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

}
