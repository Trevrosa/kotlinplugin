package me.trev.kotlinplugin.listeners

import me.trev.kotlinplugin.KotlinPlugin
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import kotlin.random.Random

object InventoryDragListener : Listener {
    @EventHandler
    fun onDrag(event: InventoryClickEvent) {
        if (event.cursor?.type?.isAir == true) {
            return
        }

        if (event.cursor?.enchantments?.count()!! > 3) {
            return
        }

        val enchantment = Enchantment.values().random()
        val level = Random.nextInt(1, 10)
        val enchantmentName = enchantment.toString().split(", ")[0].replace("Enchantment[minecraft:", "").replace("_", " ").uppercase()

        event.cursor?.addUnsafeEnchantment(enchantment, level)
        KotlinPlugin.instance?.logger?.info("Aded $enchantmentName " +
                "to ${event.cursor?.type?.name ?: "something"}")
    }
}