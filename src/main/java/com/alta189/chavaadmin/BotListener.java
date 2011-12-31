package com.alta189.chavaadmin;

import com.alta189.chavabot.event.EventHandler;
import com.alta189.chavabot.event.Listener;
import com.alta189.chavabot.event.Order;
import com.alta189.chavabot.event.botevents.SendActionEvent;
import com.alta189.chavabot.event.botevents.SendMessageEvent;
import com.alta189.chavabot.event.botevents.SendNoticeEvent;

public class BotListener implements Listener {
	
	@EventHandler(event = SendActionEvent.class, priority = Order.LATEST)
	public void onEvent(SendActionEvent event) {
		if (ChavaAdmin.getInstance().isMuted(event.getChannel().getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(event = SendNoticeEvent.class, priority = Order.LATEST)
	public void onEvent(SendNoticeEvent event) {
		if (ChavaAdmin.getInstance().isMuted(event.getChannel().getName())) {
			event.setCancelled(true);
		}		
	}
	
	@EventHandler(event = SendMessageEvent.class, priority = Order.LATEST)
	public void onEvent(SendMessageEvent event) {
		if (ChavaAdmin.getInstance().isMuted(event.getChannel().getName())) {
			event.setCancelled(true);
		}		
	}
}
