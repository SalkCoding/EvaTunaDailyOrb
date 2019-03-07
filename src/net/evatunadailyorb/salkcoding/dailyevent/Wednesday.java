package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Fishing;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.io.IOException;

public class Wednesday implements Listener {

    private static boolean enable;
    private static final Fishing fishing = new Fishing();

    public static void load() throws IOException {
        fishing.load();
    }

    public static Fishing getFishing() {
        return fishing;
    }

    static {
        enable = Constants.isEnableDay(Daily.Wednesday);
        try {
            fishing.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFishing(PlayerFishEvent event) {
        if(event.isCancelled())
            return;
        if (enable) {
            if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH || event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
                Entity entity = event.getCaught();
                Player player = event.getPlayer();
                if (player.getGameMode() == GameMode.SURVIVAL && Constants.isFishingEntity((entity.getType()))) {
                    if(fishing.getSucceed(player, entity.getType())) {
                        Constants.addItemAndDrop(player, Constants.getOrb(Daily.Wednesday));
                        Statistics.addFishing(1);
                        player.sendMessage(Constants.Info_Format + "수의 정수를 획득하셨습니다.");
                    }
                }
            }
        }
    }

}
