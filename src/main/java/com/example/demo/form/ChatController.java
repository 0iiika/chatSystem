package com.example.demo.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String view(Model model, ChatForm chatForm) {
		System.out.println("view " + chatForm.getName1());
		model.addAttribute("title", "チャットルーム");
		//ここから一覧取得
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);
		//ここまで
		//model.addAttribute("chat", chatForm);
		return "form/view";
	};

	@RequestMapping("/complete")
	public String complete(@Validated ChatForm chatForm, BindingResult result, Model model) {
		//model.addAttribute("chat", chatForm);
		//ここから登録
		model.addAttribute("title", "チャットルーム");
		EntChat entchat = new EntChat();
		entchat.setName(chatForm.getName1());
		entchat.setComment(chatForm.getComment1());

		//もしエラーがあればエラー表示
		if (result.hasErrors()) {
			model.addAttribute("title", "チャットルーム");
			return "form/view";
		}
		chatdao.insertDb(entchat);
		//ここまで

		//ここから一覧取得
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);
		//ここまで

		return "form/view";
	}

	//一覧表示
	@RequestMapping("/kanri")
	public String view(Model model) {
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);
		model.addAttribute("title", "管理者画面");
		return "form/kanri";
	}

	//削除(DELETE)
	@RequestMapping("/del/{id}")
	public String destory(@PathVariable Long id) {
		chatdao.deleteDb(id);
		return "redirect:/kanri";
	}

	//更新画面の表示(SELECT)
	@RequestMapping("/edit/{id}")
	public String editView(@PathVariable Long id, Model model, ChatForm chatForm) {
		//DBからデータを1件取ってくる(リストの形)
		List<EntChat> list = chatdao.selectOne(id);
		//リストから、オブジェクトだけをピックアップ
		EntChat entchatdb = list.get(0);
		//スタンバイしているViewに向かって、データを投げる
		model.addAttribute("form", entchatdb);
		model.addAttribute("title", "編集ページ");
		return "form/edit";
	}

	//更新処理(UPDATE)
	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, Model model, ChatForm chatForm) {
		//フォームの値をエンティティに入れ直し
		EntChat entchat = new EntChat();
		entchat.setName(chatForm.getName1());
		//更新の実行
		chatdao.updateDb(id, entchat);
		//一覧画面へリダイレクト
		return "redirect:/kanri";
	}
}
