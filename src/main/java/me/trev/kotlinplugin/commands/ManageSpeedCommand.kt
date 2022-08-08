package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.commands.PigCommand.speed
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ManageSpeedCommand : CommandExecutor {
    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.server.say("you did not specify anything")
                return true
            }
            if (args.first().lowercase() != "get" && args.first().lowercase() != "set") {
                sender.server.say("thats not correct (try using /speed get or /speed set)")
                return true
            }

            if (args.first().lowercase() == "get") {
                sender.server.say("the current speed is $speed")
            }
            else if (args.first().lowercase() == "set") {
                if (!sender.isOp) {
                    sender.server.say("you need to have op to set the speed")
                    return true
                }
                if (args.last().toDoubleOrNull() == null) {
                    sender.server.say("you did not specify a number")
                    return true
                }
                if (speed == args.last().toDouble()) {
                    sender.server.say("the speed is already $speed")
                    return true
                }

                sender.server.say("changed the speed from $speed to ${args.last().toDouble()}")

                speed = args.last().toDouble()
            }
        }
        else {
            sender.server.say("only players can use this command")
        }

        return true
    }
}