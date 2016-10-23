package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Fakultet {
    private String code;
    private String name;

    @JsonCreator
    public Fakultet(@JsonProperty("code") String code,
                    @JsonProperty("name") String name){
        this.code = code;
        this.name = name;
    }
    public String getCode(){ return code;}
    public String getName(){return name;}
}
