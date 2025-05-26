package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

public class ChatForm {
	@NotBlank(message = "名前を入力してください")
	private String name1;
	@NotBlank(message = "コメントを入力してください")
	private String comment1;

	public ChatForm() {

	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;

	}
}
