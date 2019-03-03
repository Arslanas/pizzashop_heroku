package pizzaShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ping")
public class PingController {
    @Autowired
    RestTemplate restTemplate;
    private final String server = "https://homefin-server.herokuapp.com";
    private double min = 0.2;
    private int count;


    @GetMapping()
    public void ping() {
        infinitePingOfServer(server, min);
    }
    @GetMapping("pingResponse")
    public String pingResponse() {
        return "Ping_Response_OK";
    }


    private void infinitePingOfServer(String url, double min){
        while (true) {
            sleepFor(min);
            getResponse(url);
            count++;
            System.out.println(count);
        }
    }
    private void getResponse(String url) {
        ResponseEntity<Object> entity = restTemplate.getForEntity(url, Object.class);
    }
    private void sleepFor(double min){
        long time = (long) min * 1000 * 60;
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
