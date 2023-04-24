package tree.def;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	public static ItemMeta axeMeta;
	public static NamespacedKey key;
	
	
	
	
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		
		
		
		

		
		
		ItemStack axeItem = new ItemStack(Material.DIAMOND_AXE);
		axeMeta = axeItem.getItemMeta();
		
		
		axeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Tree Destroyer");
		
		ArrayList<String> axeLore = new ArrayList<String>();
		axeLore.add(ChatColor.ITALIC + "" + ChatColor.AQUA + "Chop Chop Chop");
		
		axeMeta.setLore(axeLore);
		
		
		
		axeItem.setItemMeta(axeMeta);
		
		
		key = new NamespacedKey(this, "tree_destroyer");
		ShapedRecipe recipe = new ShapedRecipe(key, axeItem);
		recipe.shape("DDD", "DSD", " S ");
		
		
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('S', Material.STICK);
		
		
		Bukkit.addRecipe(recipe);
	}
	
	
	
	
	
	
	
}