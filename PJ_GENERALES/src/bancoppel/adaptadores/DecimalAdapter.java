package bancoppel.adaptadores;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DecimalAdapter extends XmlAdapter<DecimalAdapter.AdaptedDecimal, BigDecimal>{

    @Override
    public AdaptedDecimal marshal(BigDecimal decimal) throws Exception {
        AdaptedDecimal AdaptedDecimal = new AdaptedDecimal();
        AdaptedDecimal.value = decimal;
        return AdaptedDecimal;
    }

    @Override
    public BigDecimal unmarshal(AdaptedDecimal AdaptedDecimal) throws Exception {
        return AdaptedDecimal.value;
    }

    public static class AdaptedDecimal {
        @XmlValue
        public BigDecimal value;
    }

}
