package adver.sarius.ssb;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import adver.sarius.ssb.generation.SSBWorldGenerator;
import adver.sarius.ssb.handler.BoneMealHandler;
import adver.sarius.ssb.handler.HarvestDropsHandler;
import adver.sarius.ssb.handler.LightningHandler;
import adver.sarius.ssb.handler.LootTableHandler;
import adver.sarius.ssb.proxy.CommonProxy;
import adver.sarius.ssb.recipe.ModRecipes;
import adver.sarius.ssb.villager.VillagerTradingChanger;

@Mod(modid = SkyBlockMod.MODID, name = SkyBlockMod.NAME, 
	version = SkyBlockMod.VERSION, acceptedMinecraftVersions = "[1.10.2]")
public class SkyBlockMod {

	public static final String MODID = "simpleskyblock";
	public static final String NAME = "SimpleSkyBlock";
	public static final String VERSION = "1.0.0";
	
	@Mod.Instance(MODID)
	public static SkyBlockMod instance;
	
	@SidedProxy(serverSide = "adver.sarius.ssb.proxy.CommonProxy", clientSide="adver.sarius.ssb.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		System.out.println(NAME + " is loading,");
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		ModRecipes.init();
		MinecraftForge.EVENT_BUS.register(new LootTableHandler());
		MinecraftForge.EVENT_BUS.register(new LightningHandler());
		MinecraftForge.EVENT_BUS.register(new HarvestDropsHandler());
		MinecraftForge.EVENT_BUS.register(new BoneMealHandler());
		
		MinecraftForge.EVENT_BUS.register(new SSBWorldGenerator());
		MinecraftForge.TERRAIN_GEN_BUS.register(new SSBWorldGenerator());
		
		VillagerTradingChanger.registerVillager();
		
		WorldType worldType = new WorldType("simpleskyblock");
		
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event){
		((ServerCommandManager)event.getServer().getCommandManager()).registerCommand(new CommandTesting());
	}
	
	// TODOs:
	// ItemMonsterPlacer passt mobspawner an 
	// Was ist mit ServerProxy?
	// in en_US kann ich auch ein itemgroup.skyblock= angeben?!
	// Kommentare einheitlich
	// Doc
	// Zeilenumbrueche '||' einheitlich 
	
	// Tuts:
	// https://shadowfacts.net/tutorials/forge-modding-1102/
	// http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/2720770-crare1s-minecraft-1-10-2-forge-modding-tutorial
	// http://bedrockminer.jimdo.com/modding-tutorials/
	// http://jabelarminecraft.blogspot.de/p/minecraft-forge-172-event-handling.html
	// http://tutorials.darkhax.net/mob-loot.html
	// http://greyminecraftcoder.blogspot.de/2015/01/mining-blocks-with-tools.html
}
