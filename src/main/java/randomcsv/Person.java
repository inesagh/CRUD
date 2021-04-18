package randomcsv;

public class Person {
    private String firstName;
    private String lastName;
    private String date;
    private Gender gender;
    private Race race;
    private String phoneNumber;
    private String email;
    private String id;

    public Person(String id, String firstName, String lastName, String date, Gender gender, Race race, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
        this.race = race;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id = " + id + '\n' +
                "firstName = " + firstName + '\n' +
                "lastName = " + lastName + '\n' +
                "date = " + date + '\n' +
                "gender = " + gender + '\n' +
                "race = " + race + '\n' +
                "phoneNumber = " + phoneNumber + '\n' +
                "email = " + email + '\n';
    }
}
