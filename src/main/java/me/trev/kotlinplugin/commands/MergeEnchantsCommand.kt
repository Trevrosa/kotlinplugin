package me.trev.kotlinplugin.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object MergeEnchantsCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.sendMessage("you must do /mergeenchant 1-9")
                return true
            }
            if (args.first().toIntOrNull() == null || args[1].toIntOrNull() == null) {
                sender.sendMessage("input a number")
                return true
            }
            if (args.first().toInt() > 9 || args.first().toInt() < 1 || args[1].toInt() > 9 || args[1].toInt() < 1) {
                sender.sendMessage("only numbers from 1-9 are accepted")
                return true
            }
            if (sender.inventory.getItem(args.first().toInt() - 1) == null || sender.inventory.getItem(args[1].toInt() - 1) == null) {
                sender.sendMessage("you must specify items that exist on the hotbar")
                return true
            }

            val firstItem = sender.inventory.getItem(args.first().toInt() - 1)
            val secondItem = sender.inventory.getItem(args[1].toInt() - 1)

            for (enchant in secondItem!!.enchantments) {
                if (firstItem?.containsEnchantment(enchant.key) == true) {
                    firstItem.addUnsafeEnchantment(enchant.key, enchant.value + firstItem.enchantments.toList().first {a -> a.first == enchant.key}.second)
                }
                else {
                    firstItem?.addUnsafeEnchantment(enchant.key, enchant.value)
                }
            }

            sender.inventory.setItem(args[1].toInt() - 1, ItemStack(Material.AIR))
            return true
        }

        sender.sendMessage("you are not player")

        return true
    }
}