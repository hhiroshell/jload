package com.github.hhiroshell.jload.lemming;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class LemmingFactory {

    private List<Long> ids = new ArrayList<>();

    private Random random = new Random();

    private final long ID_MAX = Long.MAX_VALUE;

    public Lemming generate(String name) {
        Long id = getRandomId();
        while (ids.contains(id)) {
            id = getRandomId();
        }
        ids.add(id);

        return new Lemming(id, name);
    }

    private long getRandomId() {
        return random.nextLong(ID_MAX);
    }
}
