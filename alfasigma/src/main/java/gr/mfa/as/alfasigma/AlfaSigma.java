package gr.mfa.as.data;

import java.sql.Date;

public class AlfaSigma {

    int AS;
    String ASE;
    int aa;
    String ekdotis;
    Date theDate;
    String fakelos;
    int ypofakelos;
    String thema;
    String syntaktis;
    String notes;
    Date registerDate;
    Date registerTime;
    String classification;
    String sxetiko;

    public int getAS() {
        return AS;
    }

    public void setAS(int AS) {
        this.AS = AS;
    }

    public String getASE() {
        return ASE;
    }

    public void setASE(String ASE) {
        this.ASE = ASE;
    }

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }

    public String getEkdotis() {
        return ekdotis;
    }

    public void setEkdotis(String ekdotis) {
        this.ekdotis = ekdotis;
    }

    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }

    public String getFakelos() {
        return fakelos;
    }

    public void setFakelos(String fakelos) {
        this.fakelos = fakelos;
    }

    public int getYpofakelos() {
        return ypofakelos;
    }

    public void setYpofakelos(int ypofakelos) {
        this.ypofakelos = ypofakelos;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getSyntaktis() {
        return syntaktis;
    }

    public void setSyntaktis(String syntaktis) {
        this.syntaktis = syntaktis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSxetiko() {
        return sxetiko;
    }

    public void setSxetiko(String sxetiko) {
        this.sxetiko = sxetiko;
    }

    @Override
    public String toString() {
        return "AlfaSigma{" +
                "AS=" + AS +
                ", ASE='" + ASE + '\'' +
                ", aa=" + aa +
                ", ekdotis='" + ekdotis + '\'' +
                ", theDate=" + theDate +
                ", fakelos='" + fakelos + '\'' +
                ", ypofakelos=" + ypofakelos +
                ", thema='" + thema + '\'' +
                ", syntaktis='" + syntaktis + '\'' +
                ", notes='" + notes + '\'' +
                ", registerDate=" + registerDate +
                ", registerTime=" + registerTime +
                ", classification='" + classification + '\'' +
                ", sxetiko='" + sxetiko + '\'' +
                '}';
    }
}
