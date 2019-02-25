package net.evatunadailyorb.salkcoding.Statistics;

import com.google.gson.JsonObject;
import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.file.JsonLoad;
import net.evatunadailyorb.salkcoding.file.JsonSave;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class Statistics {

    private static int hunting = 0;
    private static int mining = 0;
    private static int farming = 0;
    private static int fishing = 0;
    private static int breeding = 0;
    private static int logging = 0;
    private static int smelting = 0;

    public static void load() throws IOException {
        JsonObject json = JsonLoad.LoadJson(Constants.statisticsDir, Constants.JSON_STATISTICS);
        hunting = json.get(Constants.JSON_STATISTICS_HUNTING).getAsInt();
        mining = json.get(Constants.JSON_STATISTICS_MINING).getAsInt();
        farming = json.get(Constants.JSON_STATISTICS_FARMING).getAsInt();
        fishing = json.get(Constants.JSON_STATISTICS_FISHING).getAsInt();
        breeding = json.get(Constants.JSON_STATISTICS_BREEDING).getAsInt();
        logging = json.get(Constants.JSON_STATISTICS_LOGGING).getAsInt();
        smelting = json.get(Constants.JSON_STATISTICS_FURNACE).getAsInt();
    }

    public static void save() throws IOException {
        JsonSave.writeStatistics(hunting, mining, logging, fishing, breeding, smelting, farming);
    }

    public static void addBreeding(int count) {
        breeding += count;
    }

    public static void addFarming(int count) {
        farming += count;
    }

    public static void addFishing(int count) {
        fishing += count;
    }

    public static void addHunting(int count) {
        hunting += count;
    }

    public static void addLogging(int count) {
        logging += count;
    }

    public static void addMining(int count) {
        mining += count;
    }

    public static void addSmelting(int count) {
        smelting += count;
    }

    public static void printToPlayer(CommandSender sender) {
        sender.sendMessage(Constants.Info_Format + "Created hunting orb : " + hunting);
        sender.sendMessage(Constants.Info_Format + "Created mining orb : " + mining);
        sender.sendMessage(Constants.Info_Format + "Created logging orb : " + logging);
        sender.sendMessage(Constants.Info_Format + "Created farming orb : " + farming);
        sender.sendMessage(Constants.Info_Format + "Created fishing orb : " + fishing);
        sender.sendMessage(Constants.Info_Format + "Created smelting orb : " + smelting);
        sender.sendMessage(Constants.Info_Format + "Created breeding orb : " + breeding);
    }
}
