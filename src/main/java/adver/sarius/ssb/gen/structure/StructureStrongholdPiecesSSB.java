package adver.sarius.ssb.gen.structure;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import adver.sarius.ssb.config.SSBConfig;

import com.google.common.collect.Lists;

public class StructureStrongholdPiecesSSB
{
    private static final StructureStrongholdPiecesSSB.PieceWeight[] PIECE_WEIGHTS = new StructureStrongholdPiecesSSB.PieceWeight[] {new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.Straight.class, 40, 0), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.Prison.class, 5, 5), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.LeftTurn.class, 20, 0), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.RightTurn.class, 20, 0), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.RoomCrossing.class, 10, 6), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.StairsStraight.class, 5, 5), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.Stairs.class, 5, 5), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.Crossing.class, 5, 4), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.ChestCorridor.class, 5, 4), new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.Library.class, 10, 2)
    {
        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 4;
        }
    }, new StructureStrongholdPiecesSSB.PieceWeight(StructureStrongholdPiecesSSB.PortalRoom.class, 20, 1)
    {
        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 5;
        }
    }
                                                                                                                             };
    private static List<StructureStrongholdPiecesSSB.PieceWeight> structurePieceList;
    private static Class <? extends StructureStrongholdPiecesSSB.Stronghold > strongComponentType;
    static int totalWeight;
    private static final StructureStrongholdPiecesSSB.Stones STRONGHOLD_STONES = new StructureStrongholdPiecesSSB.Stones();

    public static void registerStrongholdPieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.ChestCorridor.class, "SHCCSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Corridor.class, "SHFCSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Crossing.class, "SH5CSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.LeftTurn.class, "SHLTSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Library.class, "SHLiSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.PortalRoom.class, "SHPRSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Prison.class, "SHPHSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.RightTurn.class, "SHRTSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.RoomCrossing.class, "SHRCSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Stairs.class, "SHSDSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Stairs2.class, "SHStartSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.Straight.class, "SHSSSB");
        MapGenStructureIO.registerStructureComponent(StructureStrongholdPiecesSSB.StairsStraight.class, "SHSSDSSB");
    }

    /**
     * sets up Arrays with the Structure pieces and their weights
     */
    public static void prepareStructurePieces()
    {
        structurePieceList = Lists.<StructureStrongholdPiecesSSB.PieceWeight>newArrayList();

        for (StructureStrongholdPiecesSSB.PieceWeight StructureStrongholdPiecesSSB$pieceweight : PIECE_WEIGHTS)
        {
            StructureStrongholdPiecesSSB$pieceweight.instancesSpawned = 0;
            structurePieceList.add(StructureStrongholdPiecesSSB$pieceweight);
        }

        strongComponentType = null;
    }

    private static boolean canAddStructurePieces()
    {
        boolean flag = false;
        totalWeight = 0;

        for (StructureStrongholdPiecesSSB.PieceWeight StructureStrongholdPiecesSSB$pieceweight : structurePieceList)
        {
            if (StructureStrongholdPiecesSSB$pieceweight.instancesLimit > 0 && StructureStrongholdPiecesSSB$pieceweight.instancesSpawned < StructureStrongholdPiecesSSB$pieceweight.instancesLimit)
            {
                flag = true;
            }

            totalWeight += StructureStrongholdPiecesSSB$pieceweight.pieceWeight;
        }

        return flag;
    }

    private static StructureStrongholdPiecesSSB.Stronghold findAndCreatePieceFactory(Class <? extends StructureStrongholdPiecesSSB.Stronghold > clazz, List<StructureComponent> p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, @Nullable EnumFacing p_175954_6_, int p_175954_7_)
    {
        StructureStrongholdPiecesSSB.Stronghold StructureStrongholdPiecesSSB$stronghold = null;

        if (clazz == StructureStrongholdPiecesSSB.Straight.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.Straight.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.Prison.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.Prison.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.LeftTurn.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.LeftTurn.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.RightTurn.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.RightTurn.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.RoomCrossing.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.RoomCrossing.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.StairsStraight.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.StairsStraight.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.Stairs.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.Stairs.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.Crossing.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.Crossing.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.ChestCorridor.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.ChestCorridor.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.Library.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.Library.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (clazz == StructureStrongholdPiecesSSB.PortalRoom.class)
        {
            StructureStrongholdPiecesSSB$stronghold = StructureStrongholdPiecesSSB.PortalRoom.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }

        return StructureStrongholdPiecesSSB$stronghold;
    }

    private static StructureStrongholdPiecesSSB.Stronghold generatePieceFromSmallDoor(StructureStrongholdPiecesSSB.Stairs2 p_175955_0_, List<StructureComponent> p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, EnumFacing p_175955_6_, int p_175955_7_)
    {
        if (!canAddStructurePieces())
        {
            return null;
        }
        else
        {
            if (strongComponentType != null)
            {
                StructureStrongholdPiecesSSB.Stronghold StructureStrongholdPiecesSSB$stronghold = findAndCreatePieceFactory(strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
                strongComponentType = null;

                if (StructureStrongholdPiecesSSB$stronghold != null)
                {
                    return StructureStrongholdPiecesSSB$stronghold;
                }
            }

            int j = 0;

            while (j < 5)
            {
                ++j;
                int i = p_175955_2_.nextInt(totalWeight);

                for (StructureStrongholdPiecesSSB.PieceWeight StructureStrongholdPiecesSSB$pieceweight : structurePieceList)
                {
                    i -= StructureStrongholdPiecesSSB$pieceweight.pieceWeight;

                    if (i < 0)
                    {
                        if (!StructureStrongholdPiecesSSB$pieceweight.canSpawnMoreStructuresOfType(p_175955_7_) || StructureStrongholdPiecesSSB$pieceweight == p_175955_0_.strongholdPieceWeight)
                        {
                            break;
                        }

                        StructureStrongholdPiecesSSB.Stronghold StructureStrongholdPiecesSSB$stronghold1 = findAndCreatePieceFactory(StructureStrongholdPiecesSSB$pieceweight.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);

                        if (StructureStrongholdPiecesSSB$stronghold1 != null)
                        {
                            ++StructureStrongholdPiecesSSB$pieceweight.instancesSpawned;
                            p_175955_0_.strongholdPieceWeight = StructureStrongholdPiecesSSB$pieceweight;

                            if (!StructureStrongholdPiecesSSB$pieceweight.canSpawnMoreStructures())
                            {
                                structurePieceList.remove(StructureStrongholdPiecesSSB$pieceweight);
                            }

                            return StructureStrongholdPiecesSSB$stronghold1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureStrongholdPiecesSSB.Corridor.findPieceBox(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);

            if (structureboundingbox != null && structureboundingbox.minY > 1)
            {
                return new StructureStrongholdPiecesSSB.Corridor(p_175955_7_, p_175955_2_, structureboundingbox, p_175955_6_);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent generateAndAddPiece(StructureStrongholdPiecesSSB.Stairs2 p_175953_0_, List<StructureComponent> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, @Nullable EnumFacing p_175953_6_, int p_175953_7_)
    {
        if (p_175953_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = generatePieceFromSmallDoor(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);

            if (structurecomponent != null)
            {
                p_175953_1_.add(structurecomponent);
                p_175953_0_.pendingChildren.add(structurecomponent);
            }

            return structurecomponent;
        }
        else
        {
            return null;
        }
    }

    public static class ChestCorridor extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean hasMadeChest;

            public ChestCorridor()
            {
            }

            public ChestCorridor(int p_i45582_1_, Random p_i45582_2_, StructureBoundingBox p_i45582_3_, EnumFacing p_i45582_4_)
            {
                super(p_i45582_1_);
                this.setCoordBaseMode(p_i45582_4_);
                this.entryDoor = this.getRandomDoor(p_i45582_2_);
                this.boundingBox = p_i45582_3_;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Chest", this.hasMadeChest);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.hasMadeChest = tagCompound.getBoolean("Chest");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
            }

            public static StructureStrongholdPiecesSSB.ChestCorridor createPiece(List<StructureComponent> p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, EnumFacing p_175868_5_, int p_175868_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175868_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.ChestCorridor(p_175868_6_, p_175868_1_, structureboundingbox, p_175868_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                { // TODO: remove?
                    return false;
                }
                else
                {
                    if (!this.hasMadeChest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3))))
                    {
                        this.hasMadeChest = true;
                    }
                    return true;
                }
            }
        }

    public static class Corridor extends StructureStrongholdPiecesSSB.Stronghold
        {
            private int steps;

            public Corridor()
            {
            }

            public Corridor(int p_i45581_1_, Random p_i45581_2_, StructureBoundingBox p_i45581_3_, EnumFacing p_i45581_4_)
            {
                super(p_i45581_1_);
                this.setCoordBaseMode(p_i45581_4_);
                this.boundingBox = p_i45581_3_;
                this.steps = p_i45581_4_ != EnumFacing.NORTH && p_i45581_4_ != EnumFacing.SOUTH ? p_i45581_3_.getXSize() : p_i45581_3_.getZSize();
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("Steps", this.steps);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.steps = tagCompound.getInteger("Steps");
            }

            public static StructureBoundingBox findPieceBox(List<StructureComponent> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, EnumFacing p_175869_5_)
            {
//                int i = 3;
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
                StructureComponent structurecomponent = StructureComponent.findIntersecting(p_175869_0_, structureboundingbox);

                if (structurecomponent == null)
                {
                    return null;
                }
                else
                {
                    if (structurecomponent.getBoundingBox().minY == structureboundingbox.minY)
                    {
                        for (int j = 3; j >= 1; --j)
                        {
                            structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);

                            if (!structurecomponent.getBoundingBox().intersectsWith(structureboundingbox))
                            {
                                return StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
                            }
                        }
                    }

                    return null;
                }
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class Crossing extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean leftLow;
            private boolean leftHigh;
            private boolean rightLow;
            private boolean rightHigh;

            public Crossing()
            {
            }

            public Crossing(int p_i45580_1_, Random p_i45580_2_, StructureBoundingBox p_i45580_3_, EnumFacing p_i45580_4_)
            {
                super(p_i45580_1_);
                this.setCoordBaseMode(p_i45580_4_);
                this.entryDoor = this.getRandomDoor(p_i45580_2_);
                this.boundingBox = p_i45580_3_;
                this.leftLow = p_i45580_2_.nextBoolean();
                this.leftHigh = p_i45580_2_.nextBoolean();
                this.rightLow = p_i45580_2_.nextBoolean();
                this.rightHigh = p_i45580_2_.nextInt(3) > 0;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("leftLow", this.leftLow);
                tagCompound.setBoolean("leftHigh", this.leftHigh);
                tagCompound.setBoolean("rightLow", this.rightLow);
                tagCompound.setBoolean("rightHigh", this.rightHigh);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.leftLow = tagCompound.getBoolean("leftLow");
                this.leftHigh = tagCompound.getBoolean("leftHigh");
                this.rightLow = tagCompound.getBoolean("rightLow");
                this.rightHigh = tagCompound.getBoolean("rightHigh");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                int i = 3;
                int j = 5;
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.NORTH)
                {
                    i = 8 - i;
                    j = 8 - j;
                }

                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 5, 1);

                if (this.leftLow)
                {
                    this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, i, 1);
                }

                if (this.leftHigh)
                {
                    this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, j, 7);
                }

                if (this.rightLow)
                {
                    this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, i, 1);
                }

                if (this.rightHigh)
                {
                    this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, j, 7);
                }
            }

            public static StructureStrongholdPiecesSSB.Crossing createPiece(List<StructureComponent> p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, EnumFacing p_175866_5_, int p_175866_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175866_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.Crossing(p_175866_6_, p_175866_1_, structureboundingbox, p_175866_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class LeftTurn extends StructureStrongholdPiecesSSB.Stronghold
        {
            public LeftTurn()
            {
            }

            public LeftTurn(int p_i45579_1_, Random p_i45579_2_, StructureBoundingBox p_i45579_3_, EnumFacing p_i45579_4_)
            {
                super(p_i45579_1_);
                this.setCoordBaseMode(p_i45579_4_);
                this.entryDoor = this.getRandomDoor(p_i45579_2_);
                this.boundingBox = p_i45579_3_;
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
                {
                    this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
                }
                else
                {
                    this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
                }
            }

            public static StructureStrongholdPiecesSSB.LeftTurn createPiece(List<StructureComponent> p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, EnumFacing p_175867_5_, int p_175867_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175867_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.LeftTurn(p_175867_6_, p_175867_1_, structureboundingbox, p_175867_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class Library extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean isLargeRoom;

            public Library()
            {
            }

            public Library(int p_i45578_1_, Random p_i45578_2_, StructureBoundingBox p_i45578_3_, EnumFacing p_i45578_4_)
            {
                super(p_i45578_1_);
                this.setCoordBaseMode(p_i45578_4_);
                this.entryDoor = this.getRandomDoor(p_i45578_2_);
                this.boundingBox = p_i45578_3_;
                this.isLargeRoom = p_i45578_3_.getYSize() > 6;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Tall", this.isLargeRoom);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.isLargeRoom = tagCompound.getBoolean("Tall");
            }

            public static StructureStrongholdPiecesSSB.Library createPiece(List<StructureComponent> p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, EnumFacing p_175864_5_, int p_175864_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);

                if (!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.findIntersecting(p_175864_0_, structureboundingbox) != null)
                {
                    structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);

                    if (!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.findIntersecting(p_175864_0_, structureboundingbox) != null)
                    {
                        return null;
                    }
                }

                return new StructureStrongholdPiecesSSB.Library(p_175864_6_, p_175864_1_, structureboundingbox, p_175864_5_);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class PortalRoom extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean hasSpawner;

            public PortalRoom()
            {
            }

            public PortalRoom(int p_i45577_1_, Random p_i45577_2_, StructureBoundingBox p_i45577_3_, EnumFacing p_i45577_4_)
            {
                super(p_i45577_1_);
                this.setCoordBaseMode(p_i45577_4_);
                this.boundingBox = p_i45577_3_;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Mob", this.hasSpawner);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.hasSpawner = tagCompound.getBoolean("Mob");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                if (componentIn != null)
                {
                    ((StructureStrongholdPiecesSSB.Stairs2)componentIn).strongholdPortalRoom = this;
                }
            }

            public static StructureStrongholdPiecesSSB.PortalRoom createPiece(List<StructureComponent> p_175865_0_, Random p_175865_1_, int p_175865_2_, int p_175865_3_, int p_175865_4_, EnumFacing p_175865_5_, int p_175865_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175865_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.PortalRoom(p_175865_6_, p_175865_1_, structureboundingbox, p_175865_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
            	if(!SSBConfig.createStrongholdPortalRoom){
            		return true;
            	}
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 10, 7, 15, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, StructureStrongholdPiecesSSB.Stronghold.Door.GRATES, 4, 1, 0);
                int i = 6;
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, i, 1, 1, i, 14, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, i, 1, 9, i, 14, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 1, 8, i, 2, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 14, 8, i, 14, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 2, 1, 4, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, 1, 1, 9, 1, 4, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 1, 1, 3, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 1, 9, 1, 3, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 3, 1, 8, 7, 1, 12, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 6, 1, 11, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);

                for (int j = 3; j < 14; j += 2)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, j, 0, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, j, 10, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                }

                for (int i1 = 2; i1 < 9; i1 += 2)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, 3, 15, i1, 4, 15, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                }

                IBlockState iblockstate3 = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 5, 6, 1, 7, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 2, 6, 6, 2, 7, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 3, 7, 6, 3, 7, false, randomIn, StructureStrongholdPiecesSSB.STRONGHOLD_STONES);

                for (int k = 4; k <= 6; ++k)
                {
                    this.setBlockState(worldIn, iblockstate3, k, 1, 4, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate3, k, 2, 5, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate3, k, 3, 6, structureBoundingBoxIn);
                }

                IBlockState iblockstate4 = Blocks.END_PORTAL_FRAME.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.NORTH);
                IBlockState iblockstate = Blocks.END_PORTAL_FRAME.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.SOUTH);
                IBlockState iblockstate1 = Blocks.END_PORTAL_FRAME.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.EAST);
                IBlockState iblockstate2 = Blocks.END_PORTAL_FRAME.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.WEST);
                boolean flag = true;
                boolean[] aboolean = new boolean[12];

                for (int l = 0; l < aboolean.length; ++l)
                {
                    aboolean[l] = randomIn.nextFloat() > 0.9F;
                    flag &= aboolean[l];
                }

                this.setBlockState(worldIn, iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[0])), 4, 3, 8, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[1])), 5, 3, 8, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[2])), 6, 3, 8, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[3])), 4, 3, 12, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[4])), 5, 3, 12, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[5])), 6, 3, 12, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[6])), 3, 3, 9, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[7])), 3, 3, 10, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[8])), 3, 3, 11, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[9])), 7, 3, 9, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[10])), 7, 3, 10, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(aboolean[11])), 7, 3, 11, structureBoundingBoxIn);

                if (flag)
                {
                    IBlockState iblockstate5 = Blocks.END_PORTAL.getDefaultState();
                    this.setBlockState(worldIn, iblockstate5, 4, 3, 9, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 5, 3, 9, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 6, 3, 9, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 4, 3, 10, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 5, 3, 10, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 6, 3, 10, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 4, 3, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 5, 3, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate5, 6, 3, 11, structureBoundingBoxIn);
                }

                if (!this.hasSpawner)
                {
                    i = this.getYWithOffset(3);
                    BlockPos blockpos = new BlockPos(this.getXWithOffset(5, 6), i, this.getZWithOffset(5, 6));

                    if (structureBoundingBoxIn.isVecInside(blockpos))
                    {
                        this.hasSpawner = true;
                        worldIn.setBlockState(blockpos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                        TileEntity tileentity = worldIn.getTileEntity(blockpos);

                        if (tileentity instanceof TileEntityMobSpawner)
                        {
                            ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName("Silverfish");
                        }
                    }
                }
                return true;
            }
        }

    public static class Prison extends StructureStrongholdPiecesSSB.Stronghold
        {
            public Prison()
            {
            }

            public Prison(int p_i45576_1_, Random p_i45576_2_, StructureBoundingBox p_i45576_3_, EnumFacing p_i45576_4_)
            {
                super(p_i45576_1_);
                this.setCoordBaseMode(p_i45576_4_);
                this.entryDoor = this.getRandomDoor(p_i45576_2_);
                this.boundingBox = p_i45576_3_;
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
            }

            public static StructureStrongholdPiecesSSB.Prison createPiece(List<StructureComponent> p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, EnumFacing p_175860_5_, int p_175860_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175860_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.Prison(p_175860_6_, p_175860_1_, structureboundingbox, p_175860_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class RightTurn extends StructureStrongholdPiecesSSB.LeftTurn
        {
            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
                {
                    this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
                }
                else
                {
                    this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
                }
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class RoomCrossing extends StructureStrongholdPiecesSSB.Stronghold
        {
            protected int roomType;

            public RoomCrossing()
            {
            }

            public RoomCrossing(int p_i45575_1_, Random p_i45575_2_, StructureBoundingBox p_i45575_3_, EnumFacing p_i45575_4_)
            {
                super(p_i45575_1_);
                this.setCoordBaseMode(p_i45575_4_);
                this.entryDoor = this.getRandomDoor(p_i45575_2_);
                this.boundingBox = p_i45575_3_;
                this.roomType = p_i45575_2_.nextInt(5);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setInteger("Type", this.roomType);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.roomType = tagCompound.getInteger("Type");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 4, 1);
                this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 4);
                this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 4);
            }

            public static StructureStrongholdPiecesSSB.RoomCrossing createPiece(List<StructureComponent> p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, EnumFacing p_175859_5_, int p_175859_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175859_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.RoomCrossing(p_175859_6_, p_175859_1_, structureboundingbox, p_175859_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class Stairs extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean source;

            public Stairs()
            {
            }

            public Stairs(int p_i2081_1_, Random p_i2081_2_, int p_i2081_3_, int p_i2081_4_)
            {
                super(p_i2081_1_);
                this.source = true;
                this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(p_i2081_2_));
                this.entryDoor = StructureStrongholdPiecesSSB.Stronghold.Door.OPENING;

                if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
                {
                    this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                }
                else
                {
                    this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                }
            }

            public Stairs(int p_i45574_1_, Random p_i45574_2_, StructureBoundingBox p_i45574_3_, EnumFacing p_i45574_4_)
            {
                super(p_i45574_1_);
                this.source = false;
                this.setCoordBaseMode(p_i45574_4_);
                this.entryDoor = this.getRandomDoor(p_i45574_2_);
                this.boundingBox = p_i45574_3_;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Source", this.source);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.source = tagCompound.getBoolean("Source");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                if (this.source)
                {
                    StructureStrongholdPiecesSSB.strongComponentType = StructureStrongholdPiecesSSB.Crossing.class;
                }

                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
            }

            public static StructureStrongholdPiecesSSB.Stairs createPiece(List<StructureComponent> p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, EnumFacing p_175863_5_, int p_175863_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175863_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.Stairs(p_175863_6_, p_175863_1_, structureboundingbox, p_175863_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public static class Stairs2 extends StructureStrongholdPiecesSSB.Stairs
        {
            public StructureStrongholdPiecesSSB.PieceWeight strongholdPieceWeight;
            public StructureStrongholdPiecesSSB.PortalRoom strongholdPortalRoom;
            public List<StructureComponent> pendingChildren = Lists.<StructureComponent>newArrayList();

            public Stairs2()
            {
            }

            public Stairs2(int p_i2083_1_, Random p_i2083_2_, int p_i2083_3_, int p_i2083_4_)
            {
                super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
            }

            public BlockPos getBoundingBoxCenter()
            {
                return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getBoundingBoxCenter() : super.getBoundingBoxCenter();
            }
        }

    public static class StairsStraight extends StructureStrongholdPiecesSSB.Stronghold
        {
            public StairsStraight()
            {
            }

            public StairsStraight(int p_i45572_1_, Random p_i45572_2_, StructureBoundingBox p_i45572_3_, EnumFacing p_i45572_4_)
            {
                super(p_i45572_1_);
                this.setCoordBaseMode(p_i45572_4_);
                this.entryDoor = this.getRandomDoor(p_i45572_2_);
                this.boundingBox = p_i45572_3_;
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);
            }

            public static StructureStrongholdPiecesSSB.StairsStraight createPiece(List<StructureComponent> p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, EnumFacing p_175861_5_, int p_175861_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175861_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.StairsStraight(p_175861_6_, p_175861_1_, structureboundingbox, p_175861_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    static class Stones extends StructureComponent.BlockSelector
        {
            private Stones()
            {
            }

            /**
             * picks Block Ids and Metadata (Silverfish)
             */
            public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
            {
                if (p_75062_5_)
                {
                    float f = rand.nextFloat();

                    if (f < 0.2F)
                    {
                        this.blockstate = Blocks.STONEBRICK.getStateFromMeta(BlockStoneBrick.CRACKED_META);
                    }
                    else if (f < 0.5F)
                    {
                        this.blockstate = Blocks.STONEBRICK.getStateFromMeta(BlockStoneBrick.MOSSY_META);
                    }
                    else if (f < 0.55F)
                    {
                        this.blockstate = Blocks.MONSTER_EGG.getStateFromMeta(BlockSilverfish.EnumType.STONEBRICK.getMetadata());
                    }
                    else
                    {
                        this.blockstate = Blocks.STONEBRICK.getDefaultState();
                    }
                }
                else
                {
                    this.blockstate = Blocks.AIR.getDefaultState();
                }
            }
        }

    public static class Straight extends StructureStrongholdPiecesSSB.Stronghold
        {
            private boolean expandsX;
            private boolean expandsZ;

            public Straight()
            {
            }

            public Straight(int p_i45573_1_, Random p_i45573_2_, StructureBoundingBox p_i45573_3_, EnumFacing p_i45573_4_)
            {
                super(p_i45573_1_);
                this.setCoordBaseMode(p_i45573_4_);
                this.entryDoor = this.getRandomDoor(p_i45573_2_);
                this.boundingBox = p_i45573_3_;
                this.expandsX = p_i45573_2_.nextInt(2) == 0;
                this.expandsZ = p_i45573_2_.nextInt(2) == 0;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                super.writeStructureToNBT(tagCompound);
                tagCompound.setBoolean("Left", this.expandsX);
                tagCompound.setBoolean("Right", this.expandsZ);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                super.readStructureFromNBT(tagCompound);
                this.expandsX = tagCompound.getBoolean("Left");
                this.expandsZ = tagCompound.getBoolean("Right");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
            {
                this.getNextComponentNormal((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 1);

                if (this.expandsX)
                {
                    this.getNextComponentX((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 2);
                }

                if (this.expandsZ)
                {
                    this.getNextComponentZ((StructureStrongholdPiecesSSB.Stairs2)componentIn, listIn, rand, 1, 2);
                }
            }

            public static StructureStrongholdPiecesSSB.Straight createPiece(List<StructureComponent> p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, EnumFacing p_175862_5_, int p_175862_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
                /**
                 * returns false if the Structure Bounding Box goes below 10
                 */
                return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175862_0_, structureboundingbox) == null ? new StructureStrongholdPiecesSSB.Straight(p_175862_6_, p_175862_1_, structureboundingbox, p_175862_5_) : null;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

    public abstract static class Stronghold extends StructureComponent
        {
            protected StructureStrongholdPiecesSSB.Stronghold.Door entryDoor = StructureStrongholdPiecesSSB.Stronghold.Door.OPENING;

            public Stronghold()
            {
            }

            protected Stronghold(int p_i2087_1_)
            {
                super(p_i2087_1_);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound tagCompound)
            {
                tagCompound.setString("EntryDoor", this.entryDoor.name());
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound tagCompound)
            {
                this.entryDoor = StructureStrongholdPiecesSSB.Stronghold.Door.valueOf(tagCompound.getString("EntryDoor"));
            }

            /**
             * builds a door of the enumerated types (empty opening is a door)
             */
            protected void placeDoor(World worldIn, Random p_74990_2_, StructureBoundingBox p_74990_3_, StructureStrongholdPiecesSSB.Stronghold.Door p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_)
            {
                switch (p_74990_4_)
                {
                    case OPENING:
                        this.fillWithBlocks(worldIn, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                        break;
                    case WOOD_DOOR:
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        break;
                    case GRATES:
                        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        break;
                    case IRON_DOOR:
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockButton.FACING, EnumFacing.NORTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
                        this.setBlockState(worldIn, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockButton.FACING, EnumFacing.SOUTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
                }
            }

            protected StructureStrongholdPiecesSSB.Stronghold.Door getRandomDoor(Random p_74988_1_)
            {
                int i = p_74988_1_.nextInt(5);

                switch (i)
                {
                    case 0:
                    case 1:
                    default:
                        return StructureStrongholdPiecesSSB.Stronghold.Door.OPENING;
                    case 2:
                        return StructureStrongholdPiecesSSB.Stronghold.Door.WOOD_DOOR;
                    case 3:
                        return StructureStrongholdPiecesSSB.Stronghold.Door.GRATES;
                    case 4:
                        return StructureStrongholdPiecesSSB.Stronghold.Door.IRON_DOOR;
                }
            }

            /**
             * Gets the next component in any cardinal direction
             */
            protected StructureComponent getNextComponentNormal(StructureStrongholdPiecesSSB.Stairs2 p_74986_1_, List<StructureComponent> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, enumfacing, this.getComponentType());
                        case SOUTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType());
                        case WEST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());
                        case EAST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());
                    }
                }

                return null;
            }

            /**
             * Gets the next component in the +/- X direction
             */
            protected StructureComponent getNextComponentX(StructureStrongholdPiecesSSB.Stairs2 p_74989_1_, List<StructureComponent> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                        case SOUTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                        case WEST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        case EAST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }

                return null;
            }

            /**
             * Gets the next component in the +/- Z direction
             */
            protected StructureComponent getNextComponentZ(StructureStrongholdPiecesSSB.Stairs2 p_74987_1_, List<StructureComponent> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_)
            {
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != null)
                {
                    switch (enumfacing)
                    {
                        case NORTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                        case SOUTH:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                        case WEST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        case EAST:
                            return StructureStrongholdPiecesSSB.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }

                return null;
            }

            /**
             * returns false if the Structure Bounding Box goes below 10
             */
            protected static boolean canStrongholdGoDeeper(StructureBoundingBox p_74991_0_)
            {
                return p_74991_0_ != null && p_74991_0_.minY > 10;
            }

            public static enum Door
            {
                OPENING,
                WOOD_DOOR,
                GRATES,
                IRON_DOOR;
            }
        }

    static class PieceWeight
    {
        public Class <? extends StructureStrongholdPiecesSSB.Stronghold > pieceClass;
        /**
         * This basically keeps track of the 'epicness' of a structure. Epic structure components have a higher
         * 'weight', and Structures may only grow up to a certain 'weight' before generation is stopped
         */
        public final int pieceWeight;
        public int instancesSpawned;
        /** How many Structure Pieces of this type may spawn in a structure */
        public int instancesLimit;

        public PieceWeight(Class <? extends StructureStrongholdPiecesSSB.Stronghold > p_i2076_1_, int p_i2076_2_, int p_i2076_3_)
        {
            this.pieceClass = p_i2076_1_;
            this.pieceWeight = p_i2076_2_;
            this.instancesLimit = p_i2076_3_;
        }

        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }

        public boolean canSpawnMoreStructures()
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }
}