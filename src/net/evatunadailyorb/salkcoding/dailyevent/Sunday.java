package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Breeding;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import java.io.IOException;

public class Sunday implements Listener {

    private static boolean enable;
    private static final Breeding breeding = new Breeding();

    public static void load() throws IOException {
        breeding.load();
    }

    public static Breeding getBreeding() {
        return breeding;
    }

    static {
        enable = Constants.isEnableDay(Daily.Sunday);
        try {
            breeding.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreeding(EntityBreedEvent event) {
        if(event.isCancelled())
            return;
        if (enable) {
            Entity entity = event.getEntity();
            if (!(event.getBreeder() instanceof Player))
                return;
            Player player = (Player) event.getBreeder();
            if (player.getGameMode() == GameMode.SURVIVAL && Constants.isBreedingEntity(entity.getType()) && breeding.getSucceed(player, entity.getType())) {
                Constants.addItemAndDrop(player, Constants.getOrb(Daily.Sunday));
                Statistics.addBreeding(1);
                player.sendMessage(Constants.Info_Format + "일의 정수를 획득하셨습니다.");
            }
        }
    }

}
