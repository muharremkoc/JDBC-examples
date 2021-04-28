public class Person {
    private int ID;
    private String name;
    private String surName;

    public Person(int ID, String name, String surName) {
        this.ID = ID;
        this.name = name;
        this.surName = surName;
    }
    public  Person(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
