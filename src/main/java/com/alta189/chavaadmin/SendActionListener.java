package com.alta189.chavaadmin;

import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.botevents.SendActionEvent;

public class SendActionListener implements Listener<SendActionEvent> {
	private final ChavaAdmin core;

	public SendActionListener(ChavaAdmin core) {
		this.core = core;
	}

	public void onEvent(SendActionEvent event) {
		if (core.isMuted(event.getTarget())) {
			event.setCancelled(true);
		}
	}
}
