package com.Nicogeta.DisplayDepth.DisplayDepth;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEvent;



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
		if((DisplayDepth.permissions == null && player.isOnline()))
			display = true;
		if(DisplayDepth.permissions != null && DisplayDepth.permissions.has(player, "DisplayDepth.display"))
			display = true;


		if (display) {
			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK ) {
				if (player.getItemInHand().getTypeId() == plugin.theItemID) {
					player.sendMessage(ChatColor.GREEN + "INFO: " + "You are " + location + " over 0"); 
				}
			}
		}
	}		
}