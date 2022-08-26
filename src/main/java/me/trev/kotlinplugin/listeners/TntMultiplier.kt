package me.trev.kotlinplugin.listeners

import me.trev.kotlinplugin.KotlinPlugin
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin


object TntMultiplier : Listener {

    private fun Vector.stringify(): String {
        return "${this.x}, ${this.y}, ${this.z}"
    }

    @EventHandler
    fun onTntExplode(event: EntityExplodeEvent) {
        if (event.entityType != EntityType.PRIMED_TNT) {
            return
        }
        if ((event.entity as TNTPrimed).source?.type != EntityType.PLAYER) {
            return
        }

        val locations: MutableList<Location> = mutableListOf()

        var i = 0.0
        while (i < 360) {
            i += 2
            val angle = i * Math.PI / 180
            val x = (event.location.x + 5 * cos(angle))
            val y = event.location.y
            val z = (event.location.z + 5 * sin(angle))

            val loc = Location(event.entity.world, x, y, z)

            locations.add(loc)
        }

        for ((it, loc) in locations.withIndex()) {
            val tnt = event.entity.world.spawnEntity(event.location, EntityType.PRIMED_TNT)
            if (tnt !is TNTPrimed) return

            val oldFuse = tnt.fuseTicks
            tnt.fuseTicks *= 10

            val vector = (loc.toVector().subtract(tnt.location.toVector())).multiply(0.175)

            vector.y += 1

            KotlinPlugin.instance?.logger?.info("tnt #$it added ${vector.stringify()}")

            tnt.velocity = vector
            tnt.fuseTicks = oldFuse / 2
        }

        // event.location.createExplosion(5F)
    }
}