package com.pankal.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.pankal.entities.CarPart;
import com.pankal.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "hello")
@RestController
public class Application {

    private final MyService myService;

    public Application(MyService myService) {
        this.myService = myService;
    }

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @GetMapping("/")
    public String home() {
        return myService.message();
    }

    public static void main(String[] args) {

//        SpringApplication.run(Application.class, args);

        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("pankal-e7786-firebase-adminsdk-s39q1-1348fc0b9d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://pankal-e7786.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            Firestore fs = FirestoreClient.getFirestore();



            fs.getCollections().forEach(((k)->{

                System.out.println("Item : " + k.getId() );

            }));

            CollectionReference car_parts = fs.collection("car_parts");

            byte[] jsonData = Files.readAllBytes(Paths.get("car_parts.json"));

            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //convert json string to object
            JsonNode rootNode = objectMapper.readTree(jsonData);

            if(rootNode.isArray()){
                ((ArrayNode)rootNode).forEach(cpart->{
                    System.out.println("cpart : " + cpart.toString() );
                    CarPart cp = new CarPart(
                            cpart.get("id").asInt(),
                            cpart.get("parent_id").asInt(),
                            cpart.get("name").asText(),
                            cpart.get("path").asText(),
                            cpart.get("has_make").asBoolean());

                    car_parts.add(cp);

                });
            }


//            CarPart cp = new CarPart(1344, 1328, "Κοτσαδόροι222",
//                    "Ανταλλακτικα & Αξεσουάρ-> Κλάρκ-> Αμάξωμα-> Κοτσαδόροι", true);
//
//
//            car_parts.add(cp);



            System.out.println("FirebaseApp initialized");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
