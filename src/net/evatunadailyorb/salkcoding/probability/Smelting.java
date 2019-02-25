package net.evatunadailyorb.salkcoding.probability;

import com.google.gson.JsonObject;
import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.file.JsonLoad;
import net.evatunadailyorb.salkcoding.main.Main;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class Smelting extends Probability<String> {
    @Override
    public void load() throws IOException {
        JsonObject object = JsonLoad.LoadJson(Constants.settingDir, Constants.JSON_FURNACE);
        addProbability(Constants.JSON_FURNACE, object.get(Constants.JSON_FURNACE_PROBABILITY).getAsFloat());
        /*Set<Map.Entry<String, JsonElement>> set = object.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> map = iter.next();
            addProbability(String.valueOf(map.getKey()), map.getValue().getAsFloat());
        }*/
    }

    public boolean getSucceed(Player player, int amount) {
        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        Float chance;
        float p = (random.nextFloat() * 100);
        if(Main.getDebug()) player.sendMessage(p + "<= random | chance => " + getProbability(Constants.JSON_FURNACE)*amount);
        if ((chance = this.getProbability(Constants.JSON_FURNACE)) != null && p <= (chance * amount))
            return true;
        return false;
    }

}
