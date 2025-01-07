package com.min.edu.anno05;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Spring Bean Configuration 에 xml 작성을 통해서 Bean을 등록하여 여러개를 사용했던 클래스
 * @Component 를 사용하면 해당 클래스는 하나의 Bean으로 만들어진다.
 * @Configuration을 통해서 @Bean을 통해 여러개의 메소드를 Bean으로 만들어 질 수 있도록 함 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private String name;
	private String addr;
	private String age;
}
