package adver.sarius.ssb.gen.structure;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentScatteredFeaturePiecesSSB
{
    public static void registerScatteredFeaturePieces()
    {
        MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePiecesSSB.DesertPyramid.class, "TeDPSSB");
        MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePiecesSSB.JunglePyramid.class, "TeJPSSB");
        MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePiecesSSB.SwampHut.class, "TeSHSSB");
        MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePiecesSSB.Igloo.class, "IgluSSB");
    }

    abstract static class Feature extends StructureComponent
    {
        /** The size of the bounding box for this feature in the X axis */
        protected int scatteredFeatureSizeX;
        /** The size of the bounding box for this feature in the Y axis */
        protected int scatteredFeatureSizeY;
        /** The size of the bounding box for this feature in the Z axis */
        protected int scatteredFeatureSizeZ;
        protected int horizontalPos = -1;

        public Feature()
        {
        }

        protected Feature(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
        {
            super(0);
            this.scatteredFeatureSizeX = sizeX;
            this.scatteredFeatureSizeY = sizeY;
            this.scatteredFeatureSizeZ = sizeZ;
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("Width", this.scatteredFeatureSizeX);
            tagCompound.setInteger("Height", this.scatteredFeatureSizeY);
            tagCompound.setInteger("Depth", this.scatteredFeatureSizeZ);
            tagCompound.setInteger("HPos", this.horizontalPos);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.scatteredFeatureSizeX = tagCompound.getInteger("Width");
            this.scatteredFeatureSizeY = tagCompound.getInteger("Height");
            this.scatteredFeatureSizeZ = tagCompound.getInteger("Depth");
            this.horizontalPos = tagCompound.getInteger("HPos");
        }

        /**
         * Calculates and offsets this structure boundingbox to average ground level
         */
        protected boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset)
        {
            if (this.horizontalPos >= 0)
            {
                return true;
            }
            else
            {
                int i = 0;
                int j = 0;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
                {
                    for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                    {
                        blockpos$mutableblockpos.setPos(l, 64, k);

                        if (structurebb.isVecInside(blockpos$mutableblockpos))
                        {
                            i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel());
                            ++j;
                        }
                    }
                }

                if (j == 0)
                {
                    return false;
                }
                else
                {
                    this.horizontalPos = i / j;
                    this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
                    return true;
                }
            }
        }
    }
    
    public static class DesertPyramid extends ComponentScatteredFeaturePiecesSSB.Feature
        {
            private final boolean[] hasPlacedChest = new boolean[4];

            public DesertPyramid()
            {
            }

            public DesertPyramid(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_)
            {
                super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("hasPlacedChest0", this.hasPlacedChest[0]);
                tagCompound.setBoolean("hasPlacedChest1", this.hasPlacedChest[1]);
                tagCompound.setBoolean("hasPlacedChest2", this.hasPlacedChest[2]);
                tagCompound.setBoolean("hasPlacedChest3", this.hasPlacedChest[3]);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.hasPlacedChest[0] = tagCompound.getBoolean("hasPlacedChest0");
                this.hasPlacedChest[1] = tagCompound.getBoolean("hasPlacedChest1");
                this.hasPlacedChest[2] = tagCompound.getBoolean("hasPlacedChest2");
                this.hasPlacedChest[3] = tagCompound.getBoolean("hasPlacedChest3");
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {

//                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
//                {
//                    if (!this.hasPlacedChest[enumfacing.getHorizontalIndex()])
//                    {
//                        int k1 = enumfacing.getFrontOffsetX() * 2;
//                        int l1 = enumfacing.getFrontOffsetZ() * 2;
//                        this.hasPlacedChest[enumfacing.getHorizontalIndex()] = this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 10 + k1, -11, 10 + l1, LootTableList.CHESTS_DESERT_PYRAMID);
//                    }
//                }

                return true;
            }
        }

    
    public static class Igloo extends ComponentScatteredFeaturePiecesSSB.Feature
        {
            private static final ResourceLocation IGLOO_TOP_ID = new ResourceLocation("igloo/igloo_top");
            private static final ResourceLocation IGLOO_MIDDLE_ID = new ResourceLocation("igloo/igloo_middle");
            private static final ResourceLocation IGLOO_BOTTOM_ID = new ResourceLocation("igloo/igloo_bottom");

            public Igloo()
            {
            }

            public Igloo(Random rand, int x, int z)
            {
                super(rand, x, 64, z, 7, 5, 8);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
//                if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -1))
//                {
//                    return false;
//                }
                    return true;
            }
        }

    public static class JunglePyramid extends ComponentScatteredFeaturePiecesSSB.Feature
        {
            private boolean placedMainChest;
            private boolean placedHiddenChest;
            private boolean placedTrap1;
            private boolean placedTrap2;
            /** List of random stones to be generated in the Jungle Pyramid. */

            public JunglePyramid()
            {
            }

            public JunglePyramid(Random rand, int x, int z)
            {
                super(rand, x, 64, z, 12, 10, 15);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("placedMainChest", this.placedMainChest);
                tagCompound.setBoolean("placedHiddenChest", this.placedHiddenChest);
                tagCompound.setBoolean("placedTrap1", this.placedTrap1);
                tagCompound.setBoolean("placedTrap2", this.placedTrap2);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.placedMainChest = tagCompound.getBoolean("placedMainChest");
                this.placedHiddenChest = tagCompound.getBoolean("placedHiddenChest");
                this.placedTrap1 = tagCompound.getBoolean("placedTrap1");
                this.placedTrap2 = tagCompound.getBoolean("placedTrap2");
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
//                if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0))
//                {
//                    return false;
//                }
                    return true;
            }
        }

    public static class SwampHut extends ComponentScatteredFeaturePiecesSSB.Feature
        {
            /** Whether this swamp hut has a witch. */
            private boolean hasWitch;

            public SwampHut()
            {
            }

            public SwampHut(Random p_i2066_1_, int p_i2066_2_, int p_i2066_3_)
            {
                super(p_i2066_1_, p_i2066_2_, 64, p_i2066_3_, 7, 7, 9);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Witch", this.hasWitch);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.hasWitch = tagCompound.getBoolean("Witch");
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
//                if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0))
//                {
//                    return false;
//                }
            	return true;
            }
        }
}