package adver.sarius.ssb.gen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldTypeSSB extends WorldType {

	public WorldTypeSSB(String name) {
		// auto register type
		super(name);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkProviderOverworldSSB(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
	}
}