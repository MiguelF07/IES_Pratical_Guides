package com.ies.ex2_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowList {
    List<Show> shows;

    public ShowList()
    {
        shows = new ArrayList<Show>();
        List<String> q1 = new ArrayList<>();
        q1.add("Do not pity the dead, Harry. Pity the living, and, above all those who live without love.");
        q1.add("It is impossible to manufacture or imitate love");
        q1.add("Differences of habit and language are nothing at all if our aims are identical and our hearts are open.");
        Show harryPotter = new Show("Harry Potter",q1);
        List<String> q2 = new ArrayList<>();
        q2.add("[Hope] Is The Only Thing Stronger Than Fear");
        q2.add("But Our Lives Aren't Just Measured In Years. They're Measured In The Lives Of People We Touch Around Us.");
        q2.add("Thank You For Your Consideration.");
        Show hungerGames = new Show("Hunger Games",q2);
        List<String> q3 = new ArrayList<>();
        q3.add("BOND. JAMES BOND");
        q3.add("A MARTINI. SHAKEN, NOT STIRRED");
        q3.add("THERE'S A SAYING IN ENGLAND: WHERE THERE'S SMOKE, THERE'S FIRE");
        Show double07 = new Show("007",q3);
        shows.add(harryPotter);
        shows.add(hungerGames);
        shows.add(double07);
    }

    public List<String> listShows()
    {
        List<String> showNames = new ArrayList<>();
        for(Show s:this.shows)
        {
            showNames.add(s.getNome());
        }
        return showNames;
    }

    public int randomShow() //Returns the id of the show
    {
        Random rand = new Random();
        int opcao = rand.nextInt(shows.size());
        return shows.get(opcao).getId();
    }

    public String[] randomQuote(int showId)
    {
        for(Show s:shows)
        {
            if(s.getId()==showId)
            {
                Random rand = new Random();
                int opcao = rand.nextInt(s.quotes.size());
                String[] lista=new String[2];
                lista[0] = shows.get(showId).getNome();
                lista[1] = s.quotes.get(opcao);
                return lista;
            }
        }
        return null;
    }

}
