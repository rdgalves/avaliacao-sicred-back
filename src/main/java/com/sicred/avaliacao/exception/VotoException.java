package com.sicred.avaliacao.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class VotoException extends BaseAvaliacaoException {
    private static final long serialVersionUID = 1L;
    private MessageSource messageSource;
    private String messageKey;
    private Object[] params;

    public VotoException(MessageSource messageSource, String messageKey, Object... params) {
        super();
        this.messageSource = messageSource;
        this.messageKey = messageKey;
        this.params = params;
    }

    @Override
    public String getMessage() {
        if (messageSource != null) {
            return messageSource.getMessage(messageKey, params, LocaleContextHolder.getLocale());
        } else {
            return "Falha ao recuperar mensagem de erro.";
        }
    }
}
