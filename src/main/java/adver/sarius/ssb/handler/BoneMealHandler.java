package adver.sarius.ssb.handler;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BoneMealHandler {
	
	private final Random random = new Random();
	
	@SubscribeEvent
	public void onBoneMealUse(BonemealEvent event){
		// taken from BlockGrass.grow()
		if(event.getBlock().getBlock() instanceof BlockDirt && 
				event.getBlock().getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL){
			event.setResult(Result.ALLOW);
			World world = event.getWorld();
			
			for(int i = 0; i < 128; i++){
				BlockPos position = event.getPos().up();
				int j = 0;
				while(true){
					if(j >= i/16){
						if(world.isAirBlock(position)){
							// define chance
							if(random.nextInt(40) == 0){
								IBlockState blockState = Blocks.TALLGRASS.getStateFromMeta(BlockTallGrass.EnumType.FERN.getMeta());
								if(Blocks.TALLGRASS.canBlockStay(world, position, blockState)){
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
		} else if(event.getBlock().getBlock() instanceof BlockSand){
			event.setResult(Result.ALLOW);
			World world = event.getWorld();
			BlockPos position = event.getPos().up();
			
			if(random.nextInt(10) < 8){
				IBlockState blockState = Blocks.DEADBUSH.getDefaultState();
				if(Blocks.DEADBUSH.canBlockStay(world, position, blockState)){
					world.setBlockState(position, blockState);
				}
			}
		}
	}	
}