package adver.sarius.ssb.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {
	
	public static void init(){
		
		// TODO: place somewhere else?
		final ItemStack stone = new ItemStack(Blocks.STONE, 1, 0);
		final ItemStack dirt = new ItemStack(Blocks.DIRT, 1, 0);
		final ItemStack eggSpider = new ItemStack(Items.SPAWN_EGG);
		applyEntityIdToItemStack(eggSpider, "Spider");
		final ItemStack eggZombie = new ItemStack(Items.SPAWN_EGG);
		applyEntityIdToItemStack(eggZombie, "Zombie");
		final ItemStack eggSkeleton = new ItemStack(Items.SPAWN_EGG);
		applyEntityIdToItemStack(eggSkeleton, "Skeleton");
		final ItemStack eggCave = new ItemStack(Items.SPAWN_EGG);
		applyEntityIdToItemStack(eggCave, "CaveSpider");
		
		// TODO: use Dictionary Items everywhere? (OreDictionary)
		
		// Spawn eggs
		GameRegistry.addShapedRecipe(eggSpider, "###", "#X#", "###", '#', Items.SPIDER_EYE, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggZombie, "###", "#X#", "###", '#', Items.ROTTEN_FLESH, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggSkeleton, "###", "#X#", "###", '#', Items.BONE, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggCave, "###", "#X#", "###", '#', Items.FERMENTED_SPIDER_EYE, 'X', Items.NETHER_STAR);
		
		// Empty bucket remains 
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.CLAY, 4), new ItemStack(Blocks.SAND), new ItemStack(Blocks.SAND), dirt, dirt, Items.WATER_BUCKET);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.PACKED_ICE, 2), "II", "II", 'I', Blocks.ICE);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.NETHERRACK, 3), Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE, Items.MAGMA_CREAM);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Blocks.SAND, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.DIRT, 1, 2), "LL", "DD", 'L', "treeLeaves", 'D', dirt));
		// Mushroom order is optional
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.MYCELIUM), "RB", "DD", 'R', Blocks.RED_MUSHROOM_BLOCK, 'B', Blocks.BROWN_MUSHROOM_BLOCK, 'D', Blocks.DIRT);
		
		// Ores
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.EMERALD_ORE), "EEE","ESE","EEE", 'E', Blocks.EMERALD_BLOCK, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.LAPIS_ORE), "LLL","LSL","LLL", 'L', Blocks.LAPIS_BLOCK, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.COAL_ORE), "CCC","CSC","CCC", 'C', new ItemStack(Items.COAL, 1, 1) , 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.GOLD_ORE), "GGG","GSG","GGG", 'G', Items.GOLD_INGOT, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.IRON_ORE), "III","ISI","III", 'I', Items.IRON_INGOT, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.DIAMOND_ORE), "DDD","DSD","DDD", 'D', Items.DIAMOND, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.REDSTONE_ORE), "RRR","RSR","RRR", 'R', Items.REDSTONE, 'S', stone);
	}

	// see ItemMonsterPlacer.applyEntityIdToItemStack()
	public static void applyEntityIdToItemStack(ItemStack stack, String entityId){
	        NBTTagCompound nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
	        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	        nbttagcompound1.setString("id", entityId);
	        nbttagcompound.setTag("EntityTag", nbttagcompound1);
	        stack.setTagCompound(nbttagcompound);
	}
}