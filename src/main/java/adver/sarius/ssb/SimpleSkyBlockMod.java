package adver.sarius.ssb;

import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
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
import adver.sarius.ssb.handler.HarvestDropsHandler;
import adver.sarius.ssb.handler.LightningHandler;
import adver.sarius.ssb.handler.LootTableHandler;
import adver.sarius.ssb.handler.SpawnEggHandler;
import adver.sarius.ssb.handler.SpawnPointHandler;
import adver.sarius.ssb.proxy.CommonProxy;
import adver.sarius.ssb.recipe.ModRecipes;
import adver.sarius.ssb.villager.VillagerTradingChanger;

@Mod(modid = SimpleSkyBlockMod.MODID, name = SimpleSkyBlockMod.NAME, 
	version = SimpleSkyBlockMod.VERSION, acceptedMinecraftVersions = "[1.10,)", dependencies = "required-after:Forge@[12.18.2.2099,)")
public class SimpleSkyBlockMod {

	public static final String MODID = "simpleskyblock";
	public static final String NAME = "SimpleSkyBlock";
	public static final String VERSION = "${version}";
	public static final WorldTypeSSB WORLD_TYPE_SSB = new WorldTypeSSB("simpleSkyBlock");
	@Mod.Instance(MODID)
	public static SimpleSkyBlockMod instance;
	
	@SidedProxy(serverSide = "adver.sarius.ssb.proxy.CommonProxy", clientSide="adver.sarius.ssb.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		ModRecipes.init();
		MinecraftForge.EVENT_BUS.register(new LootTableHandler());
		MinecraftForge.EVENT_BUS.register(new LightningHandler());
		MinecraftForge.EVENT_BUS.register(new HarvestDropsHandler());
		MinecraftForge.EVENT_BUS.register(new BoneMealHandler());
		MinecraftForge.EVENT_BUS.register(new SpawnPointHandler());
		MinecraftForge.EVENT_BUS.register(new SpawnEggHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new FossilGeneratorHandler());
//		MinecraftForge.EVENT_BUS.register(new GUIHandler()); // only on client side!
		
		VillagerTradingChanger.registerVillager();
		this.registerWorldType();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event){
//		((ServerCommandManager)event.getServer().getCommandManager()).registerCommand(new CommandTesting());
	}
	
	private void registerWorldType(){
		WorldType worldType = WORLD_TYPE_SSB;
		
		DimensionType dimOver = DimensionType.register("OverworldSSB", "", 0, WorldProviderSurfaceSSB.class, true);
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerDimension(0, dimOver);

		MapGenStructureIO.registerStructure(StructureOceanMonumentSSB.StartMonument.class, "MonumentSSB");
		StructureOceanMonumentPiecesSSB.registerOceanMonumentPieces();

		MapGenStructureIO.registerStructure(MapGenStrongholdSSB.Start.class, "StrongholdSSB");
		StructureStrongholdPiecesSSB.registerStrongholdPieces();
		
		MapGenStructureIO.registerStructure(MapGenScatteredFeatureSSB.Start.class, "TempleSSB");
		ComponentScatteredFeaturePiecesSSB.registerScatteredFeaturePieces();
		
		DimensionType dimHell = DimensionType.register("NetherSSB", "_nether", -1, WorldProviderHellSSB.class, true);
		DimensionManager.unregisterDimension(-1);
		DimensionManager.registerDimension(-1, dimHell);
	}
	
	public static boolean useSSBGen(World world){
		return world.getWorldType() == WORLD_TYPE_SSB;
	}
}