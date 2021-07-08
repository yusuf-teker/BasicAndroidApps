package com.yusufteker.learnturkisapp;

public class Word { // BU KULLANACAGIMIZ DATANIN SEKLI GIBI BISEY
    private String mWordEn;
    private String mWordTr;
    private int resourceImageId = -1;
    private int resourceSoundId;
    private final int NO_IMAGE = -1;

    @Override
    public String toString() {
        return "Word{" +
                "mWordEn='" + mWordEn + '\'' +
                ", mWordTr='" + mWordTr + '\'' +
                ", resourceImageId=" + resourceImageId +
                ", resourceSoundId=" + resourceSoundId +
                ", NO_IMAGE=" + NO_IMAGE +
                '}';
    }

    public Word(String mWordEn, String wordTr ) {
        this.mWordEn = mWordEn;
        this.mWordTr = wordTr;
    }
    public Word(String mWordEn, String wordTr, int resourceImageId, int resourceSoundId) {
        this.mWordEn = mWordEn;
        this.mWordTr = wordTr;
        this.resourceImageId = resourceImageId;
        this.resourceSoundId = resourceSoundId;
    }
    public Word(String mWordEn, String wordTr, int resourceImageId) {
        this.mWordEn = mWordEn;
        this.mWordTr = wordTr;
        this.resourceImageId = resourceImageId;
    }

    public void setWordEn(String wordEn) {
        this.mWordEn = wordEn;
    }
    public void setWordTr(String wordTr) { this.mWordTr = wordTr; }
    public void setResourceImageId(int resourceImageId) { this.resourceImageId = resourceImageId; }
    public String getWordEn() {
        return mWordEn;
    }
    public String getWordTr() {
        return mWordTr;
    }
    public int getResourceImageId() { return resourceImageId; }
    public int getResourceSoundId() { return resourceSoundId; }
    public void setResourceSoundId(int resourceSoundId) { this.resourceSoundId = resourceSoundId; }
    public boolean hasImage(){
        return resourceImageId != NO_IMAGE;
    }

}
