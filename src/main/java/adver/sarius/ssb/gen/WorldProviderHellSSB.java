package adver.sarius.ssb.gen;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderHellSSB extends WorldProviderHell{
	
	@Override
	public IChunkGenerator createChunkGenerator(){
		if(SimpleSkyBlockMod.useSSBGen(super.worldObj)){
			return new ChunkProviderHellSSB(this.worldObj, this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getSeed());
		}else{
			return super.createChunkGenerator();
        }
    }
}