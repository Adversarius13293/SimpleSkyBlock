package adver.sarius.ssb;

public class NamesSRG {

	// From http://export.mcpbot.bspk.rs/
	public static final NamesSRG SELECTEDINDEX = new NamesSRG("selectedIndex","field_146331_K");
	public static final NamesSRG BONUSCHESTENABLED = new NamesSRG("bonusChestEnabled","field_146338_v");
	public static final NamesSRG PARENTSCREEN = new NamesSRG("parentScreen","field_146332_f");
	public static final NamesSRG CHUNKPROVIDERSETTINGSJSON = new NamesSRG("chunkProviderSettingsJson","field_146334_a");
	public static final NamesSRG WORLDSEED = new NamesSRG("worldSeed","field_146329_I");
	public static final NamesSRG WORLDNAME = new NamesSRG("worldName","field_146330_J");
	public static final NamesSRG ALLOWCHEATS = new NamesSRG("allowCheats","field_146340_t");
	public static final NamesSRG GENERATESTRUCTURESENABLED = new NamesSRG("generateStructuresEnabled","field_146341_s");
	public static final NamesSRG GAMEMODE = new NamesSRG("gameMode","field_146342_r");
	public static final NamesSRG UPDATEDISPLAYSTATE = new NamesSRG("updateDisplayState","func_146319_h");
	
	public final String readable;
	public final String srg;
	protected NamesSRG(String readable, String srg){
		this.readable = readable;
		this.srg = srg;
	}
}