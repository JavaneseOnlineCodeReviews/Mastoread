package house.winkleak.mastoreader.util;

/**
 * Created by Happy on 06.02.2018.
 * Файл конфигурации приложжения
 */

public interface AppConfig {

    //NETWORK

        String VERSION_API = "v1";
        String BASE_URL = "https://mastodon.social/api/" + VERSION_API + "/";

        int MAX_CONNECT_TIMEOUT = 5000;
        int MAX_READ_TIMEOUT = 5000;
        int START_DELAY = 1500;

    //END NETWORK

}
