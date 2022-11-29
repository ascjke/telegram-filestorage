package ru.borisov.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.borisov.dao.AppUserDao;
import ru.borisov.dao.RawDataDAO;
import ru.borisov.entity.AppUser;
import ru.borisov.entity.RawData;
import ru.borisov.entity.enums.UserState;
import ru.borisov.service.MainService;
import ru.borisov.service.ProducerService;

import static ru.borisov.entity.enums.UserState.BASIC_STATE;

@Service
public class MainServiceImpl implements MainService {

    private final RawDataDAO rawDataDAO;
    private final ProducerService producerService;
    private final AppUserDao appUserDao;

    public MainServiceImpl(RawDataDAO rawDataDAO, ProducerService producerService, AppUserDao appUserDao) {
        this.rawDataDAO = rawDataDAO;
        this.producerService = producerService;
        this.appUserDao = appUserDao;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
        var textMessage = update.getMessage();
        var telegramUser = textMessage.getFrom();
        var appUser = findOrSaveAppUser(telegramUser);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from Node");
        producerService.produceAnswer(sendMessage);
    }

    private AppUser findOrSaveAppUser(User telegramuser) {

        AppUser persistentAppUser = appUserDao.findAppUserByTelegramUserId(telegramuser.getId());
        if (persistentAppUser == null) {
            AppUser transientAppUser = AppUser.builder()
                    .telegramUserId(telegramuser.getId())
                    .username(telegramuser.getUserName())
                    .firstName(telegramuser.getFirstName())
                    .lastName(telegramuser.getLastName())
                    //TODO изменить значение по умолчанию после добавления регистрации
                    .isActive(true)
                    .state(BASIC_STATE)
                    .build();
            return appUserDao.save(transientAppUser);
        }
        return persistentAppUser;
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                .event(update)
                .build();
        rawDataDAO.save(rawData);

    }
}
