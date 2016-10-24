package sofysmol.metodclient.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Kafedra {
    String code;
    String name;
    String phone;
    String codeFak;

    @JsonCreator
    public Kafedra(@JsonProperty("code") String code,
                    @JsonProperty("name") String name,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("codeFak") String codeFak){
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.codeFak = codeFak;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCodeFak() {
        return codeFak;
    }

    public String getCode() {
        return code;
    }
}
