package adver.sarius.ssb;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import adver.sarius.ssb.handler.BoneMealHandler;
import adver.sarius.ssb.handler.HarvestDropsHandler;
import adver.sarius.ssb.handler.LightningHandler;
import adver.sarius.ssb.handler.LootTableHandler;
import adver.sarius.ssb.proxy.CommonProxy;
import adver.sarius.ssb.recipe.ModRecipes;
import adver.sarius.ssb.villager.VillagerProfessionChanger;

@Mod(modid = SkyBlockMod.MODID, name = SkyBlockMod.NAME, 
	version = SkyBlockMod.VERSION, acceptedMinecraftVersions = "[1.10.2]")
public class SkyBlockMod {

	public static final String MODID = "skyblock";
	public static final String NAME = "SkyBlockMinimalistic"; // SimpleSkyBlock
	public static final String VERSION = "1.0.0";
	
	@Mod.Instance(MODID)
	public static SkyBlockMod instance;
	
	@SidedProxy(serverSide = "adver.sarius.sbm.proxy.CommonProxy", clientSide="adver.sarius.sbm.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	// Die 3 Methoden koennen beliebig benannt werden.
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
		
		VillagerProfessionChanger.registerVillager();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	// TODOs:
	// ItemMonsterPlacer passt mobspawner an 
	// Was ist mit ServerProxy?
	// in en_US kann ich auch ein itemgroup.skyblock= angeben?!
	// Kommentare einheitlich
	// Doc
	// setzt nen dead bush in die mitte der dann verschwindet
	// Zeilenumbrueche '||' einheitlich 
	
	// Tuts:
	// https://shadowfacts.net/tutorials/forge-modding-1102/
	// http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/2720770-crare1s-minecraft-1-10-2-forge-modding-tutorial
	// http://bedrockminer.jimdo.com/modding-tutorials/
	// http://jabelarminecraft.blogspot.de/p/minecraft-forge-172-event-handling.html
	// http://tutorials.darkhax.net/mob-loot.html
	// http://greyminecraftcoder.blogspot.de/2015/01/mining-blocks-with-tools.html
}
