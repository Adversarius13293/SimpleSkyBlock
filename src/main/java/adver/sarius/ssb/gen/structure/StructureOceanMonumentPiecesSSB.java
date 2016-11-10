package adver.sarius.ssb.gen.structure;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

import com.google.common.collect.Lists;

public class StructureOceanMonumentPiecesSSB
{
	public static void registerOceanMonumentPieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.MonumentBuilding.class, "OMBSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.MonumentCoreRoom.class, "OMCRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.DoubleXRoom.class, "OMDXRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.DoubleXYRoom.class, "OMDXYRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.DoubleYRoom.class, "OMDYRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.DoubleYZRoom.class, "OMDYZRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.DoubleZRoom.class, "OMDZRSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.EntryRoom.class, "OMEntrySSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.Penthouse.class, "OMPenthouseSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.SimpleRoom.class, "OMSimpleSSB");
        MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPiecesSSB.SimpleTopRoom.class, "OMSimpleTSSB");
    }
	
    public static class DoubleXRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public DoubleXRoom()
            {
            }

            public DoubleXRoom(EnumFacing p_i45597_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45597_2_, Random p_i45597_3_)
            {
                super(1, p_i45597_1_, p_i45597_2_, 2, 1, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class DoubleXYRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public DoubleXYRoom()
            {
            }

            public DoubleXYRoom(EnumFacing p_i45596_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45596_2_, Random p_i45596_3_)
            {
                super(1, p_i45596_1_, p_i45596_2_, 2, 2, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class DoubleYRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public DoubleYRoom()
            {
            }

            public DoubleYRoom(EnumFacing p_i45595_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45595_2_, Random p_i45595_3_)
            {
                super(1, p_i45595_1_, p_i45595_2_, 1, 2, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class DoubleYZRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public DoubleYZRoom()
            {
            }

            public DoubleYZRoom(EnumFacing p_i45594_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45594_2_, Random p_i45594_3_)
            {
                super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class DoubleZRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public DoubleZRoom()
            {
            }

            public DoubleZRoom(EnumFacing p_i45593_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45593_2_, Random p_i45593_3_)
            {
                super(1, p_i45593_1_, p_i45593_2_, 1, 1, 2);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class EntryRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public EntryRoom()
            {
            }

            public EntryRoom(EnumFacing p_i45592_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45592_2_)
            {
                super(1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    // maybe I just need this, because it initializes everything?
    public static class MonumentBuilding extends StructureOceanMonumentPiecesSSB.Piece
        {
            private StructureOceanMonumentPiecesSSB.RoomDefinition sourceRoom;
            private StructureOceanMonumentPiecesSSB.RoomDefinition coreRoom;
            private final List<StructureOceanMonumentPiecesSSB.Piece> childPieces = Lists.<StructureOceanMonumentPiecesSSB.Piece>newArrayList();

            public MonumentBuilding()
            {
            }

            public MonumentBuilding(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, EnumFacing p_i45599_4_)
            {
                super(0);
                this.setCoordBaseMode(p_i45599_4_);
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing.getAxis() == EnumFacing.Axis.Z)
                {
                    this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
                }
                else
                {
                    this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
                }

                List<StructureOceanMonumentPiecesSSB.RoomDefinition> list = this.generateRoomGraph(p_i45599_1_);
                this.sourceRoom.claimed = true;
                this.childPieces.add(new StructureOceanMonumentPiecesSSB.EntryRoom(enumfacing, this.sourceRoom));
                this.childPieces.add(new StructureOceanMonumentPiecesSSB.MonumentCoreRoom(enumfacing, this.coreRoom, p_i45599_1_));
                List<StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper> list1 = Lists.<StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper>newArrayList();
                list1.add(new StructureOceanMonumentPiecesSSB.XYDoubleRoomFitHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.YZDoubleRoomFitHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.ZDoubleRoomFitHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.XDoubleRoomFitHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.YDoubleRoomFitHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.FitSimpleRoomTopHelper());
                list1.add(new StructureOceanMonumentPiecesSSB.FitSimpleRoomHelper());
                
                label294:for (StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition : list)
                {
                    if (!StructureOceanMonumentPiecesSSB$roomdefinition.claimed && !StructureOceanMonumentPiecesSSB$roomdefinition.isSpecial())
                    {
                        Iterator iterator = list1.iterator();
                        StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper StructureOceanMonumentPiecesSSB$monumentroomfithelper;

                        while (true)
                        {
                            if (!iterator.hasNext())
                            {
                                continue label294;
                            }

                            StructureOceanMonumentPiecesSSB$monumentroomfithelper = (StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper)iterator.next();

                            if (StructureOceanMonumentPiecesSSB$monumentroomfithelper.fits(StructureOceanMonumentPiecesSSB$roomdefinition))
                            {
                                break;
                            }
                        }

                        this.childPieces.add(StructureOceanMonumentPiecesSSB$monumentroomfithelper.create(enumfacing, StructureOceanMonumentPiecesSSB$roomdefinition, p_i45599_1_));
                    }
                }

                int j = this.boundingBox.minY;
                int k = this.getXWithOffset(9, 22);
                int l = this.getZWithOffset(9, 22);

                for (StructureOceanMonumentPiecesSSB.Piece StructureOceanMonumentPiecesSSB$piece : this.childPieces)
                {
                    StructureOceanMonumentPiecesSSB$piece.getBoundingBox().offset(k, j, l);
                }

                StructureBoundingBox structureboundingbox1 = StructureBoundingBox.createProper(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1), this.getXWithOffset(23, 21), this.getYWithOffset(8), this.getZWithOffset(23, 21));
                StructureBoundingBox structureboundingbox2 = StructureBoundingBox.createProper(this.getXWithOffset(34, 1), this.getYWithOffset(1), this.getZWithOffset(34, 1), this.getXWithOffset(56, 21), this.getYWithOffset(8), this.getZWithOffset(56, 21));
                StructureBoundingBox structureboundingbox = StructureBoundingBox.createProper(this.getXWithOffset(22, 22), this.getYWithOffset(13), this.getZWithOffset(22, 22), this.getXWithOffset(35, 35), this.getYWithOffset(17), this.getZWithOffset(35, 35));
                int i = p_i45599_1_.nextInt();
                this.childPieces.add(new StructureOceanMonumentPiecesSSB.WingRoom(enumfacing, structureboundingbox1, i++));
                this.childPieces.add(new StructureOceanMonumentPiecesSSB.WingRoom(enumfacing, structureboundingbox2, i++));
                this.childPieces.add(new StructureOceanMonumentPiecesSSB.Penthouse(enumfacing, structureboundingbox));
            }

            private List<StructureOceanMonumentPiecesSSB.RoomDefinition> generateRoomGraph(Random p_175836_1_)
            {
                StructureOceanMonumentPiecesSSB.RoomDefinition[] aStructureOceanMonumentPiecesSSB$roomdefinition = new StructureOceanMonumentPiecesSSB.RoomDefinition[75];

                for (int i = 0; i < 5; ++i)
                {
                    for (int j = 0; j < 4; ++j)
                    {
                        int k = 0;
                        int l = getRoomIndex(i, 0, j);
                        aStructureOceanMonumentPiecesSSB$roomdefinition[l] = new StructureOceanMonumentPiecesSSB.RoomDefinition(l);
                    }
                }

                for (int i2 = 0; i2 < 5; ++i2)
                {
                    for (int l2 = 0; l2 < 4; ++l2)
                    {
                        int k3 = 1;
                        int j4 = getRoomIndex(i2, 1, l2);
                        aStructureOceanMonumentPiecesSSB$roomdefinition[j4] = new StructureOceanMonumentPiecesSSB.RoomDefinition(j4);
                    }
                }

                for (int j2 = 1; j2 < 4; ++j2)
                {
                    for (int i3 = 0; i3 < 2; ++i3)
                    {
                        int l3 = 2;
                        int k4 = getRoomIndex(j2, 2, i3);
                        aStructureOceanMonumentPiecesSSB$roomdefinition[k4] = new StructureOceanMonumentPiecesSSB.RoomDefinition(k4);
                    }
                }

                this.sourceRoom = aStructureOceanMonumentPiecesSSB$roomdefinition[GRIDROOM_SOURCE_INDEX];

                for (int k2 = 0; k2 < 5; ++k2)
                {
                    for (int j3 = 0; j3 < 5; ++j3)
                    {
                        for (int i4 = 0; i4 < 3; ++i4)
                        {
                            int l4 = getRoomIndex(k2, i4, j3);

                            if (aStructureOceanMonumentPiecesSSB$roomdefinition[l4] != null)
                            {
                                for (EnumFacing enumfacing : EnumFacing.values())
                                {
                                    int i1 = k2 + enumfacing.getFrontOffsetX();
                                    int j1 = i4 + enumfacing.getFrontOffsetY();
                                    int k1 = j3 + enumfacing.getFrontOffsetZ();

                                    if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && j1 >= 0 && j1 < 3)
                                    {
                                        int l1 = getRoomIndex(i1, j1, k1);

                                        if (aStructureOceanMonumentPiecesSSB$roomdefinition[l1] != null)
                                        {
                                            if (k1 == j3)
                                            {
                                                aStructureOceanMonumentPiecesSSB$roomdefinition[l4].setConnection(enumfacing, aStructureOceanMonumentPiecesSSB$roomdefinition[l1]);
                                            }
                                            else
                                            {
                                                aStructureOceanMonumentPiecesSSB$roomdefinition[l4].setConnection(enumfacing.getOpposite(), aStructureOceanMonumentPiecesSSB$roomdefinition[l1]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition = new StructureOceanMonumentPiecesSSB.RoomDefinition(1003);
                StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition1 = new StructureOceanMonumentPiecesSSB.RoomDefinition(1001);
                StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition2 = new StructureOceanMonumentPiecesSSB.RoomDefinition(1002);
                aStructureOceanMonumentPiecesSSB$roomdefinition[GRIDROOM_TOP_CONNECT_INDEX].setConnection(EnumFacing.UP, StructureOceanMonumentPiecesSSB$roomdefinition);
                aStructureOceanMonumentPiecesSSB$roomdefinition[GRIDROOM_LEFTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, StructureOceanMonumentPiecesSSB$roomdefinition1);
                aStructureOceanMonumentPiecesSSB$roomdefinition[GRIDROOM_RIGHTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, StructureOceanMonumentPiecesSSB$roomdefinition2);
                StructureOceanMonumentPiecesSSB$roomdefinition.claimed = true;
                StructureOceanMonumentPiecesSSB$roomdefinition1.claimed = true;
                StructureOceanMonumentPiecesSSB$roomdefinition2.claimed = true;
                this.sourceRoom.isSource = true;
                this.coreRoom = aStructureOceanMonumentPiecesSSB$roomdefinition[getRoomIndex(p_175836_1_.nextInt(4), 0, 2)];
                this.coreRoom.claimed = true;
                this.coreRoom.connections[EnumFacing.EAST.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.NORTH.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.UP.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
                this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
                List<StructureOceanMonumentPiecesSSB.RoomDefinition> list = Lists.<StructureOceanMonumentPiecesSSB.RoomDefinition>newArrayList();

                for (StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition4 : aStructureOceanMonumentPiecesSSB$roomdefinition)
                {
                    if (StructureOceanMonumentPiecesSSB$roomdefinition4 != null)
                    {
                        StructureOceanMonumentPiecesSSB$roomdefinition4.updateOpenings();
                        list.add(StructureOceanMonumentPiecesSSB$roomdefinition4);
                    }
                }

                StructureOceanMonumentPiecesSSB$roomdefinition.updateOpenings();
                Collections.shuffle(list, p_175836_1_);
                int i5 = 1;

                for (StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition3 : list)
                {
                    int j5 = 0;
                    int k5 = 0;

                    while (j5 < 2 && k5 < 5)
                    {
                        ++k5;
                        int l5 = p_175836_1_.nextInt(6);

                        if (StructureOceanMonumentPiecesSSB$roomdefinition3.hasOpening[l5])
                        {
                            int i6 = EnumFacing.getFront(l5).getOpposite().getIndex();
                            StructureOceanMonumentPiecesSSB$roomdefinition3.hasOpening[l5] = false;
                            StructureOceanMonumentPiecesSSB$roomdefinition3.connections[l5].hasOpening[i6] = false;

                            if (StructureOceanMonumentPiecesSSB$roomdefinition3.findSource(i5++) && StructureOceanMonumentPiecesSSB$roomdefinition3.connections[l5].findSource(i5++))
                            {
                                ++j5;
                            }
                            else
                            {
                                StructureOceanMonumentPiecesSSB$roomdefinition3.hasOpening[l5] = true;
                                StructureOceanMonumentPiecesSSB$roomdefinition3.connections[l5].hasOpening[i6] = true;
                            }
                        }
                    }
                }

                list.add(StructureOceanMonumentPiecesSSB$roomdefinition);
                list.add(StructureOceanMonumentPiecesSSB$roomdefinition1);
                list.add(StructureOceanMonumentPiecesSSB$roomdefinition2);
                return list;
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class MonumentCoreRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public MonumentCoreRoom()
            {
            }

            public MonumentCoreRoom(EnumFacing p_i45598_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45598_2_, Random p_i45598_3_)
            {
                super(1, p_i45598_1_, p_i45598_2_, 2, 2, 2);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class Penthouse extends StructureOceanMonumentPiecesSSB.Piece
        {
            public Penthouse()
            {
            }

            public Penthouse(EnumFacing p_i45591_1_, StructureBoundingBox p_i45591_2_)
            {
                super(p_i45591_1_, p_i45591_2_);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class SimpleRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public SimpleRoom()
            {
            }

            public SimpleRoom(EnumFacing p_i45587_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45587_2_, Random p_i45587_3_)
            {
                super(1, p_i45587_1_, p_i45587_2_, 1, 1, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class SimpleTopRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public SimpleTopRoom()
            {
            }

            public SimpleTopRoom(EnumFacing p_i45586_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45586_2_, Random p_i45586_3_)
            {
                super(1, p_i45586_1_, p_i45586_2_, 1, 1, 1);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

    public static class WingRoom extends StructureOceanMonumentPiecesSSB.Piece
        {
            public WingRoom()
            {
            }

            public WingRoom(EnumFacing p_i45585_1_, StructureBoundingBox p_i45585_2_, int p_i45585_3_)
            {
                super(p_i45585_1_, p_i45585_2_);
            }

            public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
            {
                return true;
            }
        }

 
    public abstract static class Piece extends StructureComponent
    {
        protected static final int GRIDROOM_SOURCE_INDEX = getRoomIndex(2, 0, 0);
        protected static final int GRIDROOM_TOP_CONNECT_INDEX = getRoomIndex(2, 2, 0);
        protected static final int GRIDROOM_LEFTWING_CONNECT_INDEX = getRoomIndex(0, 1, 0);
        protected static final int GRIDROOM_RIGHTWING_CONNECT_INDEX = getRoomIndex(4, 1, 0);

        protected static final int getRoomIndex(int p_175820_0_, int p_175820_1_, int p_175820_2_)
        {
            return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
        }

        public Piece()
        {
            super(0);
        }

        public Piece(int p_i45588_1_)
        {
            super(p_i45588_1_);
        }

        public Piece(EnumFacing p_i45589_1_, StructureBoundingBox p_i45589_2_)
        {
            super(1);
            this.setCoordBaseMode(p_i45589_1_);
            this.boundingBox = p_i45589_2_;
        }

        protected Piece(int p_i45590_1_, EnumFacing p_i45590_2_, StructureOceanMonumentPiecesSSB.RoomDefinition p_i45590_3_, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_)
        {
            super(p_i45590_1_);
            this.setCoordBaseMode(p_i45590_2_);
            int i = p_i45590_3_.index;
            int j = i % 5;
            int k = i / 5 % 5;
            int l = i / 25;

            if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH)
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_4_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_6_ * 8 - 1);
            }

            switch (p_i45590_2_)
            {
                case NORTH:
                    this.boundingBox.offset(j * 8, l * 4, -(k + p_i45590_6_) * 8 + 1);
                    break;
                case SOUTH:
                    this.boundingBox.offset(j * 8, l * 4, k * 8);
                    break;
                case WEST:
                    this.boundingBox.offset(-(k + p_i45590_6_) * 8 + 1, l * 4, j * 8);
                    break;
                default:
                    this.boundingBox.offset(k * 8, l * 4, j * 8);
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
        }

    }
    
    static class RoomDefinition
        {
            int index;
            StructureOceanMonumentPiecesSSB.RoomDefinition[] connections = new StructureOceanMonumentPiecesSSB.RoomDefinition[6];
            boolean[] hasOpening = new boolean[6];
            boolean claimed;
            boolean isSource;
            int scanIndex;

            public RoomDefinition(int p_i45584_1_)
            {
                this.index = p_i45584_1_;
            }

            public void setConnection(EnumFacing p_175957_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175957_2_)
            {
                this.connections[p_175957_1_.getIndex()] = p_175957_2_;
                p_175957_2_.connections[p_175957_1_.getOpposite().getIndex()] = this;
            }

            public void updateOpenings()
            {
                for (int i = 0; i < 6; ++i)
                {
                    this.hasOpening[i] = this.connections[i] != null;
                }
            }

            public boolean findSource(int p_175959_1_)
            {
                if (this.isSource)
                {
                    return true;
                }
                else
                {
                    this.scanIndex = p_175959_1_;

                    for (int i = 0; i < 6; ++i)
                    {
                        if (this.connections[i] != null && this.hasOpening[i] && this.connections[i].scanIndex != p_175959_1_ && this.connections[i].findSource(p_175959_1_))
                        {
                            return true;
                        }
                    }

                    return false;
                }
            }

            public boolean isSpecial()
            {
                return this.index >= 75;
            }

            public int countOpenings()
            {
                int i = 0;

                for (int j = 0; j < 6; ++j)
                {
                    if (this.hasOpening[j])
                    {
                        ++i;
                    }
                }

                return i;
            }
        }
    
    interface MonumentRoomFitHelper
    {
        boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition);

        StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_);
    }

    static class FitSimpleRoomHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private FitSimpleRoomHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                return true;
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                return new StructureOceanMonumentPiecesSSB.SimpleRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }
    static class FitSimpleRoomTopHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private FitSimpleRoomTopHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                return !definition.hasOpening[EnumFacing.WEST.getIndex()] && !definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.hasOpening[EnumFacing.SOUTH.getIndex()] && !definition.hasOpening[EnumFacing.UP.getIndex()];
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                return new StructureOceanMonumentPiecesSSB.SimpleTopRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }
    static class XDoubleRoomFitHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private XDoubleRoomFitHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                return definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.connections[EnumFacing.EAST.getIndex()].claimed;
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                p_175968_2_.connections[EnumFacing.EAST.getIndex()].claimed = true;
                return new StructureOceanMonumentPiecesSSB.DoubleXRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }
    static class XYDoubleRoomFitHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private XYDoubleRoomFitHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                if (definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.connections[EnumFacing.EAST.getIndex()].claimed && definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed)
                {
                    StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition = definition.connections[EnumFacing.EAST.getIndex()];
                    return StructureOceanMonumentPiecesSSB$roomdefinition.hasOpening[EnumFacing.UP.getIndex()] && !StructureOceanMonumentPiecesSSB$roomdefinition.connections[EnumFacing.UP.getIndex()].claimed;
                }
                else
                {
                    return false;
                }
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                p_175968_2_.connections[EnumFacing.EAST.getIndex()].claimed = true;
                p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
                p_175968_2_.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
                return new StructureOceanMonumentPiecesSSB.DoubleXYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }

    static class YDoubleRoomFitHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private YDoubleRoomFitHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                return definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed;
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
                return new StructureOceanMonumentPiecesSSB.DoubleYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }

    static class YZDoubleRoomFitHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private YZDoubleRoomFitHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                if (definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed && definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed)
                {
                    StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition = definition.connections[EnumFacing.NORTH.getIndex()];
                    return StructureOceanMonumentPiecesSSB$roomdefinition.hasOpening[EnumFacing.UP.getIndex()] && !StructureOceanMonumentPiecesSSB$roomdefinition.connections[EnumFacing.UP.getIndex()].claimed;
                }
                else
                {
                    return false;
                }
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                p_175968_2_.claimed = true;
                p_175968_2_.connections[EnumFacing.NORTH.getIndex()].claimed = true;
                p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
                p_175968_2_.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
                return new StructureOceanMonumentPiecesSSB.DoubleYZRoom(p_175968_1_, p_175968_2_, p_175968_3_);
            }
        }

    static class ZDoubleRoomFitHelper implements StructureOceanMonumentPiecesSSB.MonumentRoomFitHelper
        {
            private ZDoubleRoomFitHelper()
            {
            }

            public boolean fits(StructureOceanMonumentPiecesSSB.RoomDefinition definition)
            {
                return definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed;
            }

            public StructureOceanMonumentPiecesSSB.Piece create(EnumFacing p_175968_1_, StructureOceanMonumentPiecesSSB.RoomDefinition p_175968_2_, Random p_175968_3_)
            {
                StructureOceanMonumentPiecesSSB.RoomDefinition StructureOceanMonumentPiecesSSB$roomdefinition = p_175968_2_;

                if (!p_175968_2_.hasOpening[EnumFacing.NORTH.getIndex()] || p_175968_2_.connections[EnumFacing.NORTH.getIndex()].claimed)
                {
                    StructureOceanMonumentPiecesSSB$roomdefinition = p_175968_2_.connections[EnumFacing.SOUTH.getIndex()];
                }

                StructureOceanMonumentPiecesSSB$roomdefinition.claimed = true;
                StructureOceanMonumentPiecesSSB$roomdefinition.connections[EnumFacing.NORTH.getIndex()].claimed = true;
                return new StructureOceanMonumentPiecesSSB.DoubleZRoom(p_175968_1_, StructureOceanMonumentPiecesSSB$roomdefinition, p_175968_3_);
            }
        }
}