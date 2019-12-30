/**
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 17/09/2019
 */

package com.setupteam.buscaminas.model.entity;

public class User {
	private String nickname;
	private boolean isReady;

	public User(String nickname) {
		this.nickname = nickname;
		this.isReady = false;
	}

	public String getNickname() {
		return nickname;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isReady() {
		return isReady;
	}

}
