package net.evatunadailyorb.salkcoding.dailyevent;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.QuickHash;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.probability.Mining;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Map;

public class Friday implements Listener {

    private static boolean enable;
    private static final Mining mining = new Mining();

    public static void load() throws IOException {
        mining.load();
    }

    public static Mining getMining() {
        return mining;
    }

    static {
        enable = Constants.isEnableDay(Daily.Friday);
        try {
            mining.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMining(BlockBreakEvent event) {
        if(event.isCancelled())
            return;
        if (enable) {
            Block block = event.getBlock();
            Player player = event.getPlayer();
            if (player == null)
                return;
            if (player.getGameMode() == GameMode.SURVIVAL
                    && Constants.isMiningBlock(block.getType())
                    && !AntiCheating.isUsed(player, block.getLocation())
                    && mining.getSucceed(player, block.getType())) {
                AntiCheating.addLocation(player, block.getLocation());
                ItemStack main = player.getInventory().getItemInMainHand();
                if(main.getEnchantments().containsKey(Enchantment.SILK_TOUCH))
                    return;
                Constants.addItemAndDrop(player, Constants.getOrb(Daily.Friday));
                Statistics.addMining(1);
                player.sendMessage(Constants.Info_Format + "금의 정수를 획득하셨습니다.");
            }
        }
    }

}
