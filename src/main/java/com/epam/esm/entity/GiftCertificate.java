package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

public class GiftCertificate {

    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<Tag> giftCertificatesTags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime lastUpdateDate;
    private int durationInDays;

    public GiftCertificate() {
    }

    public GiftCertificate(int id, String name, String description, BigDecimal price, List<Tag> giftCertificatesTags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.giftCertificatesTags = giftCertificatesTags;
    }

    public List<Tag> getGiftCertificatesTags() {
        return giftCertificatesTags;
    }

    public void setGiftCertificatesTags(List<Tag> giftCertificatesTags) {
        this.giftCertificatesTags = giftCertificatesTags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void merge(GiftCertificate giftCertificate) {
        setName(nonNull(giftCertificate.getName()) ? giftCertificate.getName() : this.name);
        setDescription(nonNull(giftCertificate.getDescription()) ? giftCertificate.getDescription() :
                this.description);
        setPrice(nonNull(giftCertificate.getPrice()) ? giftCertificate.getPrice() : this.price);
        setDurationInDays(giftCertificate.getDurationInDays() != 0 ? giftCertificate.getDurationInDays() : this.durationInDays);
        setGiftCertificatesTags(nonNull(giftCertificate.getGiftCertificatesTags()) ?
                giftCertificate.getGiftCertificatesTags() : this.giftCertificatesTags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftCertificate that = (GiftCertificate) o;

        if (id != that.id) return false;
        if (durationInDays != that.durationInDays) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (giftCertificatesTags != null ? !giftCertificatesTags.equals(that.giftCertificatesTags) : that.giftCertificatesTags != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return lastUpdateDate != null ? lastUpdateDate.equals(that.lastUpdateDate) : that.lastUpdateDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (giftCertificatesTags != null ? giftCertificatesTags.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        result = 31 * result + durationInDays;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftCertificate{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", giftCertificatesTags=").append(giftCertificatesTags);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", duration=").append(durationInDays);
        sb.append('}');
        return sb.toString();
    }
}