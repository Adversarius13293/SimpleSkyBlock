package adver.sarius.ssb.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import adver.sarius.ssb.NamesSRG;
import adver.sarius.ssb.SimpleSkyBlockMod;

public class GuiCreateWorldSSB extends GuiCreateWorld
{
	private boolean bonusChestWasSetByUser = false;
	
	public GuiCreateWorldSSB(GuiScreen parent){
		super(parent);
	}
    
    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
    	int selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, this, 
    			NamesSRG.SELECTEDINDEX.srg, NamesSRG.SELECTEDINDEX.readable);
    	if (button.enabled){
    		if (button.id == 7) // toggle bonusChest
            {
            	this.bonusChestWasSetByUser = true;
            }
            else if (button.id == 5) // change world type from ssb
            {
            	if(WorldType.WORLD_TYPES[selectedIndex] == SimpleSkyBlockMod.WORLD_TYPE_SSB
                		&& !this.bonusChestWasSetByUser){
            		ReflectionHelper.setPrivateValue(GuiCreateWorld.class, this, false, 
            				NamesSRG.BONUSCHESTENABLED.srg, NamesSRG.BONUSCHESTENABLED.readable);
                }
            }
    	}    	
    	
    	super.actionPerformed(button);
    	
    	selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, this, 
    			NamesSRG.SELECTEDINDEX.srg, NamesSRG.SELECTEDINDEX.readable);
    	if (button.enabled){    		
	    	if (button.id == 5) { // change world type to ssb
		    	if(WorldType.WORLD_TYPES[selectedIndex] == SimpleSkyBlockMod.WORLD_TYPE_SSB 
		    			&& !this.bonusChestWasSetByUser){
            		ReflectionHelper.setPrivateValue(GuiCreateWorld.class, this, true, 
            				NamesSRG.BONUSCHESTENABLED.srg, NamesSRG.BONUSCHESTENABLED.readable);
		    	}
		    	try {
					ReflectionHelper.findMethod(GuiCreateWorld.class, this, new String[]{
						NamesSRG.UPDATEDISPLAYSTATE.srg, NamesSRG.UPDATEDISPLAYSTATE.readable}).invoke(this);
				} catch (Exception ex) {
					SimpleSkyBlockMod.logger.error("Error while updating the display. Please report this to the mods author.", ex);
				}
	        }
    	}    	
    }   
}