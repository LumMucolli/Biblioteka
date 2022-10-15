package com.probitacademy.biblioteka.dao;
import java.util.List;
import com.probitacademy.biblioteka.domain.Book;
public class BookDao implements Dao<Book>{
@Override
public Book get(long id) throws Exception {
throw new Exception("Nuk eshte implementuar ende!");
}
@Override
public List<Book> getAll() throws Exception {
throw new Exception("Nuk eshte implementuar ende!");	
}
@Override
public int save(Book record) throws Exception {
throw new Exception("Nuk eshte implementuar ende!");
}
@Override
public int update(Book record) throws Exception {
throw new Exception("Nuk eshte implementuar ende!");
}
@Override
public int delete(Book record) throws Exception {
throw new Exception("Nuk eshte implementuar ende!");
}
}