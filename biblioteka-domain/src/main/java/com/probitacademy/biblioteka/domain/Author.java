package com.probitacademy.biblioteka.domain;
public class Author {
private int id;
private String name;
private String lastName;
private String country;
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
public String getLastName() {
return lastName;
}
public void setLastName(String lastName) {
this.lastName = lastName;
}
public String getCountry() {
return country;
}
public void setCountry(String country) {
this.country = country;
}
@Override
public int hashCode() {
final int prime = 31;
int result = 1;
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
Author other = (Author) obj;
if (id != other.id)
return false;
return true;
}
@Override
public String toString() {
return "Author [id=" + id + ", name=" + name + ", lastName=" +
lastName + ", country=" + country + "]";
}
}