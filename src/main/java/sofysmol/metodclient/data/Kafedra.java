package sofysmol.metodclient.data;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Kafedra {
    String code;
    String name;
    String phone;
    String codeFak;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCodeFak(String codeFak) {
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
