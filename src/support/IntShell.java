package support;

public class IntShell {
    private int number;
    private final int maxNumber;

    public IntShell(int number, int maxNumber) {
        this.maxNumber = maxNumber;
        this.number = Math.min(number, maxNumber);
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
