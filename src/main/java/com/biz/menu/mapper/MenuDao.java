package com.biz.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.biz.menu.model.DropMenuDto;

public interface MenuDao {
	
	/*
	 * menu_p_id가 null인 데이터(주메뉴에 해당하는 데이터)를 
	 * 모두 select해서 DropMenuDto에 담고, List로 묶어서 return해 달라
	 * 
	 * 그 과정중에 menu_id와 일치하는 menu_p_id가 존재하는 데이터들이 있으면
	 * (submenu들이 있으면)
	 * 그 데이터를 같이 select해서 DropMenuDto의 sub_menus list에 포함한 후 
	 * 같이 묶어달라
	 * 
	 * 1:다 관계의 SQL을 불러오는 것.
	 */
	@Select("SELECT * FROM tbl_menu WHERE menu_p_id is null")
	@Results(value= {
			@Result(property="menu_id",column="menu_id"),
			// select해서 메뉴 안에 집어넣어라
			@Result(property="sub_menus", column="menu_id", many=@Many(select="getSubMenus"))
			
			/*
			 * getSubMenus 메서드를 호출해서 결과가 있으면 (없으면 null이니까 상관없음)
			 * DropMenuDto의 sub_menus 변수에 저장해서 같이 묶어라
			 * many =@Many: getSubMenus가 리턴하는 데이터가 2개 이상일때
			 * 				사용하는 키워드
			 * one=@One : 리턴되는 데이터가 1개이하일때
			 * 
			 */
			
	})
	
	public List<DropMenuDto> getMenus();
		
	@Select("SELECT * FROM tbl_menu WHERE menu_p_id = #{menu_id}")
	public List<DropMenuDto> getSubMenus(String menu_id);
}
