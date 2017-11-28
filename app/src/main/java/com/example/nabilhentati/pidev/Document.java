package com.example.nabilhentati.pidev;

/**
 * Created by Nabil HENTATI on 26/11/2017.
 */

public class Document {
    private int  id;
    private String  typeDocu;
    private  String title;
    private  String Date;
    private boolean signeDoc ;

    public Document(int id, String typeDocu, String title, String date, boolean signeDoc) {
        this.id = id;
        this.typeDocu = typeDocu;
        this.title = title;
        Date = date;
        this.signeDoc = signeDoc;
    }

    public Document(String typeDocu, String title, String date, boolean signeDoc) {
        this.typeDocu = typeDocu;
        this.title = title;
        Date = date;
        this.signeDoc = signeDoc;
    }

    public Document(String typeDocu, String title, boolean signeDoc) {
        this.typeDocu = typeDocu;
        this.title = title;
        this.signeDoc = signeDoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeDocu() {
        return typeDocu;
    }

    public void setTypeDocu(String typeDocu) {
        this.typeDocu = typeDocu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public boolean isSigneDoc() {
        return signeDoc;
    }

    public void setSigneDoc(boolean signeDoc) {
        this.signeDoc = signeDoc;
    }


    public String getSigned(){
        if(signeDoc==true)
            return  "signed";
        else
            return  "not Signed";
    }
    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", typeDocu='" + typeDocu + '\'' +
                ", title='" + title + '\'' +
                ", Date='" + Date + '\'' +
                ", signeDoc=" + signeDoc +
                '}';
    }
}
