package me.trev.kotlinplugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ManageTargetEntityCommand : CommandExecutor {

    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    var targetEntity = "player"

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.server.say("you did not specify anything")
                return true
            }
            if (args.first().lowercase() != "get" && args.first().lowercase() != "set") {
                sender.server.say("thats not correct (try using /target get or /target set)")
                return true
            }

            if (args.first().lowercase() == "get") {
                sender.server.say("the current targeted entity is [$targetEntity]")
            }
            else if (args.first().lowercase() == "set") {
                if (!sender.isOp) {
                    sender.server.say("you need to be op to set the target entity")
                    return true
                }
                if (args.last().isEmpty()) {
                    sender.server.say("you did not specify anything")
                    return true
                }
                if (args.last().toIntOrNull() != null || args.last().toFloatOrNull() != null || args.last().toDoubleOrNull() != null) {
                    sender.server.say("you did not specify a valid string")
                    return true
                }
                if (targetEntity == args.last().lowercase()) {
                    sender.server.say("the target entity is already [$targetEntity]")
                    return true
                }

                sender.server.say("changed target entity from $targetEntity to ${args.last().lowercase()}")
                targetEntity = args.last().lowercase()
            }
        }
        else {
            sender.server.say("only players can use this command")
        }

        return true
    }
}