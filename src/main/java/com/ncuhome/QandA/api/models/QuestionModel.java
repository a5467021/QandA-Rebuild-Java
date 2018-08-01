package com.ncuhome.QandA.api.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ncuhome.QandA.api.utils;
import com.ncuhome.QandA.api.models.BaseDatabaseModel;

public class QuestionModel {
	
	public String description;
	public String image;
	public String category;
	
	public void randQuestion(String category) throws SQLException {
		this.category = category;
		this.image = this.getImage(category);
		this.description = this.getQuestion(category);
	}
	
	protected String getQuestion(String category) throws SQLException {
		Connection conn = BaseDatabaseModel.getConn();
		
		String sql = "select description from question "
				+ "left join name_category on question.cid=name_category.category_id "
				+ "where name_category.name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, category);
		
		ResultSet rs = psmt.executeQuery();
		rs.last();
		int questionAmount = rs.getRow();
		questionAmount  = utils.randIntRanged(questionAmount) + 1;
		rs.absolute(questionAmount);
		String description = rs.getString(1);
		return description;
	}
	
	protected String getImage(String category) {
		List<String> files = utils.listDir("static/bgimage/" + category, ".jpg");
		if(files.size() > 0) {
			return "static/bgimage/" + files.get(utils.randIntRanged(files.size()));
		}
		else {
			return "static/bgimage/default.jpg";
		}
	}
	
	public static List<String> getAllCategories() throws SQLException {
		Connection conn = BaseDatabaseModel.getConn();
		Statement st = conn.createStatement();
		
		String sql = "select name from name_category";
		ResultSet rs = st.executeQuery(sql);
		
		List<String> categories = new ArrayList<String>();
		while(rs.next()) {
			categories.add(rs.getString(1));
		}
		return categories;
	}
}
