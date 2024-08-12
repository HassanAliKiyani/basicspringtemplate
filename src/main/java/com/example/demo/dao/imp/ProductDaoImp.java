package com.example.demo.dao.imp;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.dao.ProductDao;
import com.example.demo.mappers.ProductRowMapper;
import com.example.demo.model.Product;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDaoImp implements ProductDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);

	@Override
	public List<Product> findAll() {
		 String sql = "SELECT * FROM product";
	      return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Product findById(Long id) throws EmptyResultDataAccessException{
		String sql = "SELECT * FROM product WHERE id ="+id;
		return jdbcTemplate.queryForObject(sql, rowMapper);
	}
	

	@Override
	public Product save(Product product) {
		   if (product.getId() == null) {
	            // Insert new product
	            String sql = "INSERT INTO product (name, description, price) VALUES (?, ?, ?)";
	            KeyHolder keyHolder = new GeneratedKeyHolder();
	            jdbcTemplate.update((PreparedStatementCreator) connection -> {
	                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, product.getName());
	                ps.setString(2, product.getDescription());
	                ps.setDouble(3, product.getPrice());
	                return ps;
	            }, keyHolder);
	            product.setId(keyHolder.getKey().longValue());
	        } else {
	            // Update existing product
	            String sql = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
	            jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getId());
	        }
	        return product;
	}

	@Override
	public void deleteById(Long id) {
		
		findById(id);
		
		 String sql = "DELETE FROM product WHERE id = ?";
	     jdbcTemplate.update(sql, id);
	}

}
