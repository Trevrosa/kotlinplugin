@file:Suppress("SpellCheckingInspection")

package me.trev.pigs.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object PigCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            sender.sendMessage("Youre mom")
        }
        else {
            sender.sendMessage("U not player")
        }

        return true
    }
}