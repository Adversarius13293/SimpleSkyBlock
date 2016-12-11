package adver.sarius.ssb.gen;

import java.util.Random;

import adver.sarius.ssb.SimpleSkyBlockMod;
import adver.sarius.ssb.config.SSBConfig;
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
		if(position.getY() < 3 || position.getY() > 249 || SSBConfig.spawnIslandSize == 0){
			return false;
		} else{
			int offsetTree;
			switch (SSBConfig.spawnIslandSize){
				case 1: 
					offsetTree = 1;
					this.generateGrassSmall(worldIn, position);
					break;
				case 2: 
					offsetTree = 3;
					this.generateGrassNormal(worldIn, position);
					break;
				case 3:
					offsetTree = 3;
					this.generateGrassBig(worldIn, position);
					break;
				default:
					offsetTree = 3;
					this.generateGrassNormal(worldIn, position);
					SimpleSkyBlockMod.logger.warn("Found invalid spawnIslandSize, using default");
			}

			// leaves
			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 2; z++) {
					// lowest leaves
					worldIn.setBlockState(position.add(x,3,z+offsetTree), LEAVES);
					// second row without the corners
					if(x*z != -4 && x*z != 4){
						worldIn.setBlockState(position.add(x,4,z+offsetTree), LEAVES);
					}
				}
			}
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					worldIn.setBlockState(position.add(x,5,z+offsetTree), LEAVES);
					if(x*z == 0){ // only if not a corner
						worldIn.setBlockState(position.add(x,6,z+offsetTree), LEAVES);
					}
				}
			}						
			// log, replacing some leaves
			for (int y = 0; y < 6; y++) {
				worldIn.setBlockState(position.add(0, y, offsetTree), Blocks.LOG.getDefaultState());
			}
			
			// set block below tree to dirt for consistency
			worldIn.setBlockState(position.add(0, -1, offsetTree), Blocks.DIRT.getDefaultState());
			
			// starting chest
			if (this.isBonusChestEnabled || SSBConfig.forceBonusChest){
				worldIn.setBlockState(position.add(0, 0, offsetTree-1), Blocks.CHEST.getDefaultState(), 3);
	            TileEntity tileentity = worldIn.getTileEntity(position.add(0, 0, offsetTree-1));
	            if (tileentity instanceof TileEntityChest) {
		            ((TileEntityChest)tileentity).setInventorySlotContents(0, new ItemStack(Blocks.ICE));
		            ((TileEntityChest)tileentity).setInventorySlotContents(1, new ItemStack(Items.LAVA_BUCKET));
	            }
			}
			return true;
		}
	}

	// TODO: Make these somehow related to the treePos and spawnPos?
	private void generateGrassNormal(World worldIn, BlockPos position){
		for (int z = 0; z < 4; z++) {
			worldIn.setBlockState(position.add(0, -1, z), Blocks.GRASS.getDefaultState());
			worldIn.setBlockState(position.add(0, -2, z), Blocks.DIRT.getDefaultState());
		}
	}
	
	private void generateGrassSmall(World worldIn, BlockPos position){
		worldIn.setBlockState(position.add(0, -1, 0), Blocks.GRASS.getDefaultState());
	}
	
	private void generateGrassBig(World worldIn, BlockPos position){
		for (int x = -1; x < 2; x++) {
			for (int z = -1; z < 5; z++) {
				worldIn.setBlockState(position.add(x, -1, z), Blocks.GRASS.getDefaultState());
				worldIn.setBlockState(position.add(x, -2, z), Blocks.DIRT.getDefaultState());
				worldIn.setBlockState(position.add(x, -3, z), Blocks.DIRT.getDefaultState());
			}
		}
		for (int x = 2; x < 5; x++) {
			for (int z = -1; z < 2; z++) {
				worldIn.setBlockState(position.add(x, -1, z), Blocks.GRASS.getDefaultState());
				worldIn.setBlockState(position.add(x, -2, z), Blocks.DIRT.getDefaultState());
				worldIn.setBlockState(position.add(x, -3, z), Blocks.DIRT.getDefaultState());
			}
		}
	}
}