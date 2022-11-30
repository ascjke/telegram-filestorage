package ru.borisov.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.borisov.entity.AppDocument;

public interface FileService {
    AppDocument processDoc(Message externalMessage);
}
