package adver.sarius.ssb.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGeneratorSpawnIsland extends WorldGenerator{

	protected static final IBlockState LEAVES = Blocks.LEAVES.getDefaultState();
	
	private boolean isBonusChestEnabled;
	
	public WorldGeneratorSpawnIsland() {
		this(true);
	}
	
	public WorldGeneratorSpawnIsland(boolean isBonusChestEnabled){
		super();
		this.isBonusChestEnabled = isBonusChestEnabled;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(position.getY() < 3 || position.getY() > 249){
			return false;
		} else{
			
			// dirt/grass
			for (int z = 0; z < 4; z++) {
				worldIn.setBlockState(position.add(0, -1, z), Blocks.GRASS.getDefaultState()); // TODO: Set grass under tree to dirt for consistency?
				worldIn.setBlockState(position.add(0, -2, z), Blocks.DIRT.getDefaultState());
			}
			
			// lowest leaves
			for (int x = 0; x < 5; x++) {
				for (int z = 0; z < 5; z++) {
					worldIn.setBlockState(position.add(x-2,3,z+1), LEAVES);
				}
			}			
			for (int x = 0; x < 5; x++) {
				for (int z = 0; z < 5; z++) {
					if((z|x|4) != 4){ // without the corners
						worldIn.setBlockState(position.add(x-2,4,z+1), LEAVES);
					}
				}
			}
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					worldIn.setBlockState(position.add(x-1,5,z+2), LEAVES);
				}
			}
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					if((z|x|2) != 2){
						worldIn.setBlockState(position.add(x-1,6,z+2), LEAVES);
					}
				}
			}
			
			// log, replacing some leaves
			for (int y = 0; y < 6; y++) {
				worldIn.setBlockState(position.add(0, y, 3), Blocks.LOG.getDefaultState());
			}
			
			// starting chest
			if (this.isBonusChestEnabled){
				worldIn.setBlockState(position.add(0, 0, 2), Blocks.CHEST.getDefaultState(), 3);
	            TileEntity tileentity = worldIn.getTileEntity(position.add(0, 0, 2));
	            if (tileentity instanceof TileEntityChest) {
		            ((TileEntityChest)tileentity).setInventorySlotContents(0, new ItemStack(Blocks.ICE));
		            ((TileEntityChest)tileentity).setInventorySlotContents(1, new ItemStack(Items.LAVA_BUCKET));
	            }
			}
			return true;
		}
	}
}