package adver.sarius.ssb.gen;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderSurfaceSSB extends WorldProviderSurface {

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.OVERWORLD;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator(){
		// I am overriding the vanilla dimension. So I have to return the vanilla generator when generating normal worlds.
		if(super.worldObj.getWorldType() instanceof WorldTypeSSB){
			return new ChunkProviderOverworldSSB(this.worldObj, this.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getWorldInfo().getGeneratorOptions());
		} else{
			return super.worldObj.getWorldInfo().getTerrainType().getChunkGenerator(worldObj, worldObj.getWorldInfo().getGeneratorOptions());
		}
	}
}