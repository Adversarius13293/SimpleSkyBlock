package adver.sarius.ssb.handler;

import adver.sarius.ssb.config.SSBConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChanceWithLooting;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler {

	// called only once, so I guess its only server side
	@SubscribeEvent
	public void onLootTablesLoaded(LootTableLoadEvent event){
		if (event.getName().equals(LootTableList.ENTITIES_CAVE_SPIDER) && SSBConfig.enableCobWebDrop) {
			// TODO: name already in use?
			LootPool pool = new LootPool(new LootEntryItem[]{new LootEntryItem(Item.getItemFromBlock(Blocks.WEB), 1, 0, new LootFunction[0], new LootCondition[0], Blocks.WEB.getRegistryName().toString())}, 
						new LootCondition[]{new KilledByPlayer(false), new RandomChanceWithLooting(0.025f, 0.01f)}, new RandomValueRange(1), new RandomValueRange(0), "pool2");
			event.getTable().addPool(pool);
		} else if(event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE) && SSBConfig.enableNetherLootChange){
			LootPool pool = event.getTable().getPool("main");
			if(pool != null){
				pool.addEntry(new LootEntryItem(Items.GOLDEN_APPLE, 1, 0, new LootFunction[]{
						new SetMetadata(new LootCondition[0], new RandomValueRange(1))}, new LootCondition[0], Items.GOLDEN_APPLE.getRegistryName().toString()));
				pool.addEntry(new LootEntryItem(Items.MELON_SEEDS, 10, 0, new LootFunction[]{
						new SetCount(new LootCondition[0], new RandomValueRange(2, 4))}, new LootCondition[0], Items.MELON_SEEDS.getRegistryName().toString()));
				pool.addEntry(new LootEntryItem(Items.PUMPKIN_SEEDS, 10, 0, new LootFunction[]{
						new SetCount(new LootCondition[0], new RandomValueRange(2, 4))}, new LootCondition[0], Items.PUMPKIN_SEEDS.getRegistryName().toString()));
			}
		}
	}
}