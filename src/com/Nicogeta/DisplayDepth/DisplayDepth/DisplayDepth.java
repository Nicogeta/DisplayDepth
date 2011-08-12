package com.Nicogeta.DisplayDepth.DisplayDepth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class DisplayDepth extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler permissions;
	private final DisplayDepthPlayerListener playerListener = new DisplayDepthPlayerListener(this);

	public int theItemID;
	private PluginDescriptionFile info;
	private FileOutputStream out;
	private FileInputStream inFile;
	private File configFile, directory;
	static Properties prop = new Properties();


	public void loadProcedure() {

		try {
			inFile = new FileInputStream(configFile);
			prop.load(inFile);
		} catch(FileNotFoundException e) {
			throw new RuntimeException("Config file not found.", e);
		} catch (IOException e){
			throw new RuntimeException("Failed to load config file.", e);
		} finally {
			try {
				inFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String theItem = prop.getProperty("itemToUse");
		try {
			theItemID = Integer.parseInt(theItem);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Failed to parse item ID.", e);
		}
	}


	public void onEnable() {
		info = getDescription();
		directory = getDataFolder();

		configFile = new File(directory, "config.properties");

		if(!configFile.exists()) {

			try {
				try {
					new File(directory.toString()).mkdir();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				try {
					configFile.createNewFile();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				out = new FileOutputStream(configFile);
				prop.put("itemToUse", "347");
				prop.store(out, "DisplayDepth config, please put the Id of the item you want to use");
				out.flush();
			} catch (IOException e) {
				throw new RuntimeException("Failed to create default config file.", e);
			} finally {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("plop");
		}
		loadProcedure();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		setupPermissions();
		log.info(info.getName()+ " ENABLE (by Nicogeta)");
		log.info(info.getName() + " ver " + info.getVersion());
	}


	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
		if (DisplayDepth.permissions == null)
		{
			if (permissionsPlugin != null) {
				DisplayDepth.permissions = ((Permissions)permissionsPlugin).getHandler();
				log.info("DisplayDepth has detected Permissions!");
			} else {
				log.info("DisplayDepth has not detected Permissions.");
			}
		}
	}


	public void onDisable() {
		log.info("DisplayDepth DISABLE");
	}


	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		if (command.getName().equalsIgnoreCase("rdd")) {
			if(sender instanceof Player) {
				player = (Player) sender;
				if ((DisplayDepth.permissions == null && player.isOp()) 
						|| (DisplayDepth.permissions != null && DisplayDepth.permissions.has(player, "DisplayDepth.reload"))
						|| (DisplayDepth.permissions == null && player.hasPermission("DisplayDepth.reload"))) {

					if(args.length == 0) {
						loadProcedure();
						player.sendMessage(ChatColor.GREEN + "INFO: Changes applied");
					} else {
						player.sendMessage(ChatColor.RED + "INFO: You have to type /rdd");
					}
				} else {
					player.sendMessage("You don't have the permission to do that !");
				}
			}
		}
		return true;	
	}
}
