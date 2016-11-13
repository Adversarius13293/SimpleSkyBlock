package adver.sarius.ssb.gen;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderHellSSB extends WorldProviderHell{
	
	@Override
	public IChunkGenerator createChunkGenerator(){
		if(super.worldObj.getWorldType() instanceof WorldTypeSSB){
			return new ChunkProviderHellSSB(this.worldObj, this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getSeed());
		}else{
			return super.createChunkGenerator();
        }
    }
}