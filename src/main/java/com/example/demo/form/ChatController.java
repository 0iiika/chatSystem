package com.example.demo.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ChatDao;
import com.example.demo.entity.EntChat;

@Controller //このクラスがSpringのコントローラー(webリクエストを処理する)
public class ChatController {

	//DAOのオブジェクトを用意
	private final ChatDao chatdao;

	public ChatController(ChatDao chatdao) {
		this.chatdao = chatdao;
	}

	@RequestMapping("/")
	public String chat() {
		return "form/index";
	}

	@RequestMapping("/view")
	public String view(Model model, ChatForm chat) {
		System.out.println("view " + chat.getName1());
		model.addAttribute("title", "チャットルーム");
		//ここから一覧取得
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);
		//ここまで
		model.addAttribute("chat",chat);
		return "form/view";
	};

	@RequestMapping("/complete")
	public String complete(@Validated ChatForm chatform, BindingResult result, Model model) {

		//ここから登録
		model.addAttribute("title", "チャットルーム");
		EntChat entchat = new EntChat();
		entchat.setName(chatform.getName1());
		entchat.setComment(chatform.getComment1());
		chatdao.insertDb(entchat);
		//ここまで

		//もしエラーがあればエラー表示
		if (result.hasErrors()) {
			return "form/view";
		}

		//ここから一覧取得
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);
		//ここまで

		return "form/view";
	}

	//更新画面の表示(SELECT)
	//@RequestMapping("/edit/{id}")
	//public String editView(@PathVariable Long id, Model model) {
	//DBからデータを1件取ってくる(リストの形)
	//List<EntChat> list = chatdao.selectOne(id);
	//リストから、オブジェクトだけをピックアップ
	//EntChat entchatdb = list.get(0);
	//スタンバイしているViewに向かって、データを投げる
	//model.addAttribute("form", entchatdb);
	//model.addAttribute("title", "編集ページ");
	//return "form/view";
	//}

}
