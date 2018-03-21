package gr.mfa.alfasigma.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataFactory {

    public static AlfaSigma get(ResultSet rs) throws SQLException {
        int i=1;
        AlfaSigma result = new AlfaSigma();
        result.setAS(rs.getInt("AS"));
        result.setASE(rs.getString("ASE"));
        result.setAa(rs.getInt("a/a"));
        result.setEkdotis(rs.getString("ekdotis"));
        result.setTheDate(rs.getDate("TheDate"));
        result.setFakelos(rs.getString("Fakelos"));
        result.setYpofakelos(rs.getInt("Ypofakelos"));
        result.setThema(rs.getString("Thema"));
        result.setSyntaktis(rs.getString("Syntaktis"));
        result.setNotes(rs.getString("Notes"));
        result.setRegisterDate(rs.getDate("RegisterDate"));
        result.setRegisterTime(rs.getDate("RegisterTime"));
        result.setClassification(rs.getString("Classification"));
        result.setSxetiko(rs.getString("Sxetiko"));
        return result;
    }

}
