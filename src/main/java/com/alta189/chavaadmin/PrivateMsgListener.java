package com.alta189.chavaadmin;

import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.botevents.PrivateMessageEvent;

public class PrivateMsgListener implements Listener<PrivateMessageEvent> {

	public void onEvent(PrivateMessageEvent event) {
		CommandParser.parse(event.getMessage(), event.getSender().getNick());
	}

}
