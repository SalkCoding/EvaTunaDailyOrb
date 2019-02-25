package net.evatunadailyorb.salkcoding.file;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.evatunadailyorb.salkcoding.Constants;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.io.*;

public class JsonLoad {

    private static void write(File file, String value) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(value);
        writer.flush();
        writer.close();
    }

    public static JsonObject LoadJson(File folder, String name) throws IOException {
        File dir = folder;
        File file = new File(dir, name + ".json");
        if (!dir.exists())
            dir.mkdirs();
        if (!file.exists())
            makeSample();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(builder.toString());
        return element.getAsJsonObject();
    }

    public static void makeSample() throws IOException {
        File hunting = new File(Constants.settingDir, Constants.JSON_HUNTING + ".json");
        File mining = new File(Constants.settingDir, Constants.JSON_MINING + ".json");
        File logging = new File(Constants.settingDir, Constants.JSON_LOGGING + ".json");
        File farming = new File(Constants.settingDir, Constants.JSON_FARMING + ".json");
        File breeding = new File(Constants.settingDir, Constants.JSON_BREEDING + ".json");
        File fishing = new File(Constants.settingDir, Constants.JSON_FISHING + ".json");
        File furnace = new File(Constants.settingDir, Constants.JSON_FURNACE + ".json");

        //귀찮아 막 적을꺼야...
        File statistics = new File(Constants.statisticsDir, Constants.JSON_STATISTICS + ".json");

        if (!Constants.itemDir.exists())
            Constants.itemDir.mkdirs();

        if (!Constants.statisticsDir.exists())
            Constants.statisticsDir.mkdirs();

        if (!statistics.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            statistics.createNewFile();

            json.addProperty(Constants.JSON_STATISTICS_BREEDING, 0);
            json.addProperty(Constants.JSON_STATISTICS_FARMING, 0);
            json.addProperty(Constants.JSON_STATISTICS_FISHING, 0);
            json.addProperty(Constants.JSON_STATISTICS_FURNACE, 0);
            json.addProperty(Constants.JSON_STATISTICS_LOGGING, 0);
            json.addProperty(Constants.JSON_STATISTICS_MINING, 0);
            json.addProperty(Constants.JSON_STATISTICS_HUNTING, 0);

            write(statistics, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + statistics.getName() + " created");
        }

        for (int i = 1; i <= 7; i++) {
            File file = new File(Constants.itemDir, "Null");
            switch (i) {
                case 1:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_MONDAY + ".json");
                    break;
                case 2:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_TUESDAY + ".json");
                    break;
                case 3:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_WEDNESDAY + ".json");
                    break;
                case 4:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_THURSDAY + ".json");
                    break;
                case 5:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_FRIDAY + ".json");
                    break;
                case 6:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_SATURDAY + ".json");
                    break;
                case 7:
                    file = new File(Constants.itemDir, Constants.JSON_ITEM_SUNDAY + ".json");
                    break;
            }
            if(!file.exists()) {
                write(file, "{\n" +
                        "  \"Display-name\":\"\\u0026a하아아안그르르르르으으으을 한글 English\",\n" +
                        "  \"Material-type\": \"GHAST_TEAR\",\n" +
                        "  \"Lore\": [\"a a\"],\n" +
                        "  \"Glow\": true\n" +
                        "}");
                System.out.println(Constants.Console + Constants.Info_Format + file.getName() + " created");
            }

        }

        if (!hunting.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            hunting.createNewFile();

            for (EntityType type : EntityType.values()) {
                if (Constants.isHuntingEntity(type))
                    json.addProperty(type.toString(), 100);
            }

            write(hunting, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + hunting.getName() + " created");
        }

        if (!mining.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            for (Material type : Material.values()) {
                if (Constants.isMiningBlock(type))
                    json.addProperty(type.toString(), 100);
            }

            mining.createNewFile();

            write(mining, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + mining.getName() + " created");
        }

        if (!logging.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            for (Material type : Material.values()) {
                if (Constants.isLoggingBlock(type))
                    json.addProperty(type.toString(), 100);
            }

            logging.createNewFile();

            write(logging, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + logging.getName() + " created");
        }

        if (!farming.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            for (Material type : Material.values()) {
                if (Constants.isFarmingBlock(type))
                    json.addProperty(type.toString(), 100);
            }

            farming.createNewFile();

            write(farming, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + farming.getName() + " created");
        }

        if (!breeding.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            for (EntityType type : EntityType.values()) {
                if (Constants.isBreedingEntity(type))
                    json.addProperty(type.toString(), 100);
            }

            breeding.createNewFile();

            write(breeding, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + breeding.getName() + " created");
        }

        if (!fishing.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            for (EntityType type : EntityType.values()) {
                if (Constants.isFishingEntity(type))
                    json.addProperty(type.toString(), 100);
            }

            fishing.createNewFile();

            write(fishing, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + fishing.getName() + " created");
        }

        if (!furnace.exists()) {
            Gson gson = new Gson();
            JsonObject json = new JsonObject();

            json.addProperty(Constants.JSON_FURNACE_PROBABILITY, 100);

            furnace.createNewFile();

            write(furnace, gson.toJson(json));

            System.out.println(Constants.Console + Constants.Info_Format + furnace.getName() + " created");
        }


    }

}
