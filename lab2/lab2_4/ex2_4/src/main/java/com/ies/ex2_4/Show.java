package com.ies.ex2_4;

import java.util.List;

public class Show {
    int id;
    String nome;
    List<String> quotes;

    public static int counter = 0;

    public Show(String nome, List<String> quotes)
    {
        this.id = counter;
        this.nome = nome;
        this.quotes = quotes;
        counter++;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> listQuotes() {
        return this.quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
    

}
    

