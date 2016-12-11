package adver.sarius.ssb.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import adver.sarius.ssb.SimpleSkyBlockMod;

// TODO: I don't like this this construct of a constructor and the static class... 
// But I also don't want a static class which has to be initialized first.
public class SSBConfig {

	static Configuration configFile;
	
	public SSBConfig(FMLPreInitializationEvent event){
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}
	
	public static final String CATEGORY_GENERATION = "generation";
	public static final String CATEGORY_GENERAL = Configuration.CATEGORY_GENERAL;
	
	public static boolean disableNetherSpawnerChange = true;
	public static boolean forceBonusChest = false;
	public static int spawnIslandSize = 2;
	
	public static boolean useDefaultNether = false;
	public static boolean removeNetherFloor = true;
	public static boolean removeNetherCeiling = false;
	
	private static void syncConfig(){
		// TODO: setRequiresMinecraftStart?
		disableNetherSpawnerChange = configFile.getBoolean("Disable Nether Spawner Change", CATEGORY_GENERAL, true, "Disable the possibility to change mob spawner in the Nether. Since you can only have blaze spawners there in vanilla.");
		forceBonusChest = configFile.getBoolean("Force Bonus Chest", CATEGORY_GENERAL, false, "Always create the starting bonus chest. This option is mostly for server maps, which can't use the create world gui.");
		spawnIslandSize = configFile.getInt("Spawn Island Size", CATEGORY_GENERAL, 2, 0, 3, "Size of the starting island, ranging from 0 to 3. Value 0 will disable the island generation.");

		useDefaultNether = configFile.getBoolean("Use Default Nether", CATEGORY_GENERATION, false, "Disable the SSB world generation for the Nether and use the default generation. Overrides Nether bedrock options. You need to reload the Nether, if you are changing this option while playing!");
		removeNetherFloor = configFile.getBoolean("Remove Nether Floor", CATEGORY_GENERATION, true, "Remove the bedrock floor of the Nether.");
		removeNetherCeiling = configFile.getBoolean("Remove Nether Ceiling", CATEGORY_GENERATION, false, "Remove the bedrock ceiling of the Nether.");
		
		if(configFile.hasChanged()){
			configFile.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equals(SimpleSkyBlockMod.MODID)){
			syncConfig();
		}
	}
}