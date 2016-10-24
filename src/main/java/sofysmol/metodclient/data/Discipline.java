package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 23.10.16.
 */
public class Discipline {
    String code;
    String name;

    @JsonCreator
    public Discipline(@JsonProperty("code") String code,
                   @JsonProperty("name") String name){
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
}
