package com.example.ahmed.hearingexpretsystem.model;

/**
 * Created by Ahmed on 5/17/2018.
 */

public class Patient_Data {
    int P_ID;
    String P_name ;
    String P_age ;
    String Noise_type ;
    String Test_Degree ;
    String Dr_name ;

    public Patient_Data(int p_id,String p_name, String p_age, String noise_type, String test_Degree, String dr_name) {
        P_ID=p_id;
        P_name = p_name;
        P_age = p_age;
        Noise_type = noise_type;
        Test_Degree = test_Degree;
        Dr_name = dr_name;
    }

//    public Patient_Data(Object o) {
//        P_ID=o.;
//        P_name = p_name;
//        P_age = p_age;
//        Noise_type = noise_type;
//        Test_Degree = test_Degree;
//        Dr_name = dr_name;
//    }


    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }

    public String getP_age() {
        return P_age;
    }

    public void setP_age(String p_age) {
        P_age = p_age;
    }

    public String getNoise_type() {
        return Noise_type;
    }

    public void setNoise_type(String noise_type) {
        Noise_type = noise_type;
    }

    public String getTest_Degree() {
        return Test_Degree;
    }

    public void setTest_Degree(String test_Degree) {
        Test_Degree = test_Degree;
    }

    public String getDr_name() {
        return Dr_name;
    }

    public void setDr_name(String dr_name) {
        Dr_name = dr_name;
    }
}
