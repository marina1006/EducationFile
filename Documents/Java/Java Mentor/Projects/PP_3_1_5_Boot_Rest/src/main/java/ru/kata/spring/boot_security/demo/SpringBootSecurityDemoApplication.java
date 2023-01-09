package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

}
	/*Тестим всё быстро через VSCode без постоянных перезапусков проекта в Idea при изменениях в HTML/JS файлах
	 (после написания рест-контроллеров):
		0) Отключаем секьюрность через .antMatchers("ссылкаНаРест/**").permitAll() + ставим .csrf().disable(),
		чтобы проходили не только гет запросы
		1) Ставим над своими контроллерами аннотацию @CrossOrigin
2) Открываем в VSCode наши хтмл/джс
		3) Редачим их, подключаем к хтмл скрипты
		4) Открываем наш хтмл через браузер, просто копируя путь до него (я открывал через хром)
		5) Видим, что наша таблица заполняется, новые юзеры добавляются, а старых можно удалять/редачить
		(если используете Principal, то элементы,
		которые фетчат инфу с рест контроллера с принципалом, будут возвращать ошибки и это нормально,
		потому что вы не залогинены)
		Кроме того, если вы используете бутстрап 5 и при этом подключаете джсник от него (а вам, скорее всего,
		придётся подключить его),
		то нужно будет использовать preventDefault (я делал это внутри addEventListener) на некоторых элементах,
		 чтобы игнорировалось поведение,
		 например, кнопки по умолчанию (которое вполне может начать перезагружать страницу, чего нам не надо)	 */

