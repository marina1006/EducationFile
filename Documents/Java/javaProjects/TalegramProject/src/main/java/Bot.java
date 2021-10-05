import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.print.Book;

public class Bot extends TelegramLongPollingBot {
        //Book book = new Book();

    @Override
    public String getBotUsername() {
        return "@testerforhelp_bot";
    }

    @Override
    public String getBotToken() {
        return "2023748691:AAGVNOSX5YBmVK0ojy26pkBGLGoYlz7o1l8";
    }

    @Override
    public void onUpdateReceived(Update update) {
       // update.getUpdateId();

        //SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        //long
        long chat_id = update.getMessage().getChatId();
        //sendMessage.setText(input(update.getMessage().getText()));

        if (update.getMessage()!=null && update.getMessage().hasText()) {
            //sendMessage.setText("Привет друг");
            try{
                execute(new SendMessage(chat_id,"Hi"  + " " + update.getMessage().getText()));
            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }

    private String input(String text) {
        if (text.contains("Hi") || text.contains("Hello") || text.contains("Привет") ) {
            return "Привет, друг";
        }

        return text;
    }
}
