package adver.sarius.ssb.gen;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderSurfaceSSB extends WorldProviderSurface {

	@Override
	public IChunkGenerator createChunkGenerator(){
		// I am overriding the vanilla dimension. So I have to return the vanilla generator when generating normal worlds.
		if(SimpleSkyBlockMod.useSSBGen(super.worldObj)){
			return new ChunkProviderOverworldSSB(this.worldObj, this.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getWorldInfo().getGeneratorOptions());
		} else{
			return super.createChunkGenerator();
		}
	}
}