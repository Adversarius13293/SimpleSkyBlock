package adver.sarius.ssb.gen;

import adver.sarius.ssb.SimpleSkyBlockMod;
import adver.sarius.ssb.config.SSBConfig;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderHellSSB extends WorldProviderHell{
	
	@Override
	public IChunkGenerator createChunkGenerator(){
		if(SimpleSkyBlockMod.useSSBGen(super.world) && !SSBConfig.useDefaultNether){
			return new ChunkProviderHellSSB(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
		}else{
			return super.createChunkGenerator();
        }
    }
}