package adver.sarius.ssb.villager;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

/**
 *  Allows trading of itemstacks for itemstacks, and not just emeralds.
 */
public class ItemAndItemToItem implements ITradeList{

    public ItemStack buyingItemStack;
    public EntityVillager.PriceInfo buyingPriceInfo;
    public ItemStack sellingItemstack1;
    public EntityVillager.PriceInfo sellingPriceInfo1;
    public ItemStack sellingItemstack2;
    public EntityVillager.PriceInfo sellingPriceInfo2;
    // TODO: re-order such that its consistent with class name
    public ItemAndItemToItem(ItemStack buyingItemStack,
			PriceInfo buyingPriceInfo, ItemStack sellingItemstack1,
			PriceInfo sellingPriceInfo1, ItemStack sellingItemstack2,
			PriceInfo sellingPriceInfo2) {
		this.buyingItemStack = buyingItemStack;
		this.buyingPriceInfo = buyingPriceInfo;
		this.sellingItemstack1 = sellingItemstack1;
		this.sellingPriceInfo1 = sellingPriceInfo1;
		this.sellingItemstack2 = sellingItemstack2;
		this.sellingPriceInfo2 = sellingPriceInfo2;
	}
    
	/**
     * Affects the given MerchantRecipeList to possibly add or remove MerchantRecipes.
     */
    @Override
    public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
    {
        int b = 1;
        if (this.buyingPriceInfo != null)
        {
            b = this.buyingPriceInfo.getPrice(random);
        }

        int s1 = 1;
        if (this.sellingPriceInfo1 != null)
        {
            s1 = this.sellingPriceInfo1.getPrice(random);
        }
        
        int s2 = 1;
        if (this.sellingPriceInfo2 != null)
        {
            s2 = this.sellingPriceInfo2.getPrice(random);
        }

        recipeList.add(new MerchantRecipe(new ItemStack(this.sellingItemstack1.getItem(), s1, this.sellingItemstack1.getMetadata()), 
        		new ItemStack(this.sellingItemstack2.getItem(), s2, this.sellingItemstack2.getMetadata()), 
        		new ItemStack(this.buyingItemStack.getItem(), b, this.buyingItemStack.getMetadata())));
    }
}