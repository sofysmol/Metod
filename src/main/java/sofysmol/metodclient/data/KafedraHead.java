package sofysmol.metodclient.data;

/**
 * Created by sofysmo on 08.10.16.
 */
public class KafedraHead {
    int passport;
    String codeKaf;
    String fio;
    String degree;

    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public String getCodeKaf() {
        return codeKaf;
    }

    public void setCodeKaf(String codeKaf) {
        this.codeKaf = codeKaf;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;
}
