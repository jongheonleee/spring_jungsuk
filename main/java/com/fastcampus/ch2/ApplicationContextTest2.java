package com.fastcampus.ch2;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
class Engine2 {}

@Component
class SuperEngine2 extends Engine2 {
	@Override
	public String toString() {
		return "superEngine";
	}
}

@Component
class SubEngine2 extends Engine2 {
	@Override
	public String toString() {
		return "subEngine";
	}
}


@Component
class Tank2 {
	@Autowired 
	@Qualifier("superEngine2")
	private Engine2 superEngine;
	
	@Autowired 
	@Qualifier("subEngine2")
	private Engine2 subEngine;
	
	@Value("Tank2") 
	private String name;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name).append("\n")
		  .append("superEngine").append(" : ").append(superEngine)
		  .append("subEngine").append(" : ").append(subEngine);
		
		return sb.toString();
	}
}

@Component
class Scv2 {
	@Autowired 
	@Qualifier("superEngine2")
	private Engine2 superEngine;
	
	@Autowired 
	@Qualifier("subEngine2")
	private Engine2 subEngine;
	
	@Value("Scv2") 
	String name;
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name).append("\n")
		  .append("superEngine").append(" : ").append(superEngine)
		  .append("subEngine").append(" : ").append(subEngine);
		
		return sb.toString();
	}
}

public class ApplicationContextTest2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
    
        Scv2 scv  = (Scv2) ac.getBean("scv2"); // 이름으로 빈 검색
        Tank2 tank = (Tank2) ac.getBean(Tank2.class);   // 타입으로 빈 검색
        
        System.out.println("scv = " + scv);
        System.out.println("tank = " + tank);

        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); // 정의된 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); // 정의된 빈의 개수를 반환

        System.out.println("ac.containsBeanDefinition(\"scv\") = " + ac.containsBeanDefinition("scv2"));  // true 빈의 정의가 포함되어 있는지 확인
        System.out.println("ac.containsBean(\"scv\") = " + ac.containsBean("scv2")); // true 빈이 포함되어 있는지 확인

        System.out.println("ac.getType(\"scv\") = " + ac.getType("scv2")); // 빈의 이름으로 타입을 알아낼 수 있음.
        System.out.println("ac.isSingleton(\"scv\") = " + ac.isSingleton("scv2")); // true 빈이 싱글톤인지 확인
        System.out.println("ac.isPrototype(\"tank\") = " + ac.isPrototype("tank2")); // false 빈이 프로토타입인지 확인
        System.out.println("ac.isPrototype(\"tanke\") = " + ac.isPrototype("tank2")); // true
        System.out.println("ac.isTypeMatch(\"scv\", Scv.class) = " + ac.isTypeMatch("scv2", Scv2.class)); // "car"라는 이름의 빈의 타입이 Car인지 확인
        System.out.println("ac.findAnnotationOnBean(\"scv\", Component.class) = " + ac.findAnnotationOnBean("scv2", Component.class)); // 빈 car에 @Component가 붙어있으면 반환
        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class))); // @Component가 붙은 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanNamesForType(Engine.class) = " + Arrays.toString(ac.getBeanNamesForType(Engine2.class))); // Engine 또는 그 자손 타입인 빈의 이름을 배열로 반환
    
    }
}
