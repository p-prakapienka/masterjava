package ru.javaops.web.handler;

import org.slf4j.event.Level;

public class Handlers {

    public static class SoapClientLoggingHandler extends SoapLoggingHandler {
        public SoapClientLoggingHandler(Level loggingLevel) {
            super(loggingLevel);
        }

        @Override
        protected boolean isRequest(boolean isOutbound) {
            return isOutbound;
        }
    }

    public static class SoapServerLoggingHandler extends SoapLoggingHandler {

        public SoapServerLoggingHandler() {
            super(Level.DEBUG);
        }

        @Override
        protected boolean isRequest(boolean isOutbound) {
            return !isOutbound;
        }
    }
}
