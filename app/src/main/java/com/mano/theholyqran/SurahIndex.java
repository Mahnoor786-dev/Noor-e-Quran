package com.mano.theholyqran;

public class SurahIndex {
    private String nameUrdu;
    private String nameEnglish;

    public SurahIndex(String nameUrdu, String nameEnglish) {
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
