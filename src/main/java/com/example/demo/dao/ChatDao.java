package com.example.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntChat;

@Repository
public class ChatDao {

	//DB書き込み用オブジェクトの用意
	private final JdbcTemplate db;

	public ChatDao(JdbcTemplate db) {
		this.db = db;
	}

	public void insertDb(EntChat entchat) {
		db.update("INSERT INTO chat (name, comment) VALUES(?, ?)", entchat.getName(), entchat.getComment());
	}

}
