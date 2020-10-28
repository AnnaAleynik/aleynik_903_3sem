package entities;

import java.util.LinkedList;

public class Branch {
    public int id;
    public String city;
    public LinkedList<Worker> workers;

    public Branch(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public Branch(int id, String city, LinkedList<Worker> workers) {
        this.id = id;
        this.city = city;
        this.workers = workers;
    }
}
