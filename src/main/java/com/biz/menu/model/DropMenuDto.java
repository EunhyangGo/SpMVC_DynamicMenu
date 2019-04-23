package com.biz.menu.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DropMenuDto {

	String menu_id;
	String menu_p_id; // parents ID
	String menu_title;
	String menu_href;
	
	List<DropMenuDto> sub_menus;
}
