package com.oo2.grupo01.entities;

public enum UserRole {
	 USER(1),
	 ADMIN(2);
	 
	 private int level;

	    UserRole(int level) {
	        this.level = level;
	    }

	    public int getLevel() {
	        return level;
	    }
	 
	 
	 
}
