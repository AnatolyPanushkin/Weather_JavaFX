package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import org.json.JSONObject;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldCity;

    @FXML
    private Button buttonEnter;

    @FXML
    private Text temp_info;
    @FXML
    private Text max_temp;

    @FXML
    private Text min_temp;

    @FXML
    private Text pres;


    @FXML
    void initialize() {
        buttonEnter.setOnAction(event->{
            String getUserCity = fieldCity.getText().trim();
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q="+getUserCity +"&appid=697bd7b56941260bcadfab56108b53db&units=metric");
            System.out.println(output);
            if(!output.isEmpty()){
                JSONObject obj = new JSONObject (output);
                temp_info.setText("Temperature:"+obj.getJSONObject("main").getInt("temp"));
                max_temp.setText("max temperature:"+obj.getJSONObject("main").getInt("temp_max"));
                min_temp.setText("min temperature:"+obj.getJSONObject("main").getInt("temp_min"));
                pres.setText("pressure:"+obj.getJSONObject("main").getInt("pressure"));
            }
        });
        }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn= url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) !=null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        }
        catch (Exception e){
            System.out.println("This town doesn't exist");
        }
        return content.toString();
    }
}