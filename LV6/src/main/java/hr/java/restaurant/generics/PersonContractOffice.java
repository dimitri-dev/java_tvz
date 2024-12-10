package hr.java.restaurant.generics;

import hr.java.restaurant.model.*;

public class PersonContractOffice<S extends Person, T extends Contract> {
    private S person;
    private T contract;

    public PersonContractOffice(S person, T contract) {
        setPerson(person);
        setContract(contract);
    }

    public S getPerson() {
        return person;
    }

    public void setPerson(S person) {
        this.person = person;
    }

    public T getContract() {
        return contract;
    }

    public void setContract(T contract) {
        this.contract = contract;
    }
}
