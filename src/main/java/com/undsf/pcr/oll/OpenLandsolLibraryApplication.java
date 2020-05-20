package com.undsf.pcr.oll;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undsf.pcr.oll.entities.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.Order;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@Order(0)
public class OpenLandsolLibraryApplication implements ApplicationRunner {
    @Autowired
    private ObjectMapper mapper;

    @Value("${oll.data.landsor-library.equipment-data}")
    private String equipmentDataPath;

    public static void main(String[] args) {
        var builder = new SpringApplicationBuilder(OpenLandsolLibraryApplication.class);
        builder.web(WebApplicationType.NONE)
                .build(args)
                .run();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String path = equipmentDataPath;
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = fis.readAllBytes();
        String json = new String(buffer, StandardCharsets.UTF_8);
        TypeReference<List<com.undsf.pcr.oll.messages.Equipment>> type = new TypeReference<>() {};
        List<com.undsf.pcr.oll.messages.Equipment> list = mapper.readValue(json, type);

        List<Equipment> equipments = new ArrayList<>();
        for (var eqmsg : list) {
            Equipment equip = eqmsg.toEntity();
            if (equip != null)
                equipments.add(equip);
        }

        log.info("get {} equipement data", equipments.size());
    }
}
