package com.Nicogeta.DisplayDepth;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nicogeta.DisplayDepth.DisplayDepth;
import com.Nicogeta.DisplayDepth.DisplayDepthPlayerListener;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class DisplayDepth extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions;
	private final DisplayDepthPlayerListener playerListener = new DisplayDepthPlayerListener(this);
	
	 private void setupPermissions() {
	      Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (DisplayDepth.Permissions == null) 
	      {
	          if (test != null) {
	              DisplayDepth.Permissions = ((Permissions)test).getHandler();
	              log.info("DisplayDepth has detected Permissions!");
	          } else {
	             log.info("DisplayDepth has not detected Permissions.");
	          }
	      }
	  }
	 
	 public void onDisable() {
		 log.info("DisplayDepth DISABLE");
	 }
	 
	 @Override
	 public void onEnable() {
		 log.info("DisplayDepth ENABLE (by Nicogeta)");
		 log.info("DisplayDepth ver 0.1");
		 PluginManager pm = getServer().getPluginManager();
		// pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
		 pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		 setupPermissions();
	 }
	 
		public void recordEvent(PlayerLoginEvent event) {
			// TODO Auto-generated method stub
			
		}

}
