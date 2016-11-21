package adver.sarius.ssb.villager;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerTradingChanger {

	public static void registerVillager(){

		// TODO: change this for 1.11
		VillagerProfession prof = new VillagerProfession(SimpleSkyBlockMod.MODID + ":nitwit",
                "minecraft:textures/entity/villager/villager.png",
                "minecraft:textures/entity/zombie_villager/zombie_villager.png");

		VillagerRegistry.instance().register(prof);
		ITradeList tradeSugar = new EntityVillager.ItemAndEmeraldToItem(Items.SUGAR, new PriceInfo(5, 8), Items.REEDS, new PriceInfo(1, 1));
//		ITradeList tradeCactus = new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.CACTUS), new PriceInfo(8, 12));
		ITradeList tradeCactus = new ItemAndItemToItem(new ItemStack(Blocks.CACTUS), new PriceInfo(1, 1), 
				new ItemStack(Items.FISH, 1, 3), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(4, 6));
		ITradeList tradeCocoa = new ItemAndItemToItem(new ItemStack(Items.DYE, 1, 3), new PriceInfo(1, 1), 
				new ItemStack(Blocks.WOOL, 1, 12), new PriceInfo(4, 4), new ItemStack(Items.EMERALD), new PriceInfo(1, 3));
		ITradeList tradeGrass = new ItemAndItemToItem(new ItemStack(Blocks.GRASS), new PriceInfo(2, 2), 
				new ItemStack(Blocks.DIRT), new PriceInfo(3, 3), new ItemStack(Items.WHEAT_SEEDS), new PriceInfo(3, 3));
		ITradeList tradeBirch = new ItemAndItemToItem(new ItemStack(Blocks.SAPLING, 1, 2), new PriceInfo(1, 1), 
				new ItemStack(Blocks.SAPLING), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(10, 14));
		ITradeList tradeDark = new ItemAndItemToItem(new ItemStack(Blocks.SAPLING, 1, 5), new PriceInfo(1, 1), 
				new ItemStack(Blocks.SAPLING), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(10, 14));
		ITradeList tradeJungle = new ItemAndItemToItem(new ItemStack(Blocks.SAPLING, 1, 3), new PriceInfo(1, 1), 
				new ItemStack(Blocks.SAPLING), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(10, 14));
		ITradeList tradeSpruce = new ItemAndItemToItem(new ItemStack(Blocks.SAPLING, 1, 1), new PriceInfo(1, 1), 
				new ItemStack(Blocks.SAPLING), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(10, 14));
		ITradeList tradeAcacia = new ItemAndItemToItem(new ItemStack(Blocks.SAPLING, 1, 4), new PriceInfo(1, 1), 
				new ItemStack(Blocks.SAPLING), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(10, 14));
		ITradeList tradeRose = new ItemAndItemToItem(new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.ROSE.getMeta()), new PriceInfo(1, 1), 
				new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.POPPY.getMeta()), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(6, 9));
		ITradeList tradeSun = new ItemAndItemToItem(new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta()), new PriceInfo(1, 1), 
				new ItemStack(Blocks.YELLOW_FLOWER, 1, BlockFlower.EnumFlowerType.DANDELION.getMeta()), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(6, 9));
		ITradeList tradePeony = new ItemAndItemToItem(new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.PAEONIA.getMeta()), new PriceInfo(1, 1), 
				new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.PINK_TULIP.getMeta()), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(6, 9));
		ITradeList tradeLilac = new ItemAndItemToItem(new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SYRINGA.getMeta()), new PriceInfo(1, 1), 
				new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.ALLIUM.getMeta()), new PriceInfo(1, 1), new ItemStack(Items.EMERALD), new PriceInfo(6, 9));

		VillagerCareer career = new VillagerCareer(prof, "forester"); 
		career.addTrade(1, tradeSugar, tradeCactus, tradeCocoa);
		career.addTrade(2, tradeSpruce, tradeBirch, tradeJungle, tradeAcacia, tradeDark);
		career.addTrade(3, tradeSun, tradeLilac, tradeRose, tradePeony);
		career.addTrade(4, tradeGrass);
	}
}