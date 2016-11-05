package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Faculty {
    private String code;
    private String name;

    @JsonCreator
    public Faculty(@JsonProperty("code") String code,
                   @JsonProperty("name") String name){
        this.code = code;
        this.name = name;
    }
    public String getCode(){ return code;}
    public String getName(){return name;}
}
