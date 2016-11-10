package adver.sarius.ssb.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {
	
	public static void init(){
		
		// TODO: place somewhere else?
		final ItemStack stone = new ItemStack(Blocks.STONE, 1, 0);
		final ItemStack dirt = new ItemStack(Blocks.DIRT, 1, 0);
		final ItemStack eggSpider = new ItemStack(Items.SPAWN_EGG);
		ItemMonsterPlacer.applyEntityIdToItemStack(eggSpider, "Spider"); // TODO: doesnt work on serverside
		final ItemStack eggZombie = new ItemStack(Items.SPAWN_EGG);
		ItemMonsterPlacer.applyEntityIdToItemStack(eggZombie, "Zombie");
		final ItemStack eggSkeleton = new ItemStack(Items.SPAWN_EGG);
		ItemMonsterPlacer.applyEntityIdToItemStack(eggSkeleton, "Skeleton");
		final ItemStack eggCave = new ItemStack(Items.SPAWN_EGG);
		ItemMonsterPlacer.applyEntityIdToItemStack(eggCave, "CaveSpider");
		// TODO: use Dictionary Items everywhere? (OreDictionary)
		
		// Spawn eggs
		GameRegistry.addShapedRecipe(eggSpider, "###", "#X#", "###", '#', Items.SPIDER_EYE, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggZombie, "###", "#X#", "###", '#', Items.ROTTEN_FLESH, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggSkeleton, "###", "#X#", "###", '#', Items.BONE, 'X', Items.NETHER_STAR);
		GameRegistry.addShapedRecipe(eggCave, "###", "#X#", "###", '#', Items.FERMENTED_SPIDER_EYE, 'X', Items.NETHER_STAR);
		
		// Empty bucket remains 
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.CLAY, 4), new ItemStack(Blocks.SAND), new ItemStack(Blocks.SAND, 2), dirt, dirt, Items.WATER_BUCKET);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.PACKED_ICE, 2), "II", "II", 'I', Blocks.ICE);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.NETHERRACK), Items.MAGMA_CREAM, Blocks.COBBLESTONE);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Blocks.SAND, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.DIRT, 1, 2), "LL", "DD", 'L', "treeLeaves", 'D', dirt));
		// Mushroom order is optional
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.MYCELIUM), "RB", "DD", 'R', Blocks.RED_MUSHROOM_BLOCK, 'B', Blocks.BROWN_MUSHROOM_BLOCK, 'D', Blocks.DIRT);
		
		// Ores
		// TODO: more Shapes?
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.EMERALD_ORE), "EEE","ESE","EEE", 'E', Blocks.EMERALD_BLOCK, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.GOLD_ORE), " G ","GSG"," G ", 'G', Items.GOLD_INGOT, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.IRON_ORE), " I ","ISI"," I ", 'I', Items.IRON_INGOT, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.COAL_ORE), "C C","CSC","C C", 'C', new ItemStack(Items.COAL, 1, 1) , 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.LAPIS_ORE), "L L","LSL","L L", 'L', Blocks.LAPIS_BLOCK, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.DIAMOND_ORE), "DDD","DSD","DDD", 'D', Items.DIAMOND, 'S', stone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.REDSTONE_ORE), "RSR", 'R', Blocks.REDSTONE_BLOCK, 'S', stone);
	}
}