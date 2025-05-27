package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

public class ChatForm {
	private int id1;
	@NotBlank(message = "※名前を入力してください")
	private String name1;
	@NotBlank(message = "※コメントを入力してください")
	private String comment1;

	public ChatForm() {

	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
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
