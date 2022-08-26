package me.trev.kotlinplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ManageTargetEntityCommand : CommandExecutor {
    var targetEntity = "player"

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.sendMessage("you did not specify anything")
                return true
            }
            if (args.first().lowercase() != "get" && args.first().lowercase() != "set") {
                sender.sendMessage("thats not correct (try using /target get or /target set)")
                return true
            }

            if (args.first().lowercase() == "get") {
                sender.sendMessage("the current targeted entity is [$targetEntity]")
            }
            else if (args.first().lowercase() == "set") {
                if (!sender.isOp) {
                    sender.sendMessage("you need to be op to set the target entity")
                    return true
                }
                if (args.last().isEmpty()) {
                    sender.sendMessage("you did not specify anything")
                    return true
                }
                if (args.last().toIntOrNull() != null || args.last().toFloatOrNull() != null || args.last().toDoubleOrNull() != null) {
                    sender.sendMessage("you did not specify a valid string")
                    return true
                }
                if (targetEntity == args.last().lowercase()) {
                    sender.sendMessage("the target entity is already [$targetEntity]")
                    return true
                }

                sender.sendMessage("changed target entity from $targetEntity to ${args.last().lowercase()}")
                targetEntity = args.last().lowercase()
            }
        }
        else {
            sender.sendMessage("only players can use this command")
        }

        return true
    }
}