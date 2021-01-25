package com.pequla.bot.programmer;

import com.pequla.bot.programmer.modules.CommandModule;
import com.pequla.bot.programmer.modules.JoinModule;
import com.pequla.bot.programmer.modules.LeaveModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main {

    public static final String GUILD_ID = "770606374618660884";
    public static final String INFO_CHANNEL_ID = "791683484342943775";
    public static final String CHAT_CHANNEL_ID = "770606374618660889";
    public static Color ORANGE_COLOR = new Color(255, 127, 0);
    public static Color GREEN_COLOR = new Color(0, 128, 0);
    public static Color RED_COLOR = new Color(130, 10, 0);

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                JDA jda = JDABuilder.createDefault(args[0])
                        .setActivity(Activity.listening("Jala i Buba"))
                        .addEventListeners(new JoinModule())
                        .addEventListeners(new LeaveModule())
                        .addEventListeners(new CommandModule())
                        .build();
                try {
                    jda.awaitReady();
                    SelfUser user = jda.getSelfUser();
                    System.out.println("Name: " + user.getName());
                    System.out.println("ID: " + user.getId());
                    System.out.println("Guilds: " + jda.getGuilds().size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (LoginException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("Please add a bot token as an argument");
        }
    }
}
