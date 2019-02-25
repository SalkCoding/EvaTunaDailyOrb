package net.evatunadailyorb.salkcoding.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.main.Main;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSave {

    public static void writeCheatingLog(Player player, String log, int length) throws IOException {
        File dir = new File(Main.getInstance().getDataFolder(), "Anti-cheating Log");
        if (!dir.exists())
            dir.mkdirs();

        Gson gson = new Gson();
        JsonObject json = new JsonObject();

        json.addProperty("Player name", player.getName());
        json.addProperty("Player UUID", player.getUniqueId().toString());
        json.addProperty("Enter and quit times", log);
        json.addProperty("Total re-entering try count", length);

        FileWriter writer = new FileWriter(new File(dir, player.getUniqueId().toString() + ".json"));
        writer.write(gson.toJson(json));
        writer.flush();
        writer.close();

        System.out.println(Constants.Console + Constants.Warn_Format + player.getName() + " is suspected with breaking abuse the plugin bug");
        System.out.println(Constants.Console + Constants.Warn_Format + "If you want more information about this situation, check the Anti-cheating log folder");
    }

    public static void writeStatistics(int hunting, int mining, int logging, int fishing, int breeding, int furnace, int farming) throws IOException {//귀찮아 막 적을꺼야...
        File statistics = new File(Constants.statisticsDir, Constants.JSON_STATISTICS + ".json");

        if (!Constants.settingDir.exists())
            Constants.settingDir.mkdirs();

            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            if(!statistics.exists())
                statistics.createNewFile();

            json.addProperty(Constants.JSON_STATISTICS_BREEDING, breeding);
            json.addProperty(Constants.JSON_STATISTICS_FARMING, farming);
            json.addProperty(Constants.JSON_STATISTICS_FISHING, fishing);
            json.addProperty(Constants.JSON_STATISTICS_FURNACE, furnace);
            json.addProperty(Constants.JSON_STATISTICS_LOGGING, logging);
            json.addProperty(Constants.JSON_STATISTICS_MINING, mining);
            json.addProperty(Constants.JSON_STATISTICS_HUNTING, hunting);

            FileWriter writer = new FileWriter(statistics);
            writer.write(gson.toJson(json));
            writer.flush();
            writer.close();
            System.out.println(Constants.Info_Format + statistics.getName() + " saved");
    }

}
