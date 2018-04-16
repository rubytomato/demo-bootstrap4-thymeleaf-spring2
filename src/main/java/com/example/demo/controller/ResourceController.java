package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("resource")
@Slf4j
public class ResourceController {

    final private String resourceFileName = "META-INF/resources/meta-inf-resource.txt";
    //final private String resourceFileName = "META-INF/resources/nested-meta-inf-resource.txt";

    @GetMapping("r1")
    public String r1(Model model) {
        StringBuilder builder = new StringBuilder();
        try {
            Resource resource = new DefaultResourceLoader().getResource("classpath:" + resourceFileName);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                reader.lines().forEach(line -> {
                    builder.append(line).append(System.lineSeparator());
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
        System.out.println(builder.toString());
        model.addAttribute("text", "R1 : " + builder.toString());
        return "resource";
    }

    /*
     * jarファイルにパッケージして実行するとエラー
     */
    @GetMapping("r1f")
    public String r1f(Model model) {
        StringBuilder builder = new StringBuilder();
        try {
            Resource resource = new DefaultResourceLoader().getResource("classpath:" + resourceFileName);

            try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
                reader.lines().forEach(line -> {
                    builder.append(line).append(System.lineSeparator());
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
        System.out.println(builder.toString());
        model.addAttribute("text", "R1F : " + builder.toString());
        return "resource";
    }

    @GetMapping("r2")
    public String r2(Model model) {
        StringBuilder builder = new StringBuilder();
        try {
            ClassPathResource resource = new ClassPathResource(resourceFileName);

            Path path = Files.createTempFile("temp_", ".txt");
            System.out.println(path.toAbsolutePath().toString());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                 BufferedWriter writer = Files.newBufferedWriter(path)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append(System.lineSeparator());
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
        System.out.println(builder.toString());
        model.addAttribute("text", "R2 : " + builder.toString());
        return "resource";
    }

    @GetMapping("r3")
    public String r3(Model model) {
        StringBuilder builder = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(resourceFileName), "UTF-8"))) {
                reader.lines().forEach(line -> {
                    builder.append(line).append(System.lineSeparator());
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(builder.toString());
        model.addAttribute("text", "R3 : " + builder.toString());
        return "resource";
    }

}
