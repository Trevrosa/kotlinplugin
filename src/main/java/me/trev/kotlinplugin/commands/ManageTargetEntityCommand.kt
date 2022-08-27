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
                sender.sendMessage("the current targeted entity is [$targetEntity]")
                return true
            }
            if (args.first().isEmpty()) {
                sender.sendMessage("the current targeted entity is [$targetEntity]")
                return true
            }
            if (!args.first().all { a -> a.isLetter() }) {
                sender.sendMessage("you did not specify a valid string")
                return true
            }
            if (targetEntity == args.first().lowercase()) {
                sender.sendMessage("the target entity is already [$targetEntity]")
                return true
            }
            if (!sender.isOp) {
                sender.sendMessage("you need to be op to set the target entity")
                return true
            }

            sender.sendMessage("changed target entity from $targetEntity to ${args.last().lowercase()}")
            targetEntity = args.last().lowercase()
        }
        else {
            sender.sendMessage("only players can use this command")
        }

        return true
    }
}