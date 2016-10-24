package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Specialty {
    String code;
    String name;
    String qualification;

    @JsonCreator
    public Specialty(@JsonProperty("code") String code,
                   @JsonProperty("name") String name,
                   @JsonProperty("qualification") String qualification){
        this.code = code;
        this.name = name;
        this.qualification = qualification;

    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }
}
