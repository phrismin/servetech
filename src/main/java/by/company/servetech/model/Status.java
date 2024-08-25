package by.company.servetech.model;

public enum Status {
    ACTIVE(1, "Active"),
    INACTIVE(2, "Inactive");

    private int id;
    private String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
