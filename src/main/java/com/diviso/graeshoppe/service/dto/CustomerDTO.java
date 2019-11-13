package com.diviso.graeshoppe.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Customer entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String customerUniqueId;

    private String reference;

    private String name;

    private String searchKey;

    private String card;

    private Double curDebt;

    private LocalDate debtDate;

    private Double maxDebt;

    private Double discount;

    private Boolean visible;

    @Lob
    private byte[] photo;

    private String photoContentType;

    private Long contactId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerUniqueId() {
        return customerUniqueId;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Double getCurDebt() {
        return curDebt;
    }

    public void setCurDebt(Double curDebt) {
        this.curDebt = curDebt;
    }

    public LocalDate getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(LocalDate debtDate) {
        this.debtDate = debtDate;
    }

    public Double getMaxDebt() {
        return maxDebt;
    }

    public void setMaxDebt(Double maxDebt) {
        this.maxDebt = maxDebt;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", customerUniqueId='" + getCustomerUniqueId() + "'" +
            ", reference='" + getReference() + "'" +
            ", name='" + getName() + "'" +
            ", searchKey='" + getSearchKey() + "'" +
            ", card='" + getCard() + "'" +
            ", curDebt=" + getCurDebt() +
            ", debtDate='" + getDebtDate() + "'" +
            ", maxDebt=" + getMaxDebt() +
            ", discount=" + getDiscount() +
            ", visible='" + isVisible() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", contact=" + getContactId() +
            "}";
    }
}
