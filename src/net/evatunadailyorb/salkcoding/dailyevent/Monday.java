package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Hunting;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.IOException;

public class Monday implements Listener {

    private static boolean enable;
    private static final Hunting hunting = new Hunting();

    public static void load() throws IOException {
        hunting.load();
    }

    public static Hunting getHunting() {
        return hunting;
    }

    static {
        enable = Constants.isEnableDay(Daily.Monday);
        try {
            hunting.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (enable) {
            LivingEntity entity = event.getEntity();
            Player player = entity.getKiller();
            if (player == null)
                return;
            if (player.getGameMode() == GameMode.SURVIVAL && Constants.isHuntingEntity(entity.getType()) && hunting.getSucceed(player, entity.getType())) {
                Constants.addItemAndDrop(player, Constants.getOrb(Daily.Monday));
                Statistics.addHunting(1);
                player.sendMessage(Constants.Info_Format + "월의 정수를 획득 하셨습니다.");
            }
        }
    }
}
