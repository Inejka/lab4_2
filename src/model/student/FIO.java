package model.student;

public class FIO {
    private String surname;  // Фамилия
    private String name;     // Имя
    private String patronymic;   //Отчество

    public FIO(String surname,String name,String patronymic){
        this.surname=surname;
        this.name=name;
        this.patronymic=patronymic;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

}
