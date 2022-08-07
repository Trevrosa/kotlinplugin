@file:Suppress("SpellCheckingInspection")

package me.trev.pigs.commands

import me.trev.pigs.Pig
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
    @Suppress("unused")
    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    private var enabled = false
    private const val speed = 0.25

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            enabled = !enabled

            if (enabled) {
                val x = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val players = sender.server.onlinePlayers
                    for (pig in pigs) {
                        for (player in players) {
                            if (player.location.x > pig.location.x) {
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
                    val players = sender.server.onlinePlayers
                    for (pig in pigs) {
                        for (player in players) {
                            if (player.location.y > pig.location.y) {
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
                    val players = sender.server.onlinePlayers
                    for (pig in pigs) {
                        for (player in players) {
                            if (player.location.z > pig.location.z) {
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
                }

                val rotation = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val players = sender.server.onlinePlayers
                    for (pig in pigs) {
                        for (player in players) {
                            val location = pig.location
                            location.direction = player.location.subtract(pig.location.toVector()).toVector()
                            pig.teleport(location)
                        }
                    }
                }

                val explosion = Runnable {
                    val pigs = sender.server.selectEntities(sender, "@e[type=pig]")
                    val players = sender.server.onlinePlayers
                    for (pig in pigs) {
                        for (player in players) {
                            if (player.location.distance(pig.location) < 1) {
                                val tnt = sender.world.spawnEntity(player.location, EntityType.PRIMED_TNT)
                                (tnt as TNTPrimed).fuseTicks = 0
                            }
                        }
                    }
                }

                val plugin = sender.server.pluginManager.getPlugin("pig")
                if (plugin != null) {
                    Bukkit.getScheduler().runTaskTimer(plugin, x, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, y, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, z, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, rotation, 0, 0)
                    Bukkit.getScheduler().runTaskTimer(plugin, explosion, 0, 0)
                    Pig.instance?.logger?.info("scheduled the pigs")

                    sender.server.say("enabled")
                }
            }
            else {
                val plugin = sender.server.pluginManager.getPlugin("pig")
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