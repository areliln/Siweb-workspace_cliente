package bancoppel.adaptadores;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateAdapter extends XmlAdapter<DateAdapter.AdaptedDate, XMLGregorianCalendar>{

    @Override
    public AdaptedDate marshal(XMLGregorianCalendar date) throws Exception {
        AdaptedDate adaptedDate = new AdaptedDate();
        adaptedDate.value = date;
        return adaptedDate;
    }

    @Override
    public XMLGregorianCalendar unmarshal(AdaptedDate adaptedDate) throws Exception {
        return adaptedDate.value;
    }

    public static class AdaptedDate {
        @XmlValue
        public XMLGregorianCalendar value;
    }

}
