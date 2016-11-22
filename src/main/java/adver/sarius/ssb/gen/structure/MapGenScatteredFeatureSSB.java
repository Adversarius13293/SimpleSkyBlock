package adver.sarius.ssb.gen.structure;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenScatteredFeatureSSB extends MapGenScatteredFeature {

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenScatteredFeatureSSB.Start(this.world, this.rand, chunkX, chunkZ);
    }
	
	@Override
	public boolean isSwampHut(BlockPos p_175798_1_)
    {
        StructureStart structurestart = this.getStructureAt(p_175798_1_);

        if (structurestart != null && structurestart instanceof MapGenScatteredFeatureSSB.Start && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.getComponents().get(0);
            return structurecomponent instanceof ComponentScatteredFeaturePiecesSSB.SwampHut;
        }
        else
        {
            return false;
        }
    }
	
	public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
        {
            super(chunkX, chunkZ);

            if (biomeIn != Biomes.JUNGLE && biomeIn != Biomes.JUNGLE_HILLS)
            {
                if (biomeIn == Biomes.SWAMPLAND)
                {
                    ComponentScatteredFeaturePiecesSSB.SwampHut componentscatteredfeaturepieces$swamphut = new ComponentScatteredFeaturePiecesSSB.SwampHut(random, chunkX * 16, chunkZ * 16);
                    this.components.add(componentscatteredfeaturepieces$swamphut);
                }
                else if (biomeIn != Biomes.DESERT && biomeIn != Biomes.DESERT_HILLS)
                {
                    if (biomeIn == Biomes.ICE_PLAINS || biomeIn == Biomes.COLD_TAIGA)
                    {
                        ComponentScatteredFeaturePiecesSSB.Igloo componentscatteredfeaturepieces$igloo = new ComponentScatteredFeaturePiecesSSB.Igloo(random, chunkX * 16, chunkZ * 16);
                        this.components.add(componentscatteredfeaturepieces$igloo);
                    }
                }
                else
                {
                    ComponentScatteredFeaturePiecesSSB.DesertPyramid componentscatteredfeaturepieces$desertpyramid = new ComponentScatteredFeaturePiecesSSB.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                    this.components.add(componentscatteredfeaturepieces$desertpyramid);
                }
            }
            else
            {
                ComponentScatteredFeaturePiecesSSB.JunglePyramid componentscatteredfeaturepieces$junglepyramid = new ComponentScatteredFeaturePiecesSSB.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$junglepyramid);
            }

            this.updateBoundingBox();
        }
    }
	
}