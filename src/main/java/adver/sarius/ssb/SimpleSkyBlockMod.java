package adver.sarius.ssb;

import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Logger;

import adver.sarius.ssb.config.SSBConfig;
import adver.sarius.ssb.gen.WorldProviderHellSSB;
import adver.sarius.ssb.gen.WorldProviderSurfaceSSB;
import adver.sarius.ssb.gen.WorldTypeSSB;
import adver.sarius.ssb.gen.structure.ComponentScatteredFeaturePiecesSSB;
import adver.sarius.ssb.gen.structure.MapGenScatteredFeatureSSB;
import adver.sarius.ssb.gen.structure.MapGenStrongholdSSB;
import adver.sarius.ssb.gen.structure.StructureOceanMonumentPiecesSSB;
import adver.sarius.ssb.gen.structure.StructureOceanMonumentSSB;
import adver.sarius.ssb.gen.structure.StructureStrongholdPiecesSSB;
import adver.sarius.ssb.handler.BoneMealHandler;
import adver.sarius.ssb.handler.FossilGeneratorHandler;
import adver.sarius.ssb.handler.GUIHandler;
import adver.sarius.ssb.handler.HarvestDropsHandler;
import adver.sarius.ssb.handler.LightningHandler;
import adver.sarius.ssb.handler.LootTableHandler;
import adver.sarius.ssb.handler.SpawnEggHandler;
import adver.sarius.ssb.handler.SpawnPointHandler;
import adver.sarius.ssb.proxy.CommonProxy;
import adver.sarius.ssb.recipe.ModRecipes;
import adver.sarius.ssb.villager.VillagerTradingChanger;

@Mod(modid = SimpleSkyBlockMod.MODID, 
	name = SimpleSkyBlockMod.NAME, 
	version = SimpleSkyBlockMod.VERSION, 
	acceptedMinecraftVersions = "[1.10,)", 
	dependencies = "required-after:Forge@[12.18.1.2039,)",
	guiFactory = "adver.sarius.ssb.config.SSBGuiFactory")
public class SimpleSkyBlockMod {

	public static final String MODID = "simpleskyblock";
	public static final String NAME = "SimpleSkyBlock";
	public static final String VERSION = "${version}";
	public static final WorldTypeSSB WORLD_TYPE_SSB = new WorldTypeSSB("simpleSkyBlock");
	public static Logger logger;
	public static SSBConfig config;
	
	@Mod.Instance(MODID)
	public static SimpleSkyBlockMod instance;
	
	@SidedProxy(serverSide = "adver.sarius.ssb.proxy.CommonProxy", clientSide="adver.sarius.ssb.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		logger = event.getModLog();
		config = new SSBConfig(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		logger.info("Initializing mod ...");
		ModRecipes.init();
		MinecraftForge.EVENT_BUS.register(new LootTableHandler());
		MinecraftForge.EVENT_BUS.register(new LightningHandler());
		MinecraftForge.EVENT_BUS.register(new HarvestDropsHandler());
		MinecraftForge.EVENT_BUS.register(new BoneMealHandler());
		MinecraftForge.EVENT_BUS.register(new SpawnPointHandler());
		MinecraftForge.EVENT_BUS.register(new SpawnEggHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new FossilGeneratorHandler());
		if(event.getSide() == Side.CLIENT){ // TODO: Time for a proxy?
			MinecraftForge.EVENT_BUS.register(new GUIHandler()); // do not call on dedicated server!
		}
		MinecraftForge.EVENT_BUS.register(config);
		
		VillagerTradingChanger.registerVillager();
		this.registerWorldType();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event){
	}
	
	private void registerWorldType(){		
		DimensionType dimOver = DimensionType.register("Overworld", "", 0, WorldProviderSurfaceSSB.class, true);
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerDimension(0, dimOver);

		MapGenStructureIO.registerStructure(StructureOceanMonumentSSB.StartMonument.class, "MonumentSSB");
		StructureOceanMonumentPiecesSSB.registerOceanMonumentPieces();

		MapGenStructureIO.registerStructure(MapGenStrongholdSSB.Start.class, "StrongholdSSB");
		StructureStrongholdPiecesSSB.registerStrongholdPieces();
		
		MapGenStructureIO.registerStructure(MapGenScatteredFeatureSSB.Start.class, "TempleSSB");
		ComponentScatteredFeaturePiecesSSB.registerScatteredFeaturePieces();
		
		DimensionType dimHell = DimensionType.register("Nether", "_nether", -1, WorldProviderHellSSB.class, true);
		DimensionManager.unregisterDimension(-1);
		DimensionManager.registerDimension(-1, dimHell);
	}
	
	public static boolean useSSBGen(World world){
		return world.getWorldType() == WORLD_TYPE_SSB;
	}
}