package discord.project;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.dv8tion.jda.core.*;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private int count = 0;
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder((AccountType.BOT));
        String token = "NjUyOTg4NDE2OTk2MTQ3MjAx.Xe6spw.um08IlIwNM-vAI8HHk_l_eTPh_o";
        builder.setToken(token);
        builder.addEventListeners((new Main()));
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getChannel().getName().equals("general")) {

            count++;
            String author = event.getAuthor().getName();
            Message message = event.getMessage();
            String content = message.getContentRaw();
            MessageChannel channel = event.getChannel();

            System.out.println("We received a message from " +
                     author + ": " +
                    event.getMessage().getContentDisplay());



            if (content.equals(("@Player"))) {
                channel.sendMessage("no").queue();
            }
            if (count == 1000) {
                count = 0;
                channel.sendMessage("I'm scared of gays").queue();
            }

        }

    }
}
