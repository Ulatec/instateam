package instateam.web;

    public enum Status {
        NOT_STARTED("Not started"),
        ACTIVE("Active"),
        ARCHIVED("Archived");
    private final String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
