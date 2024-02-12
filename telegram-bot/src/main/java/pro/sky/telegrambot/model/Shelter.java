package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pet_shelters")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;
    @Column(name = "nameOfShelter")
    private String nameOfShelter;
    @Column(name = "aboutShelter")
    private  String informationAboutShelter;
    @Column(name = "address")
    private String address;
    @Column(name="phoneNumber")
    private String phoneNumber;
    private String photo;
    public Shelter() {
    }
    public Shelter(String nameOfShelter, String informationAboutShelter, String address, String phoneNumber, String photo) {
        this.nameOfShelter = nameOfShelter;
        this.informationAboutShelter = informationAboutShelter;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameOfShelter() {
        return nameOfShelter;
    }

    public void setNameOfShelter(String nameOfShelter) {
        this.nameOfShelter = nameOfShelter;
    }

    public String getInformationAboutShelter() {
        return informationAboutShelter;
    }

    public void setInformationAboutShelter(String informationAboutShelter) {
        this.informationAboutShelter = informationAboutShelter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(nameOfShelter, shelter.nameOfShelter)
                && Objects.equals(informationAboutShelter, shelter.informationAboutShelter)
                && Objects.equals(address, shelter.address) && Objects.equals(phoneNumber, shelter.phoneNumber)
                && Objects.equals(photo, shelter.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfShelter, informationAboutShelter, address, phoneNumber, photo);
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", nameOfShelter='" + nameOfShelter + '\'' +
                ", informationAboutShelter='" + informationAboutShelter + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
