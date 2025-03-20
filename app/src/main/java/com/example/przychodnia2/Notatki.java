package com.example.przychodnia2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notatki {
    public String notatka;
    public String dataWpisu;

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public String getDataWpisu() {
        return dataWpisu;
    }

    public void setDataWpisu(String dataWpisu) {
        this.dataWpisu = dataWpisu;
    }
public Notatki(){}
    public Notatki(String notatka)
    {
        this.notatka=notatka;
        dataWpisu=new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();

    }
}
