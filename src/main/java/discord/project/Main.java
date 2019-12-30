package discord.project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.time.Duration;
import java.time.Instant;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.dv8tion.jda.core.*;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private static JDA builder;
    private static int messagecount;
    private static int callumcount;
    private static int pings;
    private static int sextalk;
    private String botID = "652988416996147201";
    private static Instant start;
    private Duration timeElapsed;


    public static void main(String[] args) throws Exception {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./MarianToken")), "UTF8"));
            builder = new JDABuilder(AccountType.BOT).setToken(reader.readLine()).build();
            reader.close();
            builder.getPresence().setActivity(Activity.watching("anime"));
            messagecount = 0;
            callumcount = 0;
            pings = 0;
            sextalk = 0;
            start = Instant.now();
            builder.addEventListener(new Main());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Instant end = Instant.now();
        timeElapsed = Duration.between(start, end);
        String author = event.getAuthor().getId();
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String[] split = content.split("\\s+", -1);
        if (author.equals("399120960906854411") && content.contains("terminate") && isTarget(split, botID)) {
            System.exit(0);
        }
        if (timeElapsed.toMinutes() >= 30 && builder.getPresence().getStatus().equals(OnlineStatus.INVISIBLE)) {
            builder.getPresence().setStatus((OnlineStatus.ONLINE));
            pings = 0;
        }

        if (event.getChannel().getName().equals("general") && !event.getAuthor().getId().equals(botID) && builder.getPresence().getStatus().equals(OnlineStatus.ONLINE)) {
            messagecount++;

            String name = event.getAuthor().getName();
            List<Role> roles = message.getMentionedRoles();
            MessageChannel channel = event.getChannel();
            Random random = new Random();



            String jamesBotID = "617295138271526912";
            String callumsID = "253532065096663042";
            String paulID = "436845534125490196";
            String dannersBotID = "654355523625287719";

            System.out.println("We received a message from " +
                     name + ": " +
                    event.getMessage().getContentDisplay()
                    + ": " + messagecount);

            for (Role r : roles) {
                if (r.getName().equals(("Player")) && random.nextInt(20) == 1){
                    channel.sendMessage("no").queue();
                    return;
                }
            }
            boolean pass = true;
            if (author.equals(jamesBotID)){
                pass = false;
                if (split[0].equals("very") && split[1].equals("cool")) {
                    channel.sendMessage("thanks <@!" + jamesBotID + ">").queue();
                    return;
                } else {
                    if (random.nextInt(10) == 1) {
                        pass = true;
                    }
                }
            } else if (author.equals(dannersBotID)) {
                pass = false;
                if (random.nextInt(10) == 1) {
                    pass = true;
                }
            }
            if (pass){
                if (content.toLowerCase().contains("sex") || content.toLowerCase().contains("pegging")) {
                    sextalk++;
                    if (sextalk < 5) {
                        switch (random.nextInt(2)) {
                            case 0:
                                channel.sendMessage("no sex talk please").queue();
                                break;
                            case 1:
                                channel.sendMessage("<@!" + author + "> stop.").queue();
                                break;
                        }
                    } else {
                        ragequits(channel);
                        sextalk = 0;
                    }
                    return;
                }

                if (split[0].toLowerCase().equals("imagine") && random.nextInt(10) == 1) {
                    channel.sendMessage("imagine").queue();
                    return;
                }

                if (isTarget(split, botID)) {
                    pings++;
                    if (pings < 20) {
                        if (author.equals(callumsID)) {
                            channel.sendMessage("<@!" + callumsID + "> motherfucker").queue();
                            callumcount = 0;
                        } else if (author.equals(paulID)) {
                            channel.sendMessage("PAULLLLLLLLLLLLLL").queue();
                        } else {
                            switch (random.nextInt(5)) {
                                case 0:
                                    channel.sendMessage("ree").queue();
                                    break;
                                case 1:
                                    channel.sendMessage("The pings are unbearable").queue();
                                    break;
                                case 2:
                                    channel.sendMessage("xd").queue();
                                    break;
                                case 3:
                                    channel.sendMessage("k").queue();
                                    break;
                                case 4:
                                    channel.sendMessage("OwO").queue();
                                    break;
                            }
                        }
                    } else {
                        switch (random.nextInt(10)) {
                            case 0:
                            case 1:
                            case 2:
                                channel.sendMessage("The pings are unbearable").queue();
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                channel.sendMessage("fuck off <@!" + author + ">").queue();
                                break;
                            case 9:
                                ragequits(channel);
                                break;
                        }
                    }
                    return;
                }

                if (messagecount >= 100) {
                    messagecount = 0;
                    if (random.nextInt(2) ==  0){
                        channel.sendMessage("I'm scared").queue();
                    } else {
                        channel.sendMessage("I am confusion").queue();
                    }

                    return;
                }

                // if callum messages a lot
                if (author.equals(callumsID)) {
                    callumcount++;
                    if (callumcount > 20) {
                        channel.sendMessage("<@!" + callumsID + "> motherfucker").queue();
                        callumcount = 0;
                    }
                    return;
                }



                if (content.toLowerCase().contains("halfflip") || content.toLowerCase().contains("half flip") || content.toLowerCase().contains("half-flip")) {
                    channel.sendMessage("whats that?").queue();
                    return;
                }

                if (content.toLowerCase().contains("nrg")) {
                    channel.sendMessage(":heart_eyes: ").queue();
                    return;
                }
                if (content.toLowerCase().contains("gay")) {
                    channel.sendMessage(":grimacing: ").queue();
                    return;
                }
                if (content.toLowerCase().contains("tory")) {
                    channel.sendMessage(":rage: :rage: :rage:").queue();
                }
                if (content.toLowerCase().contains("politics")) {
                    channel.sendMessage("if anyone votes tory unfriend me ").queue();
                }
                if (content.toLowerCase().contains("weird")) {
                    channel.sendMessage("but it worksssss").queue();
                }
                if (content.toLowerCase().equals("the pings are unbearable")) {
                    channel.sendMessage("agreed").queue();
                }
            }

        }

    }

    private void ragequits(MessageChannel channel) {
        channel.sendMessage("i'm done").queue();
        channel.sendMessage("***passive aggresively rage quits***").queue();
        builder.getPresence().setStatus(OnlineStatus.OFFLINE);
        start = Instant.now();
    }
    private boolean isTarget(String[] args, String id) {
        for (String arg : args) {
            if (arg.equals("<@!"+id+">")||arg.equals("<@"+id+">")) {
                return true;
            }
        }
        return false;
    }
}
