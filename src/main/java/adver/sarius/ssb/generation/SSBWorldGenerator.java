package adver.sarius.ssb.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.ChunkProviderOverworld;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent.ReplaceBiomeBlocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SSBWorldGenerator{

	@SubscribeEvent
	public void event1(PopulateChunkEvent event){
//		System.out.println("populatechunk");
//		event.setCanceled(true);
//		ChunkProviderFlat
//		BiomeProvider
//		WorldClient
//		WorldProviderSurface
//		ChunkProviderOverworld
//		ChunkProviderHell
		
	}
	
	@SubscribeEvent
	public void event2(BiomeEvent event){
		// Loading client multiple times. Ingame tooooo much
//		System.out.println("BiomeEvent");
	}
	
	@SubscribeEvent
	public void event3(ChunkGeneratorEvent event){
//		System.out.println("ChunkGeneratorEvent");
		// sub: ReplaceBiomeBlocks
	}
	private final Random random = new Random();
	
	@SubscribeEvent
	public void event4(DecorateBiomeEvent.Post event){
		// auf chunk null und block null testen?
		if(event.getWorld().provider.getDimensionType() == DimensionType.NETHER){
			
			int x = event.getPos().getX();
			int z = event.getPos().getZ();
			System.out.println(x + " " + z);
			for(int y = 0; y < 256; y++){
				for(int i = 0; i < 16; i++){
					for(int j = 0; j < 16; j++){ 
						BlockPos pos = new BlockPos(x+i,y,z+j);
						Block block = event.getWorld().getBlockState(pos).getBlock();
//						if(y == 5){
//							System.out.println(event.getPos() + " XX " + pos);
//						}
						
						// Dont blockupdate, or it will generate an endless chain of new chunks.
						if(block == Blocks.NETHERRACK){
							event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
						} else if(block == Blocks.LAVA){
							if(event.getWorld().getBlockState(pos.up()).getBlock() == Blocks.LAVA
									|| event.getWorld().getBlockState(pos.down()).getBlock() == Blocks.LAVA
									|| event.getWorld().getBlockState(pos.south()).getBlock() == Blocks.LAVA
									|| event.getWorld().getBlockState(pos.west()).getBlock() == Blocks.LAVA
									|| event.getWorld().getBlockState(pos.east()).getBlock() == Blocks.LAVA
									|| event.getWorld().getBlockState(pos.north()).getBlock() == Blocks.LAVA){
//								event.getWorld().immediateBlockTick(pos, Blocks.FLOWING_LAVA.getDefaultState(), random);
							} else{
								event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);								
							}
							// TODO: Flowing lava?
//							event.getWorld().immediateBlockTick(pos, Blocks.FLOWING_LAVA.getDefaultState(), new Random(event.getWorld().getSeed()));
//							event.getWorld().getBlockState(pos).neighborChanged(event.getWorld(), pos, block);
						} else if(block == Blocks.RED_MUSHROOM || block == Blocks.BROWN_MUSHROOM){
							if(!((BlockBush)block).canBlockStay(event.getWorld(), pos, event.getWorld().getBlockState(pos))){
								event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
							}						
						} else if(block == Blocks.SOUL_SAND){
							if(event.getWorld().getBlockState(pos.up()).getBlock() != Blocks.NETHER_WART){
								event.getWorld().setBlockState(pos, Blocks.GRAVEL.getDefaultState(), 2); // TODO: does not work, keeps falling.... 
							}
						} else if(block == Blocks.BEDROCK && y < 6){
							event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
						} else if(block == Blocks.FIRE || block == Blocks.GLOWSTONE || block == Blocks.field_189877_df){
							event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
						} else if(block == Blocks.QUARTZ_ORE){
							// TODO: Quartz ausduennen.
							event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
							if(random.nextInt(100) > 70){ // TODO: Rate testen
								this.replaceAllWithAir(event.getWorld(), pos, Blocks.QUARTZ_ORE, true);
							}
						}
					}
				}
			}
		}
	}
	
	private void replaceAllWithAir(World world, BlockPos pos, Block block, boolean diagonal){
		if(world.getBlockState(pos).getBlock() == block){
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			if(diagonal){
				for(int y = -1; y < 2; y++){
					for(int x = -1; x < 2; x++){
						for(int z = -1; z < 2; z++){
							this.replaceAllWithAir(world, pos.add(x,y,z), block, diagonal);
						}
					}
				}
			} else{
				this.replaceAllWithAir(world, pos.down(), block, diagonal);
				this.replaceAllWithAir(world, pos.up(), block, diagonal);
				this.replaceAllWithAir(world, pos.north(), block, diagonal);
				this.replaceAllWithAir(world, pos.east(), block, diagonal);
				this.replaceAllWithAir(world, pos.south(), block, diagonal);
				this.replaceAllWithAir(world, pos.west(), block, diagonal);
			}
		}
	}
	
	@SubscribeEvent
	public void event5(ChunkEvent event){
		// Bei jedem Chunk Load?!
	}
	
	@SubscribeEvent
	public void event6(Event event){
		if(event instanceof GuiScreenEvent || event instanceof TickEvent || event instanceof BiomeEvent.GetGrassColor 
				|| event instanceof BiomeEvent.GetFoliageColor || event instanceof ChunkWatchEvent || event instanceof RenderLivingEvent
				|| event instanceof EntityViewRenderEvent || event instanceof RenderGameOverlayEvent || event instanceof EntityEvent
				|| event instanceof LivingEvent || event instanceof LivingEvent || event instanceof DrawBlockHighlightEvent 
				|| event instanceof RenderHandEvent || event instanceof RenderWorldLastEvent || event instanceof FOVUpdateEvent 
				|| event instanceof InputEvent || event instanceof PlaySoundEvent || event instanceof PlaySoundSourceEvent 
				|| event instanceof AttachCapabilitiesEvent || event instanceof MouseEvent || event instanceof WorldEvent.PotentialSpawns
				|| event instanceof BlockEvent.NeighborNotifyEvent || event instanceof ChunkEvent.Load || event instanceof ChunkEvent.Unload 
				|| event instanceof ChunkDataEvent.Load || event instanceof ChunkDataEvent.Save)
			return;
//		System.out.println(event.getClass());
	}

	//BlockEvent.NeighborNotifyEvent physics update | WorldEvent.PotentialSpawn moegliche mobspawns | 
	
	// Im Nether: Populate dann Decorate. Wobei Pop auch in Dec sein kann.
	
}
