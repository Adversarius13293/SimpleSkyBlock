package adver.sarius.ssb.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import adver.sarius.ssb.SimpleSkyBlockMod;

public class SSBConfig {

	static Configuration configFile;
	/** Needs resync after disconnecting from multiplayer. **/
	public static boolean needsResync = true;
	
	public SSBConfig(FMLPreInitializationEvent event){
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}
	

	public static final String CATEGORY_GENERAL = Configuration.CATEGORY_GENERAL;
	public static final String CATEGORY_GENERATION = "generation";
	public static final String CATEGORY_MECHANICS = "mechanics";
	
	public static boolean disableNetherSpawnerChange = true;
	public static boolean forceBonusChest = false;
	public static int spawnIslandSize = 2;
	
	public static boolean useDefaultNether = false;
	public static boolean removeNetherFloor = true;
	public static boolean removeNetherCeiling = false;
	public static boolean createStrongholdPortalRoom = true;
	
	public static boolean enableCraftingRecipes = true;
	public static boolean enableForesterVillager = true;
	public static boolean enableGravelDropSand = true;
	public static boolean enableBoneMealOnPodzol = true;
	public static boolean enableGuardianLightning = true;
	public static boolean enableCobWebDrop = true;
	public static boolean enableNetherLootChange = true;
	
	public static void syncConfig(){
		Property prop = configFile.get(CATEGORY_GENERAL, "Disable Nether Spawner Change", true, "Disable the possibility to change mob spawner in the Nether. Since you can only have blaze spawners there in vanilla.");
		disableNetherSpawnerChange = prop.getBoolean();
		prop = configFile.get(CATEGORY_GENERAL, "Force Bonus Chest", false, "Always create the starting bonus chest. This option is mostly for server maps, which can't use the create world gui.");
		forceBonusChest = prop.getBoolean();
		prop = configFile.get(CATEGORY_GENERAL, "Spawn Island Size", 2, "Size of the starting island, ranging from 0 to 3. Value 0 will disable the island generation.", 0, 3);
		spawnIslandSize = prop.getInt();

		prop = configFile.get(CATEGORY_GENERATION, "Use Default Nether", false, "Disable the SSB world generation for the Nether and use the default generation. Overrides Nether bedrock options.");
		useDefaultNether = prop.getBoolean();
		prop.setRequiresWorldRestart(true);
		prop = configFile.get(CATEGORY_GENERATION, "Remove Nether Floor", true, "Remove the bedrock floor of the Nether. (Only for SSB Nether)");
		removeNetherFloor = prop.getBoolean();
		prop = configFile.get(CATEGORY_GENERATION, "Remove Nether Ceiling", false, "Remove the bedrock ceiling of the Nether. (Only for SSB Nether)");
		removeNetherCeiling = prop.getBoolean();
		prop = configFile.get(CATEGORY_GENERATION, "Create Stronghold Portal Room", true, "Create the stronghold portal room. If set to false, nothing of the stronghold will be created.");
		createStrongholdPortalRoom = prop.getBoolean();
		
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Crafting Recipes", true, "Enable the additional SSB crafting recipes.");
		enableCraftingRecipes = prop.getBoolean();
		prop.setRequiresMcRestart(true);
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Forester Villager", true, "Enable the green villager and his SSB recipes. Disabling this will convert already spawned forester into random villagers!");
		enableForesterVillager = prop.getBoolean();
		prop.setRequiresWorldRestart(true);
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Gravel Drop Sand", true, "Gravel drops sand when mined with an pickaxe.");
		enableGravelDropSand = prop.getBoolean();
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Bone Meal on Podzol", true, "Use bone meal on podzol for ferns and dead bushes.");
		enableBoneMealOnPodzol = prop.getBoolean();
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Guardian Lightning", true, "Guardians struck by lightning will turn into elder guardians.");
		enableGuardianLightning = prop.getBoolean();
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Cob Web Drop", true, "Cave spiders can drop cob webs when killed by player.");
		enableCobWebDrop = prop.getBoolean();
		prop.setRequiresWorldRestart(true);
		prop = configFile.get(CATEGORY_MECHANICS, "Enable Nether Loot Change", true, "Extend the loot table of nether fortress chest with some items.");
		enableNetherLootChange = prop.getBoolean();
		prop.setRequiresWorldRestart(true);
		if(configFile.hasChanged()){
			configFile.save();
		}
		needsResync = false;
	}
	
	// Fired only client side from gui
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equals(SimpleSkyBlockMod.MODID)){
			syncConfig();
		}
	}
}