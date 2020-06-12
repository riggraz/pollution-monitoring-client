import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    private Menu menu;
    private Gson gson;
    private String gatewayAddress = "http://localhost:3000";

    public Client() {
        menu = new Menu();
        gson = new Gson();
    }

    public void start() {
        while (true) {
            int[] userInput = menu.getUserInput();
            int choice = userInput[0];
            int n = userInput[1];

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("Getting number of nodes from gateway...");

                    Integer nodesCount = (Integer) makeRequest(gatewayAddress + "/nodes/count", Integer.TYPE);
                    System.out.println(nodesCount);

                    break;

                case 2:
                    System.out.println("Getting last " + n + " measurements from gateway...");

                    String url = gatewayAddress + "/measurements?n=" + n;
                    Type measurementListType = new TypeToken<ArrayList<Measurement>>(){}.getType();
                    ArrayList<Measurement> mList = (ArrayList<Measurement>) makeRequest(url, measurementListType);

                    for (Measurement m : mList) {
                        System.out.println(m);
                    }

                    break;

                case 3:
                    System.out.println("Getting standard deviation and average of last " + n + " measurements from gateway...");

                    Type t = new TypeToken<HashMap<String, Double>>(){}.getType();
                    HashMap<String, Double> standardDeviationAndAverage = (HashMap<String, Double>) makeRequest(
                            gatewayAddress + "/measurements/deviation?n=" + n,
                            t
                    );

                    System.out.println("Standard deviation: " + standardDeviationAndAverage.get("standardDeviation"));
                    System.out.println("Average: " + standardDeviationAndAverage.get("average"));

                    break;

                default:
            }
        }
    }

    private Object makeRequest(String sUrl, Type responseType) {
        try {
            URL url = new URL(sUrl);
            URLConnection req = url.openConnection();
            req.connect();

            Object res = gson.fromJson(
                    new InputStreamReader((InputStream) req.getContent()),
                    responseType
            );

            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
