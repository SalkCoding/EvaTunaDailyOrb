package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Farming;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;

public class Saturday implements Listener {

    private static boolean enable;
    private static final Farming farming = new Farming();

    public static void load() throws IOException {
        farming.load();
    }

    public static Farming getFarming() {
        return farming;
    }

    static {
        enable = Constants.isEnableDay(Daily.Saturday);
        try {
            farming.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onFarming(BlockBreakEvent event) {
        if (enable) {
            Block block = event.getBlock();
            Player player = event.getPlayer();
            if (player == null)
                return;
            if (player.getGameMode() == GameMode.SURVIVAL && Constants.isFarmingBlock(block.getType())) {
                if (!(block.getBlockData() instanceof Ageable))
                    return;
                Ageable ageable = (Ageable) block.getBlockData();
                if (ageable.getAge() != ageable.getMaximumAge())
                    return;
                if (farming.getSucceed(player, block.getType())) {
                    Constants.addItemAndDrop(player, Constants.getOrb(Daily.Saturday));
                    Statistics.addFarming(1);
                    player.sendMessage(Constants.Info_Format + "토의 정수를 획득하셨습니다.");
                }
            }
        }
    }

}
