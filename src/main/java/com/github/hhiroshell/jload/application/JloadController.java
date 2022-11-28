package com.github.hhiroshell.jload.application;

import com.github.hhiroshell.jload.lemming.Lemming;
import com.github.hhiroshell.jload.lemming.LemmingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Remote cowsay invoker using Spring Boot
 *
 * @author hhiroshell
 */
@RestController
@RequestMapping("/")
public class JloadController {
    @Value("${number_of_lemmings}")
    private int defaultNumberOfLemmings;

    @Autowired
    private LemmingFactory lemmingFactory;

    /**
     * Return cowsay's 'say' message.
     *
     * @return Cowsay's 'say' message.
     */
    @RequestMapping(value = {"/", "/generate"})
    public List<Lemming> generate(@RequestParam(required = false) Optional<Integer> num) {
        Integer n = num.orElse(defaultNumberOfLemmings);

        List<Lemming> lemmings = new ArrayList<>(n){};
        for (int i = 0; i < n; i++) {
            lemmings.add(lemmingFactory.generate("hoge"));
        }

        return lemmings.stream().sorted(comparing(l -> l.getId())).collect(Collectors.toList());
    }

    /**
     * Send back a fixed String.
     *
     * @return Send back a fixed String.
     */
    @RequestMapping("/ping")
    public String ping() {
        System.out.println("I'm working...");
        return "I'm working...";
    }

}
