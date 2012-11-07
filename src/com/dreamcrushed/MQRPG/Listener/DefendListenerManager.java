package com.dreamcrushed.MQRPG.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DefendListenerManager implements Listener {
	
	private Map<String, List<DefendListener>> listenerMap;
	
	public DefendListenerManager() {
		listenerMap = new HashMap<String, List<DefendListener>>();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			System.out.println("Player Hurt " + event.getCause());
			List<DefendListener> listeners = listenerMap.get(player.getName());
			synchronized (listeners) {
				for (DefendListener listener : listeners) {
					listener.defend(player, event);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		listenerMap.put(event.getPlayer().getName(), new ArrayList<DefendListener>());
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		listenerMap.remove(event.getPlayer().getName());
	}
	
	public void registerListener(String player, DefendListener listener) {
		List<DefendListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public void removeListener(String player, DefendListener listener) {
		List<DefendListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

}
