package ies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ies.IpmaCityForecast;
import ies.IpmaService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Scanner;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static Logger logger = LogManager.getLogger(WeatherStarter.class);
    public static String getName() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the name of the city you want to check:");
        String name = sc.nextLine();
        sc.close();
        return name;
    }

    public static int getID() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the ID of the city you want to check:");
        Integer id = sc.nextInt();
        sc.close();
        return id;
    }

    public static void  main(String[] args ) {

        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Calls the function that asks for the id
        Integer id = getID();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(id);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                logger.info( "Forecast Date: " + forecast.getData().
                listIterator().next().getForecastDate());
                logger.info( "Maximum temperature for today: " + forecast.getData().
                        listIterator().next().getTMax());
                logger.info( "Minimum temperature for today: " + forecast.getData().
                listIterator().next().getTMin());
                logger.info( "Precipitation %: " + forecast.getData().
                listIterator().next().getPrecipitaProb());
                
            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);

    }
}