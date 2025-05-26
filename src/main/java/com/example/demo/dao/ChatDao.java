package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public List<EntChat> searchDb() {
		String sql = "SELECT * FROM chat";
		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntChat> resultDb2 = new ArrayList<EntChat>();

		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			EntChat entchatdb = new EntChat();
			//name,commentのデータをentchatdbに移す
			entchatdb.setName((String) result1.get("name"));
			entchatdb.setComment((String) result1.get("comment"));
			//移し替えたデータを持ったentchatdbを、resultDB2に入れる
			resultDb2.add(entchatdb);

		}
		//Controllerに渡す
		return resultDb2;
	}

	/*public List<EntChat> selectOne(Long id) {
		//データベースから目的の1件を取り出して、そのままresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM sample where id=?", id);
		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntChat> resultDb2 = new ArrayList<EntChat>();
		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとするので、EntForm型の「entchatdb」を生成
			EntChat entchatdb = new EntChat();
			//name,commentのデータをentchatdbに移す
			entchatdb.setName((String) result1.get("name"));
			entchatdb.setComment((String) result1.get("comment"));
			//移し替えたデータを持ったentchatdbを、resultDB2に入れる
			resultDb2.add(entchatdb);
		}
		//Controllerに渡す
		return resultDb2;
	}*/

}
