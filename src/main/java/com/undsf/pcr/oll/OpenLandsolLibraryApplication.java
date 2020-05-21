package com.undsf.pcr.oll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undsf.pcr.oll.entities.Equipment;
import com.undsf.pcr.oll.repositories.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@Order(0)
public class OpenLandsolLibraryApplication implements ApplicationRunner {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EquipmentRepository equipRepo;

    @Autowired
    private OkHttpClient httpClient;

    @Value("${oll.data.landsor-library.equipment-data}")
    private String equipmentDataPath;

    public static void main(String[] args) {
        var builder = new SpringApplicationBuilder(OpenLandsolLibraryApplication.class);
        var context = builder.web(WebApplicationType.SERVLET)
                .build(args)
                .run();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        updateEquipmentsExtInfo();
    }

    private void updateEquipments() {
        try {
            String path = equipmentDataPath;
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = fis.readAllBytes();
            String json = new String(buffer, StandardCharsets.UTF_8);
            TypeReference<List<com.undsf.pcr.oll.messages.Equipment>> type = new TypeReference<>() {
            };
            List<com.undsf.pcr.oll.messages.Equipment> list = mapper.readValue(json, type);

            List<Equipment> equipments = new ArrayList<>();
            for (var eqmsg : list) {
                Equipment equip = eqmsg.toEntity();
                if (equip != null)
                    equipments.add(equip);
            }

            log.info("get {} equipement data", equipments.size());
            int savedCounter = 0;
            for (var equip : equipments) {
                Equipment saved = equipRepo.save(equip);
                if (saved != null) savedCounter++;
            }

            log.info("{} equipments saved.", savedCounter);
        }
        catch (FileNotFoundException ex) {
            log.error("文件未找到：", ex.getMessage());
        }
        catch (JsonProcessingException ex) {
            log.error("JSON解析错误：", ex.getMessage());
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    private void updateEquipmentsExtInfo() {
        var url = "https://wiki.biligame.com/pcr/%E8%A3%85%E5%A4%87%E4%B8%80%E8%A7%88";
        var req = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            var resp = httpClient.newCall(req).execute();
            var html = resp.body().string();
            var doc = Jsoup.parse(html);
            var links = doc.select("div.bili-box span a");
            for (var link : links) {
                var name = link.attr("title");
                var img = link.selectFirst("img");
                var fileName = img.attr("alt");
                int id = Integer.parseInt(fileName.substring(0, 6));
                String srcset = img.attr("srcset");
                int startAt = srcset.indexOf("1.5x,");
                int endAt = srcset.lastIndexOf(" 2x");

                String iconUrl = srcset.substring(startAt + 5, endAt).trim();

                var equipOpt = equipRepo.findById(id);
                if (equipOpt.isEmpty()) {
                    log.warn("物品{}不存在", id);
                    continue;
                }

                var equip = equipOpt.get();
                equip.name = name;
                equip.iconUrl = iconUrl;

                equipRepo.save(equip);
                log.info("物品{}:{}保存成功", id, name);
            }
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
