package edu.sportanalytics.control;

import javax.swing.*;
import java.util.logging.*;

class Logging
{
    private final static Logger log = Logger.getLogger(Logging.class.getName());

    private static JTextArea loggingArea = null;
    private static TextAreaHandler handler = null;

    public static JTextArea getLoggingArea() {
        if (loggingArea == null)
        {
            loggingArea = new JTextArea();
            loggingArea.setAutoscrolls(true);
            loggingArea.setEditable(false);
            handler = new TextAreaHandler();
            Logger.getLogger("").addHandler(handler);
            handler.setTextArea(loggingArea);
            log.info("LogArea initialized");
        }
        return loggingArea;
    }
}

class TextAreaHandler extends StreamHandler
{
    private JTextArea textArea = null;

    public void setTextArea(JTextArea textArea)
    {
        this.textArea = textArea;
    }

    @Override
    public void publish(LogRecord record)
    {
        super.publish(record);

        flush();
        if (textArea != null)
        {
            textArea.append(getFormatter().format(record));
        }
    }
}