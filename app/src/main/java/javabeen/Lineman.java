package javabeen;

/**
 * Created by liang on 2016/9/18.
 */

public class Lineman {
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Lineman(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
