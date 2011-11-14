package com.alta189.chavaadmin;

public class Responses {
	
	public final static String NO_PERMS = "You do not have permission to use this command!";
	public final static String JOIN_CHANNEL = "Joining channel %chan% ";
	public final static String PART_CHANNEL = "Parting channel %chan%";
	public final static String MUTE_CHANNEL = "I am now muted in %chan%";
	
	public final static String LOG_JOIN = "%sender% has instructed me to join %chan%";
	public final static String LOG_PART = "%sender% has instructed me to part %chan%";
	public final static String LOG_DISCONNECT = "%sender% has instructed me to disconnect";
	public final static String LOG_DISCONNECT_REASON = "%sender% has instructed me to disconnect. Reason: %reason%";
	public final static String LOG_RECONNECT = "%sender% has instructed me to reconnect";
	public final static String LOG_RECONNECT_REASON = "%sender% has instructed me to reconnect. Reason: %reason%";
	public final static String LOG_MUTE = "%sender% has muted me in %chan%";
	public final static String LOG_UNMUTE = "%sender% has unmuted me in %chan%";
	
}
