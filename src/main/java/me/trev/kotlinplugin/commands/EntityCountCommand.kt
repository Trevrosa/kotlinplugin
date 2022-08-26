package me.trev.kotlinplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EntityCountCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            try {
                val count = sender.server.selectEntities(sender, "@e[type=${args!!.first()}]").size
                sender.sendMessage("there are $count ${args.first()}s")
            }
            catch (e: Exception) {
                sender.sendMessage("not works")
            }
            return true
        }

        sender.sendMessage("not palyer")
        return true
    }
}