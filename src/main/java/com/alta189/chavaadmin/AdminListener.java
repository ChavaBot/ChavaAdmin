package com.alta189.chavaadmin;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class AdminListener extends ListenerAdapter {
	
	@Override
	public void onMessage(MessageEvent event) throws Exception {
		CommandParser.parse(event.getMessage(), event.getUser().getNick(), event.getChannel().getName());
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception {
		CommandParser.parse(event.getMessage(), event.getUser().getNick());
	}

}
