package cc.peko.neon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class CacheForumPost extends BukkitRunnable {

    @Override
    public void run() {
        try {
            URL url = new URL("https://protocol.rip/api/announcement");
            URLConnection hc = url.openConnection();
            hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(hc.getInputStream()));
            JsonObject jsonElement = JsonParser.parseReader(bufferedReader).getAsJsonObject();
            if(!Boolean.parseBoolean(jsonElement.get("success").toString())) {
                Neon.getInstance().setForumPost(new ForumPost(jsonElement.get("message").toString(), null,  null, -1, -1));
                return;
            }
            Neon.getInstance().setForumPost(new ForumPost(jsonElement.get("title").toString(), jsonElement.get("directURL").toString(), jsonElement.get("author").toString(), Long.parseLong(jsonElement.get("createdAt").toString()), Long.parseLong(jsonElement.get("lastUpdate").toString())));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
