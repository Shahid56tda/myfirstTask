package com.example.recyclerveiwanddatabase;

public class ModelClass {
    String name,number,image;

    public ModelClass(String name, String number, String image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }

    public ModelClass() {
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getImage() {
        return image;
    }
}
