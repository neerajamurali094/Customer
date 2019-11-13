package com.diviso.graeshoppe.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "phonecode")
    private Integer phonecode;

    @OneToMany(mappedBy = "country")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Address> addresses = new HashSet<>();
    @OneToMany(mappedBy = "country")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<State> states = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Country code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhonecode() {
        return phonecode;
    }

    public Country phonecode(Integer phonecode) {
        this.phonecode = phonecode;
        return this;
    }

    public void setPhonecode(Integer phonecode) {
        this.phonecode = phonecode;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Country addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Country addAddress(Address address) {
        this.addresses.add(address);
        address.setCountry(this);
        return this;
    }

    public Country removeAddress(Address address) {
        this.addresses.remove(address);
        address.setCountry(null);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<State> getStates() {
        return states;
    }

    public Country states(Set<State> states) {
        this.states = states;
        return this;
    }

    public Country addState(State state) {
        this.states.add(state);
        state.setCountry(this);
        return this;
    }

    public Country removeState(State state) {
        this.states.remove(state);
        state.setCountry(null);
        return this;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        if (country.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", phonecode=" + getPhonecode() +
            "}";
    }
}
