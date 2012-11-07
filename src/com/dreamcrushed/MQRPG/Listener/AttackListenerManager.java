package com.dreamcrushed.MQRPG.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AttackListenerManager implements Listener {
	
	private Map<String, List<AttackListener>> listenerMap;
	
	public AttackListenerManager() {
		listenerMap = new HashMap<String, List<AttackListener>>();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player)event.getDamager();
			List<AttackListener> listeners = listenerMap.get(player.getName());
			synchronized (listeners) {
				for (AttackListener listener : listeners) {
					listener.attack(player, event);
				}
			}
		} else if (event.getDamager() instanceof Player) {
			Arrow arrow = (Arrow)event.getDamager();
			
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player)arrow.getShooter();
				List<AttackListener> listeners = listenerMap.get(player.getName());
				synchronized (listeners) {
					for (AttackListener listener : listeners) {
						listener.attack(player, event);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		listenerMap.put(event.getPlayer().getName(), new ArrayList<AttackListener>());
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		listenerMap.remove(event.getPlayer().getName());
	}
	
	public void registerListener(String player, AttackListener listener) {
		List<AttackListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public void removeListener(String player, AttackListener listener) {
		List<AttackListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

}
