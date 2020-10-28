package entities;

public class Worker {
    public int id;
    public String name;
    public String profession;
    public String mail;
    public int salary;
    public String city;
    public Branch branch;

    public Worker(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Worker(int id, String name, String profession, String mail, int salary) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.mail = mail;
        this.salary = salary;
    }

    public Worker(int id, String name, String profession, String mail, int salary, Branch branch) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.mail = mail;
        this.salary = salary;
    }
}
