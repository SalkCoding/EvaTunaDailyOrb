package net.evatunadailyorb.salkcoding.command;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Daily;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.dailyevent.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.isOp()) {
            sender.sendMessage(Constants.Error_Format + "접근 권한이 없습니다.");
            return true;
        }
        if (strings.length < 1) {
            printError(sender);
            return true;
        }
        if(strings[0].equalsIgnoreCase("reload")){
            try {
                Constants.Load();
                Monday.load();
                Tuesday.load();
                Wednesday.load();
                Thursday.load();
                Friday.load();
                Saturday.load();
                Sunday.load();
            } catch (IOException e) {
                System.out.println(Constants.Console + Constants.Error_Format + "Config files initialize failed");
                e.printStackTrace();
            }
            sender.sendMessage(Constants.Info_Format + "Files are reloaded");
        } else if (strings[0].equalsIgnoreCase("probability")) {
            if (strings.length != 2) {
                printError(sender);
                return true;
            }
            int page = Integer.parseInt(strings[1]);
            sender.sendMessage(Constants.Info_Format + "--------" + page + "/7--------");
            switch (page) {
                case 1:
                    for (Map.Entry<EntityType, Float> ele : Monday.getHunting().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                case 2:
                    for (Map.Entry<String, Float> ele : Tuesday.getSmelting().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey() + " : " + ele.getValue() + "%");
                    break;
                case 3:
                    for (Map.Entry<EntityType, Float> ele : Wednesday.getFishing().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                case 4:
                    for (Map.Entry<Material, Float> ele : Thursday.getLogging().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                case 5:
                    for (Map.Entry<Material, Float> ele : Friday.getMining().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                case 6:
                    for (Map.Entry<Material, Float> ele : Saturday.getFarming().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                case 7:
                    for (Map.Entry<EntityType, Float> ele : Sunday.getBreeding().getProbabilityList().entrySet())
                        sender.sendMessage(Constants.Info_Format + ele.getKey().toString() + " : " + ele.getValue() + "%");
                    break;
                default:
                    sender.sendMessage(Constants.Error_Format + "해당 페이지가 존재하지 않습니다.");
                    break;
            }
            sender.sendMessage(Constants.Info_Format + "-------------------");
        } else if (strings[0].equalsIgnoreCase("give")) {
            if (strings.length > 3) {
                printError(sender);
                return true;
            }
            String name = strings[1];
            int amount = 0;
            try {
                amount = Integer.parseInt(strings[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(Constants.Error_Format + "잘못된 수량이 입력되었습니다.");
                return true;
            }
            Player player = Bukkit.getPlayer(name);
            if (player == null) {
                sender.sendMessage(Constants.Error_Format + "해당 플레이어가 접속해있지 않습니다.");
                return true;
            }
            for (int i = 0; i < amount; i++)
                for (Daily day : Daily.values()) {
                    if(day == Daily.Null)
                        continue;
                    player.getInventory().addItem(Constants.getOrb(day));
                }
            player.sendMessage(Constants.Info_Format + "관리자가 당신에게 정수를 " + amount + "개 지급하였습니다.");
        } else if (strings[0].equalsIgnoreCase("statistics")) {
            if (strings.length > 1) {
                printError(sender);
                return true;
            }
            sender.sendMessage(Constants.Info_Format + "정수량 통계");
            Statistics.printToPlayer(sender);
        } else {
            printError(sender);
        }
        return true;
    }

    private void printError(CommandSender sender) {
        sender.sendMessage(Constants.Warn_Format + "잘못된 명령어 입니다.");
        sender.sendMessage(Constants.Warn_Format + "/do,/dailyorb (대소문자 구분 안함)");
        sender.sendMessage(Constants.Warn_Format + "/DailyOrb Reload : config json 파일들을 리로드합니다.");
        sender.sendMessage(Constants.Warn_Format + "/DailyOrb Probability [page] : 정수 획득 확률을 보여줍니다.");
        sender.sendMessage(Constants.Warn_Format + "/DailyOrb give [name] [amount] : 정수를 [amount]만큼 [name]에게 지급합니다.");
        sender.sendMessage(Constants.Warn_Format + "/DailyOrb statistics : 총 생성된 정수의 갯수를 통계내어 줍니다.");
    }

}
