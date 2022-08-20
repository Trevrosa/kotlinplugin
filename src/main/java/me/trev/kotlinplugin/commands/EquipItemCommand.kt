package me.trev.kotlinplugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object EquipItemCommand : CommandExecutor {
    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.server.say("you need to speciffy an armor slot for an item to be equipped in")
                return true
            }

            if (sender.inventory.itemInMainHand.type.isAir) {
                return true
            }

            when (args.first().lowercase()) {
                "helmet" -> {
                    sender.inventory.helmet = sender.inventory.itemInMainHand
                    sender.inventory.setItemInMainHand(ItemStack(Material.AIR))
                }
                "chestplate" -> {
                    sender.inventory.chestplate = sender.inventory.itemInMainHand
                    sender.inventory.setItemInMainHand(ItemStack(Material.AIR))
                }
                "leggings" -> {
                    sender.inventory.leggings = sender.inventory.itemInMainHand
                    sender.inventory.setItemInMainHand(ItemStack(Material.AIR))
                }
                "boots" -> {
                    sender.inventory.boots = sender.inventory.itemInMainHand
                    sender.inventory.setItemInMainHand(ItemStack(Material.AIR))
                }
                else -> {
                    sender.server.say("you must specify a valid armor slot (helmet, chestplate, leggings, or boots)")
                    return true
                }
            }
            return true
        }

        sender.server.say("you are not player")
        return true
    }
}