package com.Nicogeta.DisplayDepth;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
//import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nicogeta.DisplayDepth.DisplayDepth;

@SuppressWarnings("unused")

public class DisplayDepthPlayerListener extends PlayerListener {
	
	final DisplayDepth plugin;
	
	public DisplayDepthPlayerListener (DisplayDepth instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract (PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int location = event.getPlayer().getLocation().getBlockY();
		Action action = event.getAction();
    	boolean display = false;
    	if((DisplayDepth.Permissions == null && player.isOp()))
    		display = true;
    	else if(DisplayDepth.Permissions != null && DisplayDepth.Permissions.has(player, "DisplayDepth.display"))
    		display = true;
	
	
    	if (display) {
    		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK ) {
    			if (player.getItemInHand().getType() == Material.WATCH) {
    				player.sendMessage("Your " + location + " over 0");
    			}
    		}
    		
    	}

}
}
