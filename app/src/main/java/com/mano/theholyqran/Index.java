package com.mano.theholyqran;

public class Index {
    private String nameUrdu;
    private String nameEnglish;

    public Index(String nameUrdu, String nameEnglish) {
        this.nameUrdu = nameUrdu;
        this.nameEnglish = nameEnglish;
    }

    public String getNameUrdu() {
        return nameUrdu;
    }

    public void setNameUrdu(String nameUrdu) {
        this.nameUrdu = nameUrdu;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }
}
