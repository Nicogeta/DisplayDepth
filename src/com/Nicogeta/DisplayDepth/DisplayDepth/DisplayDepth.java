    package com.Nicogeta.DisplayDepth.DisplayDepth;
     
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.util.Properties;
    import java.util.logging.Logger;
     
    import org.bukkit.event.Event;
    import org.bukkit.event.Event.Priority;
    import org.bukkit.event.player.PlayerLoginEvent;
    import org.bukkit.plugin.Plugin;
    import org.bukkit.plugin.PluginManager;
    import org.bukkit.plugin.java.JavaPlugin;
     
    import com.nijiko.permissions.PermissionHandler;
    import com.nijikokun.bukkit.Permissions.Permissions;
     
    public class DisplayDepth extends JavaPlugin {
     
            private static final Logger log = Logger.getLogger("Minecraft");
            public static PermissionHandler Permissions;
            private final DisplayDepthPlayerListener playerListener = new DisplayDepthPlayerListener(this);
     
            public int theItemID;
            String theItemToUse;
            static String mainDirectory = "plugins/DisplayDepth";
            static File ConfigCreate = new File(mainDirectory + File.separator + "config.properties");
            static Properties prop = new Properties();
     
     
            public void loadProcedure() {
                    FileInputStream inFile = null;
     
                    try {
                            inFile = new FileInputStream(ConfigCreate);
                            prop.load(inFile);
                            inFile.close();
                    } catch(FileNotFoundException e) {
                            throw new RuntimeException("Config file not found.", e);
                    } catch (IOException e){
                            throw new RuntimeException("Failed to load config file.", e);
                    }
     
                    String theItem = prop.getProperty("itemToUse");
                    try {
                            theItemID = Integer.parseInt(theItem);
                    } catch (NumberFormatException e) {
                            throw new RuntimeException("Failed to parse item ID.", e);
                    }
            }
     
     
            public void onEnable() {
                    new File(mainDirectory).mkdir();
                    if(!ConfigCreate.exists()) {
                            try {
                                    ConfigCreate.createNewFile();
                                    FileOutputStream out = new FileOutputStream(ConfigCreate);
                                    prop.put("itemToUse", "347");
                                    prop.store(out, "DisplayDepth config, please put the Id of the item you want to use");
                                    out.flush();
                                    out.close();
                            } catch (IOException e) {
                                    throw new RuntimeException("Failed to create default config file.", e);
                            }
                    }
                    loadProcedure();
                    PluginManager pm = getServer().getPluginManager();
                    pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
                    setupPermissions();
                    log.info("DisplayDepth ENABLE (by Nicogeta)");
                    log.info("DisplayDepth ver 0.2");
            }
     
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
     
     
     
            public void recordEvent(PlayerLoginEvent event) {
                    // TODO Auto-generated method stub
     
            }
     
    }
