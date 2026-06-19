package org.example.repository;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataRepository {

    private final Map<String, Object> data = new HashMap<>();

    public DataRepository() {

        data.put("animal_1", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name1", 5, "Cream", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_2",
                new Animal("Dog", "name2", 4, "cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_3", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Cat", "name3", 2, "black", "09-11-2020", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_4",
                new Animal("Cat", "name4", 1, "red", "12-09-2023", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_5", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name5", 5, "white", "02-05-2024", AdoptionStatus.ADOPTED, "13-12-2026"));

        data.put("animal_6",
                new Animal("Dog", "name6", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_7", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name7", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_8",
                new Animal("Dog", "name8", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_9", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name9", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_10",
                new Animal("Dog", "name10", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_11", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name11", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_12",
                new Animal("Dog", "name12", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_13", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name13", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_14",
                new Animal("Dog", "name14", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_15", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name15", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_16",
                new Animal("Dog", "name16", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_17", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name17", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_18",
                new Animal("Dog", "name18", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_19", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name19", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_20",
                new Animal("Dog", "name20", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_21", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name21", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_22",
                new Animal("Dog", "name22", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_23", // Animal - species, name, age, color, admissionDate, adoptionDate
                new Animal("Dog", "name23", 5, "white", "02-05-2024", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

        data.put("animal_24",
                new Animal("Dog", "name23", 4, "Cream", "04-01-2026", AdoptionStatus.NOT_ADOPTED, "00-00-0000"));

    }

    public List<Object> getObjects (String prefix) {

        return data.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith(prefix))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
