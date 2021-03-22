package com.pequla.bot.modules.commands;

import com.pequla.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.awt.*;
import java.time.Instant;
import java.util.Arrays;

public class AnnounceCommand implements GuildCommand {

    private String title;
    private String message;
    //String.join(" ", args)

    @Override
    public void execute(Member member, TextChannel channel, String[] args) {
        if (channel.getGuild().getId().equals(Main.GUILD_ID)) {
            if (args.length >= 1) {
                User author = member.getUser();
                if (args[0].equals("set")) {
                    if (args[1].equals("title")) {
                        title = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        channel.sendMessage("Title set to: " + title).queue();
                    }
                    if (args[1].equals("message")) {
                        message = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        channel.sendMessage("Message set to: " + message).queue();
                    }
                }
                if (args[0].equals("send")) {
                    TextChannel info = channel.getJDA().getTextChannelById(Main.INFO_CHANNEL_ID);
                    if (info != null) {
                        if (sendEmbed(info, author)) {
                            channel.sendMessage("Message sent successfully!").queue();
                            resetValues();
                        } else {
                            channel.sendMessage("Title and message can't be null or empty").queue();
                        }
                    }
                }
                if (args[0].equals("test")) {
                    channel.sendMessage("Test passed: " + sendEmbed(channel, author)).queue();
                }
                if (args[0].equals("help")) {
                    channel.sendMessage("Please visit this link: https://github.com/Pequla/Programmer#commands").queue();
                }
                if (args[0].equals("reset")) {
                    resetValues();
                }
            } else {
                channel.sendMessage("Bad arguments, do: !announce help").queue();
            }
        }
    }

    private boolean sendEmbed(TextChannel channel, User author) {
        if (title != null && !title.equals("") && message != null && !message.equals("")) {
            channel.sendMessage(
                    new EmbedBuilder()
                            .setColor(Color.GRAY)
                            .setAuthor(author.getName(), null, author.getEffectiveAvatarUrl())
                            .setTitle(MarkdownUtil.bold(title))
                            .setDescription(message)
                            .setTimestamp(Instant.now())
                            .build()
            ).queue();
            return true;
        }
        return false;
    }

    private void resetValues() {
        title = null;
        message = null;
    }
}
