package adver.sarius.ssb.gen;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderHellSSB extends WorldProviderHell{
	
	@Override
	public IChunkGenerator createChunkGenerator(){
		if(SimpleSkyBlockMod.useSSBGen(super.world)){
			return new ChunkProviderHellSSB(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
		}else{
			return super.createChunkGenerator();
        }
    }
}