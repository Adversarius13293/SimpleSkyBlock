package adver.sarius.ssb;

import net.minecraft.block.BlockFalling;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * Just a test class. Remove if not needed anymore.
 */
public class CommandTesting extends CommandBase{

	@Override
	public String getCommandName() {
		return "ssb";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "just for testing";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender,
			String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) sender;
			World world = player.getEntityWorld();
//			boolean oldValue = BlockFalling.fallInstantly;
//			BlockFalling.fallInstantly = false;
//			world.setBlockState(player.getPosition(), Blocks.SAND.getDefaultState(), 2);
//			BlockFalling.fallInstantly = oldValue;
			
			BlockPos down = player.getPosition().down();
			if(world.isAirBlock(down)){
				world.setBlockState(down, Blocks.NETHERRACK.getDefaultState(), 0);
				world.setBlockState(player.getPosition(), Blocks.GRAVEL.getDefaultState(), 2);
				world.setBlockState(down, Blocks.AIR.getDefaultState(), 0);
			} else{
				player.getEntityWorld().setBlockState(player.getPosition(), Blocks.GRAVEL.getDefaultState(), 2);
			}
		}
		
	}
}