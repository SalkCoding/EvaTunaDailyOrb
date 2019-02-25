package net.evatunadailyorb.salkcoding;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.evatunadailyorb.salkcoding.file.JsonLoad;
import net.evatunadailyorb.salkcoding.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class Constants {


    private Constants() {
    }

    public static void Load() throws IOException {
        JsonObject[] list = new JsonObject[]{
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_MONDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_TUESDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_WEDNESDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_THURSDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_FRIDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_SATURDAY),
                JsonLoad.LoadJson(Constants.itemDir, Constants.JSON_ITEM_SUNDAY)
        };
        int i = 1;
        for (JsonObject json : list) {
            switch (i) {
                case 1:
                    OrbMap.put(Daily.Monday, parseItemStack(json));
                    break;
                case 2:
                    OrbMap.put(Daily.Tuesday, parseItemStack(json));
                    break;
                case 3:
                    OrbMap.put(Daily.Wednesday, parseItemStack(json));
                    break;
                case 4:
                    OrbMap.put(Daily.Thursday, parseItemStack(json));
                    break;
                case 5:
                    OrbMap.put(Daily.Friday, parseItemStack(json));
                    break;
                case 6:
                    OrbMap.put(Daily.Saturday, parseItemStack(json));
                    break;
                case 7:
                    OrbMap.put(Daily.Sunday, parseItemStack(json));
                    break;
            }
            i++;
        }
        System.out.println(Console + Constants.Info_Format + "Constants are now initialized");
    }

    private static ItemStack parseItemStack(JsonObject json) {
        Gson gson = new Gson();
        int i = 0;
        List<String> lore = gson.fromJson(json.get(JSON_ITEM_LORE), List.class);
        for (Iterator<String> iterator = lore.iterator(); iterator.hasNext(); ++i) {
            String element = iterator.next();
            lore.set(i, ChatColor.translateAlternateColorCodes('&', element));
        }

        ItemStack item = new ItemStack(gson.fromJson(json.get(Constants.JSON_ITEM_TYPE), Material.class));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', json.get(JSON_ITEM_NAME).getAsString()));
        meta.setLore(lore);
        if (json.get(JSON_ITEM_GLOW).getAsBoolean()) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.LURE, 0, true);
        }

        item.setItemMeta(meta);
        return item;
    }

    private static Daily today = Daily.Null;

    static {
        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 1:
                today = Daily.Monday;
                break;
            case 2:
                today = Daily.Tuesday;
                break;
            case 3:
                today = Daily.Wednesday;
                break;
            case 4:
                today = Daily.Thursday;
                break;
            case 5:
                today = Daily.Friday;
                break;
            case 6:
                today = Daily.Saturday;
                break;
            case 7:
                today = Daily.Sunday;
                break;
        }
    }

    private static final HashSet<EntityType> huntingSet = new QuickHash<>(
            EntityType.ENDERMAN,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER,
            EntityType.ZOMBIE,
            EntityType.DROWNED,
            EntityType.SKELETON,
            EntityType.SKELETON_HORSE,
            EntityType.HUSK,
            EntityType.CREEPER,
            EntityType.STRAY,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.WITCH,
            EntityType.GHAST,
            EntityType.PIG_ZOMBIE,
            EntityType.BLAZE,
            EntityType.MAGMA_CUBE,
            EntityType.WITHER_SKELETON,
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDERMITE,
            EntityType.EVOKER,
            EntityType.GUARDIAN,
            EntityType.PHANTOM,
            EntityType.SHULKER,
            EntityType.VEX,
            EntityType.VINDICATOR
    );
    private static final HashSet<Material> miningSet = new QuickHash<>(
            Material.STONE,
            Material.GRANITE,
            Material.DIORITE,
            Material.ANDESITE,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.COAL_ORE,
            Material.EMERALD_ORE,
            Material.DIAMOND_ORE,
            Material.REDSTONE_ORE,
            Material.LAPIS_ORE
    );
    private static final HashSet<Material> loggingSet = new QuickHash<>(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.ACACIA_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG
    );
    private static final HashSet<Material> farmingSet = new QuickHash<>(
            Material.BEETROOTS,
            Material.CARROTS,
            Material.CHORUS_PLANT,
            Material.MELON,
            Material.POTATOES,
            Material.PUMPKIN,
            Material.WHEAT,
            Material.CACTUS,
            Material.SUGAR_CANE,
            Material.COCOA,
            Material.NETHER_WART,
            Material.KELP,
            Material.KELP_PLANT
    );
    /*private static final HashSet<Material> cropSet = new QuickHash<>(
            Material.BEETROOTS,
            Material.CARROTS,
            Material.POTATOES,
            Material.WHEAT,
            Material.COCOA
    );*/
    private static final HashSet<EntityType> breedingSet = new QuickHash<>(
            EntityType.CHICKEN,
            EntityType.COW,
            EntityType.DONKEY,
            EntityType.HORSE,
            EntityType.MULE,
            EntityType.MUSHROOM_COW,
            EntityType.OCELOT,
            EntityType.PARROT,
            EntityType.PIG,
            EntityType.RABBIT,
            EntityType.SHEEP,
            EntityType.TURTLE,
            EntityType.WOLF
    );
    private static final HashSet<EntityType> fishingSet = new QuickHash<>(
            EntityType.DROPPED_ITEM
            /*EntityType.SALMON,
            EntityType.COD,
            EntityType.TROPICAL_FISH,
            EntityType.PUFFERFISH*/
    );

    private static HashMap<Daily, ItemStack> OrbMap = new HashMap<>();

    public static final String Info_Format = ChatColor.RESET + "" + ChatColor.WHITE + ChatColor.ITALIC + "[ " + ChatColor.GREEN + ChatColor.ITALIC + "!" + ChatColor.WHITE + ChatColor.ITALIC + " ] " + ChatColor.GRAY + ChatColor.ITALIC;
    public static final String Warn_Format = ChatColor.RESET + "" + ChatColor.WHITE + ChatColor.ITALIC + "[ " + ChatColor.YELLOW + ChatColor.ITALIC + "!" + ChatColor.WHITE + ChatColor.ITALIC + " ] " + ChatColor.GRAY + ChatColor.ITALIC;
    public static final String Error_Format = ChatColor.RESET + "" + ChatColor.WHITE + ChatColor.ITALIC + "[ " + ChatColor.RED + ChatColor.ITALIC + "!" + ChatColor.WHITE + ChatColor.ITALIC + " ] " + ChatColor.GRAY + ChatColor.ITALIC;
    public static final String Console = "[EvaTunaDailyOrb] ";

    public static final String JSON_ITEM = "Item";
    public static final String JSON_ITEM_MONDAY = "Monday";
    public static final String JSON_ITEM_TUESDAY = "Tuesday";
    public static final String JSON_ITEM_WEDNESDAY = "Wednesday";
    public static final String JSON_ITEM_THURSDAY = "Thursday";
    public static final String JSON_ITEM_FRIDAY = "Friday";
    public static final String JSON_ITEM_SATURDAY = "Saturday";
    public static final String JSON_ITEM_SUNDAY = "Sunday";

    public static final String JSON_HUNTING = "Hunting_Probability";
    public static final String JSON_MINING = "Mining_Probability";
    public static final String JSON_LOGGING = "Logging_Probability";
    public static final String JSON_FARMING = "Farming_Probability";
    public static final String JSON_BREEDING = "Breeding_Probability";
    public static final String JSON_FISHING = "Fishing_Probability";
    public static final String JSON_FURNACE = "Furnace_Probability";

    public static final String JSON_STATISTICS = "Statistics";
    public static final String JSON_STATISTICS_HUNTING = "Hunting";
    public static final String JSON_STATISTICS_MINING = "Mining";
    public static final String JSON_STATISTICS_LOGGING = "Logging";
    public static final String JSON_STATISTICS_FARMING = "Farming";
    public static final String JSON_STATISTICS_BREEDING = "Breeding";
    public static final String JSON_STATISTICS_FISHING = "Fishing";
    public static final String JSON_STATISTICS_FURNACE = "Furnace";

    public static final String JSON_FURNACE_PROBABILITY = "Probability";

    public static final String JSON_ITEM_NAME = "Display-name";
    public static final String JSON_ITEM_TYPE = "Material-type";
    public static final String JSON_ITEM_LORE = "Lore";
    public static final String JSON_ITEM_GLOW = "Glow";

    public static final File settingDir = new File(Main.getInstance().getDataFolder(), "Setting");
    public static final File statisticsDir = new File(Main.getInstance().getDataFolder(), Constants.JSON_STATISTICS);
    public static final File itemDir = new File(Main.getInstance().getDataFolder(), JSON_ITEM);

    public static ItemStack getOrb(Daily day) {
        return OrbMap.get(day);
    }

    public static boolean isEnableDay(Daily day) {
        /*if (day == today)
            return true;*/
        return true;
    }

    public static boolean isHuntingEntity(EntityType type) {
        return huntingSet.contains(type);
    }

    public static boolean isMiningBlock(Material type) {
        return miningSet.contains(type);
    }

    public static boolean isLoggingBlock(Material type) {
        return loggingSet.contains(type);
    }

    public static boolean isFarmingBlock(Material type) {
        return farmingSet.contains(type);
    }

    public static boolean isFishingEntity(EntityType type) {
        return fishingSet.contains(type);
    }

    public static boolean isBreedingEntity(EntityType type) {
        return breedingSet.contains(type);
    }

    /*public static boolean isCrops(Material type){
        return cropSet.contains(type);
    }*/

    public static void addItemAndDrop(Player p, ItemStack item) {
        HashMap<Integer, ItemStack> left = p.getInventory().addItem(item);
        for (HashMap.Entry<Integer, ItemStack> i : left.entrySet()) {
            p.getWorld().dropItem(p.getLocation(), i.getValue());
        }
    }

}
