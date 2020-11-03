package com.epam.esm.dto;

import com.epam.esm.util.ResourceBundleErrorMessage;
import com.epam.esm.validation.ValidationGroup.Update;
import com.epam.esm.validation.ValidationGroup.Create;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class GiftCertificateDto {

    private int id;

    @NotBlank(message = ResourceBundleErrorMessage.CERTIFICATE_NAME_FORMAT_ERROR_MESSAGE, groups = Create.class)
    @Pattern(regexp = ".{10,255}", message = ResourceBundleErrorMessage.CERTIFICATE_NAME_FORMAT_ERROR_MESSAGE)
    private String name;

    @NotBlank(message = ResourceBundleErrorMessage.CERTIFICATE_DESCRIPTION_FORMAT_ERROR_MESSAGE, groups = Create.class)
    @Size(min = 10, message = ResourceBundleErrorMessage.CERTIFICATE_DESCRIPTION_FORMAT_ERROR_MESSAGE, groups =
            {Create.class, Update.class})
    private String description;

    @NotNull(message = ResourceBundleErrorMessage.CERTIFICATE_PRICE_IS_REQUIRED_ERROR_MESSAGE, groups = Create.class)
    @Digits(integer = 5, fraction = 2, message = ResourceBundleErrorMessage.CERTIFICATE_PRICE_FORMAT_ERROR_MESSAGE)
    private BigDecimal price;

    @NotNull(message = ResourceBundleErrorMessage.CERTIFICATE_TAG_IS_REQUIRED_ERROR_MESSAGE, groups = Create.class)
    @Size(min = 1, message = ResourceBundleErrorMessage.CERTIFICATE_TAG_IS_REQUIRED_ERROR_MESSAGE)
    private List<String> tags;

    @Null(message = ResourceBundleErrorMessage.CERTIFICATE_CREATE_DATE_MUST_BE_MISSING_ERROR_MESSAGE)
    private String createDate;

    @Null(message = ResourceBundleErrorMessage.CERTIFICATE_LAST_UPDATE__DATE_MUST_BE_MISSING_ERROR_MESSAGE)
    private String lastUpdateDate;

    @NotNull(message = ResourceBundleErrorMessage.CERTIFICATE_DURATION_IS_REQUIRED_ERROR_MESSAGE, groups = Create.class)
    @Min(value = 1, message = ResourceBundleErrorMessage.CERTIFICATE_DURATION_VALUE_ERROR_MESSAGE, groups = Create.class)

    private int durationInDays;

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftCertificateDto that = (GiftCertificateDto) o;

        if (id != that.id) return false;
        if (durationInDays != that.durationInDays) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return lastUpdateDate != null ? lastUpdateDate.equals(that.lastUpdateDate) : that.lastUpdateDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        result = 31 * result + durationInDays;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftCertificateDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", tags=").append(tags);
        sb.append(", createDate='").append(createDate).append('\'');
        sb.append(", lastUpdateDate='").append(lastUpdateDate).append('\'');
        sb.append(", durationInDays=").append(durationInDays);
        sb.append('}');
        return sb.toString();
    }
}
