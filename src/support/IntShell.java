package support;

public class IntShell {
    private int number;
    private int maxNumber;

    public IntShell(int number, int maxNumber) {
        this.maxNumber = maxNumber;
        if (number < maxNumber)
            this.number = number;
        else this.number = maxNumber;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void dec() {
        if (number != 1)
            number--;
    }

    public void inc() {
        if (number < maxNumber)
            number++;
    }

    public int getNumber() {
        return number;
    }
}
