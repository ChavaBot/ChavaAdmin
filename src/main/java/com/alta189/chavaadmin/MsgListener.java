package com.alta189.chavaadmin;

import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.channelevents.MessageEvent;

public class MsgListener implements Listener<MessageEvent> {

	public void onEvent(MessageEvent event) {
		CommandParser.parse(event.getMessage(), event.getUser().getNick(), event.getChannel());
	}

}
