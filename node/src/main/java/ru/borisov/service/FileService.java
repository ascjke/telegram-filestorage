package ru.borisov.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.borisov.entity.AppDocument;
import ru.borisov.entity.AppPhoto;

public interface FileService {
    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
}
