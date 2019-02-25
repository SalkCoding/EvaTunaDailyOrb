package net.evatunadailyorb.salkcoding.main;

import net.evatunadailyorb.salkcoding.Constants;
import net.evatunadailyorb.salkcoding.Statistics.Statistics;
import net.evatunadailyorb.salkcoding.command.AdminCommand;
import net.evatunadailyorb.salkcoding.dailyevent.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;
    private static boolean Debug = false;

    public static Main getInstance() {
        return instance;
    }

    public Main() {
        if (instance != null) {
            throw new IllegalStateException();
        }
        instance = this;
    }

    public static boolean getDebug() {
        return Debug;
    }

    @Override
    public void onEnable() {
        try {
            Constants.Load();
            Statistics.load();
        } catch (IOException e) {
            System.out.println(Constants.Console + Constants.Error_Format + "Config files initialize failed");
            e.printStackTrace();
        }

        getServer().getPluginCommand("DailyOrb").setExecutor(new AdminCommand());

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new Monday(), this);
        manager.registerEvents(new Tuesday(), this);
        manager.registerEvents(new Wednesday(), this);
        manager.registerEvents(new Thursday(), this);
        manager.registerEvents(new Friday(), this);
        manager.registerEvents(new Saturday(), this);
        manager.registerEvents(new Sunday(), this);
        //manager.registerEvents(new AntiCheating(), this);
        AntiCheating.load();
        System.out.println(Constants.Console + Constants.Warn_Format + "Plugin is now enabled");
    }

    @Override
    public void onDisable() {
        try {
            Statistics.save();
        } catch (IOException e) {
            System.out.println(Constants.Console + Constants.Error_Format + "Config files save failed");
            e.printStackTrace();
        }
        AntiCheating.unload();
        System.out.println(Constants.Console + Constants.Warn_Format + "Plugin in now disabled");
    }

}
