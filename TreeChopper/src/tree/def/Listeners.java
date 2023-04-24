package tree.def;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Listeners implements Listener {
	
	
	private final Main plugin;
	
	public Listeners(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();
		World world = block.getWorld();
		
		int count = 1;
		
		if (block.getType().name().contains("LOG") && !(world.getBlockAt(block.getLocation().clone().add(0, -1, 0)).getType().equals(Material.AIR)) && player.isSneaking() && player.getInventory().getItemInMainHand() != null
				&& player.getInventory().getItemInMainHand().getItemMeta().hasLore() &&player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals(Main.axeMeta.getLore().get(0))) {
			e.setCancelled(true);
			treeLogger(block.getLocation(), player);
			for (int i = 0; i < logList.size(); i++) {
				Location loc = logList.get(i);
				if (loc.getBlock().getType().name().contains("LOG")) {
					count++;
					treeLogger(loc, player);
				}
			}
//			System.out.print(count);
			

			world.playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, 3F, 1F);
			logList.clear();
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 10, false, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25, 10, false, false, false));
			player.setFoodLevel(player.getFoodLevel()-6);
		}
		
		
		
		
		
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().discoverRecipe(Main.key);
	}
	
	
	
	List<Location> logList = new ArrayList<>();
	
	private void treeLogger(Location origin_loc, Player player) {
		Block b = origin_loc.getBlock();
		if (b.getType().name().contains("LOG")) {
			Damageable axeDua = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
			if ((player.getInventory().getItemInMainHand().getType().getMaxDurability() - axeDua.getDamage()) != 1) {
				b.breakNaturally();
				axeDua.setDamage(axeDua.getDamage()+1);
				player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) axeDua);
				logList.add(origin_loc.clone().add(1, 0, 0));
				logList.add(origin_loc.clone().add(-1, 0, 0));
				logList.add(origin_loc.clone().add(0, 1, 0));
				logList.add(origin_loc.clone().add(0, -1, 0));
				logList.add(origin_loc.clone().add(0, 0, 1));
				logList.add(origin_loc.clone().add(0, 0, -1));
			}	
		}
	}
	
}