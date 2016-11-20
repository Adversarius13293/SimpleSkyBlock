package adver.sarius.ssb.handler;

import java.util.List;
import java.util.Random;

import adver.sarius.ssb.gen.WorldGeneratorSpawnIsland;
import adver.sarius.ssb.gen.WorldTypeSSB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.event.world.WorldEvent.CreateSpawnPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnPointHandler {

	// only called once by server
	@SubscribeEvent
	public void onCreatingSpawnPoint(CreateSpawnPosition event){
		if(event.getWorld().getWorldType() instanceof WorldTypeSSB){
			World worldIn = event.getWorld();
			
	        BiomeProvider biomeprovider = worldIn.provider.getBiomeProvider();
	        List<Biome> list = biomeprovider.getBiomesToSpawnIn();
	        Random random = new Random(worldIn.getSeed());
	        BlockPos blockpos = biomeprovider.findBiomePosition(0, 0, 256, list, random);
	        int i = 8;
	        int j = worldIn.provider.getAverageGroundLevel();
	        int k = 8;

	        if (blockpos != null)
	        {
	            i = blockpos.getX();
	            k = blockpos.getZ();
	        }
	        
	        // do I want/need this? 
	        i += random.nextInt(64) - random.nextInt(64);
	        k += random.nextInt(64) - random.nextInt(64);

	        worldIn.getWorldInfo().getGameRulesInstance().addGameRule("spawnRadius", "0", GameRules.ValueType.NUMERICAL_VALUE);
	        BlockPos spawnPos = new BlockPos(i, j, k);
	        worldIn.getWorldInfo().setSpawn(spawnPos);
			new WorldGeneratorSpawnIsland(event.getSettings().isBonusChestEnabled()).generate(worldIn, random, spawnPos);
			
			event.setCanceled(true);
		}
	}
}