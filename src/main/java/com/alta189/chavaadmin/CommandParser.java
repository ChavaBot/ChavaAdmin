package com.alta189.chavaadmin;

import java.io.IOException;
import java.util.StringTokenizer;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import com.alta189.chavabot.ChavaManager;
import com.alta189.chavaperms.ChavaPerms;

public class CommandParser {

	private static ChavaAdmin core;

	public CommandParser(ChavaAdmin core) {
		CommandParser.core = core;
	}

	public static void parse(String message, String sender) {
		CommandParser.parse(message, sender, null);
	}

	public static void parse(String message, String sender, String channel) {
		if (!message.startsWith("."))
			return;
		StringTokenizer tokens = new StringTokenizer(message);
		String cmd = tokens.nextToken().substring(1);
		if (cmd.equalsIgnoreCase("disconnect")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.disconnect") || sender.equalsIgnoreCase("alta189")) { // sender.equalsIgnoreCase("alta189") is just for debugging and will be removed shortly
				String reason = null;
				if (tokens.hasMoreTokens()) {
					StringBuilder msg = new StringBuilder();
					String word = tokens.nextToken();
					msg.append(word);
					while (tokens.hasMoreTokens()) {
						msg.append(" ");
						word = tokens.nextToken();
						msg.append(word);
					}
					reason = msg.toString();
				}
				if (reason != null) {
					ChavaAdmin.log(Responses.LOG_DISCONNECT_REASON.replace("%sender%", sender).replace("%reason%", reason));
					ChavaManager.getInstance().getChavaBot().disconnect(reason);
				} else {
					ChavaAdmin.log(Responses.LOG_DISCONNECT.replace("%sender%", sender));
					ChavaManager.getInstance().getChavaBot().disconnect();
				}
			}
		} else if (cmd.equalsIgnoreCase("join") || cmd.equalsIgnoreCase("j")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.join")) {
				String chan = "";
				while (tokens.hasMoreTokens()) {
					chan = tokens.nextToken();
					if (channel != null) {
						ChavaManager.getInstance().getChavaBot().sendMessage(channel, Responses.JOIN_CHANNEL.replace("%chan%", chan));
					} else {
						ChavaManager.getInstance().getChavaBot().sendMessage(sender, Responses.JOIN_CHANNEL.replace("%chan%", chan));
					}
					ChavaAdmin.log(Responses.LOG_JOIN.replace("%sender%", sender).replace("%chan%", chan));
					ChavaManager.getInstance().getChavaBot().joinChannel(chan);
				}
			} else {
				System.out.print("You do not have perms");
			}
			
		} else if (cmd.equalsIgnoreCase("part") || cmd.equalsIgnoreCase("p")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.part")) {
				String chan = "";
				while (tokens.hasMoreTokens()) {
					chan = tokens.nextToken();
					if (channel != null) {
						ChavaManager.getInstance().getChavaBot().sendMessage(channel, Responses.PART_CHANNEL.replace("%chan%", chan));
					} else {
						ChavaManager.getInstance().getChavaBot().sendMessage(sender, Responses.PART_CHANNEL.replace("%chan%", chan));
					}
					ChavaAdmin.log(Responses.LOG_PART.replace("%sender%", sender).replace("%chan%", chan));
					ChavaManager.getInstance().getChavaBot().partChannel(chan);
				}
			}
		} else if (cmd.equalsIgnoreCase("mute")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.mute")) {
				String chan = "";
				while (tokens.hasMoreTokens()) {
					chan = tokens.nextToken();
					ChavaManager.getInstance().getChavaBot().sendNotice(sender, Responses.MUTE_CHANNEL.replace("%chan%", chan));
					ChavaAdmin.log(Responses.LOG_MUTE.replace("%sender%", sender).replace("%chan%", chan));
					core.muteChannel(chan);
				}
			}
		} else if (cmd.equalsIgnoreCase("unmute")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.unmute")) {
				String chan = "";
				while (tokens.hasMoreTokens()) {
					chan = tokens.nextToken();
					ChavaManager.getInstance().getChavaBot().sendNotice(sender, Responses.MUTE_CHANNEL.replace("%chan%", chan));
					ChavaAdmin.log(Responses.LOG_UNMUTE.replace("%sender%", sender).replace("%chan%", chan));
					core.unmuteChannel(chan);
				}
			}
		} else if (cmd.equalsIgnoreCase("reconnect")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.reconnect")) {
				String reason = null;
				if (tokens.hasMoreTokens()) {
					StringBuilder msg = new StringBuilder();
					String word = tokens.nextToken();
					msg.append(word);
					while (tokens.hasMoreTokens()) {
						msg.append(" ");
						word = tokens.nextToken();
						msg.append(word);
					}
					reason = msg.toString();
				}
				if (reason != null) {
					ChavaAdmin.log(Responses.LOG_RECONNECT_REASON.replace("%sender%", sender).replace("%reason%", reason));
					try {
						ChavaManager.getInstance().getChavaBot().reconnect(reason);
					} catch (NickAlreadyInUseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (IrcException e) {
						e.printStackTrace();
					}
				} else {
					ChavaAdmin.log(Responses.LOG_RECONNECT.replace("%sender%", sender));
					try {
						ChavaManager.getInstance().getChavaBot().reconnect();
					} catch (NickAlreadyInUseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (IrcException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (cmd.equalsIgnoreCase("echo") || cmd.equalsIgnoreCase("e")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.echo")) {
				String target = tokens.nextToken();
				String msg = message.replaceFirst("." + cmd + " " + target + " ", "");
				ChavaManager.getInstance().getChavaBot().sendMessage(target, msg);
			}
		} else if (cmd.equalsIgnoreCase("action") || cmd.equalsIgnoreCase("a")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.action")) {
				String target = tokens.nextToken();
				String action = message.replaceFirst("." + cmd + " " + target + " ", "");
				ChavaManager.getInstance().getChavaBot().sendAction(target, action);
			}
		} else if (cmd.equalsIgnoreCase("notice") || cmd.equalsIgnoreCase("n")) {
			if (ChavaPerms.getPermsManager().hasPerms(sender, "admin.notice")) {
				String target = tokens.nextToken();
				String notice = message.replaceFirst("." + cmd + " " + target + " ", "");
				ChavaManager.getInstance().getChavaBot().sendNotice(target, notice);
			}
		}
	}
}
