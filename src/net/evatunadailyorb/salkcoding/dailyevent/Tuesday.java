package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Smelting;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

import java.io.IOException;

public class Tuesday implements Listener {

    private static boolean enable;
    private static final Smelting smelting = new Smelting();

    public static void load() throws IOException {
        smelting.load();
    }

    public static Smelting getSmelting() {
        return smelting;
    }

    static {
        enable = Constants.isEnableDay(Daily.Tuesday);
        try {
            smelting.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onSmelting(FurnaceExtractEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL && enable) {
            if (smelting.getSucceed(event.getPlayer(), event.getItemAmount())) {
                Player player = event.getPlayer();
                Constants.addItemAndDrop(player, Constants.getOrb(Daily.Tuesday));
                Statistics.addSmelting(1);
                player.sendMessage(Constants.Info_Format + "화의 정수를 획득하셨습니다.");
            }
        }
    }

}
