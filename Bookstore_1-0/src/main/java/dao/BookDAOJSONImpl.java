package dao;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Book;

@ApplicationScoped @JSON
public class BookDAOJSONImpl implements BookDAO {
	
	@Resource
	private JsonParser parser;
	
	private ObjectMapper mapper = new ObjectMapper();
	private List<Book> books = new ArrayList<>();
	private File file;
	
	public BookDAOJSONImpl() throws JsonParseException, JsonMappingException, IOException {
		
		this.file = new File("C:\\Users\\camdo\\Desktop\\CS420 Web Architectures\\eclipse-workspace\\Bookstore_1-0\\src\\main\\resources\\books.json");
		List<Book> books = mapper.readValue(file, new TypeReference<List<Book>>() {});
		this.books = books;
		System.out.println(find(5).getAuthor());
		add(6, "Fuck", "desc", "author", "69.0");
	}
	
	@Override
	public Book find(int id) {
		//look through JSON until we find ID
		for(Book b : books) {
			if(b.getId() == 5) {
				return b;
			}
		}
		return null;
	}
	
	@Override
	public void add(int it, String title, String desc, String author, String price) {
		// TODO Auto-generated method stub
		double p = Double.parseDouble(price);
		String json = "";
		Book b = new Book(it, title, desc, author, p );
		books.add(b);
		
		try {
			json = mapper.writeValueAsString(b);
			System.out.println("string successfully created");
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.append(",\n");
			writer.append(json);
			System.out.println("Written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void modify(int id, String title, String desc, String author, String price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByIdAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByIdDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByTitleAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByTitleDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByPriceAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByPriceDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByAuthorAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listByAuthorDesc() {
		// TODO Auto-generated method stub
		return null;
	}

}
