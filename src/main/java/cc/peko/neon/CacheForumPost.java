package cc.peko.neon;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
            StringBuilder sb = new StringBuilder();

            String lineRead;
            int profileViews = 0;
            while((lineRead = bufferedReader.readLine())!=null){
                sb.append(lineRead);
            }

            ForumPost forumPost = new Gson().fromJson(sb.toString(), ForumPost.class);
            Neon.getInstance().setForumPost(forumPost);
             }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
