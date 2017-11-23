package com.Apocalypse.author.model.dao;

import java.sql.SQLException;

import com.Apocalypse.author.bean.AuthorBean;

public interface IAuthorDAO {

	AuthorBean Select_By_Member_Id(String member_Id) throws SQLException;

	AuthorBean Select_By_Pen_Name(String pen_Name) throws SQLException;

	AuthorBean insertAuthor(AuthorBean bean) throws SQLException;

}