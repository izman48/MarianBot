package discord.project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.dv8tion.jda.core.*;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private static JDA builder;
    private static int messagecount;
    private static int callumcount;
    private static int pings;
    private String botid = "652988416996147201";
    private static Instant start;
    private Duration timeElapsed;


    public static void main(String[] args) throws LoginException {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("../../../MarianToken")), "UTF8"));
            builder = new JDABuilder(AccountType.BOT).setToken(reader.readLine()).build();
            reader.close();
            builder.getPresence().setActivity(Activity.watching("anime"));
            messagecount = 0;
            callumcount = 0;
            pings = 0;
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


        if (timeElapsed.toMinutes() >= 30 && builder.getPresence().getStatus().equals(OnlineStatus.INVISIBLE)) {
            builder.getPresence().setStatus((OnlineStatus.ONLINE));
            pings = 0;
        }

        if (event.getChannel().getName().equals("general") && !event.getAuthor().getId().equals(botid) && builder.getPresence().getStatus().equals(OnlineStatus.ONLINE)) {
            messagecount++;

            String name = event.getAuthor().getName();
            String author = event.getAuthor().getId();
            Message message = event.getMessage();
            String content = message.getContentRaw();
            MessageChannel channel = event.getChannel();
            Random random = new Random();

            String[] split = content.split("\\s+", -1);

            String jamesBotID = "617295138271526912";
            String callumsId = "253532065096663042";

            System.out.println("We received a message from " +
                     name + ": " +
                    event.getMessage().getContentDisplay()
                    + ": " + messagecount);

            if (content.contains("@Player")) {
                channel.sendMessage("no").queue();
            }

            if (isTarget(split, botid)) {
                    pings++;
                    if (pings < 20) {
                        switch (random.nextInt(5)) {
                            case 0:
                            case 1:
                            case 2:
                                channel.sendMessage("The pings are unbearable").queue();
                                break;
                            case 3:
                                channel.sendMessage("xd").queue();
                                break;
                            case 4:
                                channel.sendMessage("OwO").queue();
                                break;
                        }
                    } else {
                        switch (random.nextInt(5)) {
                            case 0:
                                channel.sendMessage("The pings are unbearable").queue();
                                break;
                            case 1:
                            case 2:
                            case 3:
                                channel.sendMessage("fuck off <@!" + author + ">").queue();
                                break;
                            case 4:
                                channel.sendMessage("i'm done").queue();
                                channel.sendMessage("***passive aggresively rage quits***").queue();
                                builder.getPresence().setStatus(OnlineStatus.OFFLINE);
                                start = Instant.now();
                                break;
                        }
                    }
            }

            if (messagecount >= 1000) {
                messagecount = 0;
                channel.sendMessage("I'm scared of gays").queue();
            }

            // if callum messages a lot
            if (author.equals(callumsId)){
                callumcount++;
                if (callumcount > 40) {
                    channel.sendMessage("<@!" + callumsId + "> motherfucker").queue();
                    callumcount = 0;
                }
            }

            //if jamesbot messages
            if (author.equals(jamesBotID)){
                if (content.contains("very cool")) {
                    channel.sendMessage("thanks <@!" + jamesBotID + ">").queue();
                }
            }

            if (split[0].toLowerCase().equals("imagine")) {
                channel.sendMessage("imagine").queue();
            }

        }

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
