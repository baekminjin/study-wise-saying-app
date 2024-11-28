package com.ll;

import java.util.ArrayList;

import java.util.Scanner;

public class App {
	ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	int lastld = 0;


	public void run(){
		System.out.println("===명언 앱===");


		while (true) {
			System.out.print("명령) ");
			String cmd = scanner.nextLine();

			if (cmd.equals("종료")) {
				break;
			}

			if (cmd.equals("등록")) {
				actionAdd();

			}

			if (cmd.equals("목록")) {
				actionList();
			}

			if (cmd.startsWith("삭제")) {
				String idstr = cmd.substring(6);

				int id = Integer.parseInt(idstr);

				actionDelete(id);
			}

			if (cmd.startsWith("수정")) {
				String idstr = cmd.substring(6);

				int id = Integer.parseInt(idstr);

				actionModify(id);
			}

		}
	}

	private void actionModify(int id) {
		WiseSaying foundWiseSaying = null;

		for(WiseSaying wiseSaying : wiseSayings){
			if(wiseSaying.getId() == id){
				//found가 wiseSaying을 참조하게 된다.
				foundWiseSaying = wiseSaying;
				break;
			}
		}

		if(foundWiseSaying == null){
			System.out.printf(id+"번 명언이 존재하지 않습니다.");
			return;
		}

		System.out.println("명언(기존):"+ foundWiseSaying.getContent());
		System.out.print("명언:");
		String content = scanner.nextLine();

		System.out.println("작가(기존):"+ foundWiseSaying.getAuthor());
		System.out.print("작가:");
		String author = scanner.nextLine();

		//set함수
		foundWiseSaying.setContent(content);
		foundWiseSaying.setAuthor(author);

	}

	private void actionDelete(int id) {
		//리스트에서 삭제하기 때문에 wiseSayings으로 함수를 부른다.
		boolean removed = wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);

		if(removed)
			System.out.println(id+"번 명언이 삭제되었습니다.");
		else System.out.println(id+"번이 존재하지 않습니다.");
	}


	private void actionList() {
		System.out.println("번호  /  명언  /  작가");
		System.out.println("=======================");

		for(WiseSaying wiseSaying : wiseSayings.reversed()){
			System.out.println(wiseSaying.getId()+"/"+ wiseSaying.getContent()+"/"+wiseSaying.getAuthor());
		}
	}

	private void actionAdd() {
		System.out.print("명언: ");
		String content = scanner.nextLine();

		System.out.print("작가: ");
		String author = scanner.nextLine();


		WiseSaying wiseSayings = addWiseSaaying(content, author);
		System.out.println(wiseSayings.getId()+"번 명언이 등록되었습니다.");
	}

	private WiseSaying addWiseSaaying(String content, String author) {
		int id = ++lastld;

		WiseSaying wiseSaying = new WiseSaying(id, content, author);

		wiseSayings.add(wiseSaying);

		return wiseSaying;

	}


}
