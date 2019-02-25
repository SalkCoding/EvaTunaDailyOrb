package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Logging;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;

public class Thursday implements Listener {

    private static boolean enable;
    private static final Logging logging = new Logging();

    public static void load() throws IOException {
        logging.load();
    }

    public static Logging getLogging() {
        return logging;
    }

    static {
        enable = Constants.isEnableDay(Daily.Thursday);
        try {
            logging.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onLogging(BlockBreakEvent event) {
        if (enable) {
            Block block = event.getBlock();
            Player player = event.getPlayer();
            if (player == null)
                return;
            if (player.getGameMode() == GameMode.SURVIVAL && Constants.isLoggingBlock(block.getType()) && !AntiCheating.isUsed(player, block.getLocation()) && logging.getSucceed(player, block.getType())) {
                AntiCheating.addLocation(player, block.getLocation());
                Constants.addItemAndDrop(player, Constants.getOrb(Daily.Thursday));
                Statistics.addLogging(1);
                player.sendMessage(Constants.Info_Format + "목의 정수를 획득하셨습니다.");
            }
        }
    }

}
