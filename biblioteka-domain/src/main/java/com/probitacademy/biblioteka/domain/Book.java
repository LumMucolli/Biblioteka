package com.probitacademy.biblioteka.domain;
import java.util.Date;
public class Book {
private int id;
private int categoryId;
private String title;
private String photo;
private String isbn;
private Date publishDate;
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public int getCategoryId() {
return categoryId;
}
public void setCategoryId(int categoryId) {
this.categoryId = categoryId;
}
public String getTitle() {
return title;
}
public void setTitle(String title) {
this.title = title;
}
public String getPhoto() {
return photo;
}
public void setPhoto(String photo) {
this.photo = photo;
}
public String getIsbn() {
return isbn;
}
public void setIsbn(String isbn) {
this.isbn = isbn;
}
public Date getPublishDate() {
return publishDate;
}
public void setPublishDate(Date publishDate) {
this.publishDate = publishDate;
}
@Override
public int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + categoryId;
result = prime * result + id;
return result;
}
@Override
public boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Book other = (Book) obj;
if (categoryId != other.categoryId)
return false;
if (id != other.id)
return false;
return true;
}
@Override
public String toString() {
return "Book [id=" + id + ", categoryId=" + categoryId + ",title=" + title + ", photo=" + photo + ", isbn="+ isbn + ", publishDate=" + publishDate + "]";
}
}