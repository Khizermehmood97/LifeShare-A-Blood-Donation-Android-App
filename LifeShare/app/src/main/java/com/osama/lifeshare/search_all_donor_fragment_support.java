package com.osama.lifeshare;

public class search_all_donor_fragment_support {

    private String name;
    private String blood_Group;
    private String phone;
    public search_all_donor_fragment_support()
    {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood_Group() {
        return blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        this.blood_Group = blood_Group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public search_all_donor_fragment_support(String name, String blood_Group, String phone) {
        this.name = name;
        this.blood_Group = blood_Group;
        this.phone = phone;
    }
}
