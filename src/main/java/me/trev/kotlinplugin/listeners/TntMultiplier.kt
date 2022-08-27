package me.trev.kotlinplugin.listeners

import me.trev.kotlinplugin.KotlinPlugin
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import kotlin.math.cos
import kotlin.math.sin


object TntMultiplier : Listener {
//    private fun Vector.stringify(): String {
//        return "${this.x}, ${this.y}, ${this.z}"
//    }

    @EventHandler
    fun onTntExplode(event: EntityExplodeEvent) {
        if (event.entityType != EntityType.PRIMED_TNT) {
            return
        }
        if ((event.entity as TNTPrimed).source?.type != EntityType.PLAYER) {
            return
        }

        val locations: MutableList<List<Double>> = mutableListOf()

        val entityX = event.location.x
        val entityY = event.location.y
        val entityZ = event.location.z

        KotlinPlugin.instance!!.runTaskAsync {
            var i = 0.0
            while (i < 360) {
                i += 4
                val angle = i * Math.PI / 180

                val x = entityX + 3 * cos(angle)
                val z = entityZ + 3 * sin(angle)

                val loc: List<Double> = listOf(x, entityY, z)

                locations.add(loc)
            }

            KotlinPlugin.instance!!.runTask {
                for (loc in locations) {
                    val location = Location(event.entity.world, loc[0], loc[1], loc[2])

                    val tnt = event.entity.world.spawnEntity(event.location, EntityType.PRIMED_TNT)
                    if (tnt !is TNTPrimed) return@runTask

                    val vector = (location.toVector().subtract(tnt.location.toVector())).multiply(0.175)
                    vector.y += 1

                    tnt.velocity = vector
                }
            }
        }
    }
}