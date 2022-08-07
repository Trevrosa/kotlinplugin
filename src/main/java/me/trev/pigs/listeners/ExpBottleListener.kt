@file:Suppress("RedundantVisibilityModifier")

package me.trev.pigs.listeners

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ExpBottleEvent

object ExpBottleListener : Listener {
    @EventHandler
    public fun onSomething(event: ExpBottleEvent) {
        val component = Component.text("the exp bottle at " +
                "(${event.entity.location.x}, ${event.entity.location.y}, ${event.entity.location.z}) " +
                "just hit ${event.hitBlock?.type?.name ?: "nothing"}")
        Bukkit.getServer().sendMessage(component)
    }
}