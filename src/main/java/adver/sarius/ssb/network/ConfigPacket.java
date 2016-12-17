package adver.sarius.ssb.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import adver.sarius.ssb.config.SSBConfig;
import adver.sarius.ssb.recipe.ModRecipes;

public class ConfigPacket implements IMessage{

	protected boolean disableNetherSpawnerChange;
	protected boolean enableCraftingRecipes;
	protected boolean enableForesterVillager;
	
	public ConfigPacket(){ // Default needed for FML
	}
	public ConfigPacket(boolean disableNetherSpawnerChange, boolean enableCraftingRecipes, boolean enableForesterVillager){
		this.disableNetherSpawnerChange = disableNetherSpawnerChange;
		this.enableCraftingRecipes = enableCraftingRecipes;
		this.enableForesterVillager = enableForesterVillager;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.disableNetherSpawnerChange = buf.readBoolean();
		this.enableCraftingRecipes = buf.readBoolean();
		this.enableForesterVillager = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(disableNetherSpawnerChange);
		buf.writeBoolean(enableCraftingRecipes);
		buf.writeBoolean(enableForesterVillager);
	}
	
	public static class Handler implements IMessageHandler<ConfigPacket, IMessage>{
		@Override
		public IMessage onMessage(ConfigPacket message, MessageContext ctx) {
			if(ctx.side == Side.CLIENT){
				SSBConfig.disableNetherSpawnerChange = message.disableNetherSpawnerChange;
				SSBConfig.enableCraftingRecipes = message.enableCraftingRecipes;
				SSBConfig.enableForesterVillager = message.enableForesterVillager;
				ModRecipes.syncRecipesWithConfig();
				SSBConfig.needsResync = true;
			}
			return null;
		}		
	}
}