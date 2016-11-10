package adver.sarius.ssb.gen.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenStrongholdSSB extends MapGenStronghold {


	@Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        MapGenStrongholdSSB.Start mapgenstronghold$start;

        // init with start ; while not empty or no portal ; re-init
        for (mapgenstronghold$start = new MapGenStrongholdSSB.Start(this.worldObj, this.rand, chunkX, chunkZ); mapgenstronghold$start.getComponents().isEmpty() || ((StructureStrongholdPiecesSSB.Stairs2)mapgenstronghold$start.getComponents().get(0)).strongholdPortalRoom == null; mapgenstronghold$start = new MapGenStrongholdSSB.Start(this.worldObj, this.rand, chunkX, chunkZ))
        {
            ;
        }

        return mapgenstronghold$start;
    }

    public static class Start extends StructureStart
        {
            public Start()
            {
            }

            public Start(World worldIn, Random random, int chunkX, int chunkZ)
            {
                super(chunkX, chunkZ);
                StructureStrongholdPiecesSSB.prepareStructurePieces();
                StructureStrongholdPiecesSSB.Stairs2 structurestrongholdpieces$stairs2 = new StructureStrongholdPiecesSSB.Stairs2(0, random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
                this.components.add(structurestrongholdpieces$stairs2);
                structurestrongholdpieces$stairs2.buildComponent(structurestrongholdpieces$stairs2, this.components, random);
                List<StructureComponent> list = structurestrongholdpieces$stairs2.pendingChildren;

                while (!list.isEmpty())
                {
                    int i = random.nextInt(list.size());
                    StructureComponent structurecomponent = (StructureComponent)list.remove(i);
                    structurecomponent.buildComponent(structurestrongholdpieces$stairs2, this.components, random);
                }

                this.updateBoundingBox();
                this.markAvailableHeight(worldIn, random, 10);
            }
        }
}