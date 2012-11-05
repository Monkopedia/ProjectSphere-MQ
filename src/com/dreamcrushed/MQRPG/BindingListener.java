package com.dreamcrushed.MQRPG;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class BindingListener implements Listener {

//	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//	public void onBlockDamageEvent(BlockDamageEvent event) {
//		System.out.println("Block: " + event.getPlayer().getName());
//		handleEvent(event);
//	}
//	
//	private void handleEvent(BlockDamageEvent event) {
//		Player p = event.getPlayer();
//		String player = p.getName();
//		BindManager bindings = MQRPG.getBindings(player);
//		ItemStack item = p.getItemInHand();
//		Material mat = Material.AIR;
//		if (item != null) {
//			mat = item.getType();
//		}
//
//		Binding bind = bindings.getBinding(BindingType.BLOCK, mat);
//		if (bind != null) {
//			bind.ability.getAbility().activate(p, event);
//		}
//	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamagedByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player)event.getDamager();
			System.out.println("Damage: " + p.getName());
			handleEvent(p, event);
		} else if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow)event.getDamager();
			
			if (arrow.getShooter() instanceof Player) {
				Player p = (Player)arrow.getShooter();
				System.out.println("Damage Arrow: " + p.getName());
				handleEvent(p, event);
			}
		}
	}

	private void handleEvent(Player p, EntityDamageByEntityEvent event) {
		String player = p.getName();
		BindManager bindings = MQRPG.getBindings(player);
		ItemStack item = p.getItemInHand();
		Material mat = Material.AIR;
		if (item != null) {
			mat = item.getType();
		}

		Binding bind = bindings.getBinding(BindingType.ATTACK, mat);
		if (bind != null) {
			bind.ability.getAbility().activate(p, event);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		
		if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			System.out.println("Right: " + event.getPlayer().getName());
			handleEvent(BindingType.RIGHT, event);
		} else if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
			System.out.println("Left: " + event.getPlayer().getName());
			if (event.isCancelled()) return;
			handleEvent(BindingType.LEFT, event);
		}
	}

	private void handleEvent(BindingType bindingType, PlayerInteractEvent event) {
		String player = event.getPlayer().getName();
		BindManager bindings = MQRPG.getBindings(player);
		ItemStack item = event.getPlayer().getItemInHand();
		Material mat = Material.AIR;
		if (item != null) {
			mat = item.getType();
		}

		Binding bind = bindings.getBinding(bindingType, mat);
		if (bind != null) {
			bind.ability.getAbility().activate(event.getPlayer(), event);
		}
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		MQRPG.binding.put(event.getPlayer().getName(), new BindManager());
		MQRPG.abilityManager.login(event.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		MQRPG.abilityManager.logout(event.getPlayer().getName());
		MQRPG.binding.remove(event.getPlayer().getName());
	}

}
