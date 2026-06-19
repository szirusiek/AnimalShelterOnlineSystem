package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable {

    private final String species;
    private final String name;
    private final int age;
    private final String color;
    private final String admissionDate;
    private final AdoptionStatus adoptionStatus;
    private final String adoptionDate;

    public Animal(
            String species,
            String name,
            int age,
            String color,
            String admissionDate,
            AdoptionStatus adoptionStatus,
            String adoptionDate
    ) {

        this.species = species;
        this.name = name;
        this.age = age;
        this.color = color;
        this.admissionDate = admissionDate;
        this.adoptionStatus = adoptionStatus;
        this.adoptionDate = adoptionDate;
    }

    public String getSpecies() {return species;}
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getColor() {return color;}
    public String getAdmissionDate() {return admissionDate;}
    public String getAdoptionDate() {return adoptionDate;}

    @Override
    public String toString() {
        return """
                Species = '%s'
                Name = '%s'
                Age = '%d'
                Color = '%s'
                Admission Date = '%s'
                Adoption Status = "%s"
                Adoption Date = '%s'
                
                """.formatted(
                        species,
                        name,
                        age,
                        color,
                        admissionDate,
                        adoptionStatus,
                        adoptionDate
                );
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Animal animal)) return false;

        return age == animal.age && Objects.equals(name, animal.name);
    }


}
