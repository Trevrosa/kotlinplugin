package me.trev.kotlinplugin.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Server
import org.bukkit.entity.EntityType
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityBreedEvent

object EntityListener : Listener {
    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    @Suppress("RedundantVisibilityModifier")
    @EventHandler
    public fun onBreed(event: EntityBreedEvent) {
        val tnt = event.entity.world.spawnEntity(event.entity.location, EntityType.PRIMED_TNT)
        (tnt as TNTPrimed).fuseTicks = 0
        event.entity.server.say("Christian minecraft server...")
    }
}