package org.shininet.bukkit.itemrenamer.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.shininet.bukkit.itemrenamer.ItemRenamer;

public class ItemRenamerPlayerJoin implements Listener {
	
	private ItemRenamer plugin;
	
	public ItemRenamerPlayerJoin(ItemRenamer plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(player.hasPermission("itemrenamer.update") && plugin.getUpdateReady())
		{
			player.sendMessage("[ItemRenamer] An update is available: " + plugin.getUpdateName() + "(" + plugin.getUpdateSize() + " bytes");
			player.sendMessage("[ItemRenamer] Type \"/ItemRenamer update\" if you would like to update.");
		}
	}
	
	public void unregister() {
		PlayerJoinEvent.getHandlerList().unregister(this);
	}
}
