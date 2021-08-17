package address.jdbc;

public class PhoneBookVO {

	private Long phoneId;
	private String phoneName;
	private String phoneHp;
	private String phoneTel;
	
	public PhoneBookVO() {
		
	}
	public PhoneBookVO(String name, String hp, String tel) {
		phoneName = name;
		phoneHp = hp;
		phoneTel = tel;
		
	}
	public PhoneBookVO(Long id) {
		phoneId = id;
		
	}
	public Long getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}
	public String getPhoneName() {
		return phoneName;
	}
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	public String getPhoneHp() {
		return phoneHp;
	}
	public void setPhoneHp(String phoneHp) {
		this.phoneHp = phoneHp;
	}
	public String getPhoneTel() {
		return phoneTel;
	}
	public void setPhoneTel(String phoneTel) {
		this.phoneTel = phoneTel;
	}
	@Override
	public String toString() {
		return "PhoneBookVO [phoneId = " + phoneId + ", phoneName = " + phoneName + ","
				+ " phoneHp = " + phoneHp + ", phoneTe l= "	+ phoneTel + "] ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
