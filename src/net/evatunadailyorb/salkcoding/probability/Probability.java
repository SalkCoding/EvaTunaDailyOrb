package net.evatunadailyorb.salkcoding.probability;

import net.evatunadailyorb.salkcoding.dailyevent.AntiCheating;
import net.evatunadailyorb.salkcoding.main.Main;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public abstract class Probability<T> {

    private HashMap<T, Float> probability = new HashMap<>();

    public abstract void load() throws IOException;

    protected void addProbability(T type, Float chance) {
        if (!probability.containsKey(type))
            probability.put(type, chance);
    }

    protected Float getProbability(T type) {
        return probability.get(type);
    }

    public boolean getSucceed(Player player, T type) {
        Random random = new Random(System.currentTimeMillis());
        Float chance;
        float p = (random.nextFloat() * 100);
        if (Main.getDebug()) player.sendMessage(p + "<= random | chance => " + probability.get(type));
        if ((chance = probability.get(type)) != null && p <= chance)
            return true;
        return false;
    }

    public HashMap<T, Float> getProbabilityList() {
        return probability;
    }

}
