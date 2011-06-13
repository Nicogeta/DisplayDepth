
package com.Nicogeta.DisplayDepth.DisplayDepth;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
//import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
    	if((DisplayDepth.Permissions == null && player.isOnline()))
    		display = true;
    	else if(DisplayDepth.Permissions != null && DisplayDepth.Permissions.has(player, "DisplayDepth.display"))
    		display = true;
	
	
    	if (display) {
    		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK ) {
    			if (player.getItemInHand().getTypeId() == plugin.theItemID) {
    				player.sendMessage(ChatColor.GREEN + "INFO: " + "You're " + location + " over 0"); 
    			}
    		}
    	}
    }		
}