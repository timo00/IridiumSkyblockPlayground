package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TransferCommand extends Command {

    public TransferCommand() {
        super(Arrays.asList("transfer"), "Transfer island ownership", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = User.getUser(p);
        if (user.getIsland() != null) {
            Island island = user.getIsland();
            if (island.getOwner().equals(p.getUniqueId().toString())) {
                OfflinePlayer player = Bukkit.getPlayer(args[1]);
                if (player != null) {
                    if (User.getUser(player).getIsland() == island) {
                        island.setOwner(player);
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().transferdOwnership.replace("%player%", player.getName()).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    } else {
                        sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().notInYourIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerOffline.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
