package pro.sky.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Pet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    private final ObjectMapper objectMapper;

    public InfoServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, String> getInfo() {
        Map<String, String> infoMap = new HashMap<>();
        try (InputStream infoStream = getClass().getClassLoader().getResourceAsStream("info.json")) {
            TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {

            };
            return objectMapper.readValue(infoStream, typeRef);


        } catch (IOException e) {
            return infoMap;
        }
    }
}



//    @Override
//    public List<Pet> getPets() throws IOException {
//        List<Pet> pets = new ArrayList<>();
//        try (InputStream petStream = getClass().getClassLoader().getResourceAsStream("pet.json")) {
//            JsonNode rootNode = objectMapper.readTree(petStream);
//            if (rootNode.hasNonNull("pets")) {
//                JsonNode petsNode = rootNode.get("pets");
//                pets.addAll(List.of(objectMapper.convertValue(petsNode, Pet[].class)));
//
//            }
//        }
//        return pets;
//
//    }
//}


