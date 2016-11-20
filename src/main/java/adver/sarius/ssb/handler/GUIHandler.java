package adver.sarius.ssb.handler;

import adver.sarius.ssb.gui.GuiCreateWorldSSB;


import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GUIHandler {

	// Unused
	@SubscribeEvent
	public void onGUIOpen(GuiOpenEvent event){
		GuiScreen gui = event.getGui();
		if(gui instanceof GuiCreateWorld){
			event.setGui(new GuiCreateWorldSSB(gui));// TODO: need to get the correct parent...
		}
		// actionPerformed button.7
	}
	
}
