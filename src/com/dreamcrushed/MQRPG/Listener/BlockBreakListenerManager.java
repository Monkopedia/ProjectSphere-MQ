package com.dreamcrushed.MQRPG.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BlockBreakListenerManager implements Listener {
	
	private Map<String, List<BlockBreakListener>> listenerMap;
	
	public BlockBreakListenerManager() {
		listenerMap = new HashMap<String, List<BlockBreakListener>>();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		List<BlockBreakListener> listeners = listenerMap.get(player.getName());
		player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() + 1));
		synchronized (listeners) {
			for (BlockBreakListener listener : listeners) {
				listener.blockBreak(player, event);
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		listenerMap.put(event.getPlayer().getName(), new ArrayList<BlockBreakListener>());
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		listenerMap.remove(event.getPlayer().getName());
	}
	
	public void registerListener(String player, BlockBreakListener listener) {
		List<BlockBreakListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public void removeListener(String player, BlockBreakListener listener) {
		List<BlockBreakListener> listeners = listenerMap.get(player);
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

}
