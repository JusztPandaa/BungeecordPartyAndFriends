package de.simonsator.partyandfriends.friends.subcommands;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.main.Main;

import java.util.List;
import java.util.regex.Matcher;

import static de.simonsator.partyandfriends.main.Main.getPlayerManager;
import static de.simonsator.partyandfriends.utilities.PatterCollection.PLAYER_PATTERN;

/**
 * The command remove
 *
 * @author Simonsator
 * @version 1.0.0
 */
public class Remove extends FriendSubCommand {

	public Remove(List<String> pCommands, int pPriority, String pHelp, String pPermission) {
		super(pCommands, pPriority, pHelp, pPermission);
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (!isPlayerGiven(pPlayer, args))
			return;
		PAFPlayer playerQuery = getPlayerManager().getPlayer(args[1]);
		if (!isAFriendOf(pPlayer, playerQuery, args))
			return;
		pPlayer.removeFriend(playerQuery);
		pPlayer.sendMessage((PREFIX + PLAYER_PATTERN.matcher(Main.getInstance()
				.getMessagesYml().getString("Friends.Command.Remove.Removed")).replaceAll(Matcher.quoteReplacement(args[1]))));
	}

}
