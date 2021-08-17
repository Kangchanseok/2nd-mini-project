package address.jdbc;

import java.util.*;

public class PhoneBookApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean stop = true;
		
		while (stop) {
	         System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
	         System.out.println("---------------------------------");
	         System.out.print(">메뉴번호:");

	         int num = sc.nextInt();
	         switch(num) {
	         case 1:
	            list();
	            break;
	         case 2:
	        	insert();
	            break;
	         case 3:
	            delete();
	            break; 
	         case 4:
	        	 try {
	        	search();
	        	 } catch (Exception e) {
	        		 e.printStackTrace();
	        	 }
	            break;
	         case 5:
	            System.out.println("*********************************");
	            System.out.println("*   감사합니다      *");
	            System.out.println("*********************************");
	            stop = false;
	            break;
	         default:
	            System.out.println("\n[다시입력해주세요]\n");
	            break;
	         }
	      }
	 sc.close();
	}
	
	private static void list() {
		System.out. println("<1.리스트>\n");
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO>list = dao.getList();
		Iterator<PhoneBookVO> it = list.iterator();

		while (it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.printf("%s\t%s\t%s%n",
					vo.getPhoneName(),
					vo.getPhoneHp(),
					vo.getPhoneTel());
			}
		}
	
	private static void insert() {
		Scanner sc = new Scanner(System.in);

		System.out.println("<2.등록>");
        System.out.print(">이름: "); 
        String name = sc.next();

        System.out.print(">휴대전화: "); 
        String hp = sc.next();

        System.out.print(">집전화: "); 
        String tel = sc.next();


        PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
        PhoneBookDAO dao = new PhoneBookDAOImpl();
        boolean success = dao.insert(vo);

    	System.out.println("\n[등록되었습니다.]\n");
	}
		
	private static void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<3. 삭제>");
		System.out.print("번호: ");
		long id = sc.nextLong();

		PhoneBookDAO dao = new PhoneBookDAOImpl();
        dao.delete(id);

		System.out.println("\n[삭제되었습니다.]\n");
	}
	
	private static void search() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<4. 검색>");
		System.out.print(">이름: ");
		String keyword = sc.next();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		
		List<PhoneBookVO> list = dao.search(keyword);
		Iterator<PhoneBookVO> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.printf("%s\t%s\t%s\t%d%n",
					vo.getPhoneName(),
					vo.getPhoneHp(),
					vo.getPhoneTel());
		}
		
	}
	
	
	
	

	
	

}
