package net.evatunadailyorb.salkcoding.probability;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.file.JsonLoad;
import org.bukkit.Material;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Farming extends Probability<Material> {
    @Override
    public void load() throws IOException {
        JsonObject object = JsonLoad.LoadJson(Constants.settingDir, Constants.JSON_FARMING);
        Set<Map.Entry<String, JsonElement>> set = object.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> map = iter.next();
            addProbability(Material.valueOf(map.getKey()), map.getValue().getAsFloat());
        }
    }
}
