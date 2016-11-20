package adver.sarius.ssb.handler;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BoneMealHandler {
	
	private final Random random = new Random();
	
	// called twice, needs to be on server only to prevent ghost blocks
	@SubscribeEvent
	public void onBoneMealUse(BonemealEvent event){
		World world = event.getWorld();
		if(world.isRemote){
			return;
		}
		// taken from BlockGrass.grow()
		if(event.getBlock().getBlock() instanceof BlockDirt && 
				event.getBlock().getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL){
			event.setResult(Result.ALLOW);
			
			for(int i = 0; i < 128; i++){
				BlockPos position = event.getPos().up();
				int j = 0;
				while(true){
					if(j >= i/16){
						if(world.isAirBlock(position)){
							if(random.nextInt(30) == 0){
								IBlockState blockState = Blocks.TALLGRASS.getStateFromMeta(BlockTallGrass.EnumType.FERN.getMeta());
								if(Blocks.TALLGRASS.canBlockStay(world, position, blockState)){
									world.setBlockState(position, blockState);
								}
							} else if(random.nextInt(30)==0){
								IBlockState blockState = Blocks.DEADBUSH.getDefaultState();
								if(Blocks.DEADBUSH.canBlockStay(world, position, blockState)){
									world.setBlockState(position, blockState);
								}
							}
						}
						break;
					}
					position = position.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
					//TODO: do i need isNormalBlockCube?
					if(world.getBlockState(position.down()).getBlock() != Blocks.DIRT 
							|| world.getBlockState(position.down()).getValue(BlockDirt.VARIANT) != BlockDirt.DirtType.PODZOL 
							|| event.getWorld().getBlockState(position).isBlockNormalCube()){
						break;
					}
					j++;
				}
			}
		}
	}	
}