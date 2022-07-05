package myproject.congestiontax.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Tax Request: tax request model that send by client via rest api
 */
public class TaxRequest implements Serializable {
    private String vehicle;
    private List<String> dates = new ArrayList<>();

    public TaxRequest(String vehicle, List<String> dates) {
        this.vehicle = vehicle;
        this.dates = dates;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    @JsonIgnore
    public Date[] getDateEntries() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dates.stream().map(d -> {
            try {
                return simpleDateFormat.parse(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new Date[0]);
    }
}
