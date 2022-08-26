@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.KotlinPlugin
import me.trev.kotlinplugin.commands.ManageTargetEntityCommand.targetEntity
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed

object PigCommand : CommandExecutor {

    private var enabled = false
    var speed = 0.17
    private var taskIds: MutableList<Int> = mutableListOf()

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
                            val finalLocation: Location = pig.location

                            if (targetedEntity != null) {
                                if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y > pig.location.y) {
                                    finalLocation.y += speed
                                }

                                if (targetedEntity.location.x > pig.location.x) {
                                    finalLocation.x += speed
                                    pig.teleport(finalLocation)
                                }
                                else {
                                    finalLocation.x -= speed
                                    pig.teleport(finalLocation)
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            val finalLocation: Location = pig.location

                            if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y > pig.location.y) {
                                finalLocation.y += speed
                            }

                            if (targetedEntity.location.x > pig.location.x) {
                                finalLocation.x += speed
                                pig.teleport(finalLocation)
                            }
                            else {
                                finalLocation.x -= speed
                                pig.teleport(finalLocation)
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
                            val finalLocation: Location = pig.location

                            if (targetedEntity != null) {
                                if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y > pig.location.y) {
                                    finalLocation.y += speed
                                }

                                if (targetedEntity.location.z > pig.location.z) {
                                    finalLocation.z += speed
                                    pig.teleport(finalLocation)
                                }
                                else {
                                    finalLocation.z -= speed
                                    pig.teleport(finalLocation)
                                }
                            }
                        }
                        else {
                            val targetedEntity = targetedEntities.minByOrNull { p -> p.location.distance(pig.location) }!!
                            val finalLocation: Location = pig.location

                            if (!pig.world.getBlockAt(pig.location).type.isAir && targetedEntity.location.y > pig.location.y) {
                                finalLocation.y += speed
                            }

                            if (targetedEntity.location.z > pig.location.z) {
                                finalLocation.z += speed
                                pig.teleport(finalLocation)
                            }
                            else {
                                finalLocation.z -= speed
                                pig.teleport(finalLocation)
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

                val pluginInstance = KotlinPlugin.instance!!

                taskIds.add(pluginInstance.runTaskTimer(x, 0, 0).taskId)
                taskIds.add(pluginInstance.runTaskTimer(y, 0, 0).taskId)
                taskIds.add(pluginInstance.runTaskTimer(z, 0, 0).taskId)
                taskIds.add(pluginInstance.runTaskTimer(rotation, 0, 0).taskId)
                taskIds.add(pluginInstance.runTaskTimer(explosion, 0, 0).taskId)
                taskIds.add(pluginInstance.runTaskTimer(glow, 0, 0).taskId)

                pluginInstance.logger.info("scheduled the pigs")
                sender.sendMessage("enabled")
            }
            else {
                KotlinPlugin.instance!!.cancelTasks(taskIds)
                taskIds.clear()

                sender.sendMessage("disabled")
            }
        }
        else {
            sender.sendMessage("only players can use this command")
        }

        return true
    }
}