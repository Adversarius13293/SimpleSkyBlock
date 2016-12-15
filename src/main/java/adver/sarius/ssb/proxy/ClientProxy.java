package adver.sarius.ssb.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import adver.sarius.ssb.handler.GUIHandler;

// Client/integrated server
public class ClientProxy extends CommonProxy {
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		MinecraftForge.EVENT_BUS.register(new GUIHandler());
	}
}