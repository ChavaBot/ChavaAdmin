package com.alta189.chavaadmin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alta189.chavabot.ChavaManager;
import com.alta189.chavabot.plugins.java.JavaPlugin;
import com.alta189.chavabot.util.SettingsHandler;

public class ChavaAdmin extends JavaPlugin {
	private static SettingsHandler settings;
	private static String logChan = null;
	private List<String> channels = new ArrayList<String>();

	@Override
	public void onEnable() {
		System.out.println("ChavaAdmin enabled");
		try {
			ChavaAdmin.settings = new SettingsHandler(ChavaAdmin.class.getResource("").openStream(), new File(this.getDataFolder(), "settings.properties"));
			ChavaAdmin.settings.load();
			ChavaAdmin.logChan = ChavaAdmin.settings.getPropertyString("bot-log-channel", null);
		} catch (IOException e) {
			this.getPluginLoader().disablePlugin(this);
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void onDisable() {
		System.out.println("ChavaAdmin disabled");
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
		return channels.contains(channel);
	}

	public void muteChannel(String channel) {
		channels.add(channel);
	}

	public void unmuteChannel(String channel) {
		channels.remove(channel);
	}

}
