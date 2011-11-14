package com.alta189.chavaadmin;

import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.botevents.SendNoticeEvent;

public class SendNoticeListener implements Listener<SendNoticeEvent>{
	
	private final ChavaAdmin core;
	
	public SendNoticeListener(ChavaAdmin core) {
		this.core = core;
	}
	
	public void onEvent(SendNoticeEvent event) {
		if (core.isMuted(event.getTarget())) {
			event.setCancelled(true);
		}
	}

}
