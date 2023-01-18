package pt.ipp.isep.g58;

import java.io.Serializable;

public class VaccineType implements Serializable {
    String code;
    String designation;
    String description;
    public VaccineType(String code, String designation){
        this.code = code;
        this.designation = designation;
    }
    public VaccineType(String code, String designation, String description){
        this.code = code;
        this.designation = designation;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){this.description = description;}
}

