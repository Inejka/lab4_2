package support;

public class IntShell {
    private int number;

    public IntShell(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void dec(){
        if(number!=1)
        number--;
    }

    public void inc(){
        number++;
    }

    public int getNumber(){
        return number;
    }
}
