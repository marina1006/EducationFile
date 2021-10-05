import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) throws TelegramApiRequestException{
        ApiContextInitializer.init();
        // для proxy
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        Bot bot = new Bot();
        TelegramBotsApi telegram = new TelegramBotsApi();

        try{
            telegram.registerBot(bot);
           }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
