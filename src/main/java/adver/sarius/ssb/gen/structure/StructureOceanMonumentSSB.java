package adver.sarius.ssb.gen.structure;

import java.util.Random;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureStart;

import com.google.common.collect.Sets;

public class StructureOceanMonumentSSB extends StructureOceanMonument{

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new StructureOceanMonumentSSB.StartMonument(this.world, this.rand, chunkX, chunkZ);
    }
	
	public static class StartMonument extends StructureStart
    {
        private final Set<ChunkPos> processed = Sets.<ChunkPos>newHashSet();
        private boolean wasCreated;

        public StartMonument()
        {
        }

        public StartMonument(World worldIn, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            this.create(worldIn, random, chunkX, chunkZ);
        }

        private void create(World worldIn, Random random, int chunkX, int chunkZ)
        {
            random.setSeed(worldIn.getSeed());
            long i = random.nextLong();
            long j = random.nextLong();
            long k = (long)chunkX * i;
            long l = (long)chunkZ * j;
            random.setSeed(k ^ l ^ worldIn.getSeed());
            int i1 = chunkX * 16 + 8 - 29;
            int j1 = chunkZ * 16 + 8 - 29;
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(random);
            this.components.add(new StructureOceanMonumentPiecesSSB.MonumentBuilding(random, i1, j1, enumfacing));
            this.updateBoundingBox();
            this.wasCreated = true;
        }

        /**
         * Keeps iterating Structure Pieces and spawning them until the checks tell it to stop
         */
        public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb)
        {
            if (!this.wasCreated)
            {
                this.components.clear();
                this.create(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
            }

            super.generateStructure(worldIn, rand, structurebb);
        }

        public boolean isValidForPostProcess(ChunkPos pair)
        {
            return this.processed.contains(pair) ? false : super.isValidForPostProcess(pair);
        }

        public void notifyPostProcessAt(ChunkPos pair)
        {
            super.notifyPostProcessAt(pair);
            this.processed.add(pair);
        }

        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();

            for (ChunkPos chunkpos : this.processed)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("X", chunkpos.chunkXPos);
                nbttagcompound.setInteger("Z", chunkpos.chunkZPos);
                nbttaglist.appendTag(nbttagcompound);
            }

            tagCompound.setTag("Processed", nbttaglist);
        }

        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);

            if (tagCompound.hasKey("Processed", 9))
            {
                NBTTagList nbttaglist = tagCompound.getTagList("Processed", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                    this.processed.add(new ChunkPos(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
                }
            }
        }
    }
}