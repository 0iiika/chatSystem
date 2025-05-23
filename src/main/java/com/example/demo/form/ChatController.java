package com.example.demo.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String view(Model model) {
		model.addAttribute("title", "チャットルーム");
		return "form/view";
	};

	@RequestMapping("/complete")
	public String complete(ChatForm chatform, Model model) {
		EntChat entchat = new EntChat();
		entchat.setName(chatform.getName1());
		entchat.setComment(chatform.getComment1());
		chatdao.insertDb(entchat);
		return "form/complete";
	}

}
