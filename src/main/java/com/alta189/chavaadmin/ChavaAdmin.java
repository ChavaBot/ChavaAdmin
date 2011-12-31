package com.alta189.chavaadmin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alta189.chavabot.ChavaManager;
import com.alta189.chavabot.plugin.CommonPlugin;
import com.alta189.chavabot.util.SettingsHandler;

public class ChavaAdmin extends CommonPlugin {
	private static ChavaAdmin instance;
	private static SettingsHandler settings;
	private static String logChan = null;
	private List<String> channels = new ArrayList<String>();

	@Override
	public void onEnable() {
		ChavaAdmin.instance = this;
		System.out.println("ChavaAdmin enabled");
		CommandParser.setCore(this);
		try {
			if (!getDataFolder().exists()) getDataFolder().mkdir();
			ChavaAdmin.settings = new SettingsHandler(ChavaAdmin.class.getResource("settings").openStream(), new File(this.getDataFolder(), "settings.properties"));
			ChavaAdmin.settings.load();
			ChavaAdmin.logChan = ChavaAdmin.settings.getPropertyString("bot-log-channel", null);
		} catch (IOException e) {
			getPluginLoader().disablePlugin(this);
			e.printStackTrace();
			return;
		}
		
		ChavaManager.getListenerManager().addListener(new AdminListener());
		ChavaManager.getEventManager().registerEvents(new BotListener(), this);
		if (ChavaAdmin.settings.checkProperty("muted-channels")) {
			String chans = ChavaAdmin.settings.getPropertyString("muted-channels", null);
			if (chans != null) {
				for (String chan : chans.split(",")) {
					channels.add(chan);
				}
			}
		}
	}

	@Override
	public void onDisable() {
		System.out.println("ChavaAdmin disabled");
		StringBuilder chans = new StringBuilder();
		for (String chan : channels) {
			chans.append(chan).append(",");
		}
		if (chans.length() > 1)
		ChavaAdmin.settings.changeProperty("muted-channels", chans.toString().substring(0, chans.toString().length() - 1));
		ChavaAdmin.settings = null;
	}

	public static SettingsHandler getSettings() {
		return settings;
	}

	public static String getLogChannel() {
		return logChan;
	}

	public static void log(String event) {
		if (logChan != null) {
			ChavaManager.getInstance().getChavaBot().sendMessage(logChan, event);
		}
	}

	public boolean isMuted(String channel) {
		return channels.contains(channel.toLowerCase());
	}

	public void muteChannel(String channel) {
		channels.add(channel.toLowerCase());
	}

	public void unmuteChannel(String channel) {
		channels.remove(channel.toLowerCase());
	}
	
	public static ChavaAdmin getInstance() {
		return instance;
	}

}
