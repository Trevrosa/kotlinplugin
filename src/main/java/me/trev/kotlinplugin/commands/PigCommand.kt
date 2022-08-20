@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.KotlinPlugin
import me.trev.kotlinplugin.commands.ManageTargetEntityCommand.targetEntity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed

object PigCommand : CommandExecutor {
    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    private var enabled = false
    var speed = 0.17

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            enabled = !enabled

            if (enabled) {
                val x = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val targetedEntities = sender.server.selectEntities(sender, "@e[type=$targetEntity]")
                    if (targetedEntities.isEmpty()) {
                        return@Runnable
                    }
                    for (pig in pigs) {
                        if (targetEntity == "player") {
                            val targetedEntity = targetedEntities.sortedBy { p -> p.location.distance(pig.location) }
                                .firstOrNull { p -> (p as Player).gameMode.name.lowercase() == "survival" }
                            if (targetedEntity != null) {
                                if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y >= pig.location.y) {
                                    val location = pig.location
                                    location.y += speed * 2
                                    pig.teleport(location)
                                    return@Runnable
                                }
                                if (targetedEntity.location.x > pig.location.x) {
                                    val location = pig.location
                                    location.x += speed
                                    pig.teleport(location)
                                }
                                else {
                                    val location = pig.location
                                    location.x -= speed
                                    pig.teleport(location)
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y >= pig.location.y) {
                                val location = pig.location
                                location.y += speed * 2
                                pig.teleport(location)
                                return@Runnable
                            }
                            if (targetedEntity.location.x > pig.location.x) {
                                val location = pig.location
                                location.x += speed
                                pig.teleport(location)
                            }
                            else {
                                val location = pig.location
                                location.x -= speed
                                pig.teleport(location)
                            }
                        }
                    }
                }

                val y = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val targetedEntities = sender.server.selectEntities(sender, "@e[type=$targetEntity]")
                    if (targetedEntities.isEmpty()) {
                        return@Runnable
                    }
                    for (pig in pigs) {
                        if (targetEntity == "player") {
                            val targetedEntity = targetedEntities.sortedBy { p -> p.location.distance(pig.location) }
                                .firstOrNull { p -> (p as Player).gameMode.name.lowercase() == "survival" }
                            if (targetedEntity != null) {
                                if (targetedEntity.location.y > pig.location.y) {
                                    val location = pig.location
                                    location.y += speed
                                    pig.teleport(location)
                                }
                                else {
                                    val location = pig.location
                                    location.y -= speed
                                    pig.teleport(location)
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            if (targetedEntity.location.y > pig.location.y) {
                                val location = pig.location
                                location.y += speed
                                pig.teleport(location)
                            }
                            else {
                                val location = pig.location
                                location.y -= speed
                                pig.teleport(location)
                            }
                        }
                    }
                }

                val z = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val targetedEntities = sender.server.selectEntities(sender, "@e[type=$targetEntity]")
                    if (targetedEntities.isEmpty()) {
                        return@Runnable
                    }
                    for (pig in pigs) {
                        if (targetEntity == "player") {
                            val targetedEntity = targetedEntities.sortedBy { p -> p.location.distance(pig.location) }
                                .firstOrNull { p -> (p as Player).gameMode.name.lowercase() == "survival" }
                            if (targetedEntity != null) {
                                if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y >= pig.location.y) {
                                    val location = pig.location
                                    location.y += speed * 2
                                    pig.teleport(location)
                                    return@Runnable
                                }
                                if (targetedEntity.location.z > pig.location.z) {
                                    val location = pig.location
                                    location.z += speed
                                    pig.teleport(location)
                                }
                                else {
                                    val location = pig.location
                                    location.z -= speed
                                    pig.teleport(location)
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y >= pig.location.y) {
                                val location = pig.location
                                location.y += speed * 2
                                pig.teleport(location)
                                return@Runnable
                            }
                            else {
                                val location = pig.location
                                location.z -= speed
                                pig.teleport(location)
                            }
                        }
                    }
                }

                val rotation = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val targetedEntities = sender.server.selectEntities(sender, "@e[type=$targetEntity]")
                    if (targetedEntities.isEmpty()) {
                        return@Runnable
                    }
                    for (pig in pigs) {
                        if (targetEntity == "player") {
                            val targetedEntity = targetedEntities.sortedBy { p -> p.location.distance(pig.location) }
                                .firstOrNull { p -> (p as Player).gameMode.name.lowercase() == "survival" }
                            if (targetedEntity != null) {
                                val location = pig.location
                                location.direction = targetedEntity.location.subtract(pig.location.toVector()).toVector()
                                pig.teleport(location)
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            val location = pig.location
                            location.direction = targetedEntity.location.subtract(pig.location.toVector()).toVector()
                            pig.teleport(location)
                        }
                    }
                }

                val explosion = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val targetedEntities = sender.server.selectEntities(sender, "@e[type=$targetEntity]")
                    if (targetedEntities.isEmpty()) {
                        return@Runnable
                    }
                    for (pig in pigs) {
                        if (targetEntity == "player") {
                            val targetedEntity = targetedEntities.sortedBy { p -> p.location.distance(pig.location) }
                                .firstOrNull { p -> (p as Player).gameMode.name.lowercase() == "survival" }
                            if (targetedEntity != null) {
                                if (targetedEntity.location.distance(pig.location) < 1) {
                                    val tnt = sender.world.spawnEntity(targetedEntity.location, EntityType.PRIMED_TNT)
                                    (tnt as TNTPrimed).fuseTicks = 0
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            if (targetedEntity.location.distance(pig.location) < 1) {
                                val tnt = sender.world.spawnEntity(targetedEntity.location, EntityType.PRIMED_TNT)
                                (tnt as TNTPrimed).fuseTicks = 0
                            }
                        }
                    }
                }

                val glow = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    for (pig in pigs) {
                        if (!pig.isGlowing) {
                            pig.isGlowing = true
                        }
                    }
                }

                val plugin = sender.server.pluginManager.getPlugin("kotlinplugin")
                if (plugin != null) {
                    Bukkit.getScheduler().runTaskTimer(plugin, x, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, y, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, z, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, rotation, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, explosion, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, glow, 0, 0)
                    KotlinPlugin.instance?.logger?.info("scheduled the pigs")

                    sender.server.say("enabled")
                }
            }
            else {
                val plugin = sender.server.pluginManager.getPlugin("kotlinplugin")
                if (plugin != null) {
                    Bukkit.getScheduler().cancelTasks(plugin)
                }

                sender.server.say("disabled")
            }
        }
        else {
            sender.server.say("only players can use this command")
        }

        return true
    }
}