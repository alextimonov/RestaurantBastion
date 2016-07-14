package ua.goit.timonov.enterprise.meetings_offline.meeting_4.common;

import ua.goit.timonov.enterprise.meetings_offline.meeting_4.xml.xsd.XsdProcessorImpl;

/**
 * Created by Alex on 01.07.2016.
 */
public class XmlMain {
    public static void main(String[] args) {
        XmlProcessor xmlProcessor = getProcessor(0);

    }

    public static XmlProcessor getProcessor(int type) {
        switch (type) {
            case 0:
                return null;
//                return new SaxXmlProcessorImpl();
            case 1:
                return null;
            case 2:
                return new XsdProcessorImpl();
            default: throw new RuntimeException("Incorrect type");
        }
    }
}
