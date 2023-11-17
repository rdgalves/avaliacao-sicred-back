package com.sicred.avaliacao.exception;

import com.sicred.avaliacao.components.ApplicationContextProvider;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;

@NoArgsConstructor
public abstract class BaseAvaliacaoException extends RuntimeException {

    protected MessageSource messageSource;

    public BaseAvaliacaoException(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }


    @Override
    public abstract String getMessage();

    protected String getMessageFromSource(String code, Object[] args) {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(MessageSource.class)
                .getMessage(code, args, Locale.getDefault());
    }

}