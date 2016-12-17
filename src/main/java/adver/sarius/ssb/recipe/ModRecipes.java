package adver.sarius.ssb.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import adver.sarius.ssb.config.SSBConfig;

public class ModRecipes {

	private static List<IRecipe> recipes;
	
	public static void init(){
		recipes = new ArrayList<IRecipe>();

		// TODO: use OreDictionary Items everywhere?
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
		
		// Spawn eggs
		recipes.add(GameRegistry.addShapedRecipe(eggSpider, "###", "#X#", "###", '#', Items.SPIDER_EYE, 'X', Items.NETHER_STAR));
		recipes.add(GameRegistry.addShapedRecipe(eggZombie, "###", "#X#", "###", '#', Items.ROTTEN_FLESH, 'X', Items.NETHER_STAR));
		recipes.add(GameRegistry.addShapedRecipe(eggSkeleton, "###", "#X#", "###", '#', Items.BONE, 'X', Items.NETHER_STAR));
		recipes.add(GameRegistry.addShapedRecipe(eggCave, "###", "#X#", "###", '#', Items.FERMENTED_SPIDER_EYE, 'X', Items.NETHER_STAR));
		
		List<IRecipe> registered = CraftingManager.getInstance().getRecipeList();		
		// Empty bucket remains 
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.CLAY, 4), new ItemStack(Blocks.SAND), new ItemStack(Blocks.SAND), dirt, dirt, Items.WATER_BUCKET);
		recipes.add(registered.get(registered.size()-1)); // Not so clean, but should work
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.NETHERRACK, 3), Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE, Items.MAGMA_CREAM);
		recipes.add(registered.get(registered.size()-1));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Blocks.SAND, 1, 0));
		recipes.add(registered.get(registered.size()-1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.DIRT, 1, 2), "LL", "DD", 'L', "treeLeaves", 'D', dirt));
		recipes.add(registered.get(registered.size()-1));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.PACKED_ICE, 2), "II", "II", 'I', Blocks.ICE));
		// Mushroom order is optional
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.MYCELIUM), "RB", "DD", 'R', Blocks.RED_MUSHROOM_BLOCK, 'B', Blocks.BROWN_MUSHROOM_BLOCK, 'D', dirt));
		
		// Ores
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.COAL_ORE), "CCC","CSC","CCC", 'C', new ItemStack(Items.COAL, 1, 1) , 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.IRON_ORE), "III","ISI","III", 'I', Items.IRON_INGOT, 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.GOLD_ORE), "GGG","GSG","GGG", 'G', Items.GOLD_INGOT, 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.DIAMOND_ORE), "DDD","DSD","DDD", 'D', Items.DIAMOND, 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.REDSTONE_ORE), "RRR","RSR","RRR", 'R', Items.REDSTONE, 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.LAPIS_ORE), "LLL","LSL","LLL", 'L', Blocks.LAPIS_BLOCK, 'S', stone));
		recipes.add(GameRegistry.addShapedRecipe(new ItemStack(Blocks.EMERALD_ORE), "EEE","ESE","EEE", 'E', Blocks.EMERALD_BLOCK, 'S', stone));
	}
	
	public static void syncRecipesWithConfig(){
		List<IRecipe> registered = CraftingManager.getInstance().getRecipeList();
		registered.removeAll(recipes);
		if(SSBConfig.enableCraftingRecipes){
			registered.addAll(registered.size()-1, recipes);
		}
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