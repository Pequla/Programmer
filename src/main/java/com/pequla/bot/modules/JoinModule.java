package com.pequla.bot.modules;

import com.pequla.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public class JoinModule extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        if (guild.getId().equals(Main.GUILD_ID)) {
            TextChannel channel = guild.getTextChannelById(Main.CHAT_CHANNEL_ID);
            if (channel != null) {
                User user = event.getUser();
                channel.sendMessage(user.getAsMention()).embed(
                        new EmbedBuilder()
                                .setColor(Main.GREEN_COLOR)
                                .setAuthor(user.getAsTag(), null, user.getEffectiveAvatarUrl())
                                .setTitle(MarkdownUtil.bold("Dobro došao"))
                                .setDescription("Zdravo " + user.getName() + ", dobro došao na server!")
                                .setFooter(guild.getName(),guild.getIconUrl())
                                .setTimestamp(Instant.now())
                                .build()
                ).queue();
            }
        }
    }
}
