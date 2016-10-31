package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 31.10.16.
 */
public class Sheduler {
    String semester;
    String lecture;
    String lab;
    String prak;
    String kurs;
    String report;

    @JsonCreator
    public Sheduler(@JsonProperty("semester") String semester,
                     @JsonProperty("lecture") String lecture,
                     @JsonProperty("prak") String prak,
                     @JsonProperty("lab") String lab,
                     @JsonProperty("kurs") String kurs,
                     @JsonProperty("report") String report){
        this.semester = semester;
        this.lecture = lecture;
        this.prak = prak;
        this.lab = lab;
        this.kurs = kurs;
        this.report = report;
    }

    public String getSemester() {
        return semester;
    }

    public String getLecture() {
        return lecture;
    }

    public String getLab() {
        return lab;
    }

    public String getPrak() {
        return prak;
    }

    public String getKurs() {
        return kurs;
    }

    public String getReport() {
        return report;
    }
}
