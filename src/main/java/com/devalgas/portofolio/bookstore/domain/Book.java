package com.devalgas.portofolio.bookstore.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Book entity.\n@author Devalgas kamga on 13/01/2022.
 */
@ApiModel(description = "Book entity.\n@author Devalgas kamga on 13/01/2022.")
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "language")
    private String language;

    @Column(name = "category")
    private String category;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "format")
    private String format;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "shipping_weight")
    private Double shippingWeight;

    @Column(name = "list_price")
    private Double listPrice;

    @Column(name = "our_price")
    private Double ourPrice;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "in_stock_number")
    private Integer inStockNumber;

    @Transient
    private MultipartFile bookImage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Book author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Book publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public Book publicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return this.language;
    }

    public Book language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return this.category;
    }

    public Book category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumberOfPages() {
        return this.numberOfPages;
    }

    public Book numberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFormat() {
        return this.format;
    }

    public Book format(String format) {
        this.format = format;
        return this;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getShippingWeight() {
        return this.shippingWeight;
    }

    public Book shippingWeight(Double shippingWeight) {
        this.shippingWeight = shippingWeight;
        return this;
    }

    public void setShippingWeight(Double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public Double getListPrice() {
        return this.listPrice;
    }

    public Book listPrice(Double listPrice) {
        this.listPrice = listPrice;
        return this;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getOurPrice() {
        return this.ourPrice;
    }

    public Book ourPrice(Double ourPrice) {
        this.ourPrice = ourPrice;
        return this;
    }

    public void setOurPrice(Double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Book active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return this.description;
    }

    public Book description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInStockNumber() {
        return this.inStockNumber;
    }

    public Book inStockNumber(Integer inStockNumber) {
        this.inStockNumber = inStockNumber;
        return this;
    }

    public void setInStockNumber(Integer inStockNumber) {
        this.inStockNumber = inStockNumber;
    }

    public MultipartFile getBookImage() {
        return bookImage;
    }

    public void setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", publisher='" + getPublisher() + "'" +
                ", publicationDate='" + getPublicationDate() + "'" +
                ", language='" + getLanguage() + "'" +
                ", category='" + getCategory() + "'" +
                ", numberOfPages=" + getNumberOfPages() +
                ", format='" + getFormat() + "'" +
                ", isbn='" + getIsbn() + "'" +
                ", shippingWeight=" + getShippingWeight() +
                ", listPrice=" + getListPrice() +
                ", ourPrice=" + getOurPrice() +
                ", active='" + getActive() + "'" +
                ", description='" + getDescription() + "'" +
                ", bookImage='" + getCategory() + "'" +
                ", inStockNumber=" + getInStockNumber() +
                "}";
    }
}
