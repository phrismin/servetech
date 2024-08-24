package by.company.servetech.model;

public enum Gender {
    MALE(1, "Male"),
    FEMALE(2, "Female"),
    UNDEFINED(3, "Undefined");

    private int id;
    private String name;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

