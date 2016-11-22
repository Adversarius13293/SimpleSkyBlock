package adver.sarius.ssb.gen;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderSurfaceSSB extends WorldProviderSurface {

	@Override
	public IChunkGenerator createChunkGenerator(){
		// I am overriding the vanilla dimension. So I have to return the vanilla generator when generating normal worlds.
		if(SimpleSkyBlockMod.useSSBGen(super.world)){
			return new ChunkProviderOverworldSSB(this.world, this.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getWorldInfo().getGeneratorOptions());
		} else{
			return super.createChunkGenerator();
		}
	}
}